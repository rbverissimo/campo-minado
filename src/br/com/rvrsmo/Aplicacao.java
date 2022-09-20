package br.com.rvrsmo;

import br.com.rvrsmo.model.Tabuleiro;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(8, 8 , 12);
		
		
		tabuleiro.abrirCampo(3, 4);
		tabuleiro.marcarCampo(7, 3);
		
		System.out.println(tabuleiro);
		
		

		
	}

}
