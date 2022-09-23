package br.com.rvrsmo.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rvrsmo.exception.ExplosaoException;

class TabuleiroTeste {

	Tabuleiro tabuleiro;
	
	@BeforeEach
	void construirTabuleiro() {
		tabuleiro = new Tabuleiro(2, 2, 2);
		
	}
	
	
	@Test
	void imprimirTabuleiroFechado() {
		
		assertEquals(tabuleiro.toString(), " ?  ? \n ?  ? \n");
	}
	
	@Test 
	void testeCampoEstaMarcado() {
		tabuleiro.marcarCampo(0, 0);
		assertTrue(tabuleiro.isCampoMarcado(0, 0));
	}
	
	@Test
	void imprimirTabuleiroMarcado() {
		tabuleiro.marcarCampo(0, 1);
		assertEquals(tabuleiro.toString(), " ?  x \n ?  ? \n");
		
	}
	
	@Test 
	void imprimirTabuleiroAbertoNaoMinado(){
		tabuleiro = new Tabuleiro(2, 2);
		tabuleiro.marcarCampo(1, 1);
		tabuleiro.abrirCampo(0, 0);
		assertEquals(tabuleiro.toString(), "      \n    x \n");
	}
	
	@Test
	void imprimirTabuleiroAbertoMinado() {
		tabuleiro = new Tabuleiro(2, 2);
		tabuleiro.marcarCampo(1, 1);
		tabuleiro.minarCampo(0, 0);
		assertThrows(ExplosaoException.class, () -> {tabuleiro.abrirCampo(0, 0);});
		assertEquals(tabuleiro.toString(), " *  ? \n ?  x \n");
		
	}
	
	@Test
	void imprimirTabuleiroAbertoMinasNaVizinhanca() {
		tabuleiro = new Tabuleiro(2,2);
		tabuleiro.minarCampo(0, 1);
		tabuleiro.abrirCampo(0, 0);
		assertEquals(tabuleiro.toString(), " 1  ? \n ?  ? \n");
		
	}
	
	@Test
	void testeCampoEstaMinado() {
		tabuleiro = new Tabuleiro(2,2);
		tabuleiro.minarCampo(0, 0);
		assertTrue(tabuleiro.isCampoMinado(0, 0));
	}
	
	
	@Test
	void testeObjetivoAlcancadoTabuleiroDesmarcado() {
		
		assertFalse(tabuleiro.objetivoAlcancado());
	}
	
	@Test
	void testeObjetivoAlcancado() {
		tabuleiro = new Tabuleiro(2, 2);
		tabuleiro.marcarCampo(1, 1);
		tabuleiro.abrirCampo(0, 0);
		assertTrue(tabuleiro.objetivoAlcancado());	
		
	}
	
	@Test
	void testeNaoObjetivoAlcancado() {
		tabuleiro = new Tabuleiro(2, 2);
		tabuleiro.minarCampo(0, 1);
		tabuleiro.marcarCampo(1, 1);
		tabuleiro.abrirCampo(0, 0);
		assertFalse(tabuleiro.objetivoAlcancado());	
		
	}
	
	@Test
	void reiniciarTabuleiro() {
		tabuleiro = new Tabuleiro(3, 3);
		tabuleiro.minarCampo(2, 0);
		tabuleiro.marcarCampo(2, 1);
		tabuleiro.abrirCampo(0, 0);
		tabuleiro.reiniciarTabuleiro();
		assertFalse(tabuleiro.isCampoMarcado(2, 1));
		assertFalse(tabuleiro.isCampoMinado(2, 0));
		assertFalse(tabuleiro.isCampoAberto(0, 0));
		assertTrue(tabuleiro.isCampoFechado(0, 0)); 
	}

}
