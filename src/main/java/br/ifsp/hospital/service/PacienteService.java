package br.ifsp.hospital.service;

import br.ifsp.hospital.model.Medico;
import br.ifsp.hospital.model.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.ifsp.hospital.util.TelaUtils.isNumerico;
import static br.ifsp.hospital.util.TelaUtils.limparTela;

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
        paciente.setEmail( scan.next() );

        System.out.println("Digite a data de nascimento");
//        medico.setDataNascimento( scan.next() );

        System.out.println("Digite o sexo");


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

}
