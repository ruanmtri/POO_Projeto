package trabalho_v2;

import java.util.ArrayList;
import java.util.List;

public class ControlCadastro {
    private CadastroPassagem cadastro;
    private List<Passageiro> listaPassageiros;
    private List<Onibus> listaOnibus; 
    private List<Passagem> passagensEmitidas = new ArrayList<>();
   
    public List<Passagem> listarPassagensEmitidas() {
        return passagensEmitidas;
    }

    public Onibus buscarOnibusPorNome(String nome) {
        List<Onibus> onibus = listarOnibus();
        for (Onibus onibusItem : onibus) {
            if (onibusItem.getNome().equals(nome)) {
                return onibusItem;
            }
        }
        return null; 
    }

    public Passageiro buscarPassageiroPorNome(String nome) {
        List<Passageiro> passageiros = listarPassageiros();
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getNome().equals(nome)) {
                return passageiro;
            }
        }
        return null; 
    }
    
    public ControlCadastro(CadastroPassagem cadastro) {
        this.cadastro = cadastro;
        this.listaPassageiros = listaPassageiros;
        this.listaOnibus = listaOnibus;
    }

    public void cadastrarPassageiro(String nome, Companhia companhia, Cidade rotaEscolhida, Onibus onibusEscolhido) {
        Passageiro passageiro = new Passageiro(nome, companhia, rotaEscolhida, onibusEscolhido);
        cadastro.cadastrarPassageiro(passageiro);
    }

    public List<Passageiro> listarPassageirosPorOnibus(Onibus onibus) {
        List<Passageiro> passageirosPorOnibus = new ArrayList<>();
        List<Passageiro> passageiros = cadastro.listarPassageiros(); 

        for (Passageiro passageiro : passageiros) {
            if (passageiro.getOnibusEscolhido() != null && passageiro.getOnibusEscolhido().equals(onibus)) {
                passageirosPorOnibus.add(passageiro);
            }
        }
        return passageirosPorOnibus;
    }

    public List<Onibus> listarOnibusDisponiveisParaRota(List<Onibus> listaOnibus, Cidade rota, Companhia companhiaPassageiro) {
        List<Onibus> onibusDisponiveis = new ArrayList<>();
        for (Onibus onibus : listaOnibus) {
            if (onibus.getCidade().equals(rota) && onibus.getCompanhia().equals(companhiaPassageiro)) {
                onibusDisponiveis.add(onibus);
            }
        }
        return onibusDisponiveis;
    }
    
    public void cadastrarCompanhia(String nome) {
        Companhia companhia = new Companhia(nome);
        cadastro.cadastrarCompanhia(companhia);
    }

    public List<Passageiro> listarPassageiros() {
        return cadastro.listarPassageiros();
    }

    public List<Onibus> listarOnibus() {
        return cadastro.listarOnibus();
    }

    public List<Companhia> listarCompanhias() {
        return cadastro.listarCompanhias();
    }   
}
