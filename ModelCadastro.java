package trabalho_v2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public 
class ModelCadastro {
    private List<Cidade> rotasValidas;
   
    public ModelCadastro() {
        rotasValidas = new ArrayList<>();
    }
    
    public void removERROta(Cidade rota) {
        rotasValidas.remove(rota);
    }
        
    public boolean validarRota(String nomeRota) {
        for (Cidade rota : rotasValidas) {
            if (rota.getNome().equals(nomeRota)) {
                return false; 
            }
        }
        return true;
    }

    public void cadastrarRota(Cidade rota) {
        if (validarRota(rota.getNome())) {
            rotasValidas.add(rota);
            JOptionPane.showMessageDialog(null, "Rota cadastrada com sucesso: " + rota.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Rota inválida. Rota não cadastrada.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void cadastrarRota2(Cidade rota) {
            rotasValidas.add(rota);
    }
    
    public List<Cidade> listarRotasValidas() {
        return new ArrayList<>(rotasValidas);
    }
}