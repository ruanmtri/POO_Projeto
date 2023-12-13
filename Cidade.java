package trabalho_v2;

import java.io.Serializable;
import java.util.Random;


public class Cidade implements Serializable {
    private String nome;
    private String origem;
    private String destino;
    private float tarifaPassagem; 
    private int tempoPercurso;
    
    public Cidade(String nome, String origem, String destino, int tempoPercurso, float tarifaPassagem) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.tempoPercurso = tempoPercurso;
        this.tarifaPassagem = tarifaPassagem;
    }

    public String toString() {
        return nome;
    }
    
    public int calcularTempoDeViagemComAcidente() {
        Random random = new Random();
        int chance = random.nextInt(100); 

        if (chance < 30) { 
            return tempoPercurso + 20; 
        } else if (chance < 60) { 
            return tempoPercurso + 10; 
        } else {
            return tempoPercurso + 5; 
        }
    }
    
    public String getNome() {
        return nome;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public int getTempoPercurso() {
        return tempoPercurso;
    }
    
    public float getPrecoBase() {
        return tarifaPassagem;
    }
}