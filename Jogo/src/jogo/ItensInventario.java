
package jogo;

public class ItensInventario {
    private int qMadeira;
    private int qOuro;
    private int qFlecha;
    private int lanterna = 2;

    public ItensInventario(int qMadeira, int qOuro, int qFlecha, int lanterna)
    {
        this.qMadeira = qMadeira;
        this. qOuro = qOuro;
        this.qFlecha = qFlecha;
        this.lanterna = lanterna;
    }

    public int getqMadeira(){ return qMadeira;}
    public int getqOuro(){ return qOuro; }
    public int getqFlecha(){ return qFlecha; }
    public int getLanterna(){ return lanterna; }
    public void setqOuro(int ouro){ this.qOuro = ouro; }
    public void setqMadeira(int madeira) { this.qMadeira = madeira; }
    public void setqFlecha(int qFlecha) {
        this.qFlecha = qFlecha;
    }
    public void setLanterna() { if(lanterna> 0) lanterna--; }

}
