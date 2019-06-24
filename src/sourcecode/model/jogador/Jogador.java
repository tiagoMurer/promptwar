package sourcecode.model.jogador;

import sourcecode.model.inputs_outputs.Outputs;
import sourcecode.model.territorios.Continente;
import sourcecode.model.territorios.Pais;
import sourcecode.model.territorios.Territorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Jogador {
    private String cor;
    private Territorios t;
    private int exercitos;
    private int exercitosLivres;
    private ArrayList<Pais> dominio;
    private ArrayList<Continente> continentesDominados;
    private int numpaises;
    private Outputs op;
    private Scanner scanner;

    public Jogador(String cor, int exercitos) throws IOException{
        this.cor = cor;
        this.exercitos = exercitos;
        this.dominio =  new ArrayList();
        this.numpaises = 0;
        this.exercitosLivres = 0;
        this.scanner = new Scanner(System.in);
        this.op = new Outputs();
        this.t = new Territorios();
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
            System.out.println((i+1) + " de " + exercitosLivres);
            int num = scanner.nextInt();
            Pais pais = dominio.get(num-1);
            pais.setExercitos(pais.getExercitos()+1);
            System.out.println(pais.getNome() + " recebeu 1 exército");
        }
        setExercitosLivres(0);
    }

    public void atacar() throws IOException {
        boolean x = true;
        while(x){
            op.perguntarSimNao("Gostaria de iniciar um ataque?");
            int num = scanner.nextInt();
            while(num != 1 && num != 2){
                System.out.println("Insira sim(1) ou nao(2)");
                num = scanner.nextInt();
            }
            if(num == 2){
                x = false;
                break;
            }else{
                System.out.println("Escolha um pais atacante:");
                op.listarPaises(dominio);
                int i = scanner.nextInt();
                Pais atacante = dominio.get(i - 1);
                if(atacante.getExercitos() < 2){
                    System.out.println("Você precisa manter um exército de ocupação");
                }else{
                    System.out.println("Escolha um pais na fronteira do país atacante:");
                    op.listarPaises(atacante.getFronteirasInimigas());
                    i = scanner.nextInt();
                    Pais defensor = atacante.getPaisById(i);
                    if(defensor.getOcupante().getCor() == atacante.getOcupante().getCor() || defensor.getExercitos() == 0){
                        System.out.println("Você só pode atacar países inimigos");
                    }else {
                        System.out.println("Determine um número de atacantes ( 1  a " + (atacante.getExercitos() - 1) + ")");
                        i = scanner.nextInt();
                        Ataque ataque = new Ataque(atacante, defensor, i);
                        ataque.batalha();
                    }
                }
            }

        }
    }

    public void deslocar() throws IOException {

        op.perguntarSimNao("Gostaria de deslocar suas tropas?");
        int opcao = scanner.nextInt();

        while(opcao == 1) {
            System.out.println("Escolha um pais para ter suas tropas deslocadas:");
            op.listarPaises(dominio);
            int i = scanner.nextInt();

            Pais pais = dominio.get(i);
            if(pais.getFronteirasNaoInimigas().size() > 0) {
                System.out.println("Escolha um pais para ter suas tropas deslocadas:");
                op.listarIdPaises(pais.getFronteiras());
                scanner.nextInt();
            }else{
                System.out.println("Não há para onde mover exércitos deste país:");
            }

            op.perguntarSimNao("Gostaria de deslocar mais tropas?");
            opcao = scanner.nextInt();
        }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return exercitos == jogador.exercitos &&
                exercitosLivres == jogador.exercitosLivres &&
                numpaises == jogador.numpaises &&
                Objects.equals(cor, jogador.cor) &&
                Objects.equals(t, jogador.t) &&
                Objects.equals(dominio, jogador.dominio) &&
                Objects.equals(continentesDominados, jogador.continentesDominados) &&
                Objects.equals(op, jogador.op) &&
                Objects.equals(scanner, jogador.scanner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cor, t, exercitos, exercitosLivres, dominio, continentesDominados, numpaises, op, scanner);
    }
}
