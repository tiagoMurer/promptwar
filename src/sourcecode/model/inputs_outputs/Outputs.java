package sourcecode.model.inputs_outputs;

import sourcecode.model.jogador.Jogador;
import sourcecode.model.territorios.Pais;

import java.io.IOException;
import java.util.ArrayList;

public class Outputs {

    public void titulo(String x){
        System.out.println("#####"+x+"#####");
    }

    public void listarPaises(ArrayList<Pais> paises){

        for(int i = 0; i <  paises.size(); i++){
            System.out.print("[" + (i+1) + "] "+ paises.get(i).getNome());
            if(paises.get(i).getOcupante().getCor() != null){
                System.out.print(" ("+ paises.get(i).getOcupante().getCor() + ", "+ paises.get(i).getExercitos() + " exércitos)\n");
            }else{
                System.out.println("\n");
            }
        }
    }

    public void listarIdPaises(ArrayList<Integer> ids) throws IOException {
        for(int i = 0; i < ids.size(); i++){
            Pais p = Pais.getPaisById(ids.get(i));
            System.out.println("[" + (i+1) + "] "+ p.getNome());

        }

    }

    public void perguntarSimNao(String pergunta){
        System.out.println(pergunta);
        System.out.println("[1] Sim");
        System.out.println("[2] Nao");
    }


}
