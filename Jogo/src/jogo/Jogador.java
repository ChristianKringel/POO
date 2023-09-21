
package jogo;


public class Jogador implements Personagem {
    private int posX;
    private int posY;
    private int vida;
    private int capacidadeMochila;
    private int quantidadeOuro;
    private int quantidadeMadeira;
    private int quantidadeArco;
    private int quantidadeFlecha;
    private int quantidadeLanterna;

   public Jogador(int capacidadeMochila) {
        this.capacidadeMochila = capacidadeMochila;
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

   public boolean temEspacoNaMochila() {
        return (quantidadeMadeira + quantidadeArco + quantidadeFlecha + quantidadeLanterna + quantidadeOuro) < capacidadeMochila;
   }
//   public void imprimirConteudoMochila() {
//        System.out.println("Conteúdo da mochila do jogador:");
//
//        if (this.capacidadeMochila.isEmpty()) {
//            System.out.println("A mochila está vazia.");
//        } else {
//            for (String item : mochila) {
//                System.out.println(item);
//            }
//        }
//    }
   public int adicionarArco(){
       int arco = 0;
       return arco++;
   }
   public int adicionarLanterna(){
       int lanterna = 0;
       return lanterna++;
   }
   
   public boolean adicionarOuro() {
        if (temEspacoNaMochila()) {
            quantidadeOuro++;
            return true;
        } else {
            return false;
        }
   }
   
   public boolean adicionarMadeira() {
        if (temEspacoNaMochila()) {
            quantidadeMadeira++;
            return true;
        } else {
            return false;
        }
   }
   
}

