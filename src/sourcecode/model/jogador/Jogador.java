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
        this.dominio =  new ArrayList<Pais>();
        this.numpaises = 0;
        this.exercitosLivres = 0;
        this.scanner = new Scanner(System.in);
        this.op = new Outputs();
        this.t = new Territorios();
    }

    public void receber(){
        int exRec = (int) Math.floor(dominio.size()/2);
        exercitosLivres += exRec;
        System.out.println("Você recebeu " + exRec+ " exercitos");
    }

    public void alocar(){
    	int num;
    	Pais pais;
    	int nExercitos = (int) Math.floor(dominio.size()/2);
    	System.out.println("Escolha a alocação de seus novos exercitos nos paises que você possui:");
        op.listarPaises(dominio);
        while(nExercitos > 0){
        	num = 0;
        	while(num < 1 || num > dominio.size()) {
        		System.out.println( 1 + " de " + dominio.size());
        		num = scanner.nextInt();
        	}
            pais = dominio.get(num-1);
            pais.setExercitos(pais.getExercitos()+1);
            System.out.println(pais.getNome() + " recebeu 1 exercito");
            nExercitos--;
        }
        setExercitosLivres(0);
    }

    public void atacar() throws IOException {
        boolean x = true;
        Pais atacante;
        Pais defensor;
        Ataque ataque;
        int i;
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
                do {
                	System.out.println("Escolha um pais atacante:");
                	op.listarPaises(dominio);
                	i = scanner.nextInt();
                }while(i > dominio.size() || i < 1);
                	atacante = dominio.get(i - 1);
                if(atacante.getExercitos() < 1 || !atacante.getOcupante().getCor().equals(cor) || atacante.getFronteiras().size() == 0){
                    System.out.println("Ataque inválido");
                }else{
                	defensor = null;
                	do {
                		System.out.println("Escolha um pais na fronteira do pais atacante(zero cancela): ");
                		op.listarPaises(atacante.getFronteirasInimigas());
                		i = scanner.nextInt();
                		if(i == 0) {
                			break;
                		}else {
                		defensor = atacante.getPaisById(i);
                		}
                	}while(defensor == null);
                    if(defensor == atacante && i != 0){
                        System.out.println("Você só pode atacar paises inimigos");
                    }else {
                        i = atacante.getExercitos();
                        if(i >= 3) {
                        	i = 3;
                        }
                        ataque = new Ataque(atacante, defensor, i);
                        ataque.batalha();
                    }
                }
            }

        }
    }

    public void deslocar() throws IOException {
    	 int opcao = 0;
    	 int i = 0;
    	do {
        op.perguntarSimNao("Gostaria de deslocar suas tropas?");
        opcao = scanner.nextInt();
    	}while(opcao != 1 && opcao != 2);
        
    	while(opcao == 1) {
        	do {
        	System.out.println("Escolha um pais para ter suas tropas deslocadas (origem):");
            op.listarPaises(dominio);
            i = scanner.nextInt();
        	}while(i < 1 || i > dominio.size());

            Pais origem = dominio.get(i-1);
            if(origem.getFronteirasNaoInimigas().size() > 0) {
            	i = 0;
            	do {
            	System.out.println("Escolha um pais para ter suas tropas deslocadas (destino):");
                op.listarIdPaises(origem.getFronteiras());
                i = scanner.nextInt();
            	}while(i < 1 || i > origem.getFronteiras().size());
            	Pais destino = t.getPaisById(origem.getFronteiras().get(i-1));
            	System.out.println(destino.getNome() + destino.getExercitos() + "->" + (destino.getExercitos()+1) + " exercitos");
            	destino.setExercitos(destino.getExercitos()+1);
            }else{
                System.out.println("Não ha para onde mover exercitos deste pais:");
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
