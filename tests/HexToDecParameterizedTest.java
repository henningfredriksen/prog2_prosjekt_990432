import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HexToDecParameterizedTest {
	
	private String input;
	private int expected;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private HexBinConverter hc;
	
	@Parameters
	public static List<Object[]> hexToDec() {
		return Arrays.asList(new Object[][] {			
			{"1", 1}, // min
			{"FFFFFF", 16777215}, // max			
			{"3E8635", 4097589}, // random 6 digit
			{"3E8", 1000} // non-6 digit
		});
	}
	
	// constructor
	public HexToDecParameterizedTest(String input, int expected) {
		this.input = input;
		this.expected = expected;
	}

	@Before
	public void Setup()
	{	
		hc = new HexBinConverter();
	}
	
	@Test	
	public void convertHexToDec_GivenHexString3E8635_ShouldReturn4097589()
	{
		assertEquals(expected, hc.convertHexToDec(input), 0.0);
	}
}
