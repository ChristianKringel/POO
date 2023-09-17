
package jogo;

import javax.swing.JButton;
import java.awt.*;


public class BotaoTabuleiro extends JButton {
    int posX;
    int posY;
    
    public BotaoTabuleiro(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
   
    } 
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
}
