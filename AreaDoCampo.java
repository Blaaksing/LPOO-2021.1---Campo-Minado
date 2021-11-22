package campominado;

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
                if (i > 0){                                     //condições para as bordas da area, para reconhecer as partes que não possuem quadrados
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
    }

    
