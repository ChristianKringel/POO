/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jogo;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Scanner;

/**
 *
 * @author Christian
 */
public class Jogo {

    
    
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // TODO code application logic here
        int contador;
        //System.out.println("Teste");
        //new Tabuleiro();
        Tabuleiro t = new Tabuleiro();
        Movimentos m = new Movimentos(1);
        System.out.println("Digite a quantidade de casas a andar: ");
        contador = scan.nextInt();
        for(int i = 0; i <= contador; i++)
         m.Locomover(1);
        //m.Locomover(4);
        //m.Locomover(1);
        //m.Locomover(4);
        System.out.println("Posicao x: " + m.getP1x() + " Posicao y: " + m.getP1y());



    }
    
}
