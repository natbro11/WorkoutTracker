import java.io.FileNotFoundException;

public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");
    	DatabaseManager dbm = new DatabaseManager();
    	
    	System.out.println(dbm.toStringBodyWeight());
    	
    	dbm.insertWeightSet(1,2,165,5);
    	dbm.insertWeightSet(1,2,170,5);
    	dbm.insertWeightSet(1,2,185,5);
    	dbm.insertWeightSet(1,2,190,5);
    	dbm.insertWeightSet(1,2,195,5);
    	dbm.insertCardioSet(1,2,165,5);
    	dbm.insertCardioSet(1,2,170,5);
    	dbm.insertCardioSet(1,2,185,5);
    	dbm.insertCardioSet(1,2,190,5);
    	dbm.insertCardioSet(1,2,195,5);
    	
    	System.out.println(dbm.toStringWeightSet());
    	System.out.println(dbm.toStringCardioSet());
    	
    	
		try {
			StrengthLevel sl = new StrengthLevel();
			System.out.println(sl.getStrengthLevelDescription('M', 5, 'S', 170, 224));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
    public static void sleep(int ms) {
    	try {
			Thread.sleep(ms);
		}
    	catch (InterruptedException e1) {
			e1.printStackTrace();
		}
    }
}
