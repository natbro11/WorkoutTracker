import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
	private static final String DATABASE_URL = "jdbc:sqlite:workout.db";
	private Connection conn;
	// Constructor
	public DatabaseManager() {
		this.connect();
	    this.createTables();
	}	
	
	// Initialization Functions -------------------------------------------------------------------
	// Connect to the database
	private void connect() {
		try {
			this.conn = DriverManager.getConnection(DATABASE_URL);
			System.out.println("DatabaseManager: Connection Established");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	// Create the required tables
	private void createTables() {
		String[] createTableStatements = {
			"CREATE TABLE IF NOT EXISTS weight  (weight_id INTEGER PRIMARY KEY AUTOINCREMENT, time DATETIME DEFAULT CURRENT_TIMESTAMP UNIQUE, weight FLOAT NOT NULL);",
			"CREATE TABLE IF NOT EXISTS lift    (lift_id INTEGER PRIMARY KEY AUTOINCREMENT, lift_name TEXT NOT NULL UNIQUE);",
			"CREATE TABLE IF NOT EXISTS workout (workout_id INTEGER PRIMARY KEY AUTOINCREMENT, lift_id INTEGER, time DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (lift_id) REFERENCES lift(lift_id) ON DELETE CASCADE);",
			"CREATE TABLE IF NOT EXISTS 'set'   (set_id INTEGER PRIMARY KEY AUTOINCREMENT, workout_id INT, weight FLOAT NOT NULL, reps INT NOT NULL, wucd BOOLEANs, FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE);"
		};
		try (Statement stmt = conn.createStatement()) {
		    for (String sql : createTableStatements) {
		        stmt.execute(sql);
		    }
		    System.out.println("DatabaseManager: Tables Created");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
	// Initialize common exercises
	
	// Interaction Functions ----------------------------------------------------------------------
	// register new user
	public boolean createUser(String name) {
	    String sql = "INSERT INTO user (user_name) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, name);
	        pstmt.executeUpdate();
	        System.out.println("User '" + name + "' inserted successfully.");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	    return true;
	}
	public boolean deleteUser(int user_id) {
	    String sql = "DELETE FROM user WHERE user_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, user_id);
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("User with user_id " + user_id + " deleted successfully.");
	            return true;
	        } else {
	            System.out.println("No user found with user_id " + user_id);
	            return false;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	public boolean createWeight(int user_id,float weight) {
	    String sql = "INSERT INTO weight (user_id,weight) VALUES (?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1,user_id);
	        pstmt.setFloat(2,weight);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted - User: %s, Weight: %f\n",user_id,weight));
	    return true;
	}
	public boolean deleteWeight(int weight_id) {
	    String sql = "DELETE FROM weight WHERE weight_id = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, weight_id);
	        int affectedRows = pstmt.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("User with user_id " + weight_id + " deleted successfully.");
	            return true;
	        } else {
	            System.out.println("No user found with user_id " + weight_id);
	            return false;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	
	
	// List all records in the users table
	public String toStringUsers() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user;");
			while(rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				sb.append(String.format("Userid: %d, Username: %s\n",user_id,user_name));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	// List all records in the users table
	public String toStringWeights() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM weight;");
			while(rs.next()) {
				int weight_id = rs.getInt("weight_id");
				int user_id = rs.getInt("user_id");
				String time = rs.getString("time");
				float weight = rs.getFloat("weight");
				sb.append(String.format("Weightid: %d, Userid: %d, Time: %s, Weight: %f\n",weight_id,user_id,time,weight));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	// List all tables and their row counts
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	List<String> tables = new ArrayList<>();
    	try {
    		Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'"); 
			while (rs.next()) {
				tables.add(rs.getString("name"));
			}
	        for (String table : tables) {
	            String escapedTable = "\"" + table + "\"";
	            String countQuery = "SELECT COUNT(*) AS rowcount FROM " + escapedTable;
	            try (Statement countStmt = conn.createStatement();
	                 ResultSet countRs = countStmt.executeQuery(countQuery)) {

	                if (countRs.next()) {
	                    int count = countRs.getInt("rowcount");
	                    sb.append(table + ": " + count + " rows\n");
	                }
	            }
	        }
    	} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();	   	
    }
}
