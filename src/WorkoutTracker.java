import java.io.FileNotFoundException;

public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");

    	StrengthLevel sl = null;
    	
    	
		try {
			sl = new StrengthLevel();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	System.out.println(sl.getStrengthLevelDescription('M', 5, 'S', 170, 775));
    	
    	
    	
    	
    	
    }
}
