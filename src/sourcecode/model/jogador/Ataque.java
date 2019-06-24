package sourcecode.model.jogador;

import sourcecode.model.Dado;
import sourcecode.model.inputs_outputs.Outputs;
import sourcecode.model.territorios.Pais;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ataque {

    private Jogador jogadorDaVez;

    private Pais atacante;
    private Pais defensor;
    private int numexercitos;

    private Dado dado;

    private Scanner sc;

    // construtor
    public Ataque(Pais atacante, Pais defensor, int numexercitos) {
        this.sc = new Scanner(System.in);

        this.dado = new Dado(1, 6);
        this.atacante = atacante;
        this.defensor = defensor;
        this.numexercitos = numexercitos;
        atacante.setExercitos(atacante.getExercitos() - numexercitos);
    }


    public void batalha() {
        System.out.println("Iniciar nova batalha?");
        System.out.println("[1] Sim");
        System.out.println("[2] Não");
        int novabatalha = sc.nextInt();
        if(defensor.getExercitos() == 0 || defensor.getOcupante().getCor() == atacante.getOcupante().getCor()){
            System.out.println("Você só pode atacar um país inimigo");
        }
        while(novabatalha == 1 && defensor.getExercitos() > 0 && numexercitos > 0){
            System.out.println("Uma batalha será iniciada");
            ArrayList<Integer> atk = rolarAtk();
            System.out.print("O ataque rolou: ");
            for(Integer i: atk){
                System.out.print(i);
            }
            ArrayList<Integer> def = rolarDef();
            System.out.print("A defesa rolou: ");
            for(Integer i: def){
                System.out.print(i);
            }
            comparar(atk, def);

            Outputs.perguntarSimNao("Gostaria de iniciar mais uma batalha?");
            novabatalha = sc.nextInt();
        }

    }

    public ArrayList<Integer> rolarDados(int n, Pais pais){
        ArrayList<Integer> rolagens = new ArrayList();

        for (int i = 0; i < n; i++) {
            int v = dado.rolarDado();
            System.out.println("O jogador " + pais.getOcupante().getCor() + " rolou um " + v);
            rolagens.add(dado.rolarDado());
        }
        Collections.sort(rolagens);
        Collections.reverse(rolagens);
        return rolagens;
    }

    public ArrayList<Integer> rolarAtk(){
        int atk;
        if (numexercitos >= 3) {
            atk = 3;
            numexercitos -= 3;
        } else {
            atk = numexercitos;
        }

        return rolarDados(atk, atacante);
    }

    public ArrayList<Integer> rolarDef(){
        int def;
        if (defensor.getExercitos() >= 3) {
            def = 3;
        } else {
            def = defensor.getExercitos();
        }

        return rolarDados(def, defensor);

    }

    public void comparar(ArrayList<Integer> a, ArrayList<Integer> d){
        int atkValues = a.size();
        int defValues = d.size();

        for(int i = 0; i < Math.max(atkValues, defValues) - Math.abs(atkValues - defValues); i++){
            if(a.get(i) > d.get(i)){
                System.out.println("O defensor perdeu um exército");
                defensor.setExercitos(defensor.getExercitos() - 1);
            }else{
                System.out.println("O atacante perdeu um exército");
                numexercitos -= 1;
            }
        }

        atacante.setExercitos(atacante.getExercitos() + numexercitos);
    }

}
