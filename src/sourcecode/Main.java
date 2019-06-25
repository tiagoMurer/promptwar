package sourcecode;

import sourcecode.model.dinamica.Partida;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
    	int i = 0;
    	System.out.println("Iniciando partida");
        while(i < 3 || i > 6) {
        	System.out.println("Quantos jogadores? (3-6)");
        	Scanner sc = new Scanner(System.in);
        	i = sc.nextInt();
        }
        Partida partida = new Partida(i);
        partida.novaPartida();

    }
}
