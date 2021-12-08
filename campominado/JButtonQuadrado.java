package campominado;

import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import celula.Quadrado;
import exceptions.*;
//import recordes.*; // talvez, poderá ser necessária a criação de um pacote para os recordes (????????)

public class JButtonQuadrado extends JButton{
	
	static int contMarc;
    int linha;
    int coluna;
    AreaDoCampo area;
    Quadrado espacoLogica;
    JFrameCampo campoGrafico;
    String text;

    public JButtonQuadrado(AreaDoCampo a, JFrameCampo cg) {
        this.campoGrafico = cg;
        this.text = "";
        this.setText(text);
        this.area = a;
        this.addActionListener((java.awt.event.ActionEvent evt) -> {
        botaoPressionado(false);
        });

        this.addMouseListener(new java.awt.event.MouseAdapter() { //comunicação com usuário
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
      //  Temporizador.run(999);	 //Tentativa de fazer a contagem resetar... mas também sem sucesso
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

        System.out.println("linha: " + linha + " coluna: " + coluna);
                                                                    //Retorna numVizinhosMinados se quadrado NAO POSSUI MINA
        int numVizinhosMinados = espacoLogica.clicar();
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
        boolean estaMarcado = this.espacoLogica.marcar();
        if (this.espacoLogica.getBandeira()) { //adicionar a animação da bandeira com a imagem posteriormente
            try {
                Image img = ImageIO.read(getClass().getResource(""));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
            	contMarc = contMarc + 1;
                this.setText("M");
                System.out.println(JButtonQuadrado.contMarc);
            }
        } else {
        	contMarc = contMarc - 1;
            this.setIcon(null);
            this.setText("");
        }
        if (contMarc > Tamanho.minas) {		// Se o contador de marcação for maior que a quantidade de minas
        	try {
        		contMarc = contMarc * 1;
        		this.setIcon(null);
        		this.setText("");
        		
        		throw new LimiteBandeirasExcedido(JButtonQuadrado.contMarc);				// Exceção lançada para alertar sobre o limite de bandeiras
        	}
        	catch (ArrayIndexOutOfBoundsException | LimiteBandeirasExcedido e) {
        		JOptionPane.showMessageDialog(null,"O numero de bandeiras marcadas excede o número de bombas!");
        		System.err.println("O numero de bandeiras marcadas excede o número de bombas!");
        	} catch (Exception e) {
        		System.err.println("Houve um erro");
			}
        }
    }

    public void setPos(int linhas, int colunas) {
        this.linha = linhas;
        this.coluna = colunas;
        this.espacoLogica = area.getQuadrado(linhas, colunas);
    }

    public void revela(String cod) {

        if (cod.equals("-1")) {  //adicionar a imagem da mina no local que ela aparecer
            try {
                Image img = ImageIO.read(getClass().getResource(""));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText("-1");
                System.out.println("ERRO!");
            }
        } else {
            this.setText(cod);
        }
        this.setEnabled(false);
    }

}