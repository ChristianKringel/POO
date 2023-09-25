
package jogo;

import javax.swing.JOptionPane;


public class Jogador implements Personagem {
    private int posX;
    private int posY;
    private int vida;
    private int capacidadeMochila = 0;
    private int quantidadeOuro = 0;
    private int quantidadeMadeira = 0;
    private int quantidadeArco = 0;
    private int quantidadeFlecha = 0;
    private int quantidadeLanterna = 0;
    private int bateriaLanterna = 0;

    public Jogador() {
         this.posX = 0;
         this.posY = 0;
         this.capacidadeMochila = 3;
         this.quantidadeArco = 1;
         this.quantidadeLanterna = 1;
         this.bateriaLanterna = 2;
         this.vida = 100;
     }

     @Override
    public int getPosX(){
         return posX;
     }

     @Override
     public int getPosY(){
         return posY;
     }

     @Override
    public void setPosX(int posX){
        this.posX = posX;
    }

     @Override
    public void setPosY(int posY){
        this.posY = posY;
    }

    public int getQuantidadeOuro() {
         return quantidadeOuro;
    }

    public int getQuantidadeMadeira() {
         return quantidadeMadeira;
    }

    public int getQuantidadeArco() {
         return quantidadeArco;
    }

    public int getQuantidadeFlecha() {
         return quantidadeFlecha;
    }

    public int getQuantidadeLanterna() {
         return quantidadeLanterna;
    }

    public void setQuantidadeMadeira(int quantidadeMadeira){ this.quantidadeMadeira = quantidadeMadeira; }
    public void setQuantidadeFlecha(int quantidadeFlecha) { this.quantidadeFlecha = quantidadeFlecha; }
    public void setBateriaLanterna(int bateriaLanterna) { this.bateriaLanterna = bateriaLanterna; }
    public void setQuantidadeLanterna(int quantidadeLanterna) { this.quantidadeLanterna = quantidadeLanterna; }
    
    public int getBateriaLanterna() {
        if(quantidadeLanterna > 0){
         return bateriaLanterna;
        } else{
            return bateriaLanterna = 0;
        }
    }

    public boolean temEspacoNaMochila() {
         return (quantidadeMadeira + quantidadeArco + quantidadeFlecha + quantidadeLanterna + quantidadeOuro) < capacidadeMochila;
    }

    public boolean adicionarOuro() {
         if (temEspacoNaMochila()) {
             quantidadeOuro++;
             JOptionPane.showMessageDialog(null, "Você coletou o ouro!!\n Volte para a casa inicial e vença o jogo",
                     "Aviso", JOptionPane.WARNING_MESSAGE);
             System.out.println("O jogador coletou Ouro. Total de Ouro: " + this.getQuantidadeOuro());
             return true;
         } else {
             System.out.println("Mochila cheia, não coletou o ouro");
             JOptionPane.showMessageDialog(null, "Mochila cheia, jogue um item fora para pegar o ouro", "Aviso", JOptionPane.WARNING_MESSAGE);
             return false;
         }
    }
   
    public boolean adicionarMadeira() {
         if (temEspacoNaMochila()) {
             quantidadeMadeira++;
             JOptionPane.showMessageDialog(null, "Você coletou uma madeira!\n Você pode usa-la para fazer flechas ou tapar um poço",
                     "Aviso", JOptionPane.WARNING_MESSAGE);
             System.out.println("O jogador coletou Madeira. Total de Madeira: " + this.getQuantidadeMadeira());
             return true;
         } else {
             JOptionPane.showMessageDialog(null, "Mochila cheia, jogue um item fora para pegar esta madeira", "Aviso", JOptionPane.WARNING_MESSAGE);
             System.out.println("Mochila cheia, não coletou a madeira");
             return false;
         }
    }
    public void criarFlecha() {
        if(quantidadeMadeira>0){
            quantidadeFlecha++;
            quantidadeMadeira--;
        }
    }
    public void descartarItem(){
        String[] opcoesItens = {"Ouro", "Madeira", "Arco", "Flecha", "Lanterna"};
        String itemSelecionado = (String) JOptionPane.showInputDialog(null, "Escolha um item para descartar:", "Descartar Item", JOptionPane.QUESTION_MESSAGE, null, opcoesItens, opcoesItens[0]);

            if (itemSelecionado != null) {
            switch (itemSelecionado) {
                case "Madeira":
                    if(quantidadeMadeira > 0) quantidadeMadeira--;
                    break;
                case "Arco":
                    if(quantidadeArco > 0) quantidadeArco--;
                    break;
                case "Flecha":
                    if(quantidadeFlecha > 0) quantidadeFlecha--;
                    break;
                case "Lanterna":
                    if(quantidadeLanterna > 0) quantidadeLanterna--;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Você não pode descartar ouro! Vai perder o jogo kkkk", "Aviso", JOptionPane.WARNING_MESSAGE);
                    break;
            }

            }
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
       this.vida = vida;
    }
}

