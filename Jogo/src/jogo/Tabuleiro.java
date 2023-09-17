package jogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Tabuleiro extends JFrame {
    private List<ItensTabuleiro> itensT = new ArrayList<>();
    private List<ItensInventario> itens = new ArrayList<>();
    private static final Color COR_DESTAQUE_JOGADOR = Color.YELLOW;
    private static final Color COR_DESTAQUE_MONSTRO = Color.BLACK;
    private static final Color COR_DESTAQUE_MONSTRO_RAPIDO = Color.RED;

    private BotaoTabuleiro[][] botoesTabuleiro = new BotaoTabuleiro[15][15];
    // private Tabuleiro t = new Tabuleiro();
    private MonstroLento monstroLento = new MonstroLento();
    private MonstroRapido monstroRapido = new MonstroRapido();
    private Jogador player = new Jogador();
    private JButton botaoCima;
    private JButton botaoBaixo;
    private JButton botaoEsquerda;
    private JButton botaoDireita;

    public Tabuleiro() {

        setDefaultCloseOperation(3);
        setTitle("Teste");
        setLayout(null);
        setBounds(165, 70, 900, 750);
        setPreferredSize(new Dimension(950, 700));
        setResizable(false);
        pack();
        posicaoInicialMonstroLento();
        posicaoInicialMonstroRapido();

//        monstroRapido.setPosX(7); 
//        monstroRapido.setPosY(7); 

        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i < 15; i++) {
                botoesTabuleiro[i][j] = new BotaoTabuleiro(i, j);

                botoesTabuleiro[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BotaoTabuleiro botao = (BotaoTabuleiro) e.getSource();
                        System.out.println("Posicao X: " + botao.getPosX());
                        System.out.println("Posicao Y: " + botao.getPosY());
                        System.out.println("------------------------------------");
                    }
                });

                int posX = i * 42;
                int posY = (14 - j) * 42; // Ajuste para inverter a ordem das linhas

                botoesTabuleiro[i][j].setBounds(posX, posY, 42, 42);

                add(botoesTabuleiro[i][j]);
            }
        }


        botaoCima = new JButton("Cima");
        botaoCima.setBounds(650, 20, 100, 30);
        botaoCima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMove(player.getPosX(), player.getPosY() + 1)) {
                    player.setPosY(player.getPosY() + 1);
                    System.out.println("Posicao x :" + player.getPosX() + " Posicao y: " + player.getPosY());
                    moverMonstroRapido();
                    moverMonstroLento();
                    atualizarDestaque();
                } else
                    System.out.println("Posicao invalida");
            }
        });
        add(botaoCima);

        botaoBaixo = new JButton("Baixo");
        botaoBaixo.setBounds(650, 60, 100, 30);
        botaoBaixo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMove(player.getPosX(), player.getPosY() - 1)) {
                    player.setPosY(player.getPosY() - 1);
                    System.out.println("Posicao x :" + player.getPosX() + " Posicao y: " + player.getPosY());
                    moverMonstroRapido();
                    moverMonstroLento();
                    atualizarDestaque();
                } else
                    System.out.println("Posicao invalida");
            }
        });
        add(botaoBaixo);

        botaoDireita = new JButton("Direita");
        botaoDireita.setBounds(650, 100, 100, 30);
        botaoDireita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMove(player.getPosX(), player.getPosX() + 1)) {
                    player.setPosX(player.getPosX() + 1);
                    System.out.println("Posicao x :" + player.getPosX() + " Posicao y: " + player.getPosY());
                    moverMonstroRapido();
                    moverMonstroLento();
                    atualizarDestaque();
                } else
                    System.out.println("Posicao invalida");
            }
        });
        add(botaoDireita);

        botaoEsquerda = new JButton("Esquerda");
        botaoEsquerda.setBounds(650, 140, 100, 30);
        botaoEsquerda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkMove(player.getPosX(), player.getPosX() - 1)) {
                    player.setPosX(player.getPosX() - 1);
                    System.out.println("Posicao x :" + player.getPosX() + " Posicao y: " + player.getPosY());
                    moverMonstroRapido();
                    moverMonstroLento();
                    atualizarDestaque();
                } else
                    System.out.println("Posicao invalida");
            }
        });
        add(botaoEsquerda);

        setVisible(true);
    }

    private boolean checkMove(int x, int y) {
        return x <= 14 && x >= 0 &&
                y <= 14 && y >= 0;
    }

    private void atualizarDestaque() {
        // Cor de destaque para o jogador
        int xJogador = player.getPosX();
        int yJogador = player.getPosY();

        // Cor de destaque para o monstroLento
        int xMonstroLento = monstroLento.getPosX();
        int yMonstroLento = monstroLento.getPosY();

        // Cor de destaque para o monstroRapido
        int xMonstroRapido = monstroRapido.getPosX();
        int yMonstroRapido = monstroRapido.getPosY();

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                // Verifique se o botão é a posição do jogador, do monstroLento ou do monstroRapido
                if ((i == xJogador && j == yJogador) || (i == xMonstroLento && j == yMonstroLento) || (i == xMonstroRapido && j == yMonstroRapido)) {
                    // Se for a posição do jogador, do monstroLento ou do monstroRapido, defina a cor de destaque correspondente
                    if (i == xJogador && j == yJogador) {
                        botoesTabuleiro[i][j].setBackground(COR_DESTAQUE_JOGADOR);
                    } else if (i == xMonstroLento && j == yMonstroLento) {
                        botoesTabuleiro[i][j].setBackground(COR_DESTAQUE_MONSTRO);
                    } else if (i == xMonstroRapido && j == yMonstroRapido) {
                        botoesTabuleiro[i][j].setBackground(COR_DESTAQUE_MONSTRO_RAPIDO);
                    }
                } else {
                    // Se não for a posição do jogador, do monstroLento ou do monstroRapido, defina o fundo como null
                    botoesTabuleiro[i][j].setBackground(null);
                }
            }
        }
    }



    private void atualizarDestaqueMonstro() {
        // Limpe o destaque em todos os botões
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                botoesTabuleiro[i][j].setBackground(null);
            }
        }
        // Defina a cor de fundo do botão na posição atual do monstro como uma cor de destaque
        int x = monstroLento.getPosX();
        int y = monstroLento.getPosY();
        if (x >= 0 && x < 15 && y >= 0 && y < 15) {
            botoesTabuleiro[x][y].setBackground(COR_DESTAQUE_MONSTRO);
        }
    }

    public void posicaoInicialMonstroLento() {
        Random random = new Random();
        int min = 0;
        int max = 14;

        int x = random.nextInt(max - min + 1) + min;
        int y = random.nextInt(max - min + 1) + min;

        monstroLento.setPosX(x);
        monstroLento.setPosY(y);
    }
    

    private void moverMonstroLento() {

        Random random = new Random();
        int contador = 0;
        int minimo = 0;
        int maximo = 3;
        // caso 1:
        do {
            int num = random.nextInt(maximo - minimo + 1) + minimo;
            if (num == 0) {
                if (checkMove(monstroLento.getPosY(), monstroLento.getPosY() + 1)) {
                    monstroLento.setPosY(monstroLento.getPosY() + 1);
                    contador++;
                }

            } else if (num == 1) {
                if (checkMove(monstroLento.getPosY(), monstroLento.getPosY() - 1)) {
                    monstroLento.setPosY(monstroLento.getPosY() - 1);
                    contador++;
                }
            } else if (num == 2) {
                if (checkMove(monstroLento.getPosX() - 1, monstroLento.getPosY())) {
                    monstroLento.setPosX(monstroLento.getPosX() - 1);
                    contador++;

                }
            } else if (num == 3) {
                if (checkMove(monstroLento.getPosX() + 1, monstroLento.getPosY())) {
                    monstroLento.setPosX(monstroLento.getPosX() + 1);
                    contador++;
                }
            }
        } while(contador == 0);
    }
    
     public void posicaoInicialMonstroRapido() {
        Random random = new Random();
        int min = 0;
        int max = 14;

        int x = random.nextInt(max - min + 1) + min;
        int y = random.nextInt(max - min + 1) + min;

        monstroRapido.setPosX(x);
        monstroRapido.setPosY(y);
    }
    private void moverMonstroRapido() {
        Random random = new Random();
        int num;
        int contador = 0;
        int minimo = 0;
        int maximo = 7;
        
        do {
            num = random.nextInt(maximo - minimo + 1) + minimo;
            if (num == 0) {
                if (checkMove(monstroRapido.getPosX() - 1, monstroRapido.getPosY() + 2)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() - 1);
                    monstroRapido.setPosY(monstroRapido.getPosY() + 2);
                    contador++;
                    //atualizarDestaqueMonstro();
                }
            }

            if (num == 1) {
                if (checkMove(monstroRapido.getPosX() + 1, monstroRapido.getPosY() + 2)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() + 1);
                    monstroRapido.setPosY(monstroRapido.getPosY() + 2);
                    contador++;
                    // atualizarDestaqueMonstro();
                }
            }

            if (num == 2) {
                if (checkMove(monstroRapido.getPosX() - 2, monstroRapido.getPosY() + 1)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() - 2);
                    monstroRapido.setPosY(monstroRapido.getPosY() + 1);
                    contador++;
                    //  atualizarDestaqueMonstro();
                }
            }

            if (num == 3) {
                if (checkMove(monstroRapido.getPosX() - 2, monstroRapido.getPosY() - 1)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() - 2);
                    monstroRapido.setPosY(monstroRapido.getPosY() - 1);
                    contador++;
                }
            }

            if (num == 4) {
                if (checkMove(monstroRapido.getPosX() + 2, monstroRapido.getPosY() + 1)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() + 2);
                    monstroRapido.setPosY(monstroRapido.getPosY() + 1);
                    contador++;
                    //  atualizarDestaqueMonstro();
                }
            }

            if (num == 5) {
                if (checkMove(monstroRapido.getPosX() + 2, monstroRapido.getPosY() - 1)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() + 2);
                    monstroRapido.setPosY(monstroRapido.getPosY() - 1);
                    contador++;
                    // atualizarDestaqueMonstro();
                }
            }

            if (num == 6) {
                if (checkMove(monstroRapido.getPosX() + 1, monstroRapido.getPosY() - 2)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() + 1);
                    monstroRapido.setPosY(monstroRapido.getPosY() - 2);
                    contador++;
                    //  atualizarDestaqueMonstro();
                }
            }

            if (num == 7) {
                if (checkMove(monstroRapido.getPosX() - 1, monstroRapido.getPosY() - 2)) {
                    monstroRapido.setPosX(monstroRapido.getPosX() - 1);
                    monstroRapido.setPosY(monstroRapido.getPosY() - 2);
                    contador++;
                    //  atualizarDestaqueMonstro();
                }
            }
        } while(contador == 0);
    }

}

//    public void testPoco(){
//
//    }


// #############################

//PEDRO e ALEMON
// Movimentacao wumpus Lento
// Num = 0 -> cima, 1 baixo, 2 esquerda, 3 direita

// #############################
// Movimentacao wumpus Rapido
// Num = 0 -> cima, cima, esquerda
// Num = 1 -> cima, cima, direita
// Num = 2 -> esquerda, esquerda, cima
// Num = 3 -> esquerda, esquerda, baixo
// Num = 4 -> direita, direita, cima
// Num = 5 -> direita, direita, baixo
// Num = 6 -> baixo, baixo, direita
// Num = 7 -> baixo, baixo, esquerda
