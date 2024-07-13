
package main.java.com.drawingblanks.workouttracker;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

// Manages all interaction with the internal database
public class DatabaseManager {
	private static final String DATABASE_URL = "jdbc:sqlite:src/main/resources/database/WorkoutTracker.db";
	private Connection conn;
	// Constructor
	public DatabaseManager() {
		this.connect();
	    this.createTables();
	    this.insertDefaultData();
	}	
	
	
	// Initialization Functions -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
			"CREATE TABLE IF NOT EXISTS body_weight (body_weight_id INTEGER PRIMARY KEY AUTOINCREMENT, time DATETIME DEFAULT CURRENT_TIMESTAMP UNIQUE, body_weight FLOAT NOT NULL);",
			
			"CREATE TABLE IF NOT EXISTS workout  (workout_id INTEGER PRIMARY KEY AUTOINCREMENT, time DATETIME DEFAULT CURRENT_TIMESTAMP);",
			
			"CREATE TABLE IF NOT EXISTS weight_exercise (weight_exercise_id INTEGER PRIMARY KEY AUTOINCREMENT, weight_exercise_name TEXT NOT NULL UNIQUE);",
			"CREATE TABLE IF NOT EXISTS cardio_exercise (cardio_exercise_id INTEGER PRIMARY KEY AUTOINCREMENT, cardio_exercise_name TEXT NOT NULL UNIQUE);",
			
			"CREATE TABLE IF NOT EXISTS weight_set (weight_set_id INTEGER PRIMARY KEY AUTOINCREMENT, weight_exercise_id INTEGER, workout_id INTEGER, mass FLOAT NOT NULL, reps INT NOT NULL, FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE, FOREIGN KEY (weight_exercise_id) REFERENCES weight_exercise(weight_exercise_id) ON DELETE CASCADE);",
			"CREATE TABLE IF NOT EXISTS cardio_set (cardio_set_id INTEGER PRIMARY KEY AUTOINCREMENT, cardio_exercise_id INTEGER, workout_id INTEGER, dist FLOAT NOT NULL, secs INT NOT NULL, FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE, FOREIGN KEY (cardio_exercise_id) REFERENCES cardio_exercise(cardio_exercise_id) ON DELETE CASCADE);"
		};
		try (Statement stmt = conn.createStatement()) {
		    for (String sql : createTableStatements) {
		        stmt.execute(sql);
		        // activate foreign key constraints
		        stmt.execute("PRAGMA foreign_keys = ON;");
		    }
		    System.out.println("DatabaseManager: Tables Created");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}	
	// Initialize few common exercises
	private void insertDefaultData() {
		String[] insertStatements = {
			"INSERT INTO weight_exercise (weight_exercise_name) VALUES ('Barbell Bench Press');",
			"INSERT INTO weight_exercise (weight_exercise_name) VALUES ('Barbell Deadlift');",
			"INSERT INTO weight_exercise (weight_exercise_name) VALUES ('Barbell Overhead Press');",
			"INSERT INTO weight_exercise (weight_exercise_name) VALUES ('Barbell Squat');",
			"INSERT INTO cardio_exercise (cardio_exercise_name) VALUES ('Treadmill');",
			"INSERT INTO cardio_exercise (cardio_exercise_name) VALUES ('Stationary Bike');",
			"INSERT INTO cardio_exercise (cardio_exercise_name) VALUES ('Elliptical');",
			"INSERT INTO cardio_exercise (cardio_exercise_name) VALUES ('Rowing Machine');"
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
	
	
	// Database File Functions - Caution!  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// exports the current database to the backup folder
	public void exportDatabase() {
		try {
			File source = new File("src/main/resources/database/WorkoutTracker.db");
			File backup = new File(String.format("src/main/resources/backup/WorkoutTracker_%d.db", System.currentTimeMillis()));
			Files.copy(source.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("DatabaseManager: Database backup successful");
		} catch (IOException e) {
			System.out.println("DatabaseManager: Error during database backup");
			e.printStackTrace();
		}
	}

	
	// Insertion Functions ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	// Inserts a new record into the body_weight table. Each representing a single record of ones body weight
	public boolean insertBodyWeight(float body_weight) {
	    String sql = "INSERT INTO body_weight (body_weight) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setFloat(1,body_weight);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'body_weight' - body_weight: %f",body_weight));
	    return true;
	}
	// Inserts a new record into the weight_exercise table. Each representing a single type of weight lifting exercise
	public boolean insertWeightExercise(String weight_exercise_name) {
	    String sql = "INSERT INTO weight_exercise (weight_exercise_name) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1,weight_exercise_name);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'weight_exercise' - weight_exercise_name: %s",weight_exercise_name));
	    return true;
	}
	// Inserts a new record into the cardio_exercise table. Each representing a single type of cardio exercise.
	public boolean insertCardioExercise(String cardio_exercise_name) {
	    String sql = "INSERT INTO cardio_exercise (cardio_exercise_name) VALUES (?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1,cardio_exercise_name);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'cardio_exercise' - cardio_exercise_name: %s",cardio_exercise_name));
	    return true;
	}
	// Inserts a new record into the workout table. Each representing "a trip to the gym". Individual exercise events will must reference a workout.
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
	// Inserts a new record into the WeightSet table. Each representing a number of repetitions of a 'weight' exercise.
	public boolean insertWeightSet(int weight_exercise_id, int workout_id, float mass, int reps) {
	    String sql = "INSERT INTO weight_set (weight_exercise_id,workout_id,mass,reps) VALUES (?,?,?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1,weight_exercise_id);
	        pstmt.setInt(2,workout_id);
	        pstmt.setFloat(3,mass);
	        pstmt.setInt(4,reps);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'weight_set' - weight_exercise_id: %d, workout_id: %d, mass: %f, reps: %d",weight_exercise_id,workout_id,mass,reps));
	    return true;
	}
	// Inserts a new record into the CardioSet table. Each representing an amount of a 'cardio' exercise.
	public boolean insertCardioSet(int cardio_exercise_id, int workout_id, float dist, int secs) {
	    String sql = "INSERT INTO cardio_set (cardio_exercise_id,workout_id,dist,secs) VALUES (?,?,?,?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1,cardio_exercise_id);
	        pstmt.setInt(2,workout_id);
	        pstmt.setFloat(3,dist);
	        pstmt.setInt(4,secs);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
        System.out.println(String.format("Inserted Into Table 'cardio_set' - cardio_exercise_id: %d, workout_id: %d, dist: %f, seconds: %d",cardio_exercise_id,workout_id,dist,secs));
	    return true;
	}
	
	// Searching Functions ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // get an id given a weight_exercise_name
    public int getWeightExerciseId(String weight_exercise_name) {
        String query = "SELECT weight_exercise_id FROM weight_exercise WHERE weight_exercise_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, weight_exercise_name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("weight_exercise_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    // get an id given a cardio_exercise_name
    public int getCardioExerciseId(String cardio_exercise_name) {
        String query = "SELECT cardio_exercise_id FROM cardio_exercise WHERE cardio_exercise_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cardio_exercise_name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cardio_exercise_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    // returns the highest (most recent workout) workout_id in the table (useful for testing)
    public int getMaxWorkoutId() {
        String query = "SELECT MAX (workout_id) AS workout_id FROM workout";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("workout_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
	
	// Deletion Function ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void emptyAll() {
		emptyBodyWeight();
		emptyWeightExercise();
		emptyCardioExercise();
		emptyWorkout();
		emptyWeightSet();
		emptyCardioSet();
	}
	public void emptyBodyWeight() {
		String sql = "DELETE FROM body_weight;";
        try (
            Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the body_weight table.");
        }
        catch (SQLException e) {
        	
        }
    }
	public void emptyWeightExercise() {
		String sql = "DELETE FROM weight_exercise;";
        try (
            Statement stmt = conn.createStatement()) {
          	int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the weight_exercise table.");
        }
        catch (SQLException e) {
        	
        }
    }
	public void emptyCardioExercise() {
		String sql = "DELETE FROM cardio_exercise;";
        try (
            Statement stmt = conn.createStatement()) {
          	int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the cardio_exercise table.");
        }
        catch (SQLException e) {
        	
        }
    }	
	public void emptyWorkout() {
		String sql = "DELETE FROM workout;";
        try (
            Statement stmt = conn.createStatement()) {
          	int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the workout table.");
        }
        catch (SQLException e) {
        	
        }
    }	
	public void emptyWeightSet() {
		String sql = "DELETE FROM weight_set;";
        try (
            Statement stmt = conn.createStatement()) {
          	int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the weight_set table.");
        }
        catch (SQLException e) {
        	
        }
    }	
	public void emptyCardioSet() {
		String sql = "DELETE FROM cardio_set;";
        try (
            Statement stmt = conn.createStatement()) {
          	int affectedRows = stmt.executeUpdate(sql);
            System.out.println(affectedRows + " rows were deleted from the cardio_set table.");
        }
        catch (SQLException e) {
        	
        }
    }	
	
	
	// Data Pull Functions ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Returns a TwoArray of Date and Floats representing body weights at various dates.
	public TwoArray<Date,Float> getBodyWeightData() {
	    List<Date> times = new ArrayList<>();
	    List<Float> weights = new ArrayList<>();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT time, body_weight FROM body_weight;");
	        while (rs.next()) {
	            try {
	                Date date = dateFormat.parse(rs.getString("time"));
	                times.add(date);
	                weights.add(rs.getFloat("body_weight"));
	            } 
		        catch (ParseException e) {
					System.out.println("Error parsing date: " + e.getMessage());
					continue;
	            }
	        }
	    } 
	    catch (SQLException e) {
	    	System.out.println("SQL Error: " + e.getMessage());
	    }    
	    return new TwoArray<>(times.toArray(new Date[0]),weights.toArray(new Float[0]));
	}
	
	
	// toString Functions -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public String toStringBodyWeight() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM body_weight;");
			while(rs.next()) {
				int body_weight_id = rs.getInt("body_weight_id");
				String time = rs.getString("time");
				float weight = rs.getFloat("body_weight");
				sb.append(String.format("body_weight_id: %d, time: %s, body_weight: %f\n",body_weight_id,time,weight));
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
				sb.append(String.format("workout_id: %d, Time: %s\n",workout_id,time));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringWeightExercise() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM weight_exercise;");
			while(rs.next()) {
				int weight_exercise_id = rs.getInt("weight_exercise_id");
				String weight_exercise_name = rs.getString("weight_exercise_name");
				sb.append(String.format("weight_exercise_id: %d, weight_exercise_name: %s\n",weight_exercise_id,weight_exercise_name));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	public String toStringCardioExercise() {
		StringBuilder sb = new StringBuilder();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM cardio_exercise;");
			while(rs.next()) {
				int cardio_exercise_id = rs.getInt("cardio_exercise_id");
				String cardio_exercise_name = rs.getString("cardio_exercise_name");
				sb.append(String.format("cardio_exercise_id: %d, cardio_exercise_name: %s\n",cardio_exercise_id,cardio_exercise_name));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM weight_set;");
			while(rs.next()) {
				int weight_set_id = rs.getInt("weight_set_id");
				int weight_exercise_id = rs.getInt("weight_exercise_id");
				int workout_id = rs.getInt("workout_id");
				float mass = rs.getFloat("mass");
				int reps = rs.getInt("reps");
				sb.append(String.format("weight_set_id: %d, weight_exercise: %d, workout_id: %d, mass: %.2f, reps: %d\n",weight_set_id,weight_exercise_id,workout_id,mass,reps));
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM cardio_set;");
			while(rs.next()) {
				int cardio_set_id = rs.getInt("cardio_set_id");
				int cardio_exercise_id = rs.getInt("cardio_exercise_id");
				int workout_id = rs.getInt("workout_id");
				float dist = rs.getFloat("dist");
				int secs = rs.getInt("secs");
				sb.append(String.format("cardio_set_id: %d, cardio_exercise_id: %d, workout_id: %d, dist: %.2f, secs: %d\n",cardio_set_id,cardio_exercise_id,workout_id,dist,secs));
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
