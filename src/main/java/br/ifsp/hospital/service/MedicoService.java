package br.ifsp.hospital.service;

import br.ifsp.hospital.model.Medico;
import br.ifsp.hospital.model.Paciente;
import br.ifsp.hospital.model.Sexo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.ifsp.hospital.util.TelaUtils.*;

public class MedicoService {

    private List<Medico> medicos;

    private Scanner scan;

    public MedicoService() {
        setMedicos(new ArrayList<Medico>());
        scan = new Scanner(System.in);;
    }

    public List<Medico> listarTodos() {

        limparTela();
        System.out.println("Lista medicos: ");

        for (Medico medico : getMedicos()) {
            System.out.println(medico.toString());
        }

        return getMedicos();
    }

    public Medico listarUm(int crm) {

        for (Medico medico : getMedicos()) {
            if (medico.getCrm() == crm) {
                System.out.println(medico.toString());
                return medico;
            }
        }
        return null;
    }

    public Medico incluir(Medico medico) {

        if(listarUm( medico.getCrm() ) == null) {
            getMedicos().add(medico);
            return medico;
        }

        return null;
    }

    public Medico alterar(Medico medico) {

        Medico medicoAlterar = listarUm( medico.getCrm() );

        if( medicoAlterar != null) {
            medicoAlterar.atualizar(medico);

        }

        return medicoAlterar;
    }

    public Medico excluir(Medico medicoRemover) {

        for (Medico medico : getMedicos()) {
            if (medico.equals(medicoRemover)) {
                getMedicos().remove(medicoRemover);
                return medicoRemover;
            }
        }
        return null;
    }



    public void menu() {

        boolean saida = false;
        String crm;

        while (!saida) {

            imprimirSubmenu();
            Integer opcao = scan.nextInt();;

            switch (opcao) {
                case 1 :
                    listarTodos();
                    scan.next();
                    break;
                case 2:
                    crm = imprimirDigitarCrm();
                    listarUm( Integer.parseInt(crm));
                    scan.next();
                    break;
                case 3 :
                    Medico medicoNovo = entradaMedico();
                    getMedicos().add(medicoNovo);
                    break;

                case 4 :
                    Medico medicoAlterar = entradaMedico();
                    alterar(medicoAlterar);
                    break;
                case 5 :
                    crm = imprimirDigitarCrm();
                    excluirMedico( Integer.parseInt(crm));
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

    public Medico entradaMedico() {

        Medico medico = new Medico();

        String entrada;

        limparTela();
        System.out.println("Digite a CRM");
        entrada = scan.next();
        if(isNumerico(entrada)){
            medico.setCrm( Integer.parseInt(entrada) );
        } else {
            System.out.println("CRM Inválida!");
            return null;
        }

        System.out.println("Digite o nome");
        medico.setNome( scan.next() );

        System.out.println("Digite o email");
        String email = scan.next();

        if(isEmail(email)) {
            medico.setEmail( email );
        } else {
            System.out.println("Email inválido");
            return null;
        }

        System.out.println("Digite a dia do nascimento (Ex: 11-02-2000) ");
        String dataSring = scan.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            medico.setDataNascimento( dateFormat.parse(dataSring) );
        } catch (ParseException e) {
            System.out.println("Data de nascimento inválido!");
            return null;
        }

        System.out.println("Digite o sexo (M ou F): ");
        String sexo = scan.next();

        if(sexo.equals("M") || sexo.equals("m") ) {
            medico.setSexo(Sexo.MASCULINO);
        } else if (sexo.equals("F") || sexo.equals("f")) {
            medico.setSexo(Sexo.FEMININO);
        } else {
            System.out.println("Sexo inválido!");
            return null;
        }

        System.out.println("Especialidade ");
        medico.setEspecialidade( scan.next() );


        System.out.println("Universidade de Formação ");
        medico.setUniversidadeFormacao( scan.next() );

        System.out.println("Digite o telefone sem DDD (Ex: 33611691) ");
        String telefone = scan.next();

        if(telefone.length() > 9 || telefone.length() < 8) {
            System.out.println("Telefone inválido");
            return null;
        }

        return medico;

    }

    public String imprimirDigitarCrm() {

        String crm = null;
        while(crm == null) {

            System.out.println("Digite a CRM");
            crm = scan.next();
            if(isNumerico(crm) && crm.length() == 6){
                return crm;
            }

            System.out.println("Digite somente 6 numeros.");
            scan.next();
        }
        return null;
    }

    public void imprimirSubmenu() {

        limparTela();
        System.out.println("Submenu Medico");
        System.out.println("1- Listar todos");
        System.out.println("2- Listar um");
        System.out.println("3- Incluir");
        System.out.println("4- Alterar");
        System.out.println("5- Excluir");
        System.out.println("6- Voltar");

    }

    public void excluirMedico(Integer crm) {

        medicos.removeIf( medico -> medico.getCrm().equals( crm ) );

    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}
