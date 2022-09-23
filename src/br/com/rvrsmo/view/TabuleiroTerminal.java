package br.com.rvrsmo.view;

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
			System.out.println("Você ganhou");
		} catch (ExplosaoException e) {
			System.out.println("Você perdeu :(");
		} 
	}

}
