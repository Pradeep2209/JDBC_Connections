package HMS;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pradeep@123";

    public static Connection getConnection() throws Exception {
    	try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    System.out.println("Driver loaded successfully!");
    	} catch (ClassNotFoundException e) {
    	    System.out.println("Driver not found!");
    	}

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
