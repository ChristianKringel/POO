package jogo;

import javax.swing.JButton;


public class BotaoTabuleiro extends JButton {
    int posX;
    int posY;
    
    public BotaoTabuleiro(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        
       // setBackground();
        
    } 
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
}
