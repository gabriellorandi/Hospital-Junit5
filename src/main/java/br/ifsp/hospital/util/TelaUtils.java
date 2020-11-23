package br.ifsp.hospital.util;

public class TelaUtils {

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean isNumerico(String texto) {
        return texto.matches("[0-9]+");
    }

    public static boolean isEmail(String email) { return email.matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+"); }

}
