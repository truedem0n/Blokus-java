import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.Color;

public class TurnManagerTest {
	//Initialize variables to construct a Game object:
	private final GUI frame = new GUI();
	private final String[][] boardState = new String[20][20];
	private final Map<String, String> settings = new HashMap<String, String>();
	private final Game gui = new Game(frame, boardState, settings); //Construct game object
	//Initialize variable to construct a TurnManager test object:
	private final String[] playerLabels = {"Atul", "Devin", "Dylan", "Farhan"};
	private final JLabel turnLabel = new JLabel();
	//Four Player objects to represent one each of four test players:
	private final shapesList testPlayer1 = new Player(Color.red);
	private final shapesList testPlayer2 = new Player(Color.blue);
	private final shapesList testPlayer3 = new Player(Color.green);
	private final shapesList testPlayer4 = new Player(Color.yellow);
	private final shapesList[] players = {testPlayer1, testPlayer2, testPlayer3, testPlayer4}; //Collect player objects
	private final TurnManager testObj = new TurnManager(players, playerLabels, turnLabel, gui); //Construct test object
	
	/*
	 * Tests that testObj.nextPlayer() successfully updates the current player to
	 * the next available player.
	 */
	@Test
	public void testNextPlayer() {
		//Check current player is player #1:
		assertEquals("Should be equal to first player", testPlayer1, testObj.getCurrentPlayer());
		//Update current player:
		testObj.nextPlayer();
		//Check that current player is now player #2:
		assertEquals("Should be equal to second player", testPlayer2, testObj.getCurrentPlayer());
	}
	
	/*
	 * Tests that testObj.getCurrentPlayer() returns the Player object whose turn it is.
	 */
	@Test
	public void testGetCurrentPlayer() {
		//Check that the current player is player #1, as expected when program starts (player #1 always goes first)
		assertEquals("Should be equal to first player", testPlayer1, testObj.getCurrentPlayer());
	}
	
	/*
	 * Tests that testObj.setPlayingAtBoard() successfully updates each Player object
	 * with the GameBoard object that they are associated with.
	 */
	@Test
	public void testSetPlayingAtBoard() {
		//Initialize a test GameBoard object:
		GameBoard testBoard = new GameBoard(20, testObj, boardState);
		//Call setPlayingAtBoard with test GameBoard object:
		testObj.setPlayingAtBoard(testBoard);
		//Test that the GameBoard object associated with each player is testBoard:
		assertEquals("Should equal testBoard", testBoard, testPlayer1.getPlayingAtBoard());
		assertEquals("Should equal testBoard", testBoard, testPlayer2.getPlayingAtBoard());
		assertEquals("Should equal testBoard", testBoard, testPlayer3.getPlayingAtBoard());
		assertEquals("Should equal testBoard", testBoard, testPlayer4.getPlayingAtBoard());
	}
	
	/*
	 * Tests that testObject.getSurrenderingTextForCurrentPlayer() 
	 * successfully returns the "surrender" message for the current player.
	 */
	@Test
	public void testGetSurrenderingTextForCurrentPlayer() {
		//Current player is player #1, if methods are ran independently
		String testString = ("Player" + " " + ((0) + 1) + " surrenders."); //Expected return string for player #1
		//Test that return string matches above string
		assertEquals("Should equal testString", testString, testObj.getSurrenderingTextForCurrentPlayer());
		//Update current player:
		testObj.nextPlayer();
		//Current player should now be player #2
		String testString2 = ("Player" + " " + ((1) + 1) + " surrenders."); //Expected return string for player #2
		//Test:
		assertEquals("Should equal testString2", testString2, testObj.getSurrenderingTextForCurrentPlayer());
	}

}
