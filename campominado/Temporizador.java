package campominado;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class Temporizador implements ActionListener{
JFrame frame ;

private static JLabel cronometro = new JLabel();
private static int contador = 0;
private static int segundos = 0;
private static int minutos = 0;
protected static String segundosLabel = String.format("%02d", segundos);
protected static String minutosLabel = String.format("%02d", minutos);
 
public static Timer timer = new Timer(1000, new ActionListener() {
  
public void actionPerformed(ActionEvent e) {
   
    contador = contador+1000;
    minutos = (contador/60000) % 60;
    segundos = (contador/1000) % 60;
    segundosLabel = String.format("%02d", segundos);
    minutosLabel = String.format("%02d", minutos);
    cronometro.setText(minutosLabel+":"+segundosLabel);
}
  
});
 
public Temporizador(JFrame frame ){
	 
    this.frame = frame;
    cronometro.setText(minutosLabel+":"+segundosLabel);
    cronometro.setBounds((50*Tamanho.colunas)/2 - (50)/2 +70, 70,80,40);
    cronometro.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.green));
    cronometro.setOpaque(true);
    cronometro.setLocation(0, 55);

    frame.add(cronometro);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setVisible(true);
}
 
public void reset() {
    timer.stop();
    contador = 0;
    segundos = 0;
    minutos= 0;
    segundosLabel = String.format("%02d", segundos);
    minutosLabel = String.format("%02d", minutos);    
}

public Temporizador (){
    
}

@Override
public void actionPerformed(ActionEvent e) {
    
    
}

}
