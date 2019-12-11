/**
 * Data writer class contains functions that will store the formated data that was pulled from the URl into a text or JSON file format.
 * @author NP
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Writer {

/**
 * saveToJSON function will collect the data from ArrayList and store it into a new JSON file.
 * @param fname
 * @param prezList
 * @return
 */
	@SuppressWarnings("unchecked") // turned off the bugging warnings.
	
	public static boolean saveToJSON(File fname, ArrayList<President> prezList) {
    	try {
    		
    	// print writer will be used to write data to JSON file
    		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fname)));
    	// create a JSON object for each element	
    		JSONObject memObj;
    		JSONArray jray= new JSONArray(); // JSON array to store JSON objects
    		
    		for (President p : prezList) {
    			// add values to the JSON object
    			memObj = new JSONObject();
    			
    			// add values to the object 
    			memObj.put("President", p.getName());
    			memObj.put("YearBegin",p.getYearBegin());
    			memObj.put("YearEnd", p.getYearEnd());
    			
    			jray.add(memObj);
    		}
          // create an outer Json object 
    		JSONObject out = new JSONObject();
    		out.put("List of Presidents of the United States", jray); 
    		
    		pw.println(out.toJSONString());  
    		pw.close();
    		return true;
    	}catch(Exception ex) 
    	{
    		return false;
    	}
	}
/** saveToText function will collect the data from ArrayList and store it into a new text file.
 * 
 * @param presidentList
 * @param fname
 * @return
 */
   public static boolean saveToText(ArrayList<President> presidentList, File fname) 
	{
		    try 
				{
		    	// file writer to store data into a file
		    	FileWriter fw = new FileWriter(fname);
			
			fw.write("PRESIDENT				  TERM BEGAN		    TERM ENDED"+ "\n\n");
			
			for (President p: presidentList) // iterate through the president List, get the data and write it to the file. 
			{
			fw.write(String.valueOf(p)+ "\n"); 
           		
           	 }
           
			fw.close();  // close the fileWriter to avoid leakage of data
			return true;
		        } catch (Exception ex) 
				{
			      return false;
		        }
	}
	}

