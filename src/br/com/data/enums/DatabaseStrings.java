package br.com.data.enums;

public enum DatabaseStrings {
	
	MYSQL ("MYSQL", ""),
	MYSQL_livraria ("MYSQL", "livraria"),
	SQLSERVER ("SQLSERVER", ""),
	SQLSERVER_dbAula ("SQLSERVER", "dbAula"),
	SQLSERVER_livraria ("SQLSERVER", "livraria");
	
	private String server;
	private String database;

	private DatabaseStrings(String server, String database) {
		this.server = server;
		this.database = database;
	}

	public static String[] parseByDb(DatabaseStrings dbStr) {
		for (DatabaseStrings item : values()){
			if (item == dbStr){
				return new String[]{item.getServer(), item.getDatabase()};
			}
		}
		return null;
	}

	public String getServer() {
		return server;
	}


	public void setServer(String server) {
		this.server = server;
	}


	public String getDatabase() {
		return database;
	}


	public void setDatabase(String database) {
		this.database = database;
	}

	
}
