import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DecToBinParameterizedTest {
	
	private int input;
	private String expected;
	
	private HexBinConverter hc;
	
	@Parameters
	public static List<Object[]> decToBin() {
		return Arrays.asList(new Object[][] {			
			{1, "1"}, // min
			{16777215, "111111111111111111111111"}, // max			
			{4097589, "1111101000011000110101"}, // random 6 digit
			{1000, "1111101000"} // non-6 digit
		});
	}
	
	// constructor
	public DecToBinParameterizedTest(int input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	@Before
	public void Setup()
	{	
		hc = new HexBinConverter();
	}
	
	@Test	
	public void convertDecToHex_ParameterizedTest()
	{
		assertEquals(expected, hc.convertDecToBin(input));
	}

}
