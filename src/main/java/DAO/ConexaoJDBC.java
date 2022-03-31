package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/estacionamento", 
				"root", 
				"");
	}
}
