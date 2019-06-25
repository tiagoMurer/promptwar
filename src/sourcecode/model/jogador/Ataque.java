package sourcecode.model.jogador;

import sourcecode.model.Dado;
import sourcecode.model.inputs_outputs.Outputs;
import sourcecode.model.territorios.Pais;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import jdk.internal.dynalink.support.AbstractCallSiteDescriptor;

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
        this.atacante.setExercitos(atacante.getExercitos() - numexercitos);
    }


    public void batalha() {
        if(defensor.getExercitos() == 0 || defensor.getOcupante().getCor() == atacante.getOcupante().getCor()){
        	//dont attk
        }else {
        while(defensor.getExercitos() > 0 && numexercitos > 0){
        	Scanner scanner = new Scanner(System.in);
        	System.out.println("Uma batalha será iniciada");
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
            if(defensor.getExercitos() == 0) {
            int i = 0;
            do {
            	System.out.println("Quantos exercitos voce irá mover? (conquistar)");
            	i = scanner.nextInt();
            }while(i < 1 && i > atk.size() && (i > atacante.getExercitos() - 1));
            defensor.setOcupante(atacante.getOcupante());
            defensor.setExercitos(i);
           
            atacante.setExercitos( atacante.getExercitos() - i);
            atacante.getOcupante().setNumpaises(atacante.getOcupante().getExercitos() + 1);
            }
        }
        }
    }
    

    public ArrayList<Integer> rolarDados(int n){
        ArrayList<Integer> rolagens = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
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

        return rolarDados(atk);
    }

    public ArrayList<Integer> rolarDef(){
        int def;
        if (defensor.getExercitos() >= 3) {
            def = 3;
        } else {
            def = defensor.getExercitos();
        }

        return rolarDados(def);

    }

    public void comparar(ArrayList<Integer> a, ArrayList<Integer> d){
        int atkValues = a.size();
        int defValues = d.size();
        
        for(int i = 0; i < Math.max(atkValues, defValues) - Math.abs(atkValues - defValues); i++){
            if(a.get(i) > d.get(i)){
                System.out.println("O defensor perdeu um exercito");
                defensor.setExercitos(defensor.getExercitos() - 1);
            }else{
                System.out.println("O atacante perdeu um exercito");
                numexercitos -= 1;
            }
        }
    }

}
