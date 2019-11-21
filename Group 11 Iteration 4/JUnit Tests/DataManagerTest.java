import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class DataManagerTest {
	/*
	 * The 2D array representing a (red) test shape: r 0 0
	 *                                               r r 0
	 *                                               0 r 0
	 * TEST_ARRAY:
	 */
	private final String[][] TEST_ARRAY = {{"r", "0", "0"}, {"r", "r", "0"}, {"0", "r", "0"}};
	
	// The Map<String, String> representing a test set of game settings:
	private final Map<String, String> TEST_MAP = new HashMap<String, String>(); 

	/*
	 * Tests that both DataManager.save(String[][]) saves the given 2D-array to file, then that
	 * DataManager.load() retrieves the stored data and returns it as a 2D-array. Indirectly tests
	 * the parseGameData(JSONObject) method through the load() method.
	 */
	@Test
	public void testLoad() {
		DataManager.save(TEST_ARRAY); //Save the test string to the file
		
		//Test load():
		assertArrayEquals("Should equal the same 2D-array saved", TEST_ARRAY, DataManager.load()); //Grabs board-state file data
	}
	
	/*
	 * Tests that both DataManager.updateGameSettings(Map<String, String>) saves the given map
	 * to file, then that DataManager.getGameSettings() retrieves the stored data and returns
	 * it as a map. Indirectly tests the parseGameSettings(JSONObject) through the getGameSettings()
	 * method.
	 */
	@Test
	public void testGetGameSettings() {
		//Set up test map with some test values:
		TEST_MAP.put("Sound", "false");
		TEST_MAP.put("ColourBlind", "false");
		TEST_MAP.put("Hints", "true");
		DataManager.updateGameSettings(TEST_MAP); //Save the above map to file
				
		//Test GetGameSettings():
		assertEquals("Should equal the same map as was saved", TEST_MAP, DataManager.getGameSettings()); //Grabs game file data
	}
	
	/******************************************* Note *********************************************
	 *  IOExceptions as a result of errors with the loading and saving of data to file            *
	 *   through all above methods never occurred during extensive user testing. Hence, exception *
	 *   tests have been omitted from the JUnit test.                                             *
	 **********************************************************************************************/
}
