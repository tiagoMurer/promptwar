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

    public Pais getPaisById(int x) throws IOException {
        Pais pais = null;
        for(Pais p: new LeitorArquivoConf().readPaises()){
            if(p.getId() == x){
                pais = p;
            }
        }
        return pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getContinente() {
        return continente;
    }

    public void setContinente(int continente) {
        this.continente = continente;
    }

    public ArrayList<Integer> getFronteiras() {
        return fronteiras;
    }

    public void setFronteiras(ArrayList<Integer> fronteiras) {
        this.fronteiras = fronteiras;
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

    public ArrayList<Pais> getFronteirasInimigas() throws IOException {
        ArrayList<Pais> fronteirasInimigas = new LeitorArquivoConf().readPaises();
        for(Pais i: new LeitorArquivoConf().readPaises()){
            if(fronteiras.contains(i.getId()) == false || i.getOcupante() == null || i.getOcupante().getCor() == ocupante.getCor()){
                fronteirasInimigas.remove(i);
            }
        }
        return fronteirasInimigas;
    }

    public ArrayList<Pais> getFronteirasNaoInimigas() throws IOException {
        ArrayList<Pais> fronteirasNaoInimigas = new LeitorArquivoConf().readPaises();
        for(Pais i: new LeitorArquivoConf().readPaises()){
            if(fronteiras.contains(i.getId()) == false || (i.getOcupante() != null && i.getOcupante() != ocupante)){
                fronteirasNaoInimigas.remove(i);
            }
        }
        return fronteirasNaoInimigas;
    }

}
