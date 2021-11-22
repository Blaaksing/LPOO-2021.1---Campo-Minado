package campominado;
import java.util.ArrayList;


public class Quadrado {
    protected boolean bomba;
    protected boolean revelado;
    protected boolean bandeira;
    protected boolean ativado;

    ArrayList<Quadrado> quadradosProximos; //lista dos quadrados da matriz

        public Quadrado(){             //iniciando tudo como falso, para depois definir se tem bomba e as numerações
            this.bomba = false;
            this.revelado = false;
            this.bandeira = false;
            this.ativado = false;

            this.quadradosProximos = new ArrayList<>();
        }
        public void criarQuadradosProximos (Quadrado q){    //adicionar os quadrados em volta
            this.quadradosProximos.add(q);
        }
        public boolean possuiBomba(){     //verificar se já possui bomba na matriz, caso possua, atribuir a bomba a outra coordenada
            if (!this.bomba) {
                this.bomba = true;
                return true;
            } else {
                return false;
            }
        }
        public boolean possuiBandeira(){       //verificar se já possui a bandeira no quadrado
            this.bandeira = !this.bandeira;
            return this.bandeira;
        }
        public int bombasProximas(){         //verificar a quantidade de bombas em volta
            int contador = 0;                //contador para as bombas em volta do "quadrado"
            for (Quadrado q : this.quadradosProximos) {
                if(q.bomba){
                    contador++;
                }
            }
            return contador;
        }
        public int ativar(){        //após clicar, verificar se tem bomba ou não
            this.ativado = true;
            if (this.bomba) {
                return -1;     //-1 pois numero negativo não conta no contador 
            } else {
                return bombasProximas();
            }
        }
        public void reiniciar (){   //reiniciar o campominado
            this.bomba = false;
            this.revelado = false;
            this.bandeira = false;
            this.ativado = false;
        }
} 
