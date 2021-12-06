package exceptions;

public class LimiteLinhaColunaMinasExcedido extends Exception {

	private int linha;
	private int coluna;
	private int minas;
	
	public LimiteLinhaColunaMinasExcedido(int linha, int coluna, int minas) {
		super();
		this.linha = linha;
		this.coluna = coluna;
		this.minas = minas;
	}
}	
