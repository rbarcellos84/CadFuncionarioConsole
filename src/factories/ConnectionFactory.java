package factories;

import java.sql.Connection;
import java.sql.DriverManager;

/**************************************************************************
 Classe FACTORY para retornar conexões com um banco de dados do PostGreSQL
**************************************************************************/
public class ConnectionFactory {
	/*
	 * Método para abrir e retornar uma conexão com banco de dados do PostGreSQL
	 */
	public static Connection getConnection() throws Exception {
		// parãmetros para conexão com o banco de dados
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/db_javaProjetoAula05";
		String user = "postgres";
		String password = "coti";
		// abrindo conexão com o banco de dados
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
