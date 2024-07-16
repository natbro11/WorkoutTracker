
package main.java.com.drawingblanks.workouttracker;

import java.util.Random;

public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");
    	DatabaseManager dbm = DatabaseManager.getInstance();
    	ChartGenerator cg = new ChartGenerator(dbm);
    	
    	//demoBodyWeightChart(dbm, cg);
    	//demoImportExportDatabase(dbm);
    	demoExercise(dbm);
    	
    }
    
    // Empties the database, inserts 15 random BodyWeight records, then saves a chart of the new data.
    public static void demoBodyWeightChart(DatabaseManager dbm, ChartGenerator cg) {
    	dbm.deleteDatabase();
    	int count = 165;
    	Random r = new Random();

    	for(int i = 0; i < 15; i++) {
	    	dbm.insertBodyWeight((float)r.nextGaussian(count++, 5));
	    	try {
				Thread.sleep(1001);
			} 
	    	catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	System.out.println(dbm.toStringBodyWeight());
    	cg.saveChartBodyWeight();
    }
    // Empties the database, inserts 5 random BodyWeight record, then exports a backup, deletes the active database, finally imports the same backup
    public static void demoImportExportDatabase(DatabaseManager dbm) {
    	Random r = new Random();
    	dbm.deleteDatabase();
    	for(int i = 0; i < 5; i++) {
	    	dbm.insertBodyWeight((float)r.nextGaussian(170, 5));
	    	try {
				Thread.sleep(1001);
			} 
	    	catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	System.out.println(dbm.toString());
    	
    	dbm.exportDatabase("backup");
    	
    	dbm.deleteDatabase();
    	
    	System.out.println(dbm.toString());
    	
    	dbm.importDatabase("backup");
    	
    	System.out.println(dbm.toString());
    	System.out.println(dbm.toStringBodyWeight());
    	
    }
    
    public static void demoExercise(DatabaseManager dbm) {
    	// Empty the database
    	dbm.deleteDatabase();
    	System.out.print("\n");
    	
    	// insert new exercises
    	dbm.insertCardioExercise("Treadmill Sprint");
    	dbm.insertCardioExercise("Treadmill Run");
    	dbm.insertWeightExercise("Barbell Bench Press");
    	dbm.insertWeightExercise("Dumbbell Curl");
    	System.out.print("\n");
    	
    	// view the new exercises
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    	
    	// start a new workout
    	dbm.insertWorkout();
    	System.out.print("\n");
    	
    	
    	// view the database
    	System.out.println(dbm.toString());
    	
    	// Assigning individual sets to the workout 
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 20, 45);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 10, 95);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 8, 135);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 8, 145);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 8, 150);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 7, 150);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 6, 150);
    	
    	dbm.insertCardioSet(dbm.getCardioExerciseId("Treadmill Sprint"), dbm.getMaxWorkoutId(), 0.5f, 220);
    	dbm.insertCardioSet(dbm.getCardioExerciseId("Treadmill Run"), dbm.getMaxWorkoutId(), 1.0f, 480);
    	
    	// view the database
    	System.out.println(dbm.toString());
    	
    	System.out.println(dbm.toStringWorkout());
    	
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    	
    	System.out.println(dbm.toStringWeightSet());
    	System.out.println(dbm.toStringCardioSet());
    }
}
