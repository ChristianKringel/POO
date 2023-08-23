package jogo;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Christian
 */
public class Tabuleiro extends JFrame{
            JButton[] bt = new JButton[225];
    public Tabuleiro()
    {
        int cont = 0;
        setVisible(true);
        setDefaultCloseOperation(3);
        setTitle("Teste");
        setLayout(null);
        setBounds(250, 100, 900,780);
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                    bt[cont] = new JButton();
                    add(bt[cont]);
                    bt[cont].setBounds((55 * i) + 20, (55 * j) + 20, 50, 50);
                    cont++;
                }
            }
    }
}
