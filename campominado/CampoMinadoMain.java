package campominado;

import javax.swing.JPanel;

public class CampoMinadoMain {
    
    JPanel panel;
    public static void main(String[] args) { 
        JFrameCampo campo = new JFrameCampo();	 //iniciar do zero, para os frames serem resetados, juntamente com o tempo
        Temporizador Temporizador = new Temporizador(campo);
    }

    public static void hardReset(){
        
        JFrameCampo campo = new JFrameCampo();	
        Temporizador Temporizador = new Temporizador(campo);
            
        }
    }
    
    /*
    Proximidade:
    0 - pedra
    1 - carvao
    2 - ferro
    3 - ouro
    4 - diamante
    5 - esmeralda
    6 - lapislazuli
    7 - redstone
    8 - ametista
    
    Dificuldade:
    Facil: Zumbi
    Medio: Esqueleto
    Dificil: Creeper
    Customizado: Enderman
    
    Botão reiniciar: Galinha
    */
