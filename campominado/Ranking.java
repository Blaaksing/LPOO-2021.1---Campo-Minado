package campominado;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Ranking {

    private String nome ;
    private String ler;
    private String tempo;
    private String print = "";
    private String geral;
    private String dificuldade;

    public String getDificuldade() {
        return dificuldade;
    }
    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getLer() {
        return ler;
    }

    public void setLer(String ler) {
        this.ler = ler;
    }
    public Ranking(String nome, String tempo, String dificuldade) {
        
        this.nome = nome;
        this.tempo = tempo;
        this.dificuldade = dificuldade;
    }
    
    public Ranking() {
        
    }    

    public  void salvar() {
        
        tempo = Temporizador.minutosLabel+":"+Temporizador.segundosLabel;
        nome = JOptionPane.showInputDialog("Digite seu nome: ");
        if (Tamanho.linhas == 5 && Tamanho.colunas == 5 && Tamanho.minas == 6){
            dificuldade = "Fácil";
        } else if (Tamanho.linhas == 10 && Tamanho.colunas == 8 && Tamanho.minas == 20){
            dificuldade = "Médio";
        } else if (Tamanho.linhas == 12 && Tamanho.colunas == 14 && Tamanho.minas == 44){
            dificuldade = "Difícil";
        }else{
            dificuldade = "Customizado "+Tamanho.linhas+"x"+Tamanho.colunas;
        }
        try {
            FileWriter criarArquivo = new FileWriter("Ranking.txt",true);
            PrintWriter escreverArquivo = new PrintWriter(criarArquivo);
            escreverArquivo.printf(nome +"\n"+tempo+"\n"+dificuldade+"\n" );
            
            escreverArquivo.flush();
            escreverArquivo.close();
            criarArquivo.close();
            
            
        } catch (IOException e) {
            
            e.printStackTrace();
        } 

    }
     public void lerLista() {
        Path endereco = Paths.get("Ranking.txt");
            try {
                byte[] texto  =  Files.readAllBytes(endereco);
                String lerArquivo = new String(texto);
                ler = lerArquivo;
              
            } catch (Exception e) {
                System.out.println("ERRO AO CHECAR");
            }
            
            String[] palavras = ler.split("\n");
            ArrayList<Ranking> array = new ArrayList<Ranking>();
            for (int i = 0; i < palavras.length -1  ; i++) {
                 
                
                  nome = palavras[i];
                  tempo = palavras[i+1];
                  dificuldade = palavras[i+2];

                array.add(new Ranking(nome,tempo,dificuldade));
                 i=i+2;
                
            }
            
            Collections.sort(array, new OrdenarRanking());
            
            for (int i = 0; i < array.size(); i++) {
                 geral = array.get(i).toString();
                
                print += geral+"\n";
            }
            JOptionPane.showMessageDialog(null, print,"Ranking",3);
            
     }
     

    public String toString(){
        return "Nome: " + nome + "\nTempo: " + tempo +"\nDificuldade: " + dificuldade +"\n";
    }

}