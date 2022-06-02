package br.com.msmlabs.tdd_leilao.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {
    @Test
    public void deve_DevolveDescricao_QuandoRecebeDescrição() {
        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }
    /*
    Padrão Usado
    //[nome do metodo][Estado de teste][ResultadoEsperado]
    ou
    [deve][Resultado esperado][Estado de teste]
     */
    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeApenasUmLance(){
        //verifica se devolve maior lance com apenas um lance
        // criar cenário de teste
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        // executar ação esperada
        double maiorLanceDevolvido = console.getMaiorLance();

        // testar resultado esperado
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        // criar cenário de teste
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 200.0));

        double maiorLanceDevolvido = computador.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 10000.0));
        carro.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double maiorLanceDevolvido = carro.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, 0.0001);
    }
    @Test
    public void deve_DevolveMenorLance_QuandoRecebeApenasUmLance(){
        //verifica se devolve maior lance com apenas um lance
        // criar cenário de teste
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Alex"), 200.0));

        // executar ação esperada
        double menorLanceDevolvido = console.getMenorLance();

        // testar resultado esperado
        assertEquals(200.0, menorLanceDevolvido, 0.0001);

    }

    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){

        // criar cenário de teste
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Alex"), 100.0));
        computador.propoe(new Lance(new Usuario("Fran"), 200.0));

        double menorLanceDevolvido = computador.getMenorLance();
        assertEquals(100.0, menorLanceDevolvido, 0.0001);

    }
    @Test
    public void deve_DevolveMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Alex"), 10000.0));
        carro.propoe(new Lance(new Usuario("Fran"), 9000.0));

        double menorLanceDevolvido = carro.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, 0.0001);
    }
}