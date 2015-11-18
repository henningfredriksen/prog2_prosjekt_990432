import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class DecToHexParameterizedTest {
	
	private int input;
	private String expected;
	
	private HexBinConverter hc;
	
	@Parameters
	public static List<Object[]> hexToDec() {
		return Arrays.asList(new Object[][] {			
			{1, "1"}, // min
			{16777215, "FFFFFF"}, // max			
			{4097589, "3E8635"}, // random 6 digit
			{1000, "3E8"} // non-6 digit
		});
	}
	
	// constructor
	public DecToHexParameterizedTest(int input, String expected) {
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
		assertEquals(expected, hc.convertDecToHex(input));
	}
}
