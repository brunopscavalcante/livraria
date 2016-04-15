package br.com.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public class AbstractDAO extends AbstractConnectionFactory {
	
	protected ResultSet resultado = null;

	protected boolean hasNext() throws SQLException {
		return resultado != null && resultado.next();
	}
	
	protected void setCsString(CallableStatement cs, String param, int pos) {
		try {
			if (param != null){
				cs.setString(pos, param);
			} else {
				cs.setNull(pos, Types.VARCHAR);
			}
		} catch (SQLException e) {
		} finally {
			print(pos, param);
		}
	}

	protected void setCsInt(CallableStatement cs, int param, int pos) {
		try {
			if (param != 0){
				cs.setInt(pos, param);
			} else {
				cs.setNull(pos, Types.INTEGER);
			}
		} catch (SQLException e) {
		} finally {
			print(pos, param);
		}
	}
	
	protected void setCsDate(CallableStatement cs, Date param, int pos) {
		try {
			if (param != null){
				java.sql.Date date = new java.sql.Date(param.getTime()); 
				cs.setDate(pos, date);
			} else {
				cs.setNull(pos, Types.DATE);
			}
		} catch (SQLException e) {
		} finally {
			print(pos, param);
		}
	}
	
	protected void setCsDateTime(CallableStatement cs, Timestamp param, int pos) {
		try {
			if (param != null){
				cs.setTimestamp(pos, param);
			} else {
				cs.setNull(pos, Types.TIMESTAMP);
			}
		} catch (SQLException e) {
		} finally {
			print(pos, param);
		}
	}
	
	protected void setCsTime(CallableStatement cs, Time param, int pos) {
		try {
			if (param != null){
				cs.setTime(pos, param);
			} else {
				cs.setNull(pos, Types.TIME);
			}
		} catch (SQLException e) {
		} finally {
			print(pos, param);
		}
	}
	
	private void print(int pos, Object param) {
		System.out.println("Param: " + pos + " - { " + param +" }");
	}

	/**
	 * Constrói a string que chama a proc
	 * @param proc: nome da proc
	 * @param i: numero de parâmetros da proc
	 * @return
	 */
	protected static String buildProc(String proc, int i) {
		StringBuilder params = new StringBuilder("?");
		while (i > 1){
			params.append(",?");
			i--;
		}
		params = new StringBuilder(String.format("(%s)",params.toString()));
		String str = String.format("{call %s %s }", proc, params.toString());
		System.out.println("Executando: " + str);
		return str;
	}
}
