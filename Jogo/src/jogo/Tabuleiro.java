package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Tabuleiro extends JFrame {
    private static final Color COR_DESTAQUE_POCO = Color.BLUE;
    private static final Color COR_DESTAQUE_MADEIRA = Color.GREEN; 
    private static final Color COR_DESTAQUE_JOGADOR = Color.YELLOW;
    private static final Color COR_DESTAQUE_MONSTRO = Color.BLACK;
    private static final Color COR_DESTAQUE_MONSTRO_RAPIDO = Color.RED;
    private final List<ItensTabuleiro> itensT = new ArrayList<>();
    private final List<ItensInventario> itens = new ArrayList<>();
    private final BotaoTabuleiro[][] botoesTabuleiro = new BotaoTabuleiro[15][15];
    private final MonstroLento monstroLento = new MonstroLento();
    private final MonstroRapido monstroRapido = new MonstroRapido();
    private final Jogador player = new Jogador();
    private final JButton botaoCima;
    private final JButton botaoBaixo;
    private final JButton botaoEsquerda;
    private final JButton botaoDireita;

    public Tabuleiro() {
        setDefaultCloseOperation(3);
        setTitle("Teste");
        setLayout(null);
        setBounds(165, 70, 900, 750);
        setPreferredSize(new Dimension(950, 700));
        setResizable(false);
        pack();

        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i < 15; i++) {
                botoesTabuleiro[i][j] = new BotaoTabuleiro(i, j);
                add(botoesTabuleiro[i][j]);
            }
        }

        player.setPosX(0);
        player.setPosX(0);
        botoesTabuleiro[0][0].adcionarDestaque(COR_DESTAQUE_JOGADOR);
        criaPocos();
        criaMadeira();
        moverMonstroRapido();
        moverMonstroLento();

        botaoCima = new JButton("Cima");
        botaoCima.setBounds(650, 20, 100, 30);
        botaoCima.addActionListener(e -> {
            if (checkMove(player.getPosX(), player.getPosY() + 1)) {
                atualizarPersonagem(player, COR_DESTAQUE_JOGADOR, player.getPosX(), player.getPosY(), player.getPosX(), player.getPosY() + 1);
                moverMonstroRapido();
                moverMonstroLento();
            } else System.out.println("Posicao invalida");
        });
        add(botaoCima);

        botaoBaixo = new JButton("Baixo");
        botaoBaixo.setBounds(650, 60, 100, 30);
        botaoBaixo.addActionListener(e -> {
            if (checkMove(player.getPosX(), player.getPosY() - 1)) {
                atualizarPersonagem(player, COR_DESTAQUE_JOGADOR, player.getPosX(), player.getPosY(), player.getPosX(), player.getPosY() - 1);
                moverMonstroRapido();
                moverMonstroLento();
            } else System.out.println("Posicao invalida");
        });
        add(botaoBaixo);

        botaoDireita = new JButton("Direita");
        botaoDireita.setBounds(650, 100, 100, 30);
        botaoDireita.addActionListener(e -> {
            if (checkMove(player.getPosX() + 1, player.getPosY())) {
                atualizarPersonagem(player, COR_DESTAQUE_JOGADOR, player.getPosX(), player.getPosY(), player.getPosX() + 1, player.getPosY());
                moverMonstroRapido();
                moverMonstroLento();
            } else System.out.println("Posicao invalida");
        });
        add(botaoDireita);

        botaoEsquerda = new JButton("Esquerda");
        botaoEsquerda.setBounds(650, 140, 100, 30);
        botaoEsquerda.addActionListener(e -> {
            if (checkMove(player.getPosX() - 1, player.getPosY())) {
                atualizarPersonagem(player, COR_DESTAQUE_JOGADOR, player.getPosX(), player.getPosY(), player.getPosX() - 1, player.getPosY());
                moverMonstroRapido();
                moverMonstroLento();
            } else System.out.println("Posicao invalida");
        });
        add(botaoEsquerda);

        setVisible(true);
    }

    private void atualizarPersonagem(Personagem p, Color c, int xAntigo, int yAntigo, int xNovo, int yNovo) {
        botoesTabuleiro[xAntigo][yAntigo].removerDestaque();
        botoesTabuleiro[xNovo][yNovo].adcionarDestaque(c);
        p.setPosX(xNovo);
        p.setPosY(yNovo);
        System.out.println("Nova posicao de " + p.getClass().getSimpleName() + " -> x :" + p.getPosX() + " Posicao y: " + p.getPosY());
    }

    private void moverMonstroLento() {
        Random random = new Random();
        boolean moveu = false;
        int minimo = 0;
        int maximo = 3;
        do {
            int num = random.nextInt(maximo - minimo + 1) + minimo;
            switch (num) {
                case 0 -> {
                    if (checkMove(monstroLento.getPosY(), monstroLento.getPosY() + 1)) {
                        atualizarPersonagem(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX, monstroLento.posY + 1);
                        moveu = true;
                    }
                }
                case 1 -> {
                    if (checkMove(monstroLento.getPosY(), monstroLento.getPosY() - 1)) {
                        atualizarPersonagem(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX, monstroLento.posY - 1);
                        moveu = true;
                    }
                }
                case 2 -> {
                    if (checkMove(monstroLento.getPosX() - 1, monstroLento.getPosY())) {
                        atualizarPersonagem(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX - 1, monstroLento.posY);
                        moveu = true;

                    }
                }
                case 3 -> {
                    if (checkMove(monstroLento.getPosX() + 1, monstroLento.getPosY())) {
                        atualizarPersonagem(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX + 1, monstroLento.posY);
                        moveu = true;
                    }
                }
            }
        } while (!moveu);
    }

    private void moverMonstroRapido() {
        Random random = new Random();
        int num;
        boolean moveu = false;
        int minimo = 0;
        int maximo = 7;

        do {
            num = random.nextInt(maximo - minimo + 1) + minimo;
            switch (num) {
                case 0 -> {
                    if (checkMove(monstroRapido.getPosX() - 1, monstroRapido.getPosY() + 2)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 1, monstroRapido.getPosY() + 2);
                        moveu = true;
                    }
                }
                case 1 -> {
                    if (checkMove(monstroRapido.getPosX() + 1, monstroRapido.getPosY() + 2)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 1, monstroRapido.getPosY() + 2);
                        moveu = true;
                    }
                }
                case 2 -> {
                    if (checkMove(monstroRapido.getPosX() - 2, monstroRapido.getPosY() + 1)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 2, monstroRapido.getPosY() + 1);
                        moveu = true;
                    }
                }
                case 3 -> {
                    if (checkMove(monstroRapido.getPosX() - 2, monstroRapido.getPosY() - 1)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 2, monstroRapido.getPosY() - 1);
                        moveu = true;
                    }
                }
                case 4 -> {
                    if (checkMove(monstroRapido.getPosX() + 2, monstroRapido.getPosY() + 1)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 2, monstroRapido.getPosY() + 1);
                        moveu = true;
                    }
                }
                case 5 -> {
                    if (checkMove(monstroRapido.getPosX() + 2, monstroRapido.getPosY() - 1)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 2, monstroRapido.getPosY() - 1);
                        moveu = true;
                    }
                }
                case 6 -> {
                    if (checkMove(monstroRapido.getPosX() + 1, monstroRapido.getPosY() - 2)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 1, monstroRapido.getPosY() - 2);
                        moveu = true;
                    }
                }
                case 7 -> {
                    if (checkMove(monstroRapido.getPosX() - 1, monstroRapido.getPosY() - 2)) {
                        atualizarPersonagem(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 1, monstroRapido.getPosY() - 2);
                        moveu = true;
                    }
                }
            }
        } while (!moveu);
    }

    private boolean checkMove(int x, int y) {
        return (x <= 14 && x >= 0 && y <= 14 && y >= 0) && !botoesTabuleiro[x][y].temAlguemAqui();
    }
    
    public void criaPocos(){

        Random random = new Random();
        int i = 0;
        int min = 0;
        int max = 14;
        
        while(i < 5){
            
        int pocoX = random.nextInt(max - min + 1) + min;
        int pocoY = random.nextInt(max - min + 1) + min;
        
        if(!botoesTabuleiro[pocoX][pocoY].temAlguemAqui()){
            botoesTabuleiro[pocoX][pocoY].adcionarDestaque(COR_DESTAQUE_POCO);
            i++;
        }
        
        }
    }
        

    public void criaMadeira(){
        
        Random random = new Random();
        int i=0;
        int min = 0;
        int max = 14;
        
        while(i<2){
            
            int madeiraX = random.nextInt(max - min + 1) + min;
            int madeiraY = random.nextInt(max - min + 1) + min;
            
            if(!botoesTabuleiro[madeiraX][madeiraY].temAlguemAqui()){
                botoesTabuleiro[madeiraX][madeiraY].adcionarDestaque(COR_DESTAQUE_MADEIRA);
                i++;
            }
        }
    }
      
}

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
