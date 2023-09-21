package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotaoTabuleiro extends JButton {
    private boolean temAlguemAqui = false;
    private int posX;
    private int posY;
    private TipoDeItem tipo;

    public BotaoTabuleiro(int i, int j) {
        this.posX = i;
        this.posY = j;
        int x = i * 42;
        int y = (14 - j) * 42; // Ajuste para inverter a ordem das linhas
        tipo = TipoDeItem.VAZIO;
        this.setBounds(x, y, 42, 42);
        this.setOpaque(true);
        this.setBorderPainted(true);
        this.setBackground(Color.white);
        this.addActionListener(e -> {
            BotaoTabuleiro botao = (BotaoTabuleiro) e.getSource();
            System.out.println("Posicao X: " + botao.getPosX());
            System.out.println("Posicao Y: " + botao.getPosY());
            System.out.println("Tem Alguem Aqui: " + botao.temAlguemAqui());
            System.out.println("Tipo de Item: " + botao.getTipoDeItem());
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

    public void adcionarDestaqueItem(Color color) {
        this.setBackground(color);
        this.temAlguemAqui = false;
    }

    public void removerDestaque() {
        this.setBackground(Color.white);
        this.temAlguemAqui = false;
    }

    public enum TipoDeItem {
        VAZIO, POCO, MADEIRA, OURO
    }

    public TipoDeItem getTipoDeItem() {
        return tipo;
    }

    public void setTipoDeItem(TipoDeItem tipo) {
        this.tipo = tipo;
    }
    public void setTemAlguemAqui(boolean temAlguemAqui) {
        this.temAlguemAqui = temAlguemAqui;
    }
}
