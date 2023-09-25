package jogo;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Tabuleiro extends JFrame {
    private static final Color COR_DESTAQUE_POCO = Color.BLUE;
    private static final Color COR_DESTAQUE_MADEIRA = Color.GREEN;
    private static final Color COR_DESTAQUE_OURO = Color.YELLOW;
    private static final Color COR_DESTAQUE_JOGADOR = Color.MAGENTA;
    private static final Color COR_DESTAQUE_MONSTRO = Color.BLACK;
    private static final Color COR_DESTAQUE_MONSTRO_RAPIDO = Color.RED;
    private final BotaoTabuleiro[][] botoesTabuleiro = new BotaoTabuleiro[15][15];
    private final MonstroLento monstroLento = new MonstroLento();
    private final MonstroRapido monstroRapido = new MonstroRapido();
    private final JButton botaoCima;
    private final JButton botaoBaixo;
    private final JButton botaoEsquerda;
    private final JButton botaoDireita;
    private final JButton botaoFinalizar;
    private final JButton botaoAtiraFlecha;
    private final JButton botaoCriaFlecha;
    private final JButton botaoLanterna;
    private final JButton botaoRecomecar;
    private final JButton botaoDescartarItem;
    private final JButton botaoDebug;
    private final JLabel campoVida;
    private final JLabel campoArco;
    private final JLabel campoLanterna;
    private final JLabel campoMadeira;
    private final JLabel campoOuro;
    private final JLabel campoFlecha;
    private final Jogador player;
    private boolean debug = false;
    private boolean monstroLentoFlecha = false;
    private boolean monstroRapidoFlecha = false;
    private int nJogadas = 0;

    public Tabuleiro() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Wumpus Game");
        setLayout(null);
        setBounds(165, 70, 900, 750);
        setPreferredSize(new Dimension(950, 700));
        setResizable(false);
        pack();

        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i < 15; i++) {
                botoesTabuleiro[i][j] = new BotaoTabuleiro(i, j, debug);
                add(botoesTabuleiro[i][j]);
            }
        }
        this.player = new Jogador();

        this.campoVida = new JLabel("Vida: " + this.player.getVida());
        this.campoVida.setBounds(650, 200, 100, 30);
        this.campoVida.setOpaque(true);
        this.campoVida.setBackground(Color.GREEN);
        add(this.campoVida);

        this.campoMadeira = new JLabel("Madeira: " + this.player.getQuantidadeMadeira());
        this.campoMadeira.setBounds(650, 225, 100, 30);
        add(this.campoMadeira);

        this.campoFlecha = new JLabel("Flecha: " + this.player.getQuantidadeFlecha());
        this.campoFlecha.setBounds(650, 250, 100, 30);
        add(this.campoFlecha);

        this.campoOuro = new JLabel("Ouro: " + this.player.getQuantidadeOuro());
        this.campoOuro.setBounds(650, 275, 100, 30);
        add(this.campoOuro);

        this.campoArco = new JLabel("Arco: " + this.player.getQuantidadeArco());
        this.campoArco.setBounds(650, 300, 100, 30);
        add(this.campoArco);

        this.campoLanterna = new JLabel("Cargas Lanterna: " + this.player.getBateriaLanterna());
        this.campoLanterna.setBounds(650, 325, 108, 30);
        add(this.campoLanterna);

        botaoDebug = new JButton("Debug");
        botaoDebug.setBounds(750, 605, 100, 30);
        botaoDebug.addActionListener(e -> this.toggleDebug());
        add(botaoDebug);


        botoesTabuleiro[0][0].adicionarPersonagem(player, COR_DESTAQUE_JOGADOR);
        criaPocos();
        criaOuro();
        criaMadeira();
        moverMonstroRapido();
        moverMonstroLento();


        botaoFinalizar = new JButton("Sair");
        botaoFinalizar.setBounds(650, 605, 100, 30);
        botaoFinalizar.addActionListener(e -> System.exit(0));
        add(botaoFinalizar);

        botaoRecomecar = new JButton("Recomeçar o Jogo");
        botaoRecomecar.setBounds(650, 570, 150, 30);
        botaoRecomecar.addActionListener(e -> recomecarJogo());
        add(botaoRecomecar);

        botaoAtiraFlecha = new JButton("Atirar uma flecha");
        botaoAtiraFlecha.setBounds(650, 440, 140, 30);
        botaoAtiraFlecha.addActionListener(e -> flechaDirecao());
        add(botaoAtiraFlecha);

        botaoCriaFlecha = new JButton("Criar Flecha");
        botaoCriaFlecha.setBounds(650, 400, 140, 30);
        botaoCriaFlecha.addActionListener(e -> criarFlecha());
        add(botaoCriaFlecha);

        botaoDescartarItem = new JButton("Descartar Item");
        botaoDescartarItem.setBounds(650, 480, 140, 30);
        botaoDescartarItem.addActionListener(e -> descartarItem());
        add(botaoDescartarItem);

        botaoLanterna = new JButton("Lanterna");
        botaoLanterna.setBounds(650, 360, 140, 30);
        botaoLanterna.addActionListener(e -> usarLanterna());
        add(botaoLanterna);

        botaoCima = new JButton("Cima");
        botaoCima.setBounds(650, 20, 100, 30);
        botaoCima.addActionListener(e -> novaJogada(player.getPosX(), player.getPosY() + 1));
        add(botaoCima);


        botaoBaixo = new JButton("Baixo");
        botaoBaixo.setBounds(650, 60, 100, 30);
        botaoBaixo.addActionListener(e -> novaJogada(player.getPosX(), player.getPosY() - 1));
        add(botaoBaixo);

        botaoDireita = new JButton("Direita");
        botaoDireita.setBounds(650, 100, 100, 30);
        botaoDireita.addActionListener(e -> novaJogada(player.getPosX() + 1, player.getPosY()));
        add(botaoDireita);

        botaoEsquerda = new JButton("Esquerda");
        botaoEsquerda.setBounds(650, 140, 100, 30);
        botaoEsquerda.addActionListener(e -> novaJogada(player.getPosX() - 1, player.getPosY()));
        add(botaoEsquerda);

        setVisible(true);
    }


    private void novaJogada(int novoX, int novoY) {
        System.out.println("--- Inicio da Jogada " + this.nJogadas + " ---");
        System.out.println("Vida: " + player.getVida());
        System.out.println("Jogador: (" + player.getPosX() + ", " + player.getPosY() + ")");
        System.out.println("Monstro Rápido: (" + monstroRapido.getPosX() + ", " + monstroRapido.getPosY() + ")");
        System.out.println("Monstro Lento: (" + monstroLento.getPosX() + ", " + monstroLento.getPosY() + ")");

        if (player.getVida() <= 0) {
            JOptionPane.showMessageDialog(this, "Você Morreu! ", "Derrota", JOptionPane.INFORMATION_MESSAGE);
            int escolha = JOptionPane.showOptionDialog(this, "Deseja recomeçar uma nova partida? ", "Escolha", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Recomeçar", "Sair"}, "Recomeçar");
            if (escolha == JOptionPane.YES_OPTION) {
                recomecarJogo();
            } else {
                System.exit(0);
            }
        }
        if (brisa(novoX, novoY))
            JOptionPane.showMessageDialog(this, "Você está sentindo uma brisa, cuidado", "Brisa", JOptionPane.INFORMATION_MESSAGE);
        
        if(brilhoOuro(novoX, novoY))
            JOptionPane.showMessageDialog(this, "Você está sentindo um brilho radiante", "Brisa", JOptionPane.INFORMATION_MESSAGE);
        
        if (monstroPerto(novoX, novoY))
           JOptionPane.showMessageDialog(this, "Você está sentindo um fedor, cuidado", "Fedor", JOptionPane.INFORMATION_MESSAGE);
        
        if (jogadorPoco(novoX, novoY)) {
            JOptionPane.showMessageDialog(null, "Você caiu em um poço, fim de jogo!", "Game Over", JOptionPane.WARNING_MESSAGE);
            player.setVida(0);
            atualizaCampoVida();
            int escolha = JOptionPane.showOptionDialog(this, "Deseja recomeçar uma nova partida? ", "Escolha", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Recomeçar", "Sair"}, "Recomeçar");
            if (escolha == JOptionPane.YES_OPTION) {
                recomecarJogo();
            } else {
                System.exit(0);
            }
        }
        if (posicaoValida(novoX, novoY)) {
            atualizaPosicaoJogador(novoX, novoY);
            if (player.getVida() > 0) {
                coletaItem();
                atualizaCampoItens();
                if(!monstroLentoFlecha) {
                    moverMonstroLento();
                }
                if(!monstroRapidoFlecha)
                    moverMonstroRapido();
               if (monstroPerto(novoX, novoY))
                    JOptionPane.showMessageDialog(this, "Você está sentindo um fedor, cuidado", "Fedor", JOptionPane.INFORMATION_MESSAGE);
                atualizaCampoVida();
                if (player.getQuantidadeOuro() > 0 && player.getPosX() == 0 && player.getPosY() == 0) {
                    JOptionPane.showMessageDialog(this, "Parabéns! Você ganhou o jogo.", "Vitória", JOptionPane.INFORMATION_MESSAGE);
                    int escolha = JOptionPane.showOptionDialog(this, "Deseja recomeçar uma nova partida? ", "Escolha", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Recomeçar", "Sair"}, "Recomeçar");
                    if (escolha == JOptionPane.YES_OPTION) {
                        recomecarJogo();
                    } else {
                        System.exit(0);
                    }
                }
            } else {
                System.out.println("Jogador sem vida!");
            }
        } else {
            System.out.println("Posicao invalida");
        }
        System.out.println("--- Fim da Jogada " + this.nJogadas + " ---");
        nJogadas++;
    }

    private void criarFlecha() {
        if(player.getQuantidadeMadeira() == 0)
            JOptionPane.showMessageDialog(null, "Você não possui madeiras!!", "Aviso", JOptionPane.WARNING_MESSAGE);
        else {
            player.criarFlecha();
            JOptionPane.showMessageDialog(null, "Você criou uma flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            atualizaCampoItens();
        }
    }

    private void recomecarJogo() {
        dispose();
        SwingUtilities.invokeLater(() -> {
            Tabuleiro novoTabuleiro = new Tabuleiro();
            novoTabuleiro.setVisible(true);
        });
    }

    private void descartarItem() {
        player.descartarItem();
        atualizaCampoItens();
    }


    private void atualizaPosicaoJogador(int xNovo, int yNovo) {
        System.out.println("Movendo jogador para (" + xNovo + ", " + yNovo + ")");
        Personagem p = botoesTabuleiro[xNovo][yNovo].retornarPersonagem();
        if(!monstroLentoFlecha || !monstroRapidoFlecha) {
            if (p != null) {
                System.out.println("Monstro " + p.getClass().getSimpleName() + " em (" + xNovo + ", " + yNovo + ")");
                if (p.getClass().getSimpleName().equals(MonstroLento.class.getSimpleName()) && !monstroLentoFlecha) {
                    System.out.println("Montro lento encontrou o jogador. Fim da partida!");
                    player.setVida(0);
                    atualizaCampoVida();
                    return;
                } else  if (p.getClass().getSimpleName().equals(MonstroRapido.class.getSimpleName()) && !monstroRapidoFlecha){
                    System.out.println("Montro rapido encontrou o jogador. Atualizando vida");
                    player.setVida(player.getVida() - 50);
                    System.out.println("Vida: " + player.getVida());
                    atualizaCampoVida();
                    if (player.getVida() == 0) {
                        System.out.println("Fim da partida!");
                        return;
                    }
                }
            }
        }

        botoesTabuleiro[xNovo][yNovo].adicionarPersonagem(player, COR_DESTAQUE_JOGADOR);
        botoesTabuleiro[player.getPosX()][player.getPosY()].removerPersonagem();

        player.setPosX(xNovo);
        player.setPosY(yNovo);

        System.out.println("Jogador movimentou para (" + xNovo + ", " + yNovo + ")");
    }

    private void atualizarPosicaoMonstro(Personagem m, Color c, int xAntigo, int yAntigo, int xNovo, int yNovo) {
        Personagem p = botoesTabuleiro[xNovo][yNovo].retornarPersonagem();
        if(!monstroLentoFlecha || !monstroRapidoFlecha) {
            if (p != null && p.getClass().getSimpleName().equals(Jogador.class.getSimpleName()) && !monstroLentoFlecha) {
                System.out.println("Monstro encontrou " + p.getClass().getSimpleName() + " em X: " + xNovo + " Y: " + yNovo);
                if (m.getClass().getSimpleName().equals(MonstroLento.class.getSimpleName())) {
                    System.out.println("Montro lento encontrou o jogador. Fim de partida!");
                    player.setVida(0);
                    atualizaCampoVida();
                } else  {
                    System.out.println("Montro rapido encontrou o jogador. Atualizando vida");
                    player.setVida(player.getVida() - 50);
                    atualizaCampoVida();
                    if (player.getVida() == 0) {
                        System.out.println("Montro rapido matou o jogador. Fim da partida!");
                    }
                }
            }
        }
        botoesTabuleiro[xNovo][yNovo].adicionarPersonagem(m, c);

        if (botoesTabuleiro[xAntigo][yAntigo].retornarPersonagem() != null && !botoesTabuleiro[xAntigo][yAntigo].temJogador()) {
            botoesTabuleiro[xAntigo][yAntigo].removerPersonagem();
        }

        m.setPosX(xNovo);
        m.setPosY(yNovo);
        System.out.println("Nova posicao de " + m.getClass().getSimpleName() + " -> x :" + m.getPosX() + " Posicao y: " + m.getPosY());
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
                    if (checkMoveMonstro(monstroLento.getPosY(), monstroLento.getPosY() + 1)) {
                        atualizarPosicaoMonstro(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX, monstroLento.posY + 1);
                        moveu = true;
                    }
                }
                case 1 -> {
                    if (checkMoveMonstro(monstroLento.getPosY(), monstroLento.getPosY() - 1)) {
                        atualizarPosicaoMonstro(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX, monstroLento.posY - 1);
                        moveu = true;
                    }
                }
                case 2 -> {
                    if (checkMoveMonstro(monstroLento.getPosX() - 1, monstroLento.getPosY())) {
                        atualizarPosicaoMonstro(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX - 1, monstroLento.posY);
                        moveu = true;
                    }
                }
                case 3 -> {
                    if (checkMoveMonstro(monstroLento.getPosX() + 1, monstroLento.getPosY())) {
                        atualizarPosicaoMonstro(monstroLento, COR_DESTAQUE_MONSTRO, monstroLento.posX, monstroLento.posY, monstroLento.posX + 1, monstroLento.posY);
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
                    if (checkMoveMonstro(monstroRapido.getPosX() - 1, monstroRapido.getPosY() + 2)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 1, monstroRapido.getPosY() + 2);
                        moveu = true;
                    }
                }
                case 1 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() + 1, monstroRapido.getPosY() + 2)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 1, monstroRapido.getPosY() + 2);
                        moveu = true;
                    }
                }
                case 2 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() - 2, monstroRapido.getPosY() + 1)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 2, monstroRapido.getPosY() + 1);
                        moveu = true;
                    }
                }
                case 3 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() - 2, monstroRapido.getPosY() - 1)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 2, monstroRapido.getPosY() - 1);
                        moveu = true;
                    }
                }
                case 4 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() + 2, monstroRapido.getPosY() + 1)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 2, monstroRapido.getPosY() + 1);
                        moveu = true;
                    }
                }
                case 5 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() + 2, monstroRapido.getPosY() - 1)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 2, monstroRapido.getPosY() - 1);
                        moveu = true;
                    }
                }
                case 6 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() + 1, monstroRapido.getPosY() - 2)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() + 1, monstroRapido.getPosY() - 2);
                        moveu = true;
                    }
                }
                case 7 -> {
                    if (checkMoveMonstro(monstroRapido.getPosX() - 1, monstroRapido.getPosY() - 2)) {
                        atualizarPosicaoMonstro(monstroRapido, COR_DESTAQUE_MONSTRO_RAPIDO, monstroRapido.getPosX(), monstroRapido.getPosY(), monstroRapido.getPosX() - 1, monstroRapido.getPosY() - 2);
                        moveu = true;
                    }
                }
            }
        } while (!moveu);
    }

    private boolean posicaoValida(int x, int y) {
        return (x <= 14 && x >= 0 && y <= 14 && y >= 0);
    }

    private boolean checkMoveMonstro(int x, int y) {
        return (x <= 14 && x >= 0 && y <= 14 && y >= 0) && botoesTabuleiro[x][y].retornarItem() != TipoDeItem.POCO;
    }

    public void criaPocos() {
        Random random = new Random();
        int i = 0;
        int min = 0;
        int max = 14;

        while (i < 5) {
            int pocoX = random.nextInt(max - min + 1) + min;
            int pocoY = random.nextInt(max - min + 1) + min;

            if (!botoesTabuleiro[pocoX][pocoY].temAlguemAqui()) {
                botoesTabuleiro[pocoX][pocoY].adicionarItem(TipoDeItem.POCO, COR_DESTAQUE_POCO);
                System.out.println("Poço " + i + ": " + pocoX + " " + pocoY);
                i++;
            }
        }
    }


    public void criaMadeira() {
        Random random = new Random();
        int i = 0;
        int min = 0;
        int max = 14;

        while (i < 2) {

            int madeiraX = random.nextInt(max - min + 1) + min;
            int madeiraY = random.nextInt(max - min + 1) + min;

            if (!botoesTabuleiro[madeiraX][madeiraY].temAlguemAqui()) {
                botoesTabuleiro[madeiraX][madeiraY].adicionarItem(TipoDeItem.MADEIRA, COR_DESTAQUE_MADEIRA);
                i++;
                System.out.println("Madeira " + i + ": " + madeiraX + " " + madeiraY);
            }
        }
    }

    public void criaOuro() {
        Random random = new Random();
        int i = 0;
        int min = 0;
        int max = 14;

        while (i < 1) {
            int ouroX = random.nextInt(max - min + 1) + min;
            int ouroY = random.nextInt(max - min + 1) + min;

            if (!botoesTabuleiro[ouroX][ouroY].temAlguemAqui()) {
                botoesTabuleiro[ouroX][ouroY].adicionarItem(TipoDeItem.OURO, COR_DESTAQUE_OURO);
                System.out.println("Ouro " + i + ": " + ouroX + " " + ouroY);
                i++;
            }
        }
    }

    private void coletaItem() {
        BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][player.getPosY()];
        TipoDeItem tipoDeItem = destino.retornarItem();
        switch (tipoDeItem) {
            case OURO:
                coletarOuro(destino);
                destino.setBackground(COR_DESTAQUE_JOGADOR);
                break;
            case MADEIRA:
                coletarMadeira(destino);
                destino.setBackground(COR_DESTAQUE_JOGADOR);
                break;
            default:
                break;
        }
    }

    private void coletarOuro(BotaoTabuleiro botaoOuro) {
        boolean adicionou = player.adicionarOuro();
        if (adicionou) {
            botaoOuro.removerItem();
        }
    }

    private void coletarMadeira(BotaoTabuleiro botaoMadeira) {
        boolean adicionou = player.adicionarMadeira();
        if (adicionou) {
            botaoMadeira.removerItem();
        }
    }

    private void atualizaCampoVida() {
        this.campoVida.setText("Vida: " + this.player.getVida());
        if (player.getVida() == 0) {
            this.campoVida.setBackground(Color.RED);
        } else if (player.getVida() < 50) {
            this.campoVida.setBackground(Color.YELLOW);
        } else {
            this.campoVida.setBackground(Color.GREEN);
        }
    }

    private void atualizaCampoItens() {
        this.campoMadeira.setText("Madeira: " + this.player.getQuantidadeMadeira());
        this.campoOuro.setText("Ouro: " + this.player.getQuantidadeOuro());
        this.campoFlecha.setText("Flecha: " + this.player.getQuantidadeFlecha());
        this.campoLanterna.setText("Cargas Lanterna: " + this.player.getBateriaLanterna());
        this.campoArco.setText("Arco: " + this.player.getQuantidadeArco());
    }

    public void toggleDebug() {
        this.debug = !this.debug;
        System.out.println("Debug set to: " + this.debug);

        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i < 15; i++) {
                botoesTabuleiro[i][j].setDebug(this.debug);
            }
        }
    }

    private boolean jogadorPoco(int x, int y) {
        BotaoTabuleiro destino = botoesTabuleiro[x][y];
        if (destino.retornarItem() == TipoDeItem.POCO) {
            if (player.getQuantidadeMadeira() == 0)
                return true;
            else {
                JOptionPane.showMessageDialog(null, "Você caiu em um poço mas tinha madeira (-1 madeira no inventario)", "Game Over", JOptionPane.WARNING_MESSAGE);
                destino.removerItem();
                player.setQuantidadeMadeira(player.getQuantidadeMadeira() - 1);
                return false;
            }
        }
        return false;
    }

    private boolean brisa(int x, int y) {
        if (x - 1 >= 0 && botoesTabuleiro[x - 1][y].retornarItem() == TipoDeItem.POCO) {
            return true;
        }
        if (x + 1 < 15 && botoesTabuleiro[x + 1][y].retornarItem() == TipoDeItem.POCO) {
            return true;
        }
        if (y - 1 >= 0 && botoesTabuleiro[x][y - 1].retornarItem() == TipoDeItem.POCO) {
            return true;
        }
        if (y + 1 < 15 && botoesTabuleiro[x][y + 1].retornarItem() == TipoDeItem.POCO) {
            return true;
        }

        return false;
    }
    private boolean brilhoOuro(int x, int y) {
        if (x - 1 >= 0 && botoesTabuleiro[x - 1][y].retornarItem() == TipoDeItem.OURO) {
            return true;
        }
        if (x + 1 < 15 && botoesTabuleiro[x + 1][y].retornarItem() == TipoDeItem.OURO) {
            return true;
        }
        if (y - 1 >= 0 && botoesTabuleiro[x][y - 1].retornarItem() == TipoDeItem.OURO) {
            return true;
        }
        if (y + 1 < 15 && botoesTabuleiro[x][y + 1].retornarItem() == TipoDeItem.OURO) {
            return true;
        }

        return false;
    }

    private boolean monstroPerto(int x, int y) {
        int alcance = 1;
        if (x - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x - 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento || personagem instanceof MonstroRapido) {
                return true;
            }
        }
        if (x + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x + 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento || personagem instanceof MonstroRapido) {
                return true;
            }
        }
        if (y - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x][y - 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento || personagem instanceof MonstroRapido) {
                return true;
            }
        }
        if (y + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x][y + 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento || personagem instanceof MonstroRapido) {
                return true;
            }
        }
        return false;
    }

    private int monstroLentoPerto(int x, int y) {
        if (x - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x - 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento) {
                return 3;
            }
        }
        if (x + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x + 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento) {
                return 2;
            }
        }
        if (y - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x][y - 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento) {
                return 1;
            }
        }
        if (y + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x][y + 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroLento) {
                return 0;
            }
        }
        return -1;
    }
    private int monstroRapidoPerto(int x, int y) {
        if (x - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x - 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroRapido) {
                return 3;
            }
        }
        if (x + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x + 1][y].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroRapido) {
                return 2;
            }
        }
        if (y - 1 >= 0) {
            Personagem personagem = botoesTabuleiro[x][y - 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroRapido) {
                return 1;
            }
        }
        if (y + 1 < 15) {
            Personagem personagem = botoesTabuleiro[x][y + 1].retornarPersonagem();
            if (personagem != null && personagem instanceof MonstroRapido) {
                return 0;
            }
        }
        return -1;
    }

    public void atirarFlecha(int valor) {
        if (valor == 0) {
            if (monstroLentoPerto(player.getPosX(), player.getPosY()) == 0) {
                monstroLentoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][player.getPosY() + 1];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro Lento!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
                else if (monstroRapidoPerto(player.getPosX(), player.getPosY()) == 0) {
                monstroRapidoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][player.getPosY() + 1];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro rapido!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Você errou a flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            player.setQuantidadeFlecha(player.getQuantidadeFlecha() - 1);
        }
            else if (valor == 1) {
            if (monstroLentoPerto(player.getPosX(), player.getPosY()) == 1) {
                monstroLentoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][player.getPosY() - 1];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro Lento!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else if (monstroRapidoPerto(player.getPosX(), player.getPosY()) == 1) {

                monstroRapidoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][player.getPosY() - 1];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro rapido!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Você errou a flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            player.setQuantidadeFlecha(player.getQuantidadeFlecha() - 1);
        }
            else if (valor == 2) {
            if(monstroLentoPerto(player.getPosX(), player.getPosY()) == 2){
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX() + 1][player.getPosY()];
                destino.setBackground(Color.white);
                monstroLentoFlecha = true;
                JOptionPane.showMessageDialog(null, "Você matou o monstro Lento!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
                else if (monstroRapidoPerto(player.getPosX(), player.getPosY()) == 2) {
                monstroRapidoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX() + 1][player.getPosY()];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro rapido!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Você errou a flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            player.setQuantidadeFlecha(player.getQuantidadeFlecha() - 1);
        }
            else if (valor == 3) {
            if(monstroLentoPerto(player.getPosX(), player.getPosY()) == 3) {
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX() + 1][player.getPosY()];
                destino.setBackground(Color.white);
                monstroLentoFlecha = true;
                JOptionPane.showMessageDialog(null, "Você matou o monstro Lento!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else if (monstroRapidoPerto(player.getPosX(), player.getPosY()) == 3) {
                monstroRapidoFlecha = true;
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX() - 1][player.getPosY()];
                destino.setBackground(Color.white);
                JOptionPane.showMessageDialog(null, "Você matou o monstro rapido!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Você errou a flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
            player.setQuantidadeFlecha(player.getQuantidadeFlecha() - 1);
        }
            else
            JOptionPane.showMessageDialog(null, "Você errou a flecha!!", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

// JOptionPane.showMessageDialog(null, "Você está abaixo do monstro!!", "Aviso", JOptionPane.WARNING_MESSAGE);
    public void flechaDirecao() {
        if(player.getQuantidadeFlecha() < 1)
            JOptionPane.showMessageDialog(null, "Você não possui flechas!!", "Aviso", JOptionPane.WARNING_MESSAGE);
        else if(player.getQuantidadeArco() < 1)
            JOptionPane.showMessageDialog(null, "Você não possui mais arco, reze!!", "Aviso", JOptionPane.WARNING_MESSAGE);
        else {
            String[] opcoesDirecao = {"Cima", "Baixo", "Direita", "Esquerda"};
            String direcaoSelecionada = (String) JOptionPane.showInputDialog(null, "Escolha uma direcao para atirar a flecha:",
                    "Atirar flecha", JOptionPane.QUESTION_MESSAGE, null, opcoesDirecao, opcoesDirecao[0]);
            if (direcaoSelecionada != null) {
                switch (direcaoSelecionada) {
                    case "Cima":
                        atirarFlecha(0);
                        break;
                    case "Baixo":
                        atirarFlecha(1);
                        break;
                    case "Direita":
                        atirarFlecha(2);
                        break;
                    case "Esquerda":
                        atirarFlecha(3);
                        break;
                }
            }
        }
        atualizaCampoItens();
    }
    public void usarLanterna(){
        if(player.getBateriaLanterna() < 1 || player.getQuantidadeLanterna() == 0) {
            JOptionPane.showMessageDialog(null, "Você não possui mais lanterna!!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else {
            String[] opcoesDirecao = {"Cima", "Baixo", "Direita", "Esquerda"};
            String direcaoSelecionada = (String) JOptionPane.showInputDialog(null, "Escolha uma direcao para acender a lanterna:",
                    "Descartar Item", JOptionPane.QUESTION_MESSAGE, null, opcoesDirecao, opcoesDirecao[0]);
            if (direcaoSelecionada != null) {
                switch (direcaoSelecionada) {
                    case "Cima":
                        direcaoLanterna(0);
                        break;
                    case "Baixo":
                        direcaoLanterna(1);
                        break;
                    case "Direita":
                        direcaoLanterna(2);
                        break;
                    case "Esquerda":
                        direcaoLanterna(3);
                        break;
                }
            }
        }
        atualizaCampoItens();
    }

    public void direcaoLanterna(int valor){
        if(valor == 0) {
            for (int i = player.getPosY(); i < 15; i++) {
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][i];
                destino.setDebug(true);
            }
        }
        if(valor == 1) {
            for (int i = player.getPosY(); i >= 0; i--) {
                BotaoTabuleiro destino = botoesTabuleiro[player.getPosX()][i];
                destino.setDebug(true);
            }
        }
        if(valor == 2){
            for(int i = player.getPosX(); i < 15; i++){
                BotaoTabuleiro destino = botoesTabuleiro[i][player.getPosY()];
                destino.setDebug(true);
            }
        }
        if(valor == 3){
            for(int i = player.getPosX(); i >= 0; i--){
                BotaoTabuleiro destino = botoesTabuleiro[i][player.getPosY()];
                destino.setDebug(true);
            }
        }
        player.setBateriaLanterna(player.getBateriaLanterna() - 1);
        atualizaCampoItens();
        if(player.getBateriaLanterna() == 0)
            player.setQuantidadeLanterna(0);
    }
}
