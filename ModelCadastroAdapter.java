package trabalho_v2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ModelCadastroAdapter implements ButtonClickListener {
    private ModelCadastro modelCadastro;
    private List<Cidade> rotasValidas;
   
    public ModelCadastroAdapter() {
        rotasValidas = new ArrayList<>();
    }
    
    public ModelCadastroAdapter(ModelCadastro modelCadastro) {
        this.modelCadastro = modelCadastro;
    }

    public boolean validarRota(String nomeRota) {
        for (Cidade rota : rotasValidas) {
            if (rota.getNome().equals(nomeRota)) {
                    return false; 
                }
            }
        return true;
    }
    
    public void onClick(Cidade rota) {
        if (validarRota(rota.getNome())) {
            rotasValidas.add(rota);
            JOptionPane.showMessageDialog(null, "Rota cadastrada com sucesso: " + rota.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Rota inválida. Rota não cadastrada.", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
}  