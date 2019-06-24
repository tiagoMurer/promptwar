package sourcecode.model.dinamica;

import sourcecode.model.jogador.Jogador;
import sourcecode.model.inputs_outputs.LeitorArquivoConf;
import sourcecode.model.territorios.Pais;
import sourcecode.model.territorios.Territorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Partida {
    private ArrayList<Jogador> jogadores;
    private Territorios territorio;
    private Rodada rodada;
    private Vez vez;
    private static boolean winCondition;

    public Partida(int numJogs) throws IOException {

        this.jogadores = new ArrayList();
        LeitorArquivoConf lc = new LeitorArquivoConf();
        this.territorio = new Territorios();

        inscreverJogadores(numJogs);
        distribuirPaises();

        this.rodada = new Rodada(jogadores);
        this.vez = new Vez();
        this.winCondition = false;
    }


    public void novaPartida() throws IOException {
        while (winCondition == false) {
            rodada.novaRodada(vez);
        }
    }

    public void inscreverJogadores(int numJogadores) throws IOException{
        String[] cores = {"Azul", "Vermelho", "Amarelo", "Rosa", "Branco", "Preto"};
        for(int i = 0; i < numJogadores; i++){
            Jogador jogador = new Jogador(cores[i], i + 1);
            jogadores.add(jogador);
        }

    }

    private void distribuirPaises(){
        double x = Territorios.getPaises().size()/jogadores.size();
        int ppj = (int) Math.floor(x); 		//ppj = paises por jogadores

        ArrayList<Integer> nums = new ArrayList();
        for(int i = 1; i <= 42; i++){  // nums = {1,2,3...42}
            nums.add(i);
        }
        Collections.shuffle(nums);		//nums é embaralhada;

        int p = 0; //index para nums
        for(Jogador jogador:jogadores){
            System.out.println("O jogador " + jogador.getCor() + " recebeu:");
            for(int i = 0; i < ppj; i++){
                Pais pais = Territorios.getPaisById(nums.get(p));
                System.out.print(pais.getNome()+ "("+pais.getId()+")   ");
                pais.setOcupante(jogador);
                jogador.getDominio().add(pais);
                pais.setExercitos(pais.getExercitos()+1);
                jogador.setExercitos(jogador.getExercitos() + 1);
                p++;
            }
            System.out.println("\n");
            jogador.setNumpaises(ppj);	//seta numero de paises dos jogadores
        }
    }




    //--------------------------------------getters+setters
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public static boolean isWinCondition() {
        return winCondition;
    }

    public static void setWinCondition(boolean winCondition) {
        Partida.winCondition = winCondition;
    }
}
