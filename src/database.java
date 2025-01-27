import java.sql.*;

public class database {
    // Database URL and credentials
    String url = "jdbc:mysql://localhost:3306/QuizDB"; // Change 'QuizDB' to your database name
    String username = "root"; // Change to your MySQL username
    String password = "nachi@2003"; // Change to your MySQL password
    Connection con;
    Statement st;

    public database() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(url, username, password);

            // Create a statement object for executing SQL queries
            st = con.createStatement();

            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        }
    }

    // Method to close the database connection
    public void close() {
        try {
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
