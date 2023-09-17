
package jogo;

public class ItensTabuleiro {
    int ouroX;
    int ouroY;
    int madeiraX;
    int madeiraY;
    int pocoX;
    int pocoY;

    public ItensTabuleiro(int madeiraX, int madeiraY, int pocoX, int pocoY, int ouroX, int ouroY)
    {
        this.madeiraX = madeiraX;
        this.madeiraY = madeiraY;
        this.pocoX = pocoX;
        this.pocoY = pocoY;
        this.ouroX = ouroX;
        this.ouroY = ouroY;
    }
    public int getPocoX(){ return pocoX; }
    public int getPocoY(){ return pocoY; }
    public int getMadeiraX(){ return madeiraX; }
    public int getMadeiraY(){ return madeiraY; }
    public int getOuroX(){ return ouroX; }
    public int getOuroY(){ return ouroY; }
    
}
