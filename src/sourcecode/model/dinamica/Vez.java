package sourcecode.model.dinamica;

import sourcecode.model.jogador.Jogador;
import sourcecode.model.territorios.Continente;
import sourcecode.model.territorios.Territorios;

import java.io.IOException;

public class Vez {

    public Vez(){
    }

    public void novaVez(Jogador jogador) throws IOException {
        System.out.println("Vez do jogador " + jogador.getCor());

        int pontosdominacao = 0;
        for(Continente c: Territorios.getContinentes()){
            if(Territorios.checarDominacaoContinente(jogador, c) == true){
                pontosdominacao += c.getPontosDominacao();
            }
        }

        jogador.setPontosdominacao(pontosdominacao);

        jogador.receber();
        jogador.alocar();
        jogador.atacar();
        jogador.deslocar();

        boolean x = Territorios.checkVitoria(jogador);
        Partida.setWinCondition(x);

    }
}
