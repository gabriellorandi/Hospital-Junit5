package br.ifsp.hospital.service;

import br.ifsp.hospital.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {

    private List<Paciente> pacientes;

    public PacienteService() {
        pacientes = new ArrayList<Paciente>();
    }
}
