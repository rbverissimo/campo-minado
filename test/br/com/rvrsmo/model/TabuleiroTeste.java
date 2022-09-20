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
	
	@Test
	void testeObjetivoAlcancadoTabuleiroDesmarcado() {
		
		assertFalse(tabuleiro.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado() {
		tabuleiro = new Tabuleiro(2, 2, 0);
		tabuleiro.marcarCampo(0, 0);
		tabuleiro.marcarCampo(0, 1);
		tabuleiro.abrirCampo(1, 0);
		tabuleiro.marcarCampo(1, 1);
		assertTrue(tabuleiro.objetivoAlcancado());	
		
	}

}
