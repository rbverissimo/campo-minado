package br.com.rvrsmo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rvrsmo.exception.ExplosaoException;



class CampoTeste {
	
	Campo campo; 
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}

	@Test
	void testeVizinhoDistancia1Esquerda() {
		
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);

		assertTrue(resultado);

	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Cima() {
		
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Baixo() {
	
	Campo vizinho = new Campo(4, 3);
	boolean resultado = campo.adicionarVizinho(vizinho);
	
	assertTrue(resultado);
}
	
	@Test
	void testeVizinhoDistancia2Diagonal() {
	
	Campo vizinho = new Campo(2, 2);
	boolean resultado = campo.adicionarVizinho(vizinho);
	
	assertTrue(resultado);
}
	
	@Test
	void testeNaoVizinho() {
		
		Campo vizinho = new Campo(3, 5);
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertFalse(resultado);
	}
	
	@Test
	void testeAlternarMarcacao() {
		
		campo.alternarMacacao();
		assertTrue(campo.isMarcado());
		
		campo.alternarMacacao();
		assertFalse(campo.isMarcado());	
		
		
	}
	
	
	@Test
	void abrirNaoMinadoNaoMarcado() {
	
		assertTrue(campo.abrir());
		
	}
	
	@Test
	void abrirNaoMinadoMarcado() {
		
		campo.alternarMacacao();
		assertFalse(campo.abrir());
		
	}
	
	@Test
	void abrirMinadoMarcado() {
		
		campo.alternarMacacao();
		campo.setMinado();
		assertFalse(campo.abrir());
		
	}
	
	@Test
	void abrirMinadoNaoMarcado() {
		
		campo.setMinado();
		
		assertThrows(ExplosaoException.class, () -> {campo.abrir();}); 
			
	}
	
	@Test
	void testeAbrirComVizinhos() {
		
		Campo campo34 = new Campo(3,4);
		Campo campo35 = new Campo(3,5);
		
		campo.adicionarVizinho(campo34);
		campo34.adicionarVizinho(campo35);
		
		
		assertTrue(campo.abrir() &&
				campo34.isAberto() && campo35.isAberto());
		
	}
	
	@Test
	void testeAbrirComVizinhosMinados() {
		
		Campo campo34 = new Campo(3,4);
		Campo campo35 = new Campo(3,5);
		Campo campo45 = new Campo(4,4);
		
		campo35.setMinado();
		
		campo.adicionarVizinho(campo34);
		campo34.adicionarVizinho(campo35);
		
		campo.abrir();
		
		assertTrue(campo34.isAberto());
		assertFalse(campo35.isAberto() && campo45.isAberto());
		
		
	}
	
	@Test
	void testeIsFechado() {
		campo.abrir();
		assertFalse(campo.isFechado());	
	}

	@Test
	void testeReiniciar() {
		campo.setMinado();
		campo.alternarMacacao();
		assertTrue(campo.isMarcado());
		assertTrue(campo.isMinado());
		assertFalse(campo.abrir());
		
		campo.reiniciar();
		assertFalse(campo.isMarcado());
		assertFalse(campo.isMinado());
		assertTrue(campo.abrir());
		
		campo.reiniciar();
		assertFalse(campo.isAberto());
		assertTrue(campo.isFechado());
		
		
	}
	
	@Test
	void testeLinhaColuna() {
		assertEquals(campo.getLINHA(), 3);
		assertEquals(campo.getCOLUNA(), 3);
		
	}
	
	@Test
	void testeDeImpressaoDeCampo() {
		assertEquals(campo.toString(), "?");
	}
	
	@Test
	void testeDeImpressaoDeCampoMarcado() {
		
		campo.alternarMacacao();
		assertEquals(campo.toString(), "x");
		
	}
	
	@Test
	void testeDeImpressaoDeCampoAbertoMinado() {
		
		campo.setMinado();
		assertThrows(ExplosaoException.class, () -> {campo.abrir();});
		assertEquals(campo.toString(), "*");
		
	}
	
	@Test
	void testeDeImpressaoDeCampoAbertoMinasNaVizinhança() {
		
		Campo campo34 = new Campo(3,4);
		Campo campo23 = new Campo(2,3);
		Campo campo24 = new Campo(2,4);
		
		campo23.setMinado();
		campo24.setMinado();
		
		campo.adicionarVizinho(campo34);
		campo.adicionarVizinho(campo23);
		campo.adicionarVizinho(campo24);
		
		campo.abrir();
		
		assertEquals(campo.toString(), "2");
		
	}
	
	@Test
	void testeDeImpressaoDeCampoAberto() {
		campo.abrir();
		assertEquals(campo.toString(), " "); 
	}
	
	@Test
	void testeMinasNaVizinhanca() {
		Campo campo34 = new Campo(3,4);
		Campo campo23 = new Campo(2,3);
		Campo campo24 = new Campo(2,4);
		
		campo23.setMinado();
		campo24.setMinado();
		
		campo.adicionarVizinho(campo34);
		campo.adicionarVizinho(campo23);
		campo.adicionarVizinho(campo24);
		
		assertEquals(campo.minasNaVizinhanca(), 2);
		
		
	}

}
