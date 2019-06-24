package sourcecode;

import sourcecode.model.dinamica.Partida;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando partida");
        int i = 0;
        while(i < 2 || i > 6) {
            System.out.println("Quantos jogadores? (2-6)");
            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();
            if(i < 2 || i > 6){
                throw new Exception("O número de jogadores deve ser no mínimo 2 e no máximo 6");
            }
        }
        Partida partida = new Partida(i);
        partida.novaPartida();

    }
}
