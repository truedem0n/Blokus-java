import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void getColorTest() {
		Player test = new Player(Color.green);
		//test.setBackground(Color.red);
		Color color = test.getColor();
		assertEquals(Color.green, color);
		
	}
	
	@Test
	void hasTakenCornerTest(){
		
		Player test = new Player(Color.green);
		//test.setBackground(Color.red);
		test.setHasTakenCorner(true);
		boolean output = test.hasTakenCorner();
		assertEquals(false,output);
	}
	
	
	@Test
	void isStillPlayingTest() {
		
		Player test =new Player (Color.green);
		test.setStillPlaying(true);
		boolean output = test.isStillPlaying();
		assertEquals(true,output);
		
		}
	
	@Test
	void getplacedShapesIndexesTest() {
		Player test = new Player (Color.green);
		String output = test.getPlacedShapesIndexes();
		assertEquals("", output);
	
		
	}
	
	@Test
	void setHasTakenCornerTest() {
		
		boolean input = true;
		boolean output = false;
		
		Player test = new Player(Color.green);
		test.setHasTakenCorner(input);
		
		assertEquals(test.hasTakenCorner(),output);
		
	}
	
	@Test
	void setStillPlaying()
	{
		boolean input = true;
		boolean output = true;
		
		Player test = new Player(Color.green);
		test.setStillPlaying(input);
		
		assertEquals(test.isStillPlaying(),output);
	}
	
	
	
	
	
		
		
		
		
		
		
	

}
