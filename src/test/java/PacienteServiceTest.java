import br.ifsp.hospital.model.Paciente;
import br.ifsp.hospital.model.Sexo;
import br.ifsp.hospital.service.PacienteService;
import org.junit.jupiter.api.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    public static final int TAMANHO_LISTA = 15;

    static PacienteService pacienteService;

    public static Paciente construirPaciente(Long cpf) {

        Paciente paciente = new Paciente();
        paciente.setCpf(cpf);
        paciente.setDataNascimento(new Date());
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

    @Test
    @Order(1)
    @DisplayName("Teste de sucesso para a listagem de todos os medicos")
    void testeListarTodosSucesso() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        assertEquals(TAMANHO_LISTA,pacientes.size());

    }

    @Test
    @Order(2)
    @DisplayName("Teste de falha para a listagem de todos os medicos")
    void testeListarTodosFalha() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        assertNotEquals(TAMANHO_LISTA-1,pacientes.size());

    }

    @Test
    @Order(3)
    @DisplayName("Teste de sucesso para a listar um medico")
    void testeListarUmSucesso() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        Paciente medico = pacientes.get(0);

        Paciente pacienteTeste = pacienteService.listarUm( medico.getCpf() );
        assertEquals(medico.getCpf(),pacienteTeste.getCpf());

    }

    @Test
    @Order(4)
    @DisplayName("Teste de falha para a listar um medico")
    void testeListarUmFalha() {

        Paciente pacienteTeste = pacienteService.listarUm( 999999 );
        assertNull(pacienteTeste);

    }

    @Test
    @Order(5)
    @DisplayName("Teste de sucesso para incluir um medico")
    void testeIncluirMedicoSucesso() {

        Paciente pacienteTeste = pacienteService.incluir( construirPaciente(36974125856L) );
        assertNotNull(pacienteTeste);

    }

    @Test
    @Order(6)
    @DisplayName("Teste de falha para incluir um medico")
    void testeIncluirMedicoFalha() {

        Paciente pacienteTeste = pacienteService.incluir( pacienteService.getPacientes().get(0) );
        assertNull(pacienteTeste);

    }


    @Test
    @Order(7)
    @DisplayName("Teste de sucesso para excluir um medico")
    void testeExcluirMedicoSucesso() {

        Paciente pacienteTeste = pacienteService.excluir( pacienteService.getPacientes().get(0) );
        assertNotNull(pacienteTeste);

    }

    @Test
    @Order(8)
    @DisplayName("Teste de falha para excluir um medico")
    void testeExcluirMedicoFalha() {

        Paciente pacienteTeste = pacienteService.excluir( construirPaciente(31265498745L ));
        assertNull(pacienteTeste);

    }

    @Test
    @Order(9)
    @DisplayName("Teste de sucesso para alterar um medico")
    void testeAlterarMedicoSucesso() {

        String nomeTeste = "Teste de alterar";

        Paciente paciente = construirPaciente( pacienteService.getPacientes().get(0).getCpf() );
        paciente.setNome( nomeTeste );

        Paciente pacienteTeste = pacienteService.alterar( paciente );
        assertEquals(nomeTeste,pacienteTeste.getNome());

    }

    @Test
    @Order(10)
    @DisplayName("Teste de falha para alterar um medico")
    void testeAlterarMedicoFalha() {

        String nomeTeste = "Teste de alterar";

        Paciente paciente = construirPaciente(45678912388L);
        paciente.setNome(nomeTeste);

        Paciente pacienteTeste = pacienteService.alterar(paciente);
        assertNull(pacienteTeste);
    }

}
