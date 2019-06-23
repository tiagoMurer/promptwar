package sourcecode.model.territorios;


import java.io.IOException;
import java.util.ArrayList;

public class Continente {
    private int id;
    private String nome;
    private int pontosDominacao;

    private static ArrayList<Continente> continentes;

    public Continente(int id, String nome, int pontosDominacao) throws IOException {
        this.nome = nome;
        this.pontosDominacao = pontosDominacao;
        this.id = id;
    }

    //----------------------getters+setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosDominacao() {
        return pontosDominacao;
    }

    public void setPontosDominacao(int pontosDominacao) {
        this.pontosDominacao = pontosDominacao;
    }
}
