import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.Assert;

import org.junit.jupiter.api.Test;

class customButtonTest {

	@Test
	void getColorTest() {
		
		customButton test = new customButton(2,2);
		test.setBackground(Color.green);
		Color output = test.getColor();
		assertEquals(Color.green,output);
		
	}
	
	
	@Test
	void getPosTest() {
		
		customButton test = new customButton(2,2);
		int[] output = new int[] {2,2};
		Assert.assertArrayEquals(test.getPos(), output);
		 
		}
	
	@Test
	void isTakenTest() {
		customButton test = new customButton(2,2);
		test.setTaken(true);
		boolean output = test.isTaken();
		assertEquals(true, output);
	}
	
	@Test
	void satTakentest() {
		
		boolean input = true;
		boolean output = true;
		
		customButton test = new customButton(2,2);
		test.setTaken(input);
		assertEquals(test.isTaken(), output);
	}

}
