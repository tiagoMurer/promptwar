package sourcecode.model.territorios;

import sourcecode.model.inputs_outputs.LeitorArquivoConf;

import java.io.IOException;
import java.util.ArrayList;

public class Territorios {
    private ArrayList<Pais> paises;
    private ArrayList<Continente> continentes;


    public Territorios() throws IOException {
        LeitorArquivoConf lc = new LeitorArquivoConf();
        this.paises = lc.readPaises();
        this.continentes = lc.readContinentes();
    }



    //-----------------------getters+setters

    public ArrayList<Pais> getPaises() {
        return paises;
    }

    public void setPaises(ArrayList<Pais> paises) {
        this.paises = paises;
    }

    public ArrayList<Continente> getContinentes() {
        return continentes;
    }

    public void setContinentes(ArrayList<Continente> continentes) {
        this.continentes = continentes;
    }

    public Pais getPaisById(int x){
        Pais pais = null;
        for(Pais p: paises){
            if(p.getId() == x){
                pais = p;
                break;
            }
        }
        return pais;
    }

    public ArrayList<Pais> intsToPaises(ArrayList<Integer> ints){
        ArrayList<Pais> paises = new ArrayList();
        for(Integer i: ints){
            paises.add(getPaisById(i));
        }
        return paises;
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
}
