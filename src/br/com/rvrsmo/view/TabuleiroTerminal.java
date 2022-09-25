package br.com.rvrsmo.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.rvrsmo.exception.ExplosaoException;
import br.com.rvrsmo.exception.SairException;
import br.com.rvrsmo.model.Tabuleiro;

public class TabuleiroTerminal {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroTerminal(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro; 
		
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				loopDoJogo();
				
				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}
					tabuleiro.reiniciarTabuleiro();
			}
		} catch (SairException e) {
			System.out.println("Até logo!");
		} finally {
			entrada.close();
		}
	}

	private void loopDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite a linha e a coluna: ");
				
				//conversão de string para int
				
				Iterator<Integer> linhaColuna = Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir 2 - Marcar/Desmarcar"); 
				
				if("1".equals(digitado)) {
					tabuleiro.abrirCampo(linhaColuna.next(), linhaColuna.next());
				} else if("2".equals(digitado)) {
					tabuleiro.marcarCampo(linhaColuna.next(), linhaColuna.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu :(");
		} 
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
		
	}

}
