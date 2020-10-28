import br.ifsp.hospital.model.Medico;
import br.ifsp.hospital.model.Paciente;
import br.ifsp.hospital.model.Sexo;
import br.ifsp.hospital.service.MedicoService;
import br.ifsp.hospital.service.PacienteService;
import org.junit.jupiter.api.BeforeAll;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PacienteServiceTest {

    public static final int TAMANHO_LISTA = 15;

    static PacienteService pacienteService;

    public static Paciente construirPaciente(Long cpf) {

        Paciente paciente = new Paciente();
        paciente.setCpf(cpf);
        paciente.setDataNascimento(ZonedDateTime.now());
        paciente.setPlanoSaude("Unimed teste");
        paciente.setSexo((cpf%2==0)? Sexo.MASCULINO:Sexo.FEMININO);
        paciente.setNome("Nome teste");
        paciente.setEmail("teste@teste.com.br");
        paciente.setTelefone("(16) 98771-2345");

        return paciente;

    }

    @BeforeAll
    public static void setup() {

        pacienteService = new PacienteService();

        List<Paciente> pacientes = new ArrayList<>();
        for (int index = 0; index < TAMANHO_LISTA; index++) {
            pacientes.add( construirPaciente(30045678923L + index) );
        }

        pacienteService.setPacientes(pacientes);

    }

}
