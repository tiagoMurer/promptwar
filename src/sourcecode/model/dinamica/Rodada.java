package sourcecode.model.dinamica;

import sourcecode.model.jogador.Jogador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Rodada {
    private ArrayList<Jogador> jogadores;

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

    public void novaRodada(Vez vez) throws IOException {
        for(Jogador jogador: jogadores){
            if(Partida.isWinCondition() == false) {
                vez.novaVez(jogador);
            }
        }
    }

    //--------------------------------------getters+setters


    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
