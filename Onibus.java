package trabalho_v2;

import java.io.Serializable;

public class Onibus implements Serializable {
    private String nome;
    private Cidade cidade;
    private Companhia companhia;

    public String toString() {
        return nome; 
    }
    
    public Onibus(String nome, Cidade cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Companhia getCompanhia() {
        return companhia;
    }
    
    public void setCompanhia(Companhia companhia) {
        this.companhia = companhia;
    }
}