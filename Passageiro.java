package trabalho_v2;

import java.io.Serializable;

public class Passageiro implements Serializable {
    private String nome;
    private Companhia companhia;
    private Cidade rotaEscolhida;
    private Onibus onibusEscolhido; 
    private Passagem passagem;
     
    public String toString() {
        return nome; 
    }
    
    public Passageiro(String nome, Companhia companhia, Cidade rotaEscolhida, Onibus onibusEscolhido) {
        this.nome = nome;
        this.companhia = companhia;
        this.rotaEscolhida = rotaEscolhida;
        this.onibusEscolhido = onibusEscolhido;
    }

    public String getNome() {
        return nome;
    }

    public Companhia getCompanhia() {
        return companhia;
    }

    public Cidade getRotaEscolhida() {
        return rotaEscolhida;
    }

    public Onibus getOnibusEscolhido() {
        return onibusEscolhido;
    }
    
    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }
}