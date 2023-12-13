package trabalho_v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Companhia implements Serializable {
    private String nome;
    private double tarifa;
    private List<Onibus> onibus;

    public Companhia(String nome, double tarifa) {
        this.nome = nome;
        this.tarifa = tarifa;
        this.onibus = new ArrayList<>();
    }

    public void removerOnibus(Onibus onibus) {
        this.onibus.remove(onibus);
    }
    
    public List<Onibus> getOnibus() {
        return onibus;
    }
    
    public Companhia(String nome) {
        this.nome = nome;
        this.tarifa = 1.0;
    }

    public String getNome() {
        return nome;
    }
    
    public String toString() {
         return nome;
    }

    public double getTarifa() {
        return tarifa;
    }
}