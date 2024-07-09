


public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");
    	DatabaseManager dbm = new DatabaseManager();
    	ChartGenerator cg = new ChartGenerator(dbm);
    	
    	//dbm.insertWorkout();
    	//dbm.toStringWorkout();
    	
    	
    	System.out.println(dbm.toStringWeight());
    	System.out.println(dbm.toStringWeightSet());
    	
    	
    	
    	
    	
    	
    	
    	
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
