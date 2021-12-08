package campominado;

public class CampoMinadoMain {

    public static void main(String[] args) { //apenas para testar o jogo
        JFrameCampo i = new JFrameCampo();
        Temporizador tempo = new Temporizador(); // Instanciação e inicialização do temporizador
     	tempo.run(999);	
    }

    public static void hardReset(){
    	
        JFrameCampo i = new JFrameCampo();	
    }

}