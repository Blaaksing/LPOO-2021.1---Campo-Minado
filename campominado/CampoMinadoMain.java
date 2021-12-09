package campominado;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

public class CampoMinadoMain {
    //metodo para implementar o temporizador
    private JLabel interfaceTempo;
    private Timer timer;
    private int contador = 0;


    public void init(JPanel cronometro){
        
        interfaceTempo = new JLabel("00:00");
        Dimension size = interfaceTempo.getPreferredSize();
        interfaceTempo.setBounds(10, 65, size.width, size.height);
        cronometro.add(interfaceTempo, BorderLayout.CENTER);
        
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask(){
                @Override
                public void run(){
                    contador++;
                    int segundos = contador % 60;
                    int minutos = contador / 60;
                    minutos %= 60;
                    interfaceTempo.setText(String.format("%02d:%02d", minutos , segundos));
                }
            }, 1000, 1000);
            
        
    }
    public static void main(String[] args) { 
        hardReset(); //iniciar do zero, para os frames serem resetados, juntamente com o tempo
    }

    public static void hardReset(){
        
    	
        JFrameCampo i = new JFrameCampo();	
        CampoMinadoMain tempo = new CampoMinadoMain();
        EventQueue.invokeLater( new Runnable() {
            
             @Override
             public void run() {
                 tempo.init(i.panel);
                 
                }
                
            }
            );
            
        }
    }
    
    
