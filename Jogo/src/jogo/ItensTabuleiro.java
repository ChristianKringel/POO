package jogo;

public class ItensTabuleiro {
    int ouroX;
    int ouroY;
    int madeiraX;
    int madeiraY;
    int pocoX;
    int pocoY;

    public ItensTabuleiro(int madeiraX, int madeiraY, int pocoX, int pocoY)
    {
        this.madeiraX = madeiraX;
        this.madeiraY = madeiraY;
        this.pocoX = pocoX;
        this.pocoY = pocoY;
    }
    public int getPocoX(){ return pocoX; }
    public int getPocoY(){ return pocoY; }
    public int getMadeiraX(){ return madeiraX; }
    public int getMadeiraY(){ return madeiraY; }
}
