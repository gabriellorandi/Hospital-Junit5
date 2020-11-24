import br.ifsp.hospital.model.Paciente;
import br.ifsp.hospital.model.Sexo;
import br.ifsp.hospital.service.PacienteService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    public static final int TAMANHO_LISTA = 15;

    static PacienteService pacienteService;
    static List<Paciente> pacienteExpected;

    public static Paciente construirPaciente(Long cpf) {

        Paciente paciente = new Paciente();
        paciente.setCpf(cpf);
        paciente.setDataNascimento(new Date());
        paciente.setPlanoSaude("Unimed teste");
        paciente.setSexo((cpf%2==0)? Sexo.MASCULINO:Sexo.FEMININO);
        paciente.setNome("Nome teste");
        paciente.setEmail("teste@teste.com.br");
        paciente.setTelefone("987712345");

        return paciente;

    }

    @BeforeAll
    public static void setup() {

        pacienteService = new PacienteService();

        pacienteExpected = new ArrayList<>();
        for (int index = 0; index < TAMANHO_LISTA; index++) {
            pacienteExpected.add( construirPaciente(30045678923L + index) );
        }

        pacienteService.setPacientes(pacienteExpected);

    }

    @Test
    @Order(1)
    @DisplayName("Teste de sucesso para a listagem de todos os pacientes")
    void testeListarTodosSucesso() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        assertEquals(TAMANHO_LISTA,pacientes.size());

        assertAll("Lista de pacietnes",
                () -> assertEquals(TAMANHO_LISTA,pacientes.size()),
                () -> assertArrayEquals(pacienteExpected.toArray(),pacientes.toArray())
        );

    }

    @Test
    @Order(2)
    @DisplayName("Teste de falha para a listagem de todos os pacientes")
    void testeListarTodosFalha() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        assertNotEquals(TAMANHO_LISTA-1,pacientes.size());

    }

    @Test
    @Order(3)
    @DisplayName("Teste de sucesso para a listar um paciente")
    void testeListarUmSucesso() {

        List<Paciente> pacientes = pacienteService.listarTodos();
        Paciente paciente = pacientes.get(0);

        Paciente pacienteTeste = pacienteService.listarUm( paciente.getCpf() );


        assertAll("Sucesso para listar um paciente",
                () -> assertNotNull(pacienteTeste),
                () -> assertEquals(paciente.getCpf(),pacienteTeste.getCpf())
        );

    }

    @Test
    @Order(4)
    @DisplayName("Teste de falha para a listar um paciente")
    void testeListarUmFalha() {

        Paciente pacienteTeste = pacienteService.listarUm( 999999 );
        assertNull(pacienteTeste);

    }

    @Test
    @Order(5)
    @DisplayName("Teste de sucesso para incluir um paciente")
    void testeIncluirMedicoSucesso() {

        Paciente pacienteTeste = pacienteService.incluir( construirPaciente(36974125856L) );
        assertNotNull(pacienteTeste);

    }

    @Test
    @Order(6)
    @DisplayName("Teste de falha para incluir um paciente")
    void testeIncluirMedicoFalha() {

        Paciente pacienteTeste = pacienteService.incluir( pacienteService.getPacientes().get(0) );
        assertNull(pacienteTeste);

    }


    @Test
    @Order(7)
    @DisplayName("Teste de sucesso para excluir um paciente")
    void testeExcluirMedicoSucesso() {

        Paciente pacienteTeste = pacienteService.excluir( pacienteService.getPacientes().get(0) );
        assertNotNull(pacienteTeste);

    }

    @Test
    @Order(8)
    @DisplayName("Teste de falha para excluir um paciente")
    void testeExcluirMedicoFalha() {

        Paciente pacienteTeste = pacienteService.excluir( construirPaciente(31265498745L ));
        assertNull(pacienteTeste);

    }

    @Test
    @Order(9)
    @DisplayName("Teste de sucesso para alterar um paciente")
    void testeAlterarMedicoSucesso() {

        String nomeTeste = "Teste de alterar";

        Paciente paciente = construirPaciente( pacienteService.getPacientes().get(0).getCpf() );
        paciente.setNome( nomeTeste );

        Paciente pacienteTeste = pacienteService.alterar( paciente );
        assertEquals(nomeTeste,pacienteTeste.getNome());

    }

    @Test
    @Order(10)
    @DisplayName("Teste de falha para alterar um paciente")
    void testeAlterarMedicoFalha() {

        String nomeTeste = "Teste de alterar";

        Paciente paciente = construirPaciente(45678912388L);
        paciente.setNome(nomeTeste);

        Paciente pacienteTeste = pacienteService.alterar(paciente);
        assertNull(pacienteTeste);
    }


    @Test
    @Order(11)
    @DisplayName("Teste de sucesso para campos email e telefone de um medico")
    void testeCamposMedicoSucesso() {

        String telefone = "981185698";
        String email = "medico@teste.com";

        Paciente paciente = construirPaciente(99123921313L);
        paciente.setTelefone(telefone);
        paciente.setEmail(email);

        Paciente pacienteTeste = pacienteService.incluir(paciente);
        assertNotNull(pacienteTeste);

    }

    @Test
    @Order(12)
    @DisplayName("Teste de falha para campos email e telefone de um medico")
    void testeCamposMedicoFalha() {

        String telefone = "9815698";

        Paciente paciente = construirPaciente(99123921313L);
        paciente.setTelefone(telefone);;

        Paciente pacienteTeste = pacienteService.incluir(paciente);
        assertNull(pacienteTeste);

        paciente.setTelefone("98239568");
        paciente.setEmail("paciente@");

         pacienteTeste = pacienteService.incluir(paciente);
        assertNull(pacienteTeste);

    }

}
