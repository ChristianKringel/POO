package jogo;

import javax.swing.*;
import java.awt.*;

import static jogo.TipoDeItem.VAZIO;

public class BotaoTabuleiro extends JButton {
    private static final Color COR_DESTAQUE_MADEIRA = Color.GREEN;
    private static final Color COR_DESTAQUE_OURO = Color.MAGENTA;
    private final int posX;
    private final int posY;
    private boolean escondido;
    private Personagem personagem = null;
    private TipoDeItem item = VAZIO;


    public BotaoTabuleiro(int i, int j) {
        this.posX = i;
        this.posY = j;
        this.escondido = true;
        int x = i * 42;
        int y = (14 - j) * 42; // Ajuste para inverter a ordem das linhas
        this.setBounds(x, y, 42, 42);
        this.setOpaque(true);
        this.setBorderPainted(true);
        this.setBackground(Color.gray);
        this.addActionListener(e -> {
            BotaoTabuleiro botao = (BotaoTabuleiro) e.getSource();
            System.out.println("\n");
            System.out.println("-----------------------");
            System.out.println("X: " + botao.getPosX());
            System.out.println("Y: " + botao.getPosY());
            System.out.println("Escondido: " + botao.escondido);
            System.out.println("Tem Alguem Aqui: " + botao.temAlguemAqui());
            System.out.println("Personagem: " + botao.personagem);
            System.out.println("Item: " + botao.item);
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
        if (this.personagem != null) {
            return true;
        } else return this.item != VAZIO;

    }

    public void adicionarPersonagem(Personagem personagem, Color color) {
        this.personagem = personagem;
        if (this.personagem.getClass().getSimpleName().equals(Jogador.class.getSimpleName())) {
            System.out.println("Adicionando jogador: " + personagem);
            this.setBackground(color);
            this.escondido = false;
            return;
        } else {
            System.out.println("Adicionando monstro: " + personagem);
        }
        if (!this.escondido) {
            this.setBackground(color);
        }
    }

    public void removerPersonagem() {
        if (personagem.getClass().getSimpleName().equals(Jogador.class.getSimpleName())) {
            System.out.println("Removendo jogador: " + personagem);
        } else {
            System.out.println("Removendo monstro: " + personagem);
        }
        atualizaBackground();
        this.personagem = null;
    }

    public Personagem retornarPersonagem() {
        return this.personagem;
    }

    public boolean temJogador() {
        return personagem.getClass().getSimpleName().equals(Jogador.class.getSimpleName());
    }

    public void adicionarItem(TipoDeItem item, Color color) {
        this.item = item;
        if (!escondido) {
            this.setBackground(color);
        }
    }

    public void removerItem() {
        this.item = VAZIO;
        setBackground(Color.WHITE);
    }

    public TipoDeItem retornarItem() {
        return this.item;
    }

    private void atualizaBackground() {
        if (this.escondido) {
            this.setBackground(Color.GRAY);
            return;
        }

        switch (item){
            case MADEIRA -> this.setBackground(COR_DESTAQUE_MADEIRA);
            case OURO -> this.setBackground(COR_DESTAQUE_OURO);
            case VAZIO -> this.setBackground(Color.WHITE);
            default -> System.out.println("Item errado");
        }
    }
}
