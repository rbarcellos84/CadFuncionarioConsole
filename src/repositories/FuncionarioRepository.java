package repositories;

import java.util.List;
import java.util.UUID;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import entities.Funcionario;
import java.sql.PreparedStatement;
import factories.ConnectionFactory;
import interfaces.GenericRepository;

public class FuncionarioRepository implements GenericRepository<Funcionario, UUID> {
	@Override
	public void inserir(Funcionario obj) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("INSERT INTO funcionario(id, nome, cpf, matricula, salario) VALUES (?,?,?,?,?)");
			
			statement.setObject(1, obj.getId());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getCpf());
			statement.setString(4, obj.getMatricula());
			statement.setBigDecimal(5, obj.getSalario());
			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}
	
	@Override
	public void alterar(Funcionario obj) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("UPDATE funcionario SET id = ? ,nome = ? ,cpf = ? ,matricula = ? ,"
					+ "salario = ? WHERE id = ?");
			
			statement.setObject(1, obj.getId());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getCpf());
			statement.setString(4, obj.getMatricula());
			statement.setBigDecimal(5, obj.getSalario());
			statement.setObject(6, obj.getId());
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
					("DELETE FROM funcionario WHERE id = ?");
			
			statement.setObject(1, id);
			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}
	
	@Override
	public List<Funcionario> consultarTodos() throws Exception {
	    try {
	    	Connection connection = ConnectionFactory.getConnection();

		    String sql = "SELECT * FROM funcionario";

		    PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet resultSet = statement.executeQuery();

		    List<Funcionario> funcionarios = new ArrayList<>();
		    while (resultSet.next()) {
		        Funcionario funcionario = new Funcionario();
		        funcionario.setId(UUID.fromString(resultSet.getString("id")));
		        funcionario.setNome(resultSet.getString("nome"));
		        funcionario.setCpf(resultSet.getString("cpf"));
		        funcionario.setMatricula(resultSet.getString("matricula"));
		        funcionario.setSalario(resultSet.getBigDecimal("salario"));
		        funcionarios.add(funcionario);
		    }
		    
		    connection.close();
		    return funcionarios;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	    return null;
	}
	
	@Override
	public Funcionario consultarPorId(UUID id) throws Exception {
	    try {
	    	Connection connection = ConnectionFactory.getConnection();
		    
		    PreparedStatement statement = connection.prepareStatement
					("SELECT * FROM funcionario WHERE id = ?");
			statement.setObject(1, id);
		    ResultSet resultSet = statement.executeQuery();
		    
		    Funcionario funcionario = new Funcionario();
		    if (resultSet.next()) {
		        funcionario.setId(UUID.fromString(resultSet.getString("id")));
		        funcionario.setNome(resultSet.getString("nome"));
		        funcionario.setCpf(resultSet.getString("cpf"));
		        funcionario.setMatricula(resultSet.getString("matricula"));
		        funcionario.setSalario(resultSet.getBigDecimal("salario"));
		    }

		    connection.close();
		    return funcionario;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	    return null;
	}
	
	// metodo para buscar o funcionario
	public Funcionario consultarPorMatricula(String matricula) throws Exception {
	    try {
	    	Connection connection = ConnectionFactory.getConnection();

		    PreparedStatement statement = connection.prepareStatement
					("SELECT * FROM funcionario WHERE matricula = ?");
			statement.setString(1, matricula);
		    ResultSet resultSet = statement.executeQuery();
		    
		    Funcionario funcionario = new Funcionario();
		    if (resultSet.next()) {
		        funcionario.setId(UUID.fromString(resultSet.getString("id")));
		        funcionario.setNome(resultSet.getString("nome"));
		        funcionario.setCpf(resultSet.getString("cpf"));
		        funcionario.setMatricula(resultSet.getString("matricula"));
		        funcionario.setSalario(resultSet.getBigDecimal("salario"));
		    }

		    connection.close();
		    return funcionario;
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	    return null;
	}
	
	//metodo para buscar o funcionario
	public void excluitPorMatricula(String matricula) throws Exception {
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement
					("DELETE FROM funcionario WHERE mstricula = ?");
			
			statement.setObject(1, matricula);
			statement.execute();
			connection.close();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}
}
