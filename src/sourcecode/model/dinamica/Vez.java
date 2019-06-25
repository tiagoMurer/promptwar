package sourcecode.model.dinamica;

import sourcecode.model.jogador.Jogador;

import java.io.IOException;

public class Vez {

	private Jogador jogador;
	
    public Vez(){
    }
    
    public Jogador getJogador() {
    	return jogador; 
    }
    
    public void setJogador(Jogador jogador) {
    	this.jogador = jogador;
    }

    public void novaVez(Jogador jogador) throws IOException {
        System.out.println("Vez do jogador " + jogador.getCor());
        jogador.receber();
        jogador.alocar();
        jogador.atacar();
        jogador.deslocar();
    }
}
