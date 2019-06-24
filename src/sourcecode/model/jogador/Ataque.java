package sourcecode.model.jogador;

import sourcecode.model.Dado;
import sourcecode.model.inputs_outputs.Outputs;
import sourcecode.model.territorios.Pais;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ataque {
    
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
        this.atacante.setExercitos(atacante.getExercitos() - numexercitos);
    }


    public void batalha() {
        Outputs.perguntarSimNao("Iniciar uma batalha?");

        int novabatalha = sc.nextInt();

        if(defensor.getExercitos() == 0 || defensor.getOcupante().getCor() == atacante.getOcupante().getCor()){
            novabatalha = 2;
        }

        while(novabatalha == 1 && defensor.getExercitos() > 0 && numexercitos > 0){

            ArrayList<Integer> atk = rolarAtk();
            System.out.print("O ataque rolou: ");
            for(Integer i: atk){
                System.out.print(i.intValue() + "   ");
            }

            ArrayList<Integer> def = rolarDef();
            System.out.print("\nA defesa rolou: ");
            for(Integer i: def){
                System.out.println(i.intValue() +"   ");
            }

            comparar(atk, def);
            System.out.println("NUMEXERCITOS "+ numexercitos);
            retornarExercito();

            Outputs.perguntarSimNao("Gostaria de iniciar mais uma batalha?");
            novabatalha = sc.nextInt();
        }

    }

    public ArrayList<Integer> rolarDados(int n, Pais pais){
        ArrayList<Integer> rolagens = new ArrayList();

        for (int i = 0; i < n; i++) {
            int v = dado.rolarDado();
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
        int perdadef = 0;
        int perdaatk = 0;
        for(int i = 0; i < Math.max(atkValues, defValues) - Math.abs(atkValues - defValues); i++){
            if(a.get(i) > d.get(i)){
                defensor.setExercitos(defensor.getExercitos() - 1);
                perdadef++;
            }else{
                numexercitos--;
                perdaatk++;
            }
        }
        System.out.println("O defensor perdeu " + perdadef + " exércitos");
        System.out.println("O atacante perdeu " + perdaatk + " exércitos");
    }

    public void retornarExercito(){
        this.atacante.setExercitos(this.atacante.getExercitos() + this.numexercitos);
    }
}
