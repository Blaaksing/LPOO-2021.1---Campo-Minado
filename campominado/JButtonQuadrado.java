package campominado;

import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import celula.Quadrado;
import exceptions.*;

public class JButtonQuadrado extends JButton implements Interface{
	
	static int contMarc;
    int linha;
    int coluna;
    AreaDoCampo area;
    Quadrado espacoLogica;
    JFrameCampo campoGrafico;
    String text;
    private int numVizinhosMinados;

    public JButtonQuadrado(AreaDoCampo a, JFrameCampo cg) {
        this.campoGrafico = cg;
        this.text = "";
        this.setText(text);
        this.area = a;
        this.addActionListener((java.awt.event.ActionEvent evt) -> {
        botaoPressionado(false);
        });

        this.addMouseListener(new java.awt.event.MouseAdapter() { //comunica��o com o usu�rio
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    botaoPressionado(true);
                };
            }
        });
    }

    public void reiniciar() {
        this.espacoLogica.reiniciar();
        this.text = "";
        this.setText(text);
        this.setEnabled(true);
        this.setIcon(null);
      
        JButtonQuadrado.contMarc=0;
     	
    }

    private void botaoPressionado(boolean mouseBotaoDireito) {
        if (!mouseBotaoDireito) { //botao esquerdo
            if (!this.espacoLogica.getBandeira()) {
                this.clicar();
            }
        } else {
            this.marcar();
        }
        this.campoGrafico.checkEstado();
    }
    
    public void clicar() {

                                                                    //Retorna numVizinhosMinados se quadrado NAO POSSUI MINA
        numVizinhosMinados = espacoLogica.clicar();
        if (this.espacoLogica.getBomba()) {
            this.campoGrafico.revelarMinas();
        }
        
        if (numVizinhosMinados == 0) {
            for (Quadrado vizinho : espacoLogica.getVizinhos()) {
                if (!vizinho.getAtivado()) {
                    vizinho.button.clicar();
                }
            }
            //return;
        }
        this.text = Integer.toString(numVizinhosMinados);
        this.revela(this.text);
    }
    
    public void marcar() {
        if (this.espacoLogica.getAtivado()) {
            return;
        }
        this.espacoLogica.marcar();
        if (this.espacoLogica.getBandeira()) { //adicionar a animacao da bandeira com a imagem posteriormente
            try {
                Image img = ImageIO.read(getClass().getResource("Bandeira.png"));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
            	contMarc = contMarc + 1;
                this.setText("M");
                System.out.println(JButtonQuadrado.contMarc);
            }
        } else {
        	contMarc = contMarc - 1;
            try {
                Image img = ImageIO.read(getClass().getResource("terra.jpg"));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText(" ");
                System.out.println(JButtonQuadrado.contMarc);
            }
            this.setText("");
        }
        if (contMarc > Tamanho.minas) {		// Se o contador de marcacao for maior que a quantidade de minas
        	try {
        		contMarc = contMarc * 1;
        		
        		throw new LimiteBandeirasExcedido(JButtonQuadrado.contMarc);				// Excecao lancada para alertar sobre o limite de bandeiras
        	}
        	catch (ArrayIndexOutOfBoundsException | LimiteBandeirasExcedido e) {
        		//JOptionPane.showMessageDialog(null,"O numero de bandeiras marcadas excede o numero de bombas!");
        	} catch (Exception e) {
        		System.err.println("Houve um erro");
			}
        }
    }

    public void setPos(int linhas, int colunas) {
        this.linha = linhas;
        this.coluna = colunas;
        this.espacoLogica = area.getQuadrado(linhas, colunas);
        try {
            Image img = ImageIO.read(getClass().getResource("terra.jpg"));
            img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            this.setText(" ");
            System.out.println(JButtonQuadrado.contMarc);
        }
    }

    public void revela(String cod) {

        if (cod.equals("-1")) {  //adicionar a imagem da mina no local que ela aparecer
            try {
                Image img = ImageIO.read(getClass().getResource("bomba.png"));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText("-1");
                System.out.println("ERRO!");
            }
        } else {
            if (numVizinhosMinados == 0) {
                try {
                    Image img = ImageIO.read(getClass().getResource("pedra.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            }else if (numVizinhosMinados == 1){
                try {
                    Image img = ImageIO.read(getClass().getResource("carvao.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 2){
                try {
                    Image img = ImageIO.read(getClass().getResource("ferro.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }

            } else if (numVizinhosMinados == 3){
                try {
                    Image img = ImageIO.read(getClass().getResource("ouro.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 4){
                try {
                    Image img = ImageIO.read(getClass().getResource("diamante.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 5){
                try {
                    Image img = ImageIO.read(getClass().getResource("esmeralda.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 6){
                try {
                    Image img = ImageIO.read(getClass().getResource("lapislazuli.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 7){
                try {
                    Image img = ImageIO.read(getClass().getResource("redstone.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else if (numVizinhosMinados == 8){
                try {
                    Image img = ImageIO.read(getClass().getResource("ametista.png"));
                    img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                    this.setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    this.setText(" ");
                    System.out.println(JButtonQuadrado.contMarc);
                }
            } else {
                this.setText(cod);
                this.setIcon(null);
            }
            
        }
        //this.setEnabled(false);
    }

	@Override
	public void confIniciais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revelarMinas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desativaBotoes() {
		// TODO Auto-generated method stub
		
	}

}