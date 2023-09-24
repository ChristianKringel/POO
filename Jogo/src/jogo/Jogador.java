
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
             System.out.println("O jogador coletou Ouro. Total de Ouro: " + this.getQuantidadeOuro());
             return true;
         } else {
             System.out.println("Mochila cheia, não coletou o ouro");
             return false;
         }
    }
   
    public boolean adicionarMadeira() {
         if (temEspacoNaMochila()) {
             quantidadeMadeira++;
             System.out.println("O jogador coletou Madeira. Total de Madeira: " + this.getQuantidadeMadeira());
             return true;
         } else {
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
    
//    public void usarLanterna(String direcao) {
//        if (bateriaLanterna <= 0) {
//            JOptionPane.showMessageDialog(null, "A bateria da lanterna está esgotada.", "Lanterna", JOptionPane.WARNING_MESSAGE);
//            return;
//        }
//
//        String[] direcoes = {"Cima", "Baixo", "Esquerda", "Direita"};
//        String direcaoEscolhida = (String) JOptionPane.showInputDialog(null, "Escolha a direção para usar a lanterna:", "Lanterna", JOptionPane.QUESTION_MESSAGE, null, direcoes, direcoes[0]);
//
//        if (direcaoEscolhida != null) {
//            bateriaLanterna--;
//            iluminarCasas(direcaoEscolhida);
//            JOptionPane.showMessageDialog(null, "A lanterna revelou as casas na direção " + direcaoEscolhida + ".", "Lanterna", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
       this.vida = vida;
    }
}

