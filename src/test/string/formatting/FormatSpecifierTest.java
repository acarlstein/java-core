package string.formatting;

import junit.framework.TestCase;
import org.junit.Test;

public class FormatSpecifierTest extends TestCase
{

	FormatSpecifier formatSpecifier;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
		formatSpecifier = new FormatSpecifier();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetKeyIntegerValue(){
		String result = formatSpecifier.getKeyIntegerValue("A", 1);
		assertEquals(result, "'A' : 1");
	}

	@Test
	public void testGetMedianAsString(){
		int[] values = {2, -6, 11, 13};
		String result = formatSpecifier.getMedianAsString(values);
		assertEquals(result, "2 + -6 + 11 + 13 = 6.50");
	}

	/**
	 *
	 *
	 *   'width': Right justified, spaced-padded, minimum characters to display.
	 *
	 */

	@Test
	public void testRadix()
	{
		assertEquals("050", String.format("%#o", 40));
		assertEquals("0x28", String.format("%#x", 40));
		assertEquals("0X28", String.format("%#X", 40));
	}

	@Test
	public void testFormatFlags(){

		assertEquals("101", String.format("%d", 101));
		assertEquals("-101", String.format("%d", -101));

		// Notice how the alignment gets mess up when we print an extra character
		assertEquals("X:10 Y:11", String.format("X:%d Y:%d", 10, 11));
		assertEquals("X:101 Y:11", String.format("X:%d Y:%d", 101, 11));

		// Space for positive numbers
		assertEquals(" 101", String.format("% d", 101));
		assertEquals("-101", String.format("% d", -101));

		// Show positive sign, always
		assertEquals("+101", String.format("%+d", +101));

		// Using width of 4 characters
		assertEquals("X: 101 Y:  13 Z:   2", String.format("X:%4d Y:%4d Z:%4d", 101, 13, 2));

		// Padding with zeros
		assertEquals("X:0101 Y:0013 Z:0002", String.format("X:%04d Y:%04d Z:%04d", 101, 13, 2));

		// Left Justified
		assertEquals("X:101  Y:13   Z:2   ", String.format("X:%-4d Y:%-4d Z:%-4d", 101, 13, 2));

		// Group Separator
		assertEquals("U$S 40,000", String.format("U$S %,d", 40000));
		assertEquals("U$S 40,000.50", String.format("U$S %,.2f", 40000.50));

		// Parenthesis
		assertEquals("101", String.format("%(d", 101));
		assertEquals("(101)", String.format("%(d", -101));
		assertEquals(" 101", String.format("% (d", 101));
		assertEquals("(101)", String.format("% (d", -101));
	}

	@Test
	public void testArgumentIndex(){
		assertEquals("X:10 Y:20 Z:30", String.format("X:%d Y:%d Z:%d", 10, 20, 30));

		// At index 1: 10
		// At index 2: 20
		// At index 3: 30
		assertEquals("X:30 Y:20 Z:10", String.format("X:%3$d Y:%2$d Z:%1$d", 10, 20, 30));

		assertEquals("X:30 Y:30 Z:10", String.format("X:%3$d Y:%<d Z:%1$d", 10, 20, 30));
	}

}

