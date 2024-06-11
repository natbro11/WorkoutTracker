
// imports
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import java.io.FileNotFoundException;

// Class handles determining the relative strength of lifters in 4 major lifts 
public class StrengthLevel {
	
	// Stores strength tables in a hashmap for searching
	Map<String,int[][]> hm;
	
	// Constructor initializes hashmap and loads the tables from csv files. Fails if files are missing
	public StrengthLevel() throws FileNotFoundException {
		hm = new HashMap<String,int[][]>();
		
		// Structured data
		String[] names = {"M1B","M1D","M1O","M1S","M5B","M5D","M5O","M5S","F1B","F1D","F1O","F1S","F5B","F5D","F5O","F5S"};
		String[] files = {
				"data/StrengthLevel/M1B.csv",
				"data/StrengthLevel/M1D.csv",
				"data/StrengthLevel/M1O.csv",
				"data/StrengthLevel/M1S.csv",
				"data/StrengthLevel/M5B.csv",
				"data/StrengthLevel/M5D.csv",
				"data/StrengthLevel/M5O.csv",
				"data/StrengthLevel/M5S.csv",
				"data/StrengthLevel/F1B.csv",
				"data/StrengthLevel/F1D.csv",
				"data/StrengthLevel/F1O.csv",
				"data/StrengthLevel/F1S.csv",
				"data/StrengthLevel/F5B.csv",
				"data/StrengthLevel/F5D.csv",
				"data/StrengthLevel/F5O.csv",
				"data/StrengthLevel/F5S.csv"};
		
		// Throws exception if files are missing
		if(!verifyFileExistence(files)) {
			throw new FileNotFoundException("Critical StrengthLevel Files Missing: Unable to create object");
		}
		
		// Loads files into hashmap
		for(int i = 0; i < names.length; i++) {
			hm.put(names[i], loadCSVIntoIntArray(files[i]));
		}
	}
	
	// Public Functions -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Takes parameters and returns a short String classifying the lifters competence 
	public String getStrengthLevelDescription(char mf, int reps, char type, int bw, int lift) {
		return getDescriptionFromLevel(getStrengthLevel(mf,reps,type,bw,lift));
	}
	
	// Takes parameters and returns a long String describing the lifters competence
	public String getStrengthLevelExplanation(char mf, int reps, char type, int bw, int lift) {
		return getExplanationFromLevel(getStrengthLevel(mf,reps,type,bw,lift));
	}
	
	//Private Functions -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// Returns a int describing the system's classification of the lifters competence
	private int getStrengthLevel(char mf, int reps, char type, int bw, int lift) {
		String table = "" + Character.toUpperCase(mf) + reps + Character.toUpperCase(type);
		int[][] data = hm.get(table);
		int[] row;
		int level = 0;
        int lowIndex = getLowIndex(bw);
        int highIndex = getHighIndex(bw); 
        if(lowIndex == highIndex) {
        	row = data[lowIndex];
        }
        else {
        	int[] lowRow = data[lowIndex];
        	int[] highRow = data[highIndex];
        	row = new int[lowRow.length]; 
        	for(int i = 0; i < lowRow.length; i++) {
        		float l = (bw % 50.0f) / 50.0f;
        		float h = (50.0f - (bw % 50.0f)) / 50.0f;
        		row[i] = (int)((lowRow[i] * h) + (highRow[i] * l));
        	}
        }
        for(int i = 0; i < row.length; i++) {
    		if(lift >= row[i]) {
    			level++;
    		}
        }
        return level;
	}
	
	//Returns a short String describing the relative competence of the lifter from the int level classification 
	private String getDescriptionFromLevel(int level) {
		switch(level) {
			case 0:
				return "Subpar";
			case 1:
				return "Untrained";
			case 2:
				return "Novice";
			case 3:
				return "Intermediate";
			case 4:
				return "Proficient";
			case 5:
				return "Advanced";	
			case 6:
				return "Exceptional";
			case 7:
				return "Elite";		
			case 8:
				return "World Class";
			default:
				return "Fcuk!";
		}
	}
	
	//Returns a longer String explaining the meaning of the relative competence description of the lifter from the int level classification 
	private String getExplanationFromLevel(int level) {
		switch(level) {
			case 0:
				return "Weaker than the average untrained individual.";
			case 1:
				return "Similar strength to the average untrained individual";
			case 2:
				return "Stronger than the average untrained individual";
			case 3:
				return "Lifter has likely been training casually for a few years";
			case 4:
				return "Lifter has likely been training consistently for several years";
			case 5:
				return "Lifter is among the strongest in a typical gym";	
			case 6:
				return "Lifter has likely undergone many years of structured training";
			case 7:
				return "Lifter has likely undergone a lifetime of structured training and dieting";		
			case 8:
				return "Lifter is among the strongest in the world";
			default:
				return "Fcuk!";
		}
	}
	
	// Utility. Returns the lower row index for interpolating the classification thresholds
	private int getLowIndex(int bw) {
        int lowValue = Math.min(Math.max((50 * (bw / 50)), 100),400);
        int lowIndex = Math.max((lowValue - 100) / 50,0);
        return lowIndex;
	}
	
	// Utility. Returns the higher row index for interpolating the classification thresholds
	private int getHighIndex(int bw) {
        int highValue = Math.max(Math.min((50 * ((bw + 49) / 50)), 400),100);
        int highIndex = Math.min((highValue - 100) / 50,6);
        return highIndex;
	}
	
	// Utility. Simply prints a 2D int[]
    public void print2DIntArray(int[][] ar) {
    	for(int[] i : ar) {
    		for(int j : i) {
    			System.out.print("" + j + " ");
    		}
    		System.out.println();
    	}
    }
	
    // CSV Utility Functions --------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Takes a csv file path and loads the only the internal (excluding first row and column) values into a returned int[][]
    private int[][] loadCSVIntoIntArray(String filePath){
    	return convertToIntArray(loadCSVInto2DArray(filePath));
    }
    
    //Takes a file path and loads the csv file into a string[][]
    private String[][] loadCSVInto2DArray(String filePath) {
	    List<String[]> dataList = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] values = line.split(",");
	            dataList.add(values);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return dataList.toArray(new String[0][]);
    }
    
    //Takes a csv style String[][] extracts all data from rows and columns other than the first and returns an int[][]
    private int[][] convertToIntArray(String[][] stringArray) {
		if (stringArray.length <= 1 || stringArray[0].length <= 1) {
			return new int[0][0]; // Return empty array if input is too small
		}
        int numRows = stringArray.length - 1;
        int numCols = stringArray[0].length - 1;
        int[][] intArray = new int[numRows][numCols];
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numCols; j++) {
                intArray[i - 1][j - 1] = Integer.parseInt(stringArray[i][j]);
            }
        }

        return intArray;
    }

	// Verifies that all csv files exist
	private boolean verifyFileExistence(String[] paths) {
		for (String filePath : paths) {
			File file = new File(filePath);
			if (!file.exists()) {
				return false;
			}
		}
		return true;
	}	  
}