package interfaces;

import java.util.List;

/****************************************************************
  Interface genérica para abstrair os repositórios do sistema
  <T> Tipo genérico que representa a entidade do repositório
  <ID> Tipo genérico que representa a chave primária da entidade
****************************************************************/

public interface GenericRepository<T, ID> {
	void inserir(T obj) throws Exception;
	void alterar(T obj) throws Exception;
	void excluir(ID id) throws Exception;
	List<T> consultarTodos() throws Exception;
	T consultarPorId(ID id) throws Exception;
}
