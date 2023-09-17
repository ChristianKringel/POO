
package jogo;

import java.util.Random;

public class MonstroRapido implements Personagem {
    private int posX;
    private int posY;

    public MonstroRapido() {
        Random random = new Random();
        int min = 0;
        int max = 14;

        int x = random.nextInt(max - min + 1) + min;
        int y = random.nextInt(max - min + 1) + min;

        this.setPosX(x);
        this.setPosY(y);
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
}
