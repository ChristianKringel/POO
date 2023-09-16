
package jogo;


public class Monstro implements Personagem {
    int posX;
    int posY;
    
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
}
