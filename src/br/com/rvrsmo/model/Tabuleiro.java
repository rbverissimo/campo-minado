package br.com.rvrsmo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	
	private int QtdeLinhas;
	private int QtdeColunas;
	private int QtdeMinas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int qtdeLinhas, int qtdeColunas, int qtdeMinas) {
		QtdeLinhas = qtdeLinhas;
		QtdeColunas = qtdeColunas;
		QtdeMinas = qtdeMinas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
		
	}
	
	public Tabuleiro(int qtdeLinhas, int qtdeColunas) {
		QtdeLinhas = qtdeLinhas;
		QtdeColunas = qtdeColunas;
		
		gerarCampos();
		associarVizinhos(); 
		
	}
	
	public boolean isCampoAberto(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isAberto()); 
	}
	
	public boolean isCampoFechado(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isFechado()); 
	}
	
	
	public void abrirCampo(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.findFirst()
		.ifPresent(c -> c.abrir());
		
	}
	
	public boolean isCampoMarcado(int linha, int coluna) {
		return campos.parallelStream()
				.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
				.anyMatch(c -> c.isMarcado());
	}
	
	public void marcarCampo(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMacacao());
		
	}
	
	public boolean isCampoMinado(int linha, int coluna) {
		return campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.anyMatch(c -> c.isMinado());
	}
	
	public void minarCampo(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna)
		.findFirst()
		.ifPresent(c -> c.setMinado());
		
	}


	private void gerarCampos() {
		
		for (int i = 0; i < QtdeLinhas; i++) {
			for(int j = 0; j < QtdeColunas; j++) {
				campos.add(new Campo(i, j));
			}
			
		}
		
	}
	
	
	private void associarVizinhos() {
		
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2); 		
			}	
		}
		
	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
		do {
			
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).setMinado();
			
		} while (minasArmadas < QtdeMinas); 

	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.isLimpo());
	}

	public void reiniciarTabuleiro() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for (int l = 0; l < QtdeLinhas; l++) {
			
			for (int c = 0; c < QtdeColunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
				
			}
			
			sb.append("\n");
			
		}
		
		return sb.toString();
	}
	
}
