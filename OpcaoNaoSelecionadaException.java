package trabalho_v2;

//tratamento de exceção personalizada
public class OpcaoNaoSelecionadaException extends Exception {
    public OpcaoNaoSelecionadaException(String mensagem) {
        super(mensagem);
    }
}