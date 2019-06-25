package sourcecode.model.jogador;

import sourcecode.model.territorios.Pais;

public class Deslocamento {

    private Pais origem;
    private Pais destino;
    private int exercitos;

    public Deslocamento(Pais origem, Pais destino, int exercitos){
        this.origem = origem;
        this.destino = destino;
        this.exercitos = exercitos;

    }

    public void deslocar(){
        if(origem.getExercitos() < 1){
            System.out.println("Voc� precisa ter um exercito de ocupa��o");
        }
        if(destino.getOcupante().getCor() != origem.getOcupante().getCor() && destino.getOcupante() != null){
            System.out.println("Voc� n�o pode deslocar exercitos para um territorio inimigo");
        }else{
            origem.setExercitos(origem.getExercitos() - exercitos);
            destino.setExercitos(origem.getExercitos() + exercitos);
        }
    }

    public void checarDominio(){
        destino.getContinente();
    }
}
