
package main.java.com.drawingblanks.workouttracker;

import java.util.Random;

public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");
    	DatabaseManager dbm = new DatabaseManager();
    	ChartGenerator cg = new ChartGenerator(dbm);
    	
    	dbm.emptyAll();
    	
    	System.out.println(dbm.toString());
    	
    	dbm.insertWeightExercise("Barbell Bench Press");
    	dbm.insertCardioExercise("Rowing Machine");
    	
    	System.out.println(dbm.toString());
    	
    	dbm.insertWorkout();
    	
    	System.out.println(dbm.toString());
    	
    	System.out.println(dbm.getWeightExerciseId("Barbell Bench Press"));
    	System.out.println(dbm.getCardioExerciseId("Rowing Machine"));
    	
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 20, 45);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 10, 95);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 8, 135);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 8, 145);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 7, 150);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 7, 150);
    	dbm.insertWeightSet(dbm.getWeightExerciseId("Barbell Bench Press"), dbm.getMaxWorkoutId(), 7, 150);
    	
    	dbm.insertCardioSet(dbm.getCardioExerciseId("Rowing Machine"), dbm.getMaxWorkoutId(), 0.5f, 900);
    	
    	System.out.println(dbm.toString());
    	
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    	
    	System.out.println(dbm.toStringWorkout());
    	
    	System.out.println(dbm.toStringWeightSet());
    	System.out.println(dbm.toStringCardioSet());
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    public void bodyWeightTest(DatabaseManager dbm, ChartGenerator cg) {
    	int count = 165;
    	Random r = new Random();
    	dbm.emptyBodyWeight();
    	dbm.toStringBodyWeight();
    	for(int i = 0; i < 10; i++) {
	    	dbm.insertBodyWeight((float)r.nextGaussian(count++, 5));
	    	try {
				Thread.sleep((long)r.nextGaussian(1500, 100));
			} 
	    	catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	dbm.toStringBodyWeight();
    	cg.saveChartBodyWeight();
    	dbm.exportDatabase();	
    }
    public void exerciseTest(DatabaseManager dbm) {
    	dbm.insertCardioExercise("Treadmill Sprint");
    	dbm.insertCardioExercise("Treadmill Run");
    	dbm.insertCardioExercise("Treadmill Jog");
    	dbm.insertCardioExercise("Treadmill Walk");
    	
    	dbm.insertWeightExercise("Barbell Bench Press: Wide Grip");
    	dbm.insertWeightExercise("Dumbbell Curl");
    	
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    	
    	dbm.emptyCardioExercise();
    	dbm.emptyWeightExercise();
    	
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    	
    	dbm.insertCardioExercise("Treadmill Sprint");
    	dbm.insertCardioExercise("Treadmill Run");
    	dbm.insertCardioExercise("Treadmill Jog");
    	dbm.insertCardioExercise("Treadmill Walk");
    	
    	dbm.insertWeightExercise("Barbell Bench Press: Wide Grip");
    	dbm.insertWeightExercise("Dumbbell Curl");
    	
    	System.out.println(dbm.toStringWeightExercise());
    	System.out.println(dbm.toStringCardioExercise());
    }
}
