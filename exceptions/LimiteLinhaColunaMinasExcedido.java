package exceptions;

public class LimiteLinhaColunaMinasExcedido extends Exception {

    private int linha;
    private int coluna;
    private int minas;
    
    public int getLinha() {
        return linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public int getColuna() {
        return coluna;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public int getMinas() {
        return minas;
    }
    public void setMinas(int minas) {
        this.minas = minas;
    }
    public LimiteLinhaColunaMinasExcedido(int linha, int coluna, int minas) {
        super("ALGUM PARAMETRO FOI PASSADO DE MANEIRA ERR�NEA. \nTENTE NOVAMENTE!");
        this.linha = linha;
        this.coluna = coluna;
        this.minas = minas;
    }
}    
