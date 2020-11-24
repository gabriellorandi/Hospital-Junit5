import br.ifsp.hospital.model.Medico;
import br.ifsp.hospital.model.Sexo;
import br.ifsp.hospital.service.MedicoService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicoServiceTest {

    public static final int TAMANHO_LISTA = 10;

    static MedicoService medicoService;
    static List<Medico> medicoExpected;

    public static Medico construirMedico(Integer crm) {

        Medico medico = new Medico();
        medico.setCrm(crm);
        medico.setDataNascimento(new Date());
        medico.setEspecialidade("Especialidade Teste");
        medico.setSexo((crm%2==0)?Sexo.MASCULINO:Sexo.FEMININO);
        medico.setNome("Nome teste");
        medico.setEmail("teste@teste.com.br");
        medico.setUniversidadeFormacao("IFSP");
        medico.setTelefone("987712345");

        return medico;

    }

    @BeforeAll
    public static void setup() {

        medicoService = new MedicoService();

        medicoExpected = new ArrayList<>();
        for (int index = 0; index < TAMANHO_LISTA; index++) {
            medicoExpected.add( construirMedico(100000 + index) );
        }

        medicoService.setMedicos(medicoExpected);

    }

    @Test
    @Order(1)
    @DisplayName("Teste de sucesso para a listagem de todos os medicos")
    void testeListarTodosSucesso() {

        List<Medico> medicos = medicoService.listarTodos();

        assertAll("Lista de medicos",
                () -> assertEquals(TAMANHO_LISTA,medicos.size()),
                () -> assertArrayEquals(medicoExpected.toArray(),medicos.toArray())
        );

    }

    @Test
    @Order(2)
    @DisplayName("Teste de falha para a listagem de todos os medicos")
    void testeListarTodosFalha() {

        List<Medico> medicos = medicoService.listarTodos();
        assertNotEquals(TAMANHO_LISTA-1,medicos.size());

    }

    @Test
    @Order(3)
    @DisplayName("Teste de sucesso para a listar um medico")
    void testeListarUmSucesso() {

        List<Medico> medicos = medicoService.listarTodos();
        Medico medico = medicos.get(0);

        Medico medicoTeste = medicoService.listarUm( medico.getCrm() );

        assertAll("Sucesso para listar um medido",
                () -> assertNotNull(medicoTeste),
                () -> assertEquals(medico.getCrm(),medicoTeste.getCrm())
        );
    }

    @Test
    @Order(4)
    @DisplayName("Teste de falha para a listar um medico")
    void testeListarUmFalha() {

        Medico medicoTeste = medicoService.listarUm( 999999 );
        assertNull(medicoTeste);

    }

    @Test
    @Order(5)
    @DisplayName("Teste de sucesso para incluir um medico")
    void testeIncluirMedicoSucesso() {

        Medico medicoTeste = medicoService.incluir( construirMedico(110000) );
        assertNotNull(medicoTeste);

    }

    @Test
    @Order(6)
    @DisplayName("Teste de falha para incluir um medico")
    void testeIncluirMedicoFalha() {

        Medico medicoTeste = medicoService.incluir( medicoService.getMedicos().get(0) );
        assertNull(medicoTeste);

    }


    @Test
    @Order(7)
    @DisplayName("Teste de sucesso para excluir um medico")
    void testeExcluirMedicoSucesso() {

        Medico medicoTeste = medicoService.excluir( medicoService.getMedicos().get(0) );
        assertNotNull(medicoTeste);

    }

    @Test
    @Order(8)
    @DisplayName("Teste de falha para excluir um medico")
    void testeExcluirMedicoFalha() {

        Medico medicoTeste = medicoService.excluir( construirMedico(110000 ));
        assertNull(medicoTeste);

    }

    @Test
    @Order(9)
    @DisplayName("Teste de sucesso para alterar um medico")
    void testeAlterarMedicoSucesso() {

        String nomeTeste = "Teste de alterar";

        Medico medico = construirMedico( medicoService.getMedicos().get(0).getCrm() );
        medico.setNome( nomeTeste );

        Medico medicoTeste = medicoService.alterar( medico );
        assertEquals(nomeTeste,medicoTeste.getNome());

    }

    @Test
    @Order(10)
    @DisplayName("Teste de falha para alterar um medico")
    void testeAlterarMedicoFalha() {

        String nomeTeste = "Teste de alterar";

        Medico medico = construirMedico(999999);
        medico.setNome(nomeTeste);

        Medico medicoTeste = medicoService.alterar(medico);
        assertNull(medicoTeste);
    }

    @Test
    @Order(11)
    @DisplayName("Teste de sucesso para campos email e telefone de um medico")
    void testeCamposMedicoSucesso() {

        String telefone = "981185698";
        String email = "medico@teste.com";

        Medico medico = construirMedico(991239);
        medico.setTelefone(telefone);
        medico.setEmail(email);

        Medico medicoTeste = medicoService.incluir(medico);
        assertNotNull(medicoTeste);

    }

    @Test
    @Order(12)
    @DisplayName("Teste de falha para campos email e telefone de um medico")
    void testeCamposMedicoFalha() {

        String telefone = "9815698";

        Medico medico = construirMedico(991239);
        medico.setTelefone(telefone);

        Medico medicoTeste = medicoService.incluir(medico);
        assertNull(medicoTeste);

        medico.setTelefone("987879568");
        medico.setEmail("medico@");

        medicoTeste = medicoService.incluir(medico);
        assertNull(medicoTeste);

    }

}
