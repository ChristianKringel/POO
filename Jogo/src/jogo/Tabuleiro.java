package jogo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Tabuleiro extends JFrame{
            BotaoTabuleiro[][] botoesTabuleiro = new BotaoTabuleiro[15][15];
            JButton botaoCima;
            BotaoTabuleiro[][] botaoBaixo;
            BotaoTabuleiro[][] botaoEsquerda;
            BotaoTabuleiro[][] botaoDireita;
    public Tabuleiro()
    {
        setDefaultCloseOperation(3);
        setTitle("Teste");
        setLayout(null);
        setBounds(165, 70, 900,750);
        setPreferredSize(new Dimension(950,700) );
        setResizable(false);
        pack();
        
        
//        JPanel panel = new JPanel();
//        panel.setBounds(20, 20, 600, 600);
//        add(panel);
        
        
    
        
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                   
                    botoesTabuleiro[i][j] = new BotaoTabuleiro(i, j);
                    botoesTabuleiro[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BotaoTabuleiro botao = (BotaoTabuleiro)e.getSource();
                            System.out.println("Posicao X: " + botao.getPosX());
                            System.out.println("Posicao Y: " + botao.getPosY());
                            System.out.println("------------------------------------");
                           }
                    });
                    botoesTabuleiro[i][j].setBounds((42 * i) + 20, (42 * j) + 20, 42, 42);
                    add(botoesTabuleiro[i][j]);
                }
            }
        

                    botaoCima = new JButton ("Cima");
                            botaoCima.setBounds(650, 20, 100, 30);
                            botaoCima.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    /*BotaoTabuleiro botao = (BotaoTabuleiro)e.getSource();
                                    for(int i = 0; i < 15; i++) {
                                        for (int j = 0; j < 15; j++) {
                                            botoesTabuleiro[i][j].setPosY(botoesTabuleiro[i][j].getPosY() + 1);
                                            System.out.println("Posicao X: " + botao.getPosX());
                                            System.out.println("Posicao Y: " + botao.getPosY());
                                            System.out.println("------------------------------------");
                                        }
                                    }*/
                                }
                            });
                            add(botaoCima);
                                   

//                            botaoBaixo = new BotaoTabuleiro[][]("Baixo");
//                            botaoBaixo.setBounds(650, 60, 100, 30);
//                            botaoBaixo.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    // Lógica para mover para baixo
//                                }
//                            });
//                            add(botaoBaixo);
//
//                            botaoEsquerda = new JButton("Esquerda");
//                            botaoEsquerda.setBounds(650, 100, 100, 30);
//                            botaoEsquerda.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    // Lógica para mover para a esquerda
//                                }
//                            });
//                            add(botaoEsquerda);
//
//                            botaoDireita = new JButton("Direita");
//                            botaoDireita.setBounds(650, 140, 100, 30);
//                            botaoDireita.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    // Lógica para mover para a direita
//                                }
//                            });
//                            add(botaoDireita);

  
            setVisible(true);
    }
}
