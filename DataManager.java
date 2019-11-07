/**
 * 
 * @author Ricky
 *
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
	private static File boardSaveData = new File("session_data.json");
	private static File gameSaveData = new File("settings_data.json");
	
	@SuppressWarnings("unchecked")
	public static String save(String[][] shapes) {

		// when saving a file we need to see if the file already exists or not and the status of save
		String status="";
		//Takes array from getBoard()
		int size = shapes.length; //Get the size of the nxn board
		
		//Convert each index into json map:
		JSONObject boardObj = new JSONObject(); //Create JSONObject to represent board state
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				boardObj.put((i+", "+j), (shapes[i][j]));
			}
		}
		boardObj.put("Size", String.valueOf(size)); //Store the size of the board as well, needed for load

		//Nest the previous JSONObject into an outer JSONObject (for easy finding):
		JSONObject saveObj = new JSONObject(); //Create outer JSONObject
		saveObj.put("Data", boardObj); //Add previous JSONObject to outer Object
		
		//Add outer JSONObject to a JSONArray (the JSONArray gets stored in the file):
		JSONArray saveData = new JSONArray(); //Create JSONArray
		saveData.add(saveObj); //Add outer JSONObject to JSONArray

		//Try to write JSONArray to file:
		try {
			FileWriter file = new FileWriter(boardSaveData); //Create FileWriter
			file.write(saveData.toJSONString()); //Write the JSON array to file
			//Take care of file-resource management:
			file.flush();
			file.close();
			//Catch exception (not important):
			status="sucess";
		} catch (IOException e) {
			e.printStackTrace();
			status="failed";
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public static String load() {
		JSONParser parser = new JSONParser(); //Create a JSONParser
		String status="";
		//Try to read data file:
		try {
			FileReader reader = new FileReader(boardSaveData); //Open file
			Object obj = parser.parse(reader); //Gather JSON data into Object
			JSONArray blockList = (JSONArray) obj; //Convert Object into JSONObject
			//Iterate over JSON object:
			blockList.forEach(shape -> parseGameData((JSONObject) shape)); //There's only one JSONObject, see parse function
			//Catch exceptions (not important):

			status="sucess";
		} catch (Exception e) {
			e.printStackTrace();
			status="failed";
		}
		return status;
	}

  private static String[][] parseGameData(JSONObject obj){
	  JSONObject loadObj = (JSONObject) obj.get("Data"); //Grab the outer JSONObject
	  int size = Integer.parseInt((String) loadObj.get("Size")); //Grab the value of size that was stored in save()
	  String[][] returnArray = new String[size][size]; //Initialize board-state array to return
	  //Grab the corresponding JSON map from the JSONObject for each index of the return array:
	  for(int i = 0; i < size; i++){
      	for(int j = 0; j < size; j++){
        	returnArray[i][j] = (String) loadObj.get(String.valueOf(i)+", "+String.valueOf(j));
      	}
	  }

	  return returnArray;
  }
  
  public static void updateGameSettings(String[] settings) {
	  //Will update the save file for game settings
  }
  
  public static String[] getGameSettings() {
	  //Will get saved game settings
	  String[] returnArray = new String[3];
	  return returnArray;
  }
  
  public static void autoSave(String[][] boardState) {
	  //Might be needed for autosave feature, but probably won't be needed
  }
}
