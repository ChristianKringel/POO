package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BotaoTabuleiro extends JButton {
    private boolean temAlguemAqui = false;
    private int posX;
    private int posY;
    private TipoDeItem tipo;
    private boolean escondido;
    private ArrayList<Personagem> listaPersonagem;
    private ArrayList<ItensTabuleiro> listaItens;
    
    public BotaoTabuleiro(int i, int j) {
        this.posX = i;
        this.posY = j;
        int x = i * 42;
        int y = (14 - j) * 42; // Ajuste para inverter a ordem das linhas
        tipo = TipoDeItem.VAZIO;
        listaItens = new ArrayList<ItensTabuleiro>();
        listaPersonagem = new ArrayList<Personagem>();
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
        
        if(i+j == 0){
            escondido = false;
        }else {
            escondido = false;
        }
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
    
    public void adicionarDestaque(Color color) {
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
    
    public enum TipoDePersonagem {
        VAZIO, JOGADOR, MONSTRO_RAPIDO, MONSTRO_NORMAL
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
    
    public boolean getEscondido(){
        return escondido;
    }
    
    public void setEscondido(boolean escondido){
        this.escondido = escondido;
    }
    
    public void adicionarPersonagem(Personagem personagem){
        listaPersonagem.add(personagem);
    }
    
    public void removerPersonagem(Personagem personagem){
        listaPersonagem.remove(personagem);
    }
    
    public ArrayList<Personagem> retornarPersonagem (){
        return listaPersonagem;
    }
    
    public void adicionarItem(ItensTabuleiro itens){
        listaItens.add(itens);
    }
    
    public void removerItem(ItensTabuleiro itens){
        listaItens.remove(itens);
    }
    
    public ArrayList<ItensTabuleiro> retornarItem (){
        return listaItens;
    }
}
