
package celula;

import java.util.ArrayList;

import campominado.JButtonQuadrado;


public class Quadrado {  

    private boolean bomba;
    private boolean ativado;
    private boolean revelado;
    private boolean bandeira;  

    private ArrayList<Quadrado> vizinhos;     //lista dos quadrados da matriz
    
    public JButtonQuadrado button;

    public Quadrado() {                       //iniciando tudo como falso, para depois definir se tem bomba e as numerações
        this.bomba = false;
        this.revelado = false;
        this.bandeira = false;
        this.ativado = false;

        this.vizinhos = new ArrayList<Quadrado>();
    }
    //Getters e Setters (encapsulamento)
    public boolean getBomba() {
		return bomba;
	}
    public void setBomba(boolean bomba){
        this.bomba = bomba;
    }
    public boolean getAtivado() {
		return ativado;
	}
    public void setAtivado(boolean ativado){
        this.ativado = ativado;
    }
    public boolean getRevelado() {
		return revelado;
	}
    public void setRevelado(boolean revelado){
        this.revelado = revelado;
    }
    public boolean getBandeira() {
		return bandeira;
	}
    public void setBandeira(boolean bandeira){
        this.bandeira = bandeira;
    }
    public ArrayList<Quadrado> getVizinhos() {
		return vizinhos;
	}
	public void setVizinhos(ArrayList<Quadrado> vizinhos) {
		this.vizinhos = vizinhos;
	}
    //fim
    public void adicionarVizinhos(Quadrado e){          //adicionar os quadrados em volta
        this.vizinhos.add(e);
    }
      
    public boolean minar() {                        //verificar se já possui bomba na matriz, caso possua, atribuir a bomba a outra coordenada
        if (!this.bomba) {
            this.bomba = true;
            return true;
        } else {
            return false;
        }

    }  
    
    public boolean marcar(){                    //verificar se já possui a bandeira no quadrado
        this.bandeira = !this.bandeira;
        return this.bandeira;
    }   
    
    
    public int clicar(){            //após clicar, verificar se tem bomba ou não
        this.ativado = true;        //BOMBA (-1)
        if(this.bomba){
            return -1;
        }
        else{
            return numMinasNosVizinhos();
        }
    }
    
    public int numMinasNosVizinhos(){               //verificar a quantidade de bombas em volta
        int n = 0;                                  //contador para as bombas em volta do "quadrado"
        for (Quadrado vizinho : this.vizinhos) {
            if(vizinho.bomba) n++;
        }
        return n;
    }
    
    public void reiniciar(){                //reiniciar o campominado
        this.bomba = false;
        this.revelado = false;
        this.bandeira = false;
        this.ativado = false;
    }
    
    public boolean fimDeJogo(){
        if(this.bomba && this.bandeira) return true;
        if(!this.bomba && !this.bandeira && this.ativado) return true;
        return false;
    }
    
    public void setButton(JButtonQuadrado button){
        this.button = button;
    }
    
    public String toString() {                 
        if (this.bomba)
            return "-1";
        return "+" + this.numMinasNosVizinhos();
    }

  
}

