package ducanhbui.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUnit {
	  public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String url = "jdbc:sqlserver://CODEBOY\\SQLEXPRESS:1433;databaseName=Lab04";
	        String user = "sa";
	        String password = "12345a.";

	        Connection conn = DriverManager.getConnection(url, user, password);

	        return conn;
	    }

	    public static void main(String[] args) throws SQLException, ClassNotFoundException  {

	        System.out.println("Get connection ... ");
	        Connection conn = getMySQLConnection();
	        System.out.println("Get connection " + conn);
	        System.out.println("Done!");
	    }
}
