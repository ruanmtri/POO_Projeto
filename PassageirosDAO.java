package trabalho_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PassageirosDAO {
    private static final String ONIBUS_FILE = "src/trabalho/passageiro.txt";

    public static void salvarBus(List<Passageiro> passageirosList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ONIBUS_FILE))) {
            for (Passageiro passageiro : passageirosList) {
                writer.write(passageiro.getNome() + "," + passageiro.getCompanhia()+ "," + passageiro.getRotaEscolhida());
                writer.newLine();                                                
            }
            JOptionPane.showMessageDialog(null, "Passageiro salvos com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao salvar passaheiros: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static List<Passageiro> listarPas(CadastroPassagem cadastro, ModelCadastro model, ControlCadastro control) {
        List<Passageiro> PassageirosList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ONIBUS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nome = parts[0];
                    int companhia = (int) Double.parseDouble(parts[1]);
                    
                    int id = (int) Double.parseDouble(parts[2]);
                    
                    Onibus onibusEscolhido = null;
                    
                    Onibus onibus = new Onibus(nome, model.listarRotasValidas().get(companhia));
                           
                    List<Companhia> companhiasCadastradas = control.listarCompanhias();                    
                    Companhia companhiaDesejada = companhiasCadastradas.get(id); 
                    onibus.setCompanhia(companhiaDesejada);
                                                    
                    List<Onibus> onibusDisponiveis2 = control.listarOnibusDisponiveisParaRota(control.listarOnibus(), model.listarRotasValidas().get(companhia), companhiaDesejada);
                    if (!onibusDisponiveis2.isEmpty()) {
                        onibusEscolhido = onibusDisponiveis2.get(0);
                    }

                    Passageiro passageiro = new Passageiro(nome, companhiaDesejada, model.listarRotasValidas().get(companhia), onibusEscolhido);

                    control.cadastrarPassageiro(passageiro.getNome(), passageiro.getCompanhia(), passageiro.getRotaEscolhida(), passageiro.getOnibusEscolhido());

                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERRO ao listar passageiro: " + e.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return PassageirosList;
    }
}