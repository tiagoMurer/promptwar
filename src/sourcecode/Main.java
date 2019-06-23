package sourcecode;

import sourcecode.model.dinamica.Partida;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando partida");
        System.out.println("Quantos jogadores? (2-6)");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Partida partida = new Partida(i);
        partida.novaPartida();

    }
}
