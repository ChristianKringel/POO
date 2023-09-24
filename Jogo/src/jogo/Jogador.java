
package jogo;


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

   public Jogador() {
        this.posX = 0;
        this.posY = 0;
        this.capacidadeMochila = 3;
        this.quantidadeArco = 1;
        this.quantidadeLanterna = 1;
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
//   public void criarFlecha() {
//       
//        
//       
//            Arrow arrow = new Arrow();
//            Wood wood = new Wood(0,0,0);
//            backpack.removeIf(item -> item instanceof Wood);
//            backpack.add(arrow);
//            arrow.addArrow();
//            wood.decrementWood();
//    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
       this.vida = vida;
    }
}

