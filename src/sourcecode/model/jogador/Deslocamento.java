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
        origem.setExercitos(origem.getExercitos() - exercitos);
        destino.setExercitos(origem.getExercitos() + exercitos);
    }

    public void checarDominio(){
        destino.getContinente();
    }
}
