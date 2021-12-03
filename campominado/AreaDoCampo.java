
package campominado;

import java.util.Random;

import celula.Quadrado;

public class AreaDoCampo {
    Quadrado[][] matriz;
    
    
    public AreaDoCampo(){                                       //criar a matriz e adicionar os quadrados
        matriz = new Quadrado[Tamanho.linhas][Tamanho.colunas];
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                matriz[i][j] = new Quadrado();
            }
        }
                                                       
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                if (i > 0){                                      //condições para as bordas da area, onde não possui quadrado
                    if (j > 0) matriz[i][j].adicionarVizinhos(matriz[i-1][j-1]);
                    matriz[i][j].adicionarVizinhos(matriz[i-1][j]);
                    if (j < Tamanho.colunas-1) matriz[i][j].adicionarVizinhos(matriz[i-1][j+1]);
                }
                
                if (j > 0) matriz[i][j].adicionarVizinhos(matriz[i][j-1]);                
                if (j < Tamanho.colunas-1)matriz[i][j].adicionarVizinhos(matriz[i][j+1]);
                
                if(i < Tamanho.linhas -1){
                    if (j > 0)matriz[i][j].adicionarVizinhos(matriz[i+1][j-1]);
                    matriz[i][j].adicionarVizinhos(matriz[i+1][j]);
                    if (j < Tamanho.colunas-1)matriz[i][j].adicionarVizinhos(matriz[i+1][j+1]);
                }
            }
        }
        
    }
    
    public void adicionarMinas(){                       //adicionar as minas aleatoriamente na matriz
        int n = Tamanho.minas;
        Random rand = new Random();
        while (n > 0){            
            int l = rand.nextInt(Tamanho.linhas); 
            int c = rand.nextInt(Tamanho.colunas);             
            if (matriz[l][c].minar()){
                n--;
            }            
        }
        System.out.println(this);
        
    }
    
    public int clicar(int linha, int coluna){       //identificar a coordenada do quadrado clicado
        return matriz[linha][coluna].clicar();
    }
    
    public boolean vitoria(){        
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                if (!matriz[i][j].fimDeJogo()) return false;
            }            
        }
        return true;
    }
    
    public boolean derrota(){        
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                if (matriz[i][j].getAtivado() && matriz[i][j].getBomba()) return true;
            }            
        }
        return false;
    }
    
    public Quadrado getQuadrado(int linha, int coluna){       //identificar a coordenada de cada quadrado   
        return matriz[linha][coluna];
    }
    

    
    public String toString() {
        String str = "";
        
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                str += matriz[i][j] + " ";
            }
            str += "\n";
        }
        return str;        
    }
    
    
    
    
    
}
