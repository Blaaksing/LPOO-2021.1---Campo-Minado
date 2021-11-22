package campominado;
import java.util.Random;

public class AreaDoCampo {
    Quadrado[][] area;

    public AreaDoCampo(){                       //criar a matriz e adicionar os quadrados
        area = new Quadrado[Tamanho.linhas][Tamanho.colunas];
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                area[i][j] = new Quadrado();
            }
        }
        for (int i = 0; i < Tamanho.linhas; i++) {
            for (int j = 0; j < Tamanho.colunas; j++) {
                if (i > 0){                                     //condições para as bordas da area, onde não possui quadrado
                    if (j > 0) {
                        area[i][j].criarQuadradosProximos(area[i-1][j-1]);
                    }
                        area[i][j].criarQuadradosProximos(area[i-1][j]);
                    if (j < Tamanho.colunas-1) {
                        area[i][j].criarQuadradosProximos(area[i-1][j+1]);
                    }
                    }
                if (j > 0) {
                    area[i][j].criarQuadradosProximos(area[i][j-1]);
                }                
                if (j < Tamanho.colunas-1){
                    area[i][j].criarQuadradosProximos(area[i][j+1]);
                }
                if(i < Tamanho.linhas -1){
                    if (j > 0){
                        area[i][j].criarQuadradosProximos(area[i+1][j-1]);
                    }
                        area[i][j].criarQuadradosProximos(area[i+1][j]);
                    if (j < Tamanho.colunas-1){
                        area[i][j].criarQuadradosProximos(area[i+1][j+1]);
                    }
                }
            }
        } 
    }
    public void adicionarMinas(){           //adicionar as minas aleatoriamente na matriz
        int n = Tamanho.numminas;
        Random rand = new Random();
        while (n > 0){            
            int l = rand.nextInt(Tamanho.linhas); 
            int c = rand.nextInt(Tamanho.colunas);             
            if (area[l][c].possuiBomba()){
                n--;
            }            
        }
    }
}
