package interfaces;

import java.util.List;

public interface GenericImpressao<T> {
	void imprimirMensagem(String mensagem);
	void imprimirMensagemSimples(String mensagem);
	void imprimirObjetoDetalhe(T titulo, T objeto) throws Exception;
	void imprimirObjetoResumido(T objeto) throws Exception;
    void imprimirListaDetalhe(T titulo, List<T> lista) throws Exception;
    void imprimirListaResumido(T titulo, List<T> lista) throws Exception;
}
