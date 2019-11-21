import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShapeButtonTest {

	@Test
	void getIndexTest() {
		
		shapeButton test = new shapeButton(5);
		int output = test.getIndex();
		assertEquals(5,output);
		
		
		
	}

}
