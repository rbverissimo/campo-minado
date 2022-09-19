package br.com.rvrsmo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabuleiroTeste {

	Tabuleiro tabuleiro;
	
	@BeforeEach
	void construirTabuleiro() {
		tabuleiro = new Tabuleiro(2, 2, 2);
	}
	
	@Test
	void imprimirTabuleiro() {
		
		assertEquals(tabuleiro.toString(), " ?  ? \n ?  ? \n");
	}

}
