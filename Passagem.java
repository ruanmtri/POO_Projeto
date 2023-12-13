package trabalho_v2;

import java.io.Serializable;

public class Passagem implements Serializable {
    private static final long serialVersionUID = 1L; 
    private int id;
    private Passageiro passageiro;
    private Onibus onibus;

    public Passagem(int id, Passageiro passageiro, Onibus onibus) {
        this.id = id;
        this.passageiro = passageiro;
        this.onibus = onibus;
    }

    public int getId() {
        return id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Onibus getOnibus() {
        return onibus;
    }
}