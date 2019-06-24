package sourcecode.model.inputs_outputs;

import sourcecode.model.territorios.Continente;
import sourcecode.model.territorios.Pais;

import java.io.*;
import java.util.ArrayList;

public class LeitorArquivoConf {
    private static String paisesPath = "src/resources/paises";
    private static String contPath = "src/resources/continentes";
    private static BufferedReader buf;

    //gets e sets
    public String getPaisesPath() {
        return paisesPath;
    }

    public void setPaisesPath(String paisesPath) {
        this.paisesPath = paisesPath;
    }

    public String getContPath() {
        return contPath;
    }

    public void setContPath(String contPath) {
        this.contPath = contPath;
    }

    //outros métodos
    public static String fileToString(String caminhoArquivo) throws IOException {
        InputStream is = new FileInputStream(caminhoArquivo);
        buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while(line != null){
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        String fileAsString = sb.toString();
        return fileAsString;
    }

    public static ArrayList<Continente> readContinentes() throws IOException { //recebe path e retorna arraylist de continentes de um arquivo
        //collection
        ArrayList<Continente> continentes = new ArrayList();

        int id;
        String name;
        int pontos;
        String file = fileToString(contPath);

        //classes
        Continente continente;
        for(String i: file.split("\n")){
            String[] j = i.split("/");
            id = Integer.parseInt(j[0]);
            name = j[1];
            pontos = Integer.parseInt(j[2]);

            continente = new Continente(id, name, pontos);
            continentes.add(continente);
        }

        return continentes;
    }


    public static ArrayList<Pais> readPaises() throws IOException { //recebe path e retorna hashset de paises de um arquivo
        //collection
        ArrayList<Pais> paises = new ArrayList();
        ArrayList<Integer> fronteiras;

        //ints
        int num;
        int continente;

        //strings
        String file = fileToString(paisesPath);
        String[] j;
        String name;
        String[] fr;

        for(String i: file.split("\n")){
            j = i.split("/");
            num = Integer.parseInt(j[0]); //pais.num
            name = j[1];//pais.name
            continente = Integer.parseInt(j[2]); //pais.continente
            fr = j[3].split(" ");
            fronteiras = new ArrayList<Integer>();

            for(String x: fr){                               //pais.fronteiras
                fronteiras.add(Integer.parseInt(x));
            }

            Pais pais = new Pais(num, name, continente, fronteiras);
            paises.add(pais);

        }

        return paises;
    }
}
