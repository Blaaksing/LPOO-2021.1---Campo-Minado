
package campominado;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JFrameCampo extends JFrame {

    JPanel panel;
    JButtonQuadrado[][] quadrado;
    AreaDoCampo c;
    JButton resetBut;

    JButton facilBut;
    JButton medBut;
    JButton difBut;
    JButton custBut;

    public JFrameCampo() {
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
        this.setResizable(false);
        this.setVisible(true);

        //adicionar os botões das dificuldades e para reiniciar o jogo

        this.resetBut = new JButton("Reiniciar");
        this.resetBut.addActionListener((java.awt.event.ActionEvent evt) -> {

            this.reiniciar();
        });
        this.resetBut.setSize(Tamanho.espaco+40, Tamanho.espaco);
        this.resetBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 2 - Tamanho.espaco / 2 - 20, Tamanho.margemSuperior - Tamanho.espaco);
        this.panel.add(this.resetBut);
        

        this.facilBut = new JButton("F");
        this.facilBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 6;
            Tamanho.linhas = 6;
            Tamanho.minas = 6;
            this.hardReset();
        });
        this.facilBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.facilBut.setLocation(0, 0);
        this.panel.add(this.facilBut);

        this.medBut = new JButton("M");
        this.medBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 10;
            Tamanho.linhas = 10;
            Tamanho.minas = 15;
            this.hardReset();
        });
        this.medBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.medBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4, 0);
        this.panel.add(this.medBut);

        this.difBut = new JButton("D");
        this.difBut.addActionListener((java.awt.event.ActionEvent evt) -> {
            Tamanho.colunas = 16;
            Tamanho.linhas = 14;
            Tamanho.minas = 40;
            this.hardReset();

        });
        this.difBut.setSize((Tamanho.espaco * Tamanho.colunas) / 4, Tamanho.espaco);
        this.difBut.setLocation((Tamanho.espaco * Tamanho.colunas) / 4 * 2, 0);
        this.panel.add(this.difBut);

        this.custBut = new JButton("C");
        this.custBut.addActionListener((java.awt.event.ActionEvent evt) -> {            
            int l = Integer.parseInt(JOptionPane.showInputDialog("Quantas linhas? "));
            Tamanho.linhas = l;
            
            int c = Integer.parseInt(JOptionPane.showInputDialog("Quantas colunas? "));
            Tamanho.colunas = c;
            
            int m = Integer.parseInt(JOptionPane.showInputDialog("Quantas minas? "));
            Tamanho.minas = m;
            
            this.hardReset();
            
            // ideia de exceção: if(l>=15 || c>30)
        
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
    }

    public void checkEstado() {
        System.out.println("Verificando se Ganhou ou Perdeu");
        if (this.c.vitoria()) {
            System.out.println("Venceu");
            this.desativaBotoes();
        }

        if (this.c.derrota()) {
            System.out.println("Perdeu");
            this.desativaBotoes();
        }

    }

}
