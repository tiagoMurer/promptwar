package sourcecode.model.inputs_outputs;

import sourcecode.model.Jogador;
import sourcecode.model.territorios.Pais;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Outputs {

    public void titulo(String x){
        System.out.println("#####"+x+"#####");
    }

    public void listarPaises(ArrayList<Pais> paises){
        int i = 1;
        for(Pais pais: paises){
            System.out.print("[" + i + "] "+ pais.getNome());
            if(pais.getOcupante().getCor() != null){
                System.out.print(" ("+ pais.getOcupante().getCor() + ", "+ pais.getExercitos() + " exércitos)\n");
            }else{
                System.out.println("\n");
            }
            i++;
        }
    }

    public void listarPaisesSet(HashSet<Pais> paises) {
        Iterator<Pais> itr = paises.iterator();
        int i = 0;
        while (itr.hasNext()) {
            System.out.println("[" + i + "] " + itr.next().getNome());
            i++;
        }
    }

    public void listarJogadores(ArrayList<Jogador> jogadores){
        int i = 1;
        for(Jogador jogador: jogadores){
            System.out.println("["+ i +"] " + jogador.getCor());

        }
    }
}
