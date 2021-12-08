package campominado;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import exceptions.*;

public class JFrameCampo extends JFrame {

    JPanel panel;
    JButtonQuadrado[][] quadrado;
    AreaDoCampo c;
    JButton resetBut;
    JButton facilBut;
    JButton medBut;
    JButton difBut;
    JButton custBut;
 // JButton recordeBut;

    public JFrameCampo() {
    	System.out.println();
        confIniciais();
    }

    public void hardReset() {
        CampoMinadoMain.hardReset();
        this.dispose();
        
    }
    
    
    private void confIniciais() {
    	
        this.c = new AreaDoCampo();
        c.adicionarMinas();
        this.panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        quadrado = new JButtonQuadrado[Tamanho.linhas][Tamanho.colunas];

        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                quadrado[i][j] = new JButtonQuadrado(this.c, this);
                c.getQuadrado(i, j).setButton(quadrado[i][j]);
                quadrado[i][j].setPos(i, j);
                quadrado[i][j].setSize(Tamanho.espaco, Tamanho.espaco);
                quadrado[i][j].setFocusable(false);
                quadrado[i][j].setLocation(Tamanho.espaco * j, Tamanho.espaco * i + Tamanho.margemSuperior);
                panel.add(quadrado[i][j]);

            }
        }
        //tamanho da interface
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Tamanho.colunas * Tamanho.espaco+15, Tamanho.linhas * Tamanho.espaco + Tamanho.margemSuperior + Tamanho.hBarra +17);
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("Tempo: "+Temporizador.seconds+" Marcações: "+JButtonQuadrado.contMarc+"/"+Tamanho.minas+"Bombas"); // Tentativa de fazer um título para o game, exibindo as informações do temporizador ao vivo e o numero de marcações, porém sem sucesso (ainda)

      /*  PROTOTIPO MUITO INICIAL DE MÉTODO PARA ARMAZENAR OS RECORDES
       
        this.recordeBut = new JButton("Recordes");
       
        this.recordeBut.addActionListener((java.awt.event.ActionEvent evt) -> {
        	
        	this.panel.add();
        });
        
       */ 
        
        this.resetBut = new JButton("Reiniciar");
        this.resetBut.addActionListener((java.awt.event.ActionEvent evt) -> {

            this.reiniciar();
        });
        this.resetBut.setSize(Tamanho.espaco+40, Tamanho.espaco);
        this.resetBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 2 - Tamanho.espaco / 2 - 20, Tamanho.margemSuperior - Tamanho.espaco);
        this.panel.add(this.resetBut);


        this.facilBut = new JButton("F");												// Campo no modo fácil
        this.facilBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 5;
            Tamanho.linhas = 5;
            Tamanho.minas = 6;
            JButtonQuadrado.contMarc = 0;
        //    Temporizador.seconds = 0;					// O temporizador reinicia para o zero, mas ainda não consegui implementar um meio dele iniciar a contagem.
            this.hardReset();
        });
        this.facilBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.facilBut.setLocation(0, 0);
        this.panel.add(this.facilBut);

        
        this.medBut = new JButton("M");													// Campo no modo médio
        this.medBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 8;
            Tamanho.linhas = 10;
            Tamanho.minas = 20;
            JButtonQuadrado.contMarc = 0;
         //   Temporizador.seconds = 0;					// O temporizador reinicia para o zero, mas ainda não consegui implementar um meio dele iniciar a contagem.
            this.hardReset();
        });
        this.medBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.medBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4, 0);
        this.panel.add(this.medBut);


        this.difBut = new JButton("D");													// Campo no modo difícil
        this.difBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 14;
            Tamanho.linhas = 12;
            Tamanho.minas = 44;
            JButtonQuadrado.contMarc = 0;
         //   Temporizador.seconds = 0;					// O temporizador reinicia para o zero, mas ainda não consegui implementar um meio dele iniciar a contagem.
            this.hardReset();
        });
        this.difBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.difBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4 * 2, 0);
        this.panel.add(this.difBut);
      
        
        this.custBut = new JButton("C");
        this.custBut.addActionListener((java.awt.event.ActionEvent evt) -> {            				//	Iniciar o campo customizado
            int l = Integer.parseInt(JOptionPane.showInputDialog("Quantas linhas? \n Máximo: 14"));
            Tamanho.linhas = l;
              
            int c = Integer.parseInt(JOptionPane.showInputDialog("Quantas colunas? \n Máximo: 30"));
            Tamanho.colunas = c;

            int m = Integer.parseInt(JOptionPane.showInputDialog("Quantas minas? \n Máximo: "+(m=(l*c)-1)));
            Tamanho.minas = m;

            if (Tamanho.linhas > 14 || Tamanho.colunas > 30 || Tamanho.minas > (m=(l*c)-1)) {
                try {
                	
                	throw new LimiteLinhaColunaMinasExcedido(Tamanho.linhas, Tamanho.colunas, Tamanho.minas); //Exceção para caso, no tabuleiro customizado, o tamanho, a coluna ou as minas ultrapassem o limite permitido
                	}
                	catch(ArrayIndexOutOfBoundsException | LimiteLinhaColunaMinasExcedido e) {
                		JOptionPane.showMessageDialog(null,"Algum parâmetro foi passado de maneira equivocada.\n Feche o jogo e tente novamente");
                		System.err.println("ALGUM PARÂMETRO FOI PASSADO DE MANEIRA ERRÔNEA. \n TENTE NOVAMENTE!");
                	}
                	catch(Exception e) {
                		System.err.println("Houve um erro");
                		
                	}
                }
            
            
            JButtonQuadrado.contMarc=0;
          //  Temporizador.seconds = 0;					// O temporizador reinicia para o zero, mas ainda não consegui implementar um meio dele iniciar a contagem.
            this.hardReset();
            
        });
        this.custBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.custBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4 * 3, 0);
        this.panel.add(this.custBut);

    }

    public void reiniciar() {
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                quadrado[i][j].reiniciar();
            }
        }
        this.c.adicionarMinas();
    }

    public void revelarMinas() {
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                if (quadrado[i][j].espacoLogica.getBomba()) {
                    quadrado[i][j].revela("-1");
                }
            }
        }
    }

    public void desativaBotoes() {
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                quadrado[i][j].setEnabled(false);
            }
        } 
        Temporizador.stop();
    }

    public void checkEstado() {
        System.out.println("Verificando se Ganhou ou Perdeu");
        if (this.c.vitoria()) {
            this.desativaBotoes();
            JOptionPane.showMessageDialog(null,"VOCÊ VENCEU!! :D");
        }

        if (this.c.derrota()) {
        	this.desativaBotoes();
        	JOptionPane.showMessageDialog(null,"Você perdeu!! :(");
        }
    }

}
