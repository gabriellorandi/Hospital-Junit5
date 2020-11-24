package br.ifsp.hospital.util;

import br.ifsp.hospital.model.Medico;
import br.ifsp.hospital.model.Paciente;

import static br.ifsp.hospital.util.TelaUtils.isEmail;

public class ValidationUtils {

    public static boolean validarMedico(Medico medico) {

        if(!isEmail(medico.getEmail())) {
            return false;
        }

        if(!(medico.getTelefone().length() > 9) && !(medico.getTelefone().length() < 8)) {
            return false;
        }

        return true;
    }

    public static boolean validarPaciente(Paciente paciente) {

        if(!isEmail(paciente.getEmail())) {
            return false;
        }

        if(!(paciente.getTelefone().length() > 9) && !(paciente.getTelefone().length() < 8)) {
            return false;
        }

        return true;
    }

}
