package trabalho_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OnibusDAO {
    private static final String ONIBUS_FILE = "src/trabalho/onibus.txt";

    public static void salvarBus(List<Onibus> OnibusList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ONIBUS_FILE))) {
            for (Onibus onibus : OnibusList) {
                writer.write(onibus.getNome() + "," + onibus.getCidade()+ "," + onibus.getCompanhia());
                writer.newLine();                                                
            }
            JOptionPane.showMessageDialog(null, "Onibus salvos com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar onibus: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static List<Onibus> listarBus(CadastroPassagem cadastro, ModelCadastro model, ControlCadastro control) {
        List<Onibus> OnibusList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ONIBUS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nome = parts[0];
                    int cidade = (int) Double.parseDouble(parts[1]);
                    
                    int id = (int) Double.parseDouble(parts[2]);

                    
                    Onibus onibus = new Onibus(nome, model.listarRotasValidas().get(cidade));
                           
                    List<Companhia> companhiasCadastradas = control.listarCompanhias();                    
                    Companhia companhiaDesejada = companhiasCadastradas.get(id);
                    onibus.setCompanhia(companhiaDesejada);
                                                    
                    cadastro.cadastrarOnibus(onibus);
                    
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao listar onibus: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return OnibusList;
    }
}