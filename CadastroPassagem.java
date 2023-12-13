package trabalho_v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CadastroPassagem {
    private List<Passageiro> passageiros;
    private List<Onibus> onibus;
    private List<Companhia> companhias;
    private List<Passagem> passagens;
    private List<Passagem> passagensEmitidas;
    
    public void removerOnibusPorRota(Cidade rota) {
        List<Onibus> onibusARemover = new ArrayList<>();

        for (Onibus onibus2 : onibus) {
            if (onibus2.getCidade().equals(rota)) {
                onibusARemover.add(onibus2);
            }
        }

        onibus.removeAll(onibusARemover);
    }
    
    public List<Onibus> listarOnibusPorCompanhia(Companhia companhia) {
        List<Onibus> onibusDaCompanhia = new ArrayList<>();
        for (Onibus onibus2 : onibus) {
            if (onibus2.getCompanhia().equals(companhia)) {
                onibusDaCompanhia.add(onibus2);
            }
        }
        return onibusDaCompanhia;
    }

    public void removerCompanhiaEOnibus(Companhia companhia, List<Onibus> onibusDaCompanhia) {
        companhias.remove(companhia);
        onibus.removeAll(onibusDaCompanhia);
    }
      
    public void deletarCompanhia(Companhia companhia) {
        List<Onibus> onibusDaCompanhia = new ArrayList<>();
        for (Onibus bus : onibus) {
            if (bus.getCompanhia() == companhia) {
                onibusDaCompanhia.add(bus);
            }
        }

        if (onibusDaCompanhia.isEmpty()) {
            System.out.println("A companhia " + companhia.getNome() + " não possui ônibus associados.");
        } else {
            System.out.println("Ônibus associados à companhia " + companhia.getNome() + ":");
            for (int i = 0; i < onibusDaCompanhia.size(); i++) {
                System.out.println(i + 1 + ". " + onibusDaCompanhia.get(i).getNome());
            }

            System.out.print("Deseja realmente remover a companhia e todos os seus ônibus associados? (S/N): ");
            Scanner scanner = new Scanner(System.in);
            String confirmacao = scanner.nextLine().trim().toLowerCase();

            if (confirmacao.equals("s")) {
                onibus.removeAll(onibusDaCompanhia);

                companhias.remove(companhia);
                System.out.println("Companhia " + companhia.getNome() + " e seus ônibus associados foram removidos com sucesso.");
                
            } else {
                JOptionPane.showMessageDialog(null, "A remoção foi cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void removerPassageiro(Passageiro passageiro) {
        passageiros.remove(passageiro);
    }
    
    public void removerOnibus(Onibus onibusParaRemover) {
        List<Onibus> onibusremove = listarOnibus();
        if (onibusremove.contains(onibusParaRemover)) {
            onibusremove.remove(onibusParaRemover);
            JOptionPane.showMessageDialog(null, "Ônibus removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ônibus não encontrado na lista.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public CadastroPassagem() {
        passageiros = new ArrayList<>();
        onibus = new ArrayList<>();
        companhias = new ArrayList<>();
        passagens = new ArrayList<>(); 
        passagensEmitidas = new ArrayList<>();
    }

    public void cadastrarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public void cadastrarOnibus(Onibus onibus) {
        this.onibus.add(onibus);
    }

    public List<Passagem> listarPassagens() {
        return passagens;
    }
   
    public void cadastrarCompanhia(Companhia companhia) {
        companhias.add(companhia);
    }

    public boolean emitirPassagem(Passageiro passageiro, Onibus onibus) {
        int idPassagem = new Random().nextInt(10000);
        Passagem novaPassagem = new Passagem(idPassagem, passageiro, onibus);
        passagens.add(novaPassagem);
        passageiro.setPassagem(novaPassagem);

        passagensEmitidas.add(novaPassagem);
    
         passagens.remove(novaPassagem);

        Passagem novaPassagem2 = new Passagem(idPassagem, passageiro, onibus);

        passagensEmitidas.add(novaPassagem2);

        return true;
    }

    public List<Passageiro> listarPassageiros() {
        return passageiros;
    }

    public List<Onibus> listarOnibus() {
        return onibus;
    }

    public List<Companhia> listarCompanhias() {
        return companhias;
    }
}