package campominado;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import exceptions.*;

public class JFrameCampo extends JFrame implements Interface{

    JPanel panel;
    JButtonQuadrado[][] quadrado;
    AreaDoCampo c;
    JButton resetBut;
    JButton facilBut;
    JButton medBut;
    JButton difBut;
    JButton custBut;
    JButton rankingBut;

    public JFrameCampo(){
    	System.out.println();
        confIniciais();
    }

    public void hardReset() {

        CampoMinadoMain.hardReset();
        JButtonQuadrado.contMarc=0;
        this.dispose();
        
    }
     
    
    public void confIniciais(){
        campominado.Temporizador.timer.start();
        Temporizador reset = new Temporizador();
        
        //fim do temporizador
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
        this.setSize(Tamanho.colunas * Tamanho.espaco+15, Tamanho.linhas * Tamanho.espaco + Tamanho.margemSuperior + Tamanho.hBarra +80);
        this.setResizable(false);
        this.setVisible(true);
        
        //ranking
        //Icon icon = new ImageIcon("");
		this.rankingBut = new JButton("Ranking");
		this.rankingBut.addActionListener((java.awt.event.ActionEvent evt) -> {
			Ranking save2 = new Ranking();
			save2.lerLista();
		
		});
		this.rankingBut.setSize(Tamanho.espaco+40, Tamanho.espaco);
        this.rankingBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 2 - Tamanho.espaco / 2 - 20, (Tamanho.espaco * Tamanho.linhas)+105);
        this.panel.add(this.rankingBut);
        //
        
        this.resetBut = new JButton("");
        this.resetBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            
            reset.reset();
            hardReset();
            this.reiniciar();
        });
        this.resetBut.setSize(Tamanho.espaco+40, Tamanho.espaco);
        this.resetBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 2 - Tamanho.espaco / 2 - 20, Tamanho.margemSuperior - Tamanho.espaco);
        this.panel.add(this.resetBut);
        resetBut.setIcon(new ImageIcon(getClass().getResource("galinha.png")));


        this.facilBut = new JButton("");												// Campo no modo fácil
        this.facilBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 5;
            Tamanho.linhas = 5;
            Tamanho.minas = 6;
            JButtonQuadrado.contMarc = 0;
            reset.reset();
            this.hardReset();
        });
        this.facilBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.facilBut.setLocation(0, 0);
        this.panel.add(this.facilBut);
        facilBut.setIcon(new ImageIcon(getClass().getResource("zumbi.png")));
        
        
        this.medBut = new JButton("");													// Campo no modo médio
        this.medBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 8;
            Tamanho.linhas = 10;
            Tamanho.minas = 20;
            JButtonQuadrado.contMarc = 0;
            reset.reset();
            this.hardReset();
        });
        this.medBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.medBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4, 0);
        this.panel.add(this.medBut);
        medBut.setIcon(new ImageIcon(getClass().getResource("esqueleto.png")));


        this.difBut = new JButton("");													// Campo no modo difícil
        this.difBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 14;
            Tamanho.linhas = 12;
            Tamanho.minas = 44;
            JButtonQuadrado.contMarc = 0;
            reset.reset();
            this.hardReset();
        });
        this.difBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.difBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4 * 2, 0);
        this.panel.add(this.difBut);
        difBut.setIcon(new ImageIcon(getClass().getResource("creeper.png")));
      
        

        this.custBut = new JButton("");
        this.custBut.addActionListener((java.awt.event.ActionEvent evt) -> {            				//	Iniciar o campo customizado
            int l = Integer.parseInt(JOptionPane.showInputDialog("Quantas linhas? \n Maximo: 14"));
            Tamanho.linhas = l;
              
            int c = Integer.parseInt(JOptionPane.showInputDialog("Quantas colunas? \n Maximo: 30"));
            Tamanho.colunas = c;

            int m = Integer.parseInt(JOptionPane.showInputDialog("Quantas minas? \n Maximo: "+(m=(l*c)-1)));
            Tamanho.minas = m;

            if (Tamanho.linhas > 14 || Tamanho.colunas > 30 || Tamanho.minas > (m=(l*c)-1)) {
                try {
                	
                	throw new LimiteLinhaColunaMinasExcedido(Tamanho.linhas, Tamanho.colunas, Tamanho.minas); //Excecao para caso, no tabuleiro customizado, o tamanho, a coluna ou as minas ultrapassem o limite permitido
                	}
                	catch(ArrayIndexOutOfBoundsException | LimiteLinhaColunaMinasExcedido e) {
                		JOptionPane.showMessageDialog(null,"Algum parametro foi passado de maneira equivocada.\nTente novamente");
                        //voltar para o modo f�cil (padr�o)
                        Tamanho.linhas = 5;
                        Tamanho.colunas = 5;
                        Tamanho.minas = 5;
                	}
                	catch(Exception e) {
                		System.err.println("Houve um erro");
                		
                	}
                }
            
            JButtonQuadrado.contMarc=0;
            reset.reset();
            this.hardReset();
            
        });
        this.custBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.custBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4 * 3, 0);
        this.panel.add(this.custBut);
        custBut.setIcon(new ImageIcon(getClass().getResource("enderman.png")));

        
        

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
    }

    public void checkEstado() {
        if (this.c.vitoria()) {
            Ranking save1 = new Ranking();
            Temporizador reset = new Temporizador();
			campominado.Temporizador.timer.stop();
            JOptionPane.showMessageDialog(null,"VOCE VENCEU!! :D");
            save1.salvar();
            reset.reset();
            this.desativaBotoes();
            hardReset();
        }

        if (this.c.derrota()) {
            
			campominado.Temporizador.timer.stop();
            Temporizador reset1 = new Temporizador();
            
            reset1.reset();
            
        	//this.desativaBotoes();
        	JOptionPane.showMessageDialog(null,"Voce perdeu!! :(");
            hardReset();
            
            

        }
    }

	@Override
	public void marcar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revela(String cod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPos(int linhas, int colunas) {
		// TODO Auto-generated method stub
		
	}
   
}
