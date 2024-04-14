package repositories;

import java.util.List;
import java.util.UUID;
import entities.Endereco;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import entities.Funcionario;
import java.sql.PreparedStatement;
import factories.ConnectionFactory;
import interfaces.GenericRepository;

public class EnderecoRepository implements GenericRepository<Endereco, UUID> {
	@Override
	public void inserir(Endereco obj) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("INSERT INTO endereco(id, logradouro, complemento, numero, bairro, "
				     + "cidade, estado, cep, funcionario_id) VALUES (?,?,?,?,?,?,?,?,?)");
			
			statement.setObject(1, obj.getId());
			statement.setString(2, obj.getLogradouro());
			statement.setString(3, obj.getComplemento());
			statement.setString(4, obj.getNumero());
			statement.setString(5, obj.getBairro());
			statement.setString(6, obj.getCidade());
			statement.setString(7, obj.getEstado());
			statement.setString(8, obj.getCep());
	        statement.setObject(9, obj.getFuncionario().getId());

			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}

	@Override
	public void alterar(Endereco obj) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("UPDATE endereco SET logradouro = ? ,complemento = ? ,numero = ? ,bairro = ? "
					+ ",cidade = ? ,estado = ? ,cep = ? ,funcionario_id = ? WHERE id = ?");
			
			statement.setString(1, obj.getLogradouro());
			statement.setString(2, obj.getComplemento());
			statement.setString(3, obj.getNumero());
			statement.setString(4, obj.getBairro());
			statement.setString(5, obj.getCidade());
			statement.setString(6, obj.getEstado());
			statement.setString(7, obj.getCep());
	        statement.setObject(8, obj.getFuncionario().getId());
			statement.setObject(9, obj.getId());
			
			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}

	@Override
	public void excluir(UUID id) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("DELETE FROM endereco where id = ?");
			
			statement.setObject(1, id);
			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}

	@Override
	public List<Endereco> consultarTodos() throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();

		    String sql = "SELECT * FROM endereco";

		    PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet resultSet = statement.executeQuery();

		    List<Endereco> enderecos = new ArrayList<>();
		    
		    while (resultSet.next()) {
		        Endereco endereco = new Endereco();
		        Funcionario funcionario = new Funcionario();
		        
		        endereco.setId(UUID.fromString(resultSet.getString("id")));
		        endereco.setLogradouro(resultSet.getString("logradouro"));
		        endereco.setComplemento(resultSet.getString("complemento"));
		        endereco.setNumero(resultSet.getString("numero"));
		        endereco.setBairro(resultSet.getString("bairro"));
		        endereco.setCidade(resultSet.getString("cidade"));
		        endereco.setEstado(resultSet.getString("estado"));
		        endereco.setCep(resultSet.getString("cep"));
		        
		        funcionario.setId(UUID.fromString(resultSet.getString("funcionario_id")));
		        endereco.setFuncionario(funcionario);
		        enderecos.add(endereco);
		    }
		    
		    connection.close();
		    return enderecos;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Endereco consultarPorId(UUID id) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection.prepareStatement
					("SELECT * FROM endereco WHERE id = ?");
		    statement.setObject(1, id);
		    ResultSet resultSet = statement.executeQuery();

		    Endereco endereco = new Endereco();
		    if (resultSet.next()) {
		        Funcionario funcionario = new Funcionario();
		        
		        endereco.setId(UUID.fromString(resultSet.getString("id")));
		        endereco.setLogradouro(resultSet.getString("logradouro"));
		        endereco.setComplemento(resultSet.getString("complemento"));
		        endereco.setNumero(resultSet.getString("numero"));
		        endereco.setBairro(resultSet.getString("bairro"));
		        endereco.setCidade(resultSet.getString("cidade"));
		        endereco.setEstado(resultSet.getString("estado"));
		        endereco.setCep(resultSet.getString("cep"));
		        
		        funcionario.setId(UUID.fromString(resultSet.getString("funcionario_id")));
		        endereco.setFuncionario(funcionario);
		    }
		    
		    connection.close();
		    return endereco;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		return null;
	}
	
	// retorna uma lista de endereco por cliente
	public List<Endereco> consultarTodosId(UUID id) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();

			PreparedStatement statement = connection.prepareStatement
					("SELECT * FROM endereco WHERE funcionario_id = ?");
		    statement.setObject(1, id);
		    ResultSet resultSet = statement.executeQuery();

		    List<Endereco> enderecos = new ArrayList<>();
		    while (resultSet.next()) {
		        Endereco endereco = new Endereco();
		        Funcionario funcionario = new Funcionario();
		        
		        endereco.setId(UUID.fromString(resultSet.getString("id")));
		        endereco.setLogradouro(resultSet.getString("logradouro"));
		        endereco.setComplemento(resultSet.getString("complemento"));
		        endereco.setNumero(resultSet.getString("numero"));
		        endereco.setBairro(resultSet.getString("bairro"));
		        endereco.setCidade(resultSet.getString("cidade"));
		        endereco.setEstado(resultSet.getString("estado"));
		        endereco.setCep(resultSet.getString("cep"));
		        
		        funcionario.setId(UUID.fromString(resultSet.getString("funcionario_id")));
		        endereco.setFuncionario(funcionario);
		        enderecos.add(endereco);
		    }
		    
		    connection.close();
		    return enderecos;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		return null;
	}
	
	// retorna uma lista de endereco por cliente
	public Endereco consultarEndereco(String cep, String numero) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("SELECT * FROM endereco WHERE cep = ? and numero = ?");
		    statement.setString(1, cep);
		    statement.setString(2, numero);
		    ResultSet resultSet = statement.executeQuery();
		    
		    Endereco endereco = new Endereco();
		    if (resultSet.next()) {
		        Funcionario funcionario = new Funcionario();
		        endereco.setId(UUID.fromString(resultSet.getString("id")));
		        endereco.setLogradouro(resultSet.getString("logradouro"));
		        endereco.setComplemento(resultSet.getString("complemento"));
		        endereco.setNumero(resultSet.getString("numero"));
		        endereco.setBairro(resultSet.getString("bairro"));
		        endereco.setCidade(resultSet.getString("cidade"));
		        endereco.setEstado(resultSet.getString("estado"));
		        endereco.setCep(resultSet.getString("cep"));
		        
		        funcionario.setId(UUID.fromString(resultSet.getString("funcionario_id")));
		        endereco.setFuncionario(funcionario);
		    }
		    
		    connection.close();
		    return endereco;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		return null;
	}
}
