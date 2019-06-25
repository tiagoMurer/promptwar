package sourcecode.model.territorios;

import sourcecode.model.inputs_outputs.LeitorArquivoConf;
import sourcecode.model.jogador.Jogador;

import java.io.IOException;
import java.util.ArrayList;

public class Territorios {
    private static ArrayList<Pais> paises;
    private static ArrayList<Continente> continentes;


    public Territorios() throws IOException {
        LeitorArquivoConf lc = new LeitorArquivoConf();
        this.paises = lc.readPaises();
        this.continentes = lc.readContinentes();
    }



    //-----------------------getters+setters

    public static ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }

    public static ArrayList<Continente> getContinentes() {
        return continentes;
    }

    public void setContinentes(ArrayList<Continente> continentes) {
        this.continentes = continentes;
    }

    public static Pais getPaisById(int x){
        Pais pais = null;
        for(Pais p: paises){
            if(p.getId() == x){
                pais = p;
                break;
            }
        }
        return pais;
    }

    public static ArrayList<Pais> getpaises(){
        return paises;
    }

    public ArrayList<Pais> intsToPaises(ArrayList<Integer> ints){
        ArrayList<Pais> paises = new ArrayList();
        for(Integer i: ints){
            paises.add(getPaisById(i));
        }
        return paises;
    }

    public static ArrayList<Pais> getPaisesdoContinente(Continente c){
        int idC = c.getId();
        ArrayList<Pais> p = new ArrayList();
        for(Pais pais: paises){
            if(pais.getContinente() == idC){
                p.add(pais);
            }
        }

        return p;
    }

    public static boolean checarDominacaoContinente(Jogador j, Continente c){
        for(Pais p: getPaisesdoContinente(c)){
            if(p.getOcupante().getCor() != j.getCor()){
                return false;
            }
        }
        return true;
    }


    public Continente getContinenteById(int x){
        Continente continente = null;
        for(Continente c: continentes){
            if(c.getId() == x){
                continente = c;
            }
        }
        return continente;
    }

    public static boolean checkVitoria(Jogador jogador){
        for(Pais pais: getPaises()){
            if(pais.getOcupante().getCor() != jogador.getCor()){
                return false;
            }
        }
        return true;
    }
}
