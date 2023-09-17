package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BotaoTabuleiro extends JButton {
    private boolean temAlguemAqui = false;
    private int posX;
    private int posY;

    public BotaoTabuleiro(int i, int j) {
        this.posX = i;
        this.posY = j;
        int x = i * 42;
        int y = (14 - j) * 42; // Ajuste para inverter a ordem das linhas
        this.setBounds(x, y, 42, 42);
        this.setOpaque(true);
        this.setBorderPainted(true);
        this.setBackground(Color.white);
        this.addActionListener(e -> {
            BotaoTabuleiro botao = (BotaoTabuleiro) e.getSource();
            System.out.println("Posicao X: " + botao.getPosX());
            System.out.println("Posicao Y: " + botao.getPosY());
            System.out.println("Tem Alguem Aqui: " + botao.temAlguemAqui());
            System.out.println("------------------------------------");
        });
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public boolean temAlguemAqui() {
        return this.temAlguemAqui;
    }

    public void adcionarDestaque(Color color) {
        this.setBackground(color);
        this.temAlguemAqui = true;
    }

    public void removerDestaque() {
        this.setBackground(Color.white);
        this.temAlguemAqui = false;
    }
}
