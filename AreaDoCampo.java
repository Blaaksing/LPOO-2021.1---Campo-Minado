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
    }
}