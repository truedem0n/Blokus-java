/**
 * 
 * @author Ricky
 *
 */

/*
* string = "row[0][0] row[0][1] ... , row[1][0] row[1][1] ..."
*  where row[n][m] can either be 'r' or '0', r=colour and 0=not taken
*/

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataManager {
	private static final String FILE_NAME = "save_data.json";
	private static File gameData = new File(FILE_NAME);
	
	@SuppressWarnings("unchecked")
	public static void save(String[][] shapes) {
		//Takes array from getBoard()

    //Create JSON objects:
    /*
		JSONObject blockDetails = new JSONObject();
		blockDetails.put("shape_b", "(0, 0)");
		
		JSONObject blockObject = new JSONObject();
		blockObject.put("block_one", blockDetails);
    */
    int size = shapes.length;
    
    /*
    JSONObject arraySize = new JSONObject();
    arraySize.put("Size", String.valueOf(size));
    */
    
    JSONObject shapeArray = new JSONObject();
    for(int i = 0; i < size; i++){
      for(int j = 0; j < size; j++){
        shapeArray.put((i+", "+j), (shapes[i][j]));
      }
    }
    shapeArray.put("Size", String.valueOf(size));

    JSONObject saveObj = new JSONObject();
    saveObj.put("Data", shapeArray);
		
    //Add JSON objects to a JSON array:
    /*
		JSONArray blockList = new JSONArray();
		blockList.add(blockObject);
		*/
    JSONArray saveData = new JSONArray();
    saveData.add(saveObj);

		try {
			FileWriter file = new FileWriter(gameData);
			//Write the JSON array to file:
			//file.write(blockList.toJSONString());
			file.write(saveData.toJSONString());
			file.flush();
			file.close();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void load() {
		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(gameData);
			Object obj = parser.parse(reader);
			
			JSONArray blockList = (JSONArray) obj;
			System.out.println(blockList);

			//Iterate over JSON object:
			blockList.forEach(shape -> parseShape((JSONObject) shape));

		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

  private static String[][] parseShape(JSONObject obj){
	  JSONObject loadObj = (JSONObject) obj.get("Data");
	  
	  int size = Integer.parseInt((String) loadObj.get("Size"));
	  System.out.println("Size :"+String.valueOf(size));
	  String[][] returnArray = new String[size][size];
	  for(int i = 0; i < size; i++){
      	for(int j = 0; j < size; j++){
        	//returnArray[i][j] = (String) loadObj.get(String.valueOf(i)+", "+String.valueOf(j));
    	  	System.out.println(String.valueOf(i)+", "+String.valueOf(j)+" : "+(String) loadObj.get(String.valueOf(i)+", "+String.valueOf(j)));
      	}
	  }

	  return returnArray;
  }
	
	public static void test() {
		if(gameData.exists()) {
			System.out.println("Path" + gameData.getAbsolutePath());
		} else {
			System.out.println("Rip");
		}
	}
}
