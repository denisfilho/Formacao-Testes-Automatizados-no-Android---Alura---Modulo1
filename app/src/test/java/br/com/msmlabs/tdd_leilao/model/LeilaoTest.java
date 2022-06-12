package br.com.msmlabs.tdd_leilao.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.msmlabs.tdd_leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.msmlabs.tdd_leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.msmlabs.tdd_leilao.exception.UsuarioJaDeuCincoLancesException;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescrição() {
        // executar ação esperada
        String descricaoDevolvida = CONSOLE.getDescricao();

        // testar resultado esperado
        //assertEquals("Console", descricaoDevolvida); opção 1

        assertThat(descricaoDevolvida, equalTo("Console")); // opção 2
        //assertThat(descricaoDevolvida, is("Console"));  opção 3
        //assertThat(descricaoDevolvida, is(equalTo("Console")));  opção 4
    }
    /*
    Padrão Usado
    //[nome do metodo][Estado de teste][ResultadoEsperado]
    ou
    [deve][Resultado esperado][Estado de teste]
     */
    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        // executar ação esperada
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        // testar resultado esperado
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        // executar ação esperada
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        // testar resultado esperado
        assertEquals(200.0, menorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));

        //Test Driven Development (TDD)
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasDoisLances(){
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        final Usuario FRAN = new Usuario("Fran");

        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(FRAN, 600.0));

        final List<Lance> tresMaioresLancesDevolvidosParaQuatroLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuatroLances.size());
        assertEquals(600.0, tresMaioresLancesDevolvidosParaQuatroLances.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaQuatroLances.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidosParaQuatroLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance (ALEX, 700.0));
        final List<Lance> tresMaioresLancesDevolvidosParaCincoLances = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0, tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0, tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0,menorLanceDevolvido, DELTA);
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance(){
        exception.expect(LanceMenorQueUltimoLanceException.class);
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 400.0));
    }

    @Test
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance(){
        exception.expect(LanceSeguidoDoMesmoUsuarioException.class);

        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(ALEX, 600.0));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)// Tratamento exemplo 3 para se trabalhar com excepetions
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances(){
        //exception.expect(UsuarioJaDeuCincoLancesException.class); Tratamento exemplo 2 para se trabalhar com excepetions

        CONSOLE.propoe(new Lance(ALEX, 100.0));
        Usuario FRAN = new Usuario("Fran");
        CONSOLE.propoe(new Lance(FRAN, 200.0));
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(FRAN, 600.0));
        CONSOLE.propoe(new Lance(ALEX, 700.0));
        CONSOLE.propoe(new Lance(FRAN, 800.0));
        CONSOLE.propoe(new Lance(ALEX, 900.0));
        CONSOLE.propoe(new Lance(FRAN, 1000.0));

        CONSOLE.propoe(new Lance(ALEX, 1100.0));
        /*Tratamento exemplo 1 para se trabalhar com excepetions
        try{
            CONSOLE.propoe(new Lance(ALEX, 1100.0));
            fail("Era esperada uma RuntimeException");
        }catch(RuntimeException exception){
            assertEquals("Usuario ja deu cinco lances", exception.getMessage());
        }

         */

    }
}