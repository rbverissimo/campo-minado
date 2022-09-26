import br.com.rvrsmo.model.Tabuleiro;
import br.com.rvrsmo.view.TabuleiroTerminal;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(9, 9, 9);
		new TabuleiroTerminal(tabuleiro); 
		 
	}

}
