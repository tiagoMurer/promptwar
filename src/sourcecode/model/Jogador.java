package sourcecode.model;

import sourcecode.model.inputs_outputs.Outputs;
import sourcecode.model.territorios.Pais;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {
    private String cor;
    private int exercitos;
    private int exercitosLivres;
    private ArrayList<Pais> dominio;
    private int numpaises;
    private Outputs op;
    private Scanner scanner;

    public Jogador(String cor, int exercitos) {
        this.cor = cor;
        this.exercitos = exercitos;
        this.dominio =  new ArrayList();
        this.numpaises = 0;
        this.exercitosLivres = 0;
        this.scanner = new Scanner(System.in);
        this.op = new Outputs();
    }

    public void receber(){
        int exRec = (int) Math.floor(dominio.size()/2);
        exercitosLivres += exRec;
        System.out.println("Você recebeu " + exRec+ " exércitos");
    }

    public void alocar(){
        System.out.println("Escolha a alocação de seus novos exércitos nos países que você possui:");
        op.listarPaises(dominio);
        for(int i = 0; i < exercitosLivres; i++){
            int num = scanner.nextInt();
            Pais pais = dominio.get(i);
            pais.setExercitos(pais.getExercitos()+1);
        }
    }

    public void atacar() throws IOException {
        boolean x = true;
        while(x){
            System.out.println("Gostaria de iniciar um ataque?");
            System.out.println("[0] S");
            System.out.println("[1] N");
            int num = 2;
            while(num != 0 && num != 1){
                System.out.println("Insira sim(0) ou nao(1)");
                scanner.nextInt();
            }
            if(num == 1){
                x = false;
                break;
            }else{
                System.out.println("Escolha um pais atacante:");
                op.listarPaises(dominio);
                int i = scanner.nextInt();
                Pais atacante = dominio.get(i);
                System.out.println("Escolha um pais defensor:");
                op.listarPaises(atacante.getFronteirasInimigas());
                i = scanner.nextInt();
                Pais defensor = atacante.getPaisById(i);
            }

        }
    }

    public void deslocar(){

    }


    //Arrumar este método
    public ArrayList<Pais> getFronteirasInimigas(){
        ArrayList<Pais> x = new ArrayList();
        return x;
    }


    //--------------------------------------getters+setters


    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getExercitos() {
        return exercitos;
    }

    public void setExercitos(int exercitos) {
        this.exercitos = exercitos;
    }

    public ArrayList<Pais> getDominio() {
        return dominio;
    }

    public void setDominio(ArrayList<Pais> dominio) {
        this.dominio = dominio;
    }

    public int getExercitosLivres() {
        return exercitosLivres;
    }

    public void setExercitosLivres(int exercitosLivres) {
        this.exercitosLivres = exercitosLivres;
    }

    public int getNumpaises() {
        return numpaises;
    }

    public void setNumpaises(int numpaises) {
        this.numpaises = numpaises;
    }
}
