package br.com.msmlabs.tdd_leilao.model;

import junit.framework.TestCase;

public class LeilaoTest extends TestCase {

    public void testGetDescricao() {
        // criar cenário de teste
        Leilao console = new Leilao("Console");

        // executar ação esperada
        String descricaoDevolvida = console.getDescricao();

        // testar resultado esperado
        assertEquals("Console", descricaoDevolvida);
    }
}