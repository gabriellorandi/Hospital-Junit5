package br.ifsp.hospital.service;

import br.ifsp.hospital.model.Paciente;
import br.ifsp.hospital.model.Sexo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.ifsp.hospital.util.TelaUtils.*;
import static br.ifsp.hospital.util.ValidationUtils.validarPaciente;

public class PacienteService {

    private List<Paciente> pacientes;

    private Scanner scan;

    public PacienteService() {
        setPacientes(new ArrayList<Paciente>());
        scan = new Scanner(System.in);;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void menu() {

        boolean saida = false;
        String cpf;

        while (!saida) {

            imprimirSubmenu();
            Integer opcao = scan.nextInt();;

            switch (opcao) {
                case 1 :
                    listarTodos();
                    scan.next();
                    break;
                case 2:
                    cpf = imprimirDigitarCpf();
                    listarUm( Integer.parseInt(cpf));
                    scan.next();
                    break;
                case 3 :
                    Paciente pacienteNovo = entradaPaciente();
                    getPacientes().add(pacienteNovo);
                    break;
                case 4 :
                    Paciente pacienteAlterar = entradaPaciente();
                    alterar(pacienteAlterar);
                    break;
                case 5 :
                    cpf = imprimirDigitarCpf();
                    excluirMedico( Long.valueOf(cpf));
                    break;
                case 6 :
                    saida = true;
                    break;
                default:
                    System.out.println("Opção Invalida - Precione qualquer tecla para continuar.");
                    scan.next();
            }
        }
    }

    public List<Paciente> listarTodos() {

        limparTela();
        System.out.println("Lista medicos: ");

        for (Paciente paciente : getPacientes()) {
            System.out.println(paciente.toString());
        }

        return getPacientes();
    }

    public Paciente listarUm(long cpf) {

        for (Paciente paciente : getPacientes()) {
            if (paciente.getCpf() == cpf) {
                System.out.println(paciente.toString());
                return paciente;
            }
        }
        return null;
    }


    public String imprimirDigitarCpf() {

        String cpf = null;
        while(cpf == null) {

            System.out.println("Digite a CPF");
            cpf = scan.next();
            if(isNumerico(cpf) && cpf.length() == 11){
                return cpf;
            }

            System.out.println("Digite somente 11 numeros.");
            scan.next();
        }
        return null;
    }

    public Paciente entradaPaciente() {

        Paciente paciente = new Paciente();

        String entrada;

        limparTela();
        System.out.println("Digite o CPF");
        entrada = scan.next();
        if(isNumerico(entrada)){
            paciente.setCpf( Long.valueOf(entrada) );
        } else {
            System.out.println("CPF Invalida");
            return null;
        }

        System.out.println("Digite o nome");
        paciente.setNome( scan.next() );

        System.out.println("Digite o email");
        String email = scan.next();

        if(isEmail(email)) {
            paciente.setEmail( email );
        } else {
            System.out.println("Email inválido");
            return null;
        }

        System.out.println("Digite o telefone sem DDD (Ex: 33611691) ");
        String telefone = scan.next();

        if(telefone.length() > 9 || telefone.length() < 8) {
            System.out.println("Telefone inválido");
            return null;
        }

        System.out.println("Digite a data de nascimento");
        String dataString = scan.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            paciente.setDataNascimento( dateFormat.parse(dataString) );
        } catch (ParseException e) {
            System.out.println("Data de nascimento inválido!");
            return null;
        }

        System.out.println("Digite o sexo");
        String sexo = scan.next();

        if(sexo.equals("M") || sexo.equals("m") ) {
            paciente.setSexo(Sexo.MASCULINO);
        } else if (sexo.equals("F") || sexo.equals("f")) {
            paciente.setSexo(Sexo.FEMININO);
        } else {
            System.out.println("Sexo inválido!");
            return null;
        }


        System.out.println("Digite o Plano de Saúde");
        paciente.setEmail( scan.next() );

        return paciente;

    }

    public void excluirMedico(Long cpf) {

        pacientes.removeIf( paciente -> paciente.getCpf().equals( cpf ) );

    }

    public void imprimirSubmenu() {

        limparTela();
        System.out.println("Submenu Pacientes");
        System.out.println("1- Listar todos");
        System.out.println("2- Listar um");
        System.out.println("3- Incluir");
        System.out.println("4- Alterar");
        System.out.println("5- Excluir");
        System.out.println("6- Voltar");

    }

    public Paciente incluir(Paciente paciente) {

        if(validarPaciente(paciente)) {
            return null;
        }


        if(listarUm( paciente.getCpf() ) == null) {
            getPacientes().add(paciente);
            return paciente;
        }

        return null;

    }

    public Paciente excluir(Paciente pacienteRemover) {

        for (Paciente paciente : getPacientes()) {
            if (paciente.equals(pacienteRemover)) {
                getPacientes().remove(pacienteRemover);
                return pacienteRemover;
            }
        }
        return null;

    }

    public Paciente alterar(Paciente paciente) {

        Paciente pacienteAlterar = listarUm( paciente.getCpf() );

        if( pacienteAlterar != null) {
            pacienteAlterar.atualizar(paciente);

        }

        return pacienteAlterar;


    }
}
