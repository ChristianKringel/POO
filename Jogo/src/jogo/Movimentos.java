package jogo;

public class Movimentos {

    private static int tam = 15;
    private int p1x;
    private int p1y;
    private int m1x;
    private int m1y;
    private int m2x;
    private int m2y;

    public Movimentos(int posx, int posy) {
        this.p1x = posx;
        this.p1y = posy;
    }

    public int getM1x() {
        return m1x;
    }

    public void setM1x(int m1x) {
        this.m1x = m1x;
    }
    public static int getTam() {
        return tam;
    }

    public static void setTam(int aTam) {
        tam = aTam;
    }

    public int getP1x() {
        return p1x;
    }

    public void setP1x(int p1x) {
        this.p1x = p1x;
    }

    public int getP1y() {
        return p1y;
    }

    public void setP1y(int p1y) {
        this.p1y = p1y;
    }

    public int getM1y() {
        return m1y;
    }

    public void setM1y(int m1y) {
        this.m1y = m1y;
    }

    public int getM2x() {
        return m2x;
    }

    public void setM2x(int m2x) {
        this.m2x = m2x;
    }

    public int getM2y() {
        return m2y;
    }

    public void setM2y(int m2y) {
        this.m2y = m2y;
    }
    // Add getters and setters for other variables

    public int Personagem(int posx, int posy) {
        int vida = 100;

        if (posx == getM1x() && posy == getM1y()) {
            vida = 0;
            return -1;
        } else if (posx != getM1x() || posy != getM1y()) {
            return 1;
        }

        if (posx == getM2x() && posy == getM2y()) {
            vida = 50;
            return vida;
        } else {
            return vida;
        }
    }

    public void Locomoverx(Movimentos p, int pos) {
        int aux;

        do {
            switch (pos) {
                case 1 -> {
                    if(p.getM1y() < tam - 1)
                        p.setP1y(p.getP1y() + 1);
                    break;
                }
                case 2 -> {
                    if(p.getP1y() > 0)
                        p.setP1y(p.getP1y() + 1);
                }
                case 3 -> {
                    if (p.getP1x() > 0)
                        p.setP1x(p.getP1x() - 1); // Should be -1, not +1
                    break;
                }
                case 4 -> {
                    if (p.getP1x() < tam - 1)
                        p.setP1x(p.getP1x() + 1);
                    break;
                }
                default -> {
                    System.out.println("Direcao invalida");
                }
            }
        } while (p.getP1x() >= tam || p.getP1y() >= tam || p.getP1x() < 0 || p.getP1y() < 0);
    }
}