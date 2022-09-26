import br.com.rvrsmo.model.Tabuleiro;
import br.com.rvrsmo.view.TabuleiroTerminal;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(4, 4, 2);
		new TabuleiroTerminal(tabuleiro); 
		 
	}

}
