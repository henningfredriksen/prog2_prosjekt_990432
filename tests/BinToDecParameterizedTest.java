import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BinToDecParameterizedTest {
	
	private String input;
	private int expected;
	
	private HexBinConverter hc;
	
	@Parameters
	public static List<Object[]> hexToDec() {
		return Arrays.asList(new Object[][] {			
			{"1", 1}, // min
			{"111111111111111111111111", 16777215}, // max			
			{"1111101000011000110101", 4097589}, // random 6 digit
			{"0000001111101000", 1000} // non-6 digit
		});
	}
	
	// constructor
	public BinToDecParameterizedTest(String input, int expected) {
		this.input = input;
		this.expected = expected;
	}

	@Before
	public void Setup()
	{	
		hc = new HexBinConverter();
	}
	
	@Test	
	public void convertBinToDec_ParameteriedTest()
	{
		assertEquals(expected, hc.convertBinToDec(input), 0.0);
	}
}
