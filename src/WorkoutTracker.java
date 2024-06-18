
import java.util.Random;
import java.util.Date;

public class WorkoutTracker {

    public static void main(String[] args) {
    	System.out.println("Starting Workout Tracker!");
    	DatabaseManager dbm = new DatabaseManager();
    	
    	Random r = new Random();
    	dbm.emptyBodyWeight();
    	
    	for(int i = 0; i < 5; i++) {
        	dbm.insertBodyWeight((float)r.nextGaussian(170.0, 5.0));
        	sleep((int)r.nextGaussian(2000,250));
    	}
    	
    	
    	TwoArray<Date,Float> tarr = dbm.getBodyWeightData();
    	System.out.println(tarr.getX()[0]);
    	
    	for(Date d : tarr.getX()) {
    		System.out.print(d.toString());
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
