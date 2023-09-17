
package jogo;

public class MonstroRapido implements Personagem {
    private int posX;
    private int posY;

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
