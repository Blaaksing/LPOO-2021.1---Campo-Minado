package exceptions;

public class LimiteBandeirasExcedido extends Exception {

    private int bandeira;
    
    public int getBandeira() {
        return bandeira;
    }
    public void setBandeira(int bandeira) {
        this.bandeira = bandeira;
    }
    public LimiteBandeirasExcedido(int bandeira) {

        super("O numero de bandeiras marcadas excede o numero de bombas!");
        this.bandeira = bandeira;
    }
}
