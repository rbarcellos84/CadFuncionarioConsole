package repositories;

import java.util.List;
import java.util.UUID;
import entities.FunEndereco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import factories.ConnectionFactory;

public class FunEnderecoRepository {
	public List<FunEndereco> consultarTodos() throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();

		    String sql = "select e.funcionario_id, "
		    		           + "f.nome, "
		    		           + "f.matricula, "
		    		           + "e.id, "
		    		           + "e.logradouro, "
		    		           + "e.complemento, "
		    		           + "e.numero, "
		    		           + "e.bairro, "
		    		           + "e.cidade, "
		    		           + "e.estado, "
		    		           + "e.cep "
		    		           + "from endereco e, funcionario f "
		    		           + "where f.id = e.funcionario_id "
		    		           + "order by e.funcionario_id, e.cep";

		    PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet resultSet = statement.executeQuery();

		    List<FunEndereco> enderecos = new ArrayList<>();
		    
		    while (resultSet.next()) {
		    	FunEndereco endereco = new FunEndereco();
		        
		        endereco.setFuncionario_id(UUID.fromString(resultSet.getString("funcionario_id")));
		        endereco.setNome(resultSet.getString("nome"));
		        endereco.setMatricula(resultSet.getString("matricula"));
		        endereco.setId(UUID.fromString(resultSet.getString("id")));
		        endereco.setLogradouro(resultSet.getString("logradouro"));
		        endereco.setComplemento(resultSet.getString("complemento"));
		        endereco.setNumero(resultSet.getString("numero"));
		        endereco.setBairro(resultSet.getString("bairro"));
		        endereco.setCidade(resultSet.getString("cidade"));
		        endereco.setEstado(resultSet.getString("estado"));
		        endereco.setCep(resultSet.getString("cep"));
		        enderecos.add(endereco);
		    }
		    
		    connection.close();
		    return enderecos;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		return null;
	}
}
