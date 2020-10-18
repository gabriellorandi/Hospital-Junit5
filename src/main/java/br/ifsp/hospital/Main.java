package br.ifsp.hospital;

import br.ifsp.hospital.service.MedicoService;
import br.ifsp.hospital.service.PacienteService;

import java.util.Scanner;

import static br.ifsp.hospital.util.TelaUtils.limparTela;

public class Main {


    public static void main(String[] args) {

        MedicoService medicoService = new MedicoService();
        PacienteService pacienteService = new PacienteService();

        boolean saida = false;

        Scanner scan= new Scanner(System.in);

        while(!saida) {

            limparTela();
            System.out.println("======================================");
            System.out.println("== TC1 - Sistema Hospital - JUnit 5 ==");
            System.out.println("======================================");
            System.out.println();

            System.out.println("Menu Principal");
            System.out.println("1- Medicos");
            System.out.println("2- Pacientes");
            System.out.println("3- Sair");
            System.out.println("Opcao: ");

            Integer opcao = scan.nextInt();

            switch (opcao) {
                case 1 :
                    medicoService.menu();
                    break;
                case 2:
                    break;
                case 3:
                    saida = true;
                    break;
                default:
                    System.out.println("Opção Invalida - Precione qualquer tecla para continuar.");
                    scan.next();
            }
        }


    }
}
