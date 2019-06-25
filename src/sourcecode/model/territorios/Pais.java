package sourcecode.model.territorios;

import sourcecode.model.jogador.Jogador;
import sourcecode.model.inputs_outputs.LeitorArquivoConf;

import java.io.IOException;
import java.util.ArrayList;

public class Pais {
    private int id;
    private String nome;
    private int continente;
    private ArrayList<Integer> fronteiras;
    private int exercitos;
    private Jogador ocupante;


    public Pais(int id, String nome, int continente, ArrayList<Integer> fronteiras) throws IOException {
        this.id = id;
        this.nome = nome;
        this.continente = continente;
        this.fronteiras = fronteiras;
        this.exercitos = 0;
    }



    //--------------------------------------getters+setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Pais getPaisById(int x){
        Pais pais = null;
        for(Pais p: Territorios.getPaises()){
            if(p.getId() == x){
                pais = p;
            }
        }
        return pais;
    }

    public String getNome() {
        return nome;
    }


    public ArrayList<Integer> getFronteiras() {
        return fronteiras;
    }

    public int getExercitos() {
        return exercitos;
    }

    public void setExercitos(int exercitos) {
        this.exercitos = exercitos;
    }

    public Jogador getOcupante() {
        return ocupante;
    }

    public void setOcupante(Jogador ocupante) {
        this.ocupante = ocupante;
    }

    public ArrayList<Pais> getFronteirasInimigas(){
        ArrayList<Integer> fr = this.fronteiras;
        ArrayList<Pais> fronteirasInimigas = new ArrayList();
        for(Integer i: fr){
            Pais pais = getPaisById(i);
            if(pais.getOcupante() != null && pais.getOcupante().getCor() != ocupante.getCor()){
                fronteirasInimigas.add(pais);
            }
        }
        return fronteirasInimigas;
    }

    public ArrayList<Pais> getFronteirasNaoInimigas(){
        ArrayList<Pais> paises = Territorios.getpaises();
        ArrayList<Pais> fronteirasNaoInimigas = new ArrayList<>();
        for(Pais i: paises){
            if(fronteiras.contains(i.getId()) && (i.getOcupante() == null || i.getOcupante() == ocupante)){
                fronteirasNaoInimigas.add(i);
            }
        }
        return fronteirasNaoInimigas;
    }

    public int getContinente() {
        return continente;
    }

    public void setContinente(int continente) {
        this.continente = continente;
    }
}
