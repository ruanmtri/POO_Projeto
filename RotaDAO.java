package trabalho_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RotaDAO {
    private static final String ROTA_FILE = "src/trabalho/rotas.txt";

    public static void salvarRotas(List<Cidade> cidadeList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROTA_FILE))) {
            for (Cidade cidade : cidadeList) {
                writer.write(cidade.getNome() + "," + cidade.getOrigem()+ "," + cidade.getDestino()+ "," + cidade.getPrecoBase()+ "," +cidade.getTempoPercurso());
                writer.newLine();                                                
            }
            JOptionPane.showMessageDialog(null, "Rotas salvas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar companhias: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static List<Cidade> listarRotas(ModelCadastro model) {
        List<Cidade> cidadeList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ROTA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String nome = parts[0];
                    String origem = parts[1];
                    String destino = parts[2];
                    
                    int tempop = (int) Double.parseDouble(parts[3]);
                    int tarf = (int) Double.parseDouble(parts[4]);
                    
                    Cidade cidade3 = new Cidade(nome, origem, destino, tempop, tarf);
                    model.cadastrarRota2(cidade3);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao listar rotas: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return cidadeList;
    }
}
