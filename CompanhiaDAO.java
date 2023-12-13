package trabalho_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CompanhiaDAO {
    private static final String COMPANHIA_FILE = "src/trabalho/companhias.txt";

    public static void salvarCompanhias(List<Companhia> companhiasList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMPANHIA_FILE))) {
            for (Companhia companhia : companhiasList) {
                writer.write(companhia.getNome() + "," + companhia.getTarifa());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Companhias salvas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar companhias: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static List<Companhia> listarCompanhias(ControlCadastro control) {
        List<Companhia> companhiasList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(COMPANHIA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nome = parts[0];
                    double tarifa = Double.parseDouble(parts[1]);


                    
                    Companhia companhia = new Companhia(nome, tarifa);
                    companhiasList.add(companhia);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao listar companhias: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }

        return companhiasList;
    }
}