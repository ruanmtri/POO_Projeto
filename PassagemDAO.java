package trabalho_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PassagemDAO {
    private static final String PASSAGEM_FILE = "src/trabalho/passagens.txt";

    public static void salvarPassagem(List<Passagem> passagens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASSAGEM_FILE, true))) {
            for (Passagem passagem : passagens) {
                writer.write(passagem.getId() + "," + passagem.getPassageiro().getNome() + "," + passagem.getOnibus().getNome() + "," + passagem.getOnibus().getCidade().getOrigem() + "," + passagem.getOnibus().getCidade().getDestino());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Passagens Emitida Salva", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao emitir passagens: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static List<Passagem> listarPassagensEmitidas(ControlCadastro control) {
        List<Passagem> passagensEmitidas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PASSAGEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String nomePassageiro = parts[1];
                    String nomeOnibus = parts[2];

                    Passageiro passageiro = control.buscarPassageiroPorNome(nomePassageiro);
                    Onibus onibus = control.buscarOnibusPorNome(nomeOnibus);

                    Passagem passagem = new Passagem(id, passageiro, onibus);
                    passagensEmitidas.add(passagem);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao listar passagens emitidas: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return passagensEmitidas;
    }
}