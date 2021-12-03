
package campominado;

import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import celula.Quadrado;

public final class JButtonQuadrado extends JButton{

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
        if (this.espacoLogica.getBandeira()) { //adicionar a animação da bandeira com a imagem
            try {
                Image img = ImageIO.read(getClass().getResource(""));
                img = img.getScaledInstance(Tamanho.espaco, Tamanho.espaco, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                this.setText("M");
                System.out.println("ERRO!");
            }
        } else {
            this.setIcon(null);
            this.setText("");
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
