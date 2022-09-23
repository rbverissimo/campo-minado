package br.com.rvrsmo.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.rvrsmo.model.Tabuleiro;

class TabuleiroTerminalTeste {

	@Test
	void construtorTabuleiroTerminal() {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8, 6);
		TabuleiroTerminal tt = new TabuleiroTerminal(tabuleiro);
		
		assertNotNull(tt);
	}

}
