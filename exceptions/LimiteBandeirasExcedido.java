package exceptions;

public class LimiteBandeirasExcedido extends Exception {

	private int bandeira;
	
	public LimiteBandeirasExcedido(int bandeira) {

		super();
		this.bandeira = bandeira;
	}
}