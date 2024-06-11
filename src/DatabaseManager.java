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
	    this.insertDefaultData();
	}	
	
	// Private Functions ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
			"CREATE TABLE IF NOT EXISTS bodyweight (bodyweight_id INTEGER PRIMARY KEY AUTOINCREMENT, time DATETIME DEFAULT CURRENT_TIMESTAMP UNIQUE, bodyweight FLOAT NOT NULL);",
			
			"CREATE TABLE IF NOT EXISTS weight (weight_id INTEGER PRIMARY KEY AUTOINCREMENT, weight_name TEXT NOT NULL UNIQUE);",
			"CREATE TABLE IF NOT EXISTS cardio (cardio_id INTEGER PRIMARY KEY AUTOINCREMENT, cardio_name TEXT NOT NULL UNIQUE);",
			
			"CREATE TABLE IF NOT EXISTS workout  (workout_id INTEGER PRIMARY KEY AUTOINCREMENT, time DATETIME DEFAULT CURRENT_TIMESTAMP);",
			
			"CREATE TABLE IF NOT EXISTS weightset (weightset_id INTEGER PRIMARY KEY AUTOINCREMENT, weight_id INTEGER, workout_id INTEGER, masslb FLOAT NOT NULL, reps INT NOT NULL, FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE, FOREIGN KEY (weight_id) REFERENCES weight(weight_id) ON DELETE CASCADE);",
			"CREATE TABLE IF NOT EXISTS cardioset (cardioset_id INTEGER PRIMARY KEY AUTOINCREMENT, cardio_id INTEGER, workout_id INTEGER, distmi FLOAT NOT NULL, secs INT NOT NULL, FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE, FOREIGN KEY (cardio_id) REFERENCES cardio(cardio_id) ON DELETE CASCADE);"
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
	// Initialize few common exercises
	private void insertDefaultData() {
		String[] insertStatements = {
			"INSERT INTO weight (weight_name) VALUES ('Barbell Bench Press');",
			"INSERT INTO weight (weight_name) VALUES ('Barbell Deadlift');",
			"INSERT INTO weight (weight_name) VALUES ('Barbell Overhead Press');",
			"INSERT INTO weight (weight_name) VALUES ('Barbell Squat');",
			"INSERT INTO cardio (cardio_name) VALUES ('Treadmill');",
			"INSERT INTO cardio (cardio_name) VALUES ('Stationary Bike');",
			"INSERT INTO cardio (cardio_name) VALUES ('Elliptical');",
			"INSERT INTO cardio (cardio_name) VALUES ('Rowing Machine');"
		};
		try (Statement stmt = conn.createStatement()) {
			for(String sql : insertStatements) {
				stmt.execute(sql);
			}
			System.out.println("DatabaseManager: Initial Data inserted");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Public Functions --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Inserts a new record into the bodyweight table
	public boolean insertBodyWeight(float bodyweight) {
	    String sql = "INSERT INTO bodyweight (bodyweight) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setFloat(1,bodyweight);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'bodyweight' - Weight: %f",bodyweight));
	    return true;
	}
	public boolean insertWeight(String name) {
	    String sql = "INSERT INTO weight (weight_name) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1,name);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'weight' - Weight: %S",name));
	    return true;
	}
	public boolean insertCardio(String name) {
	    String sql = "INSERT INTO cardio (cardio_name) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1,name);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'cardio' - Cardio: %S",name));
	    return true;
	}
	public boolean insertWorkout() {
	    String sql = "INSERT INTO workout DEFAULT VALUES";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'workout'"));
	    return true;
	}
	public boolean insertWeightSet(int weight_id, int workout_id, float masslb, int reps) {
	    String sql = "INSERT INTO weightset (weight_id,workout_id,masslb,reps) VALUES (?,?,?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1,weight_id);
	        pstmt.setInt(2,workout_id);
	        pstmt.setFloat(3,masslb);
	        pstmt.setInt(4,reps);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'weightset' - WeightID: %d, WorkoutID: %d, Mass: %f, Reps: %d",weight_id,workout_id,masslb,reps));
	    return true;
	}
	public boolean insertCardioSet(int cardio_id, int workout_id, float distmi, int secs) {
	    String sql = "INSERT INTO cardioset (cardio_id,workout_id,distmi,secs) VALUES (?,?,?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1,cardio_id);
	        pstmt.setInt(2,workout_id);
	        pstmt.setFloat(3,distmi);
	        pstmt.setInt(4,secs);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'cardioset' - CardioID: %d, WorkoutID: %d, DistMi: %f, Seconds: %d",cardio_id,workout_id,distmi,secs));
	    return true;
	}
	
	
	public String toStringBodyWeight() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bodyweight;");
			while(rs.next()) {
				int bodyweight_id = rs.getInt("bodyweight_id");
				String time = rs.getString("time");
				float weight = rs.getFloat("bodyweight");
				sb.append(String.format("BodyWeightID: %d, Time: %s, BodyWeight: %f\n",bodyweight_id,time,weight));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringWeight() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM weight;");
			while(rs.next()) {
				int weight_id = rs.getInt("weight_id");
				String weight_name = rs.getString("weight_name");
				sb.append(String.format("WeightID: %d, WeightName: %s\n",weight_id,weight_name));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringCardio() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cardio;");
			while(rs.next()) {
				int cardio_id = rs.getInt("cardio_id");
				String cardio_name = rs.getString("cardio_name");
				sb.append(String.format("CardioID: %d, CardioName: %s\n",cardio_id,cardio_name));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringWorkout() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM workout;");
			while(rs.next()) {
				int workout_id = rs.getInt("workout_id");
				String time = rs.getString("time");
				sb.append(String.format("WorkoutID: %d, Time: %s\n",workout_id,time));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringWeightSet() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM weightset;");
			while(rs.next()) {
				int weightset_id = rs.getInt("weightset_id");
				int weight_id = rs.getInt("weight_id");
				int workout_id = rs.getInt("workout_id");
				float masslb = rs.getFloat("masslb");
				int reps = rs.getInt("reps");
				sb.append(String.format("WeightSetId: %d, WeightId: %d, WorkoutID: %d, MassLB: %.2f, Reps: %d\n",weightset_id,weight_id,workout_id,masslb,reps));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringCardioSet() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cardioset;");
			while(rs.next()) {
				int cardioset_id = rs.getInt("cardioset_id");
				int cardio_id = rs.getInt("cardio_id");
				int workout_id = rs.getInt("workout_id");
				float distmi = rs.getFloat("distmi");
				int secs = rs.getInt("secs");
				sb.append(String.format("CardioSetId: %d, CardioId: %d, WorkoutID: %d, DistanceMiles: %.2f, Seconds: %d\n",cardioset_id,cardio_id,workout_id,distmi,secs));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	
	
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
