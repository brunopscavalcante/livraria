package br.com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import br.com.data.enums.DatabaseStrings;
import br.com.data.exception.MySqlException;

public abstract class AbstractConnectionFactory {

	protected Connection con = null;
	protected PreparedStatement pstmt = null;

	public Connection getMySQLConnection(String database) throws MySqlException{
		if (con == null){
			try {
				if (database == null) database = "";
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost/" + database, "root", "1234");
				System.out.println("Conectado a " + con.getCatalog() + " em MySQL");
			} catch (SQLException e) {
				System.out.println("Erro de conexão: " + e.getMessage());
				throw new MySqlException(e);
			}
		}
		return con;
	}

	public Connection getSQLServerConnection(String database){
		if (con == null){
			try {
				if (database == null) database = "";
				Connection con = DriverManager.getConnection(
						"jdbc:sqlserver://localhost;databaseName=" + database , "sa", "brn21");
				System.out.println("Conectado a " + con.getCatalog() + " em SQLServer");
			} catch (SQLException e) {
				System.out.println("Erro de Conexão: " + e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return con;
	}

	protected Connection getConnection(DatabaseStrings db) throws MySqlException {
		if (con == null){
			String str [] = DatabaseStrings.parseByDb(db);
			if (str[0].toUpperCase().equals(DatabaseStrings.SQLSERVER.getServer())) {
				con = getSQLServerConnection(str[1]);
			} else if (str[0].toUpperCase().equals(DatabaseStrings.MYSQL.getServer())){
				con = getMySQLConnection(str[1]);
			}
		}
		return con;
	}

	private Date getDataAtual() {
		Date data = null;
		try {
			data = Calendar.getInstance().getTime();
		} catch (Exception e){
			System.out.println("null");
		}
		return data;
	}

	protected void sb(StringBuilder sb, String string) {
		if (sb.length() > 0){
			sb.append(string);
		} else {
			sb.append(" WHERE " + string);
		}
	}

}