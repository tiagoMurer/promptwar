package sourcecode.model.dinamica;

import sourcecode.model.jogador.Jogador;
import sourcecode.model.territorios.Pais;
import sourcecode.model.territorios.Territorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Rodada {
    private ArrayList<Jogador> jogadores;
    private Jogador vencedor;

    public Rodada(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
        Collections.shuffle(jogadores);
        System.out.println("A ordem dos jogadores será:");
        int i = 1;
        for(Jogador jogador: jogadores){
            System.out.println("[" + i + "] " + jogador.getCor());
            i++;
        }
        System.out.println("\n");
    }

    public void executarRodadas(Vez vez, Territorios territorios) throws IOException {
        this.vencedor = null;
    	for(Jogador jogador: jogadores){
            vez.novaVez(jogador);
            if(checkWin(jogador)) {
            	vencedor = vez.getJogador();
            	System.out.println(jogador.getCor() + " venceu!!");
            	System.exit(0);
            	break;
            }
        }
    }

    public static boolean checkWin(Jogador jogador){
        //Jogador jogador;
        boolean retorno = true;
        for(Pais pais: Territorios.getPaises()){
            if(pais.getOcupante().getCor() != jogador.getCor()){
            	retorno = false;
            	break;
            }
        }
        return retorno;
    }
    
    //--------------------------------------getters+setters


    
    
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public Jogador getVencedor() {
		return vencedor;
	}

	public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
