import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

/**
 * Metodesignatur testmetoder:
		navnPÂMetodeSomTestes_InputParametre_ForventetResultat()
Eksempel:
		lastIndexOf_GivenStringWhereCharDoesNotExist_ShouldReturnMinusOne()
		hexToInt_TooLongString_ShouldThrowIllegalArgumentException()
 */
public class HexBinConverterTest {

	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private HexBinConverter hc;

	@Before
	public void Setup()
	{	
		hc = new HexBinConverter();
	}
	
	// HEX to DEC
	@Test
	public void convertHexToDec_GivenHexString3E8635_ShouldReturn4097589()
	{
		assertEquals("", 4097589, hc.convertHexToDec("3E8635"));
	}
	
	@Test
	public void convertHexToDec_GivenHexString1_ShouldReturn1()
	{
		assertEquals("", 1, hc.convertHexToDec("1"));
	}
	
	@Test
	public void convertHexToDec_GivenHexStringFFFFFF_ShouldReturn16777215()
	{
		assertEquals("", 16777215, hc.convertHexToDec("FFFFFF"));
	}
	
	@Test
	public void convertHexToDec_GivenNullString_ShouldReturn0()
	{
		String s = null;
		assertEquals("", 0, hc.convertHexToDec(s));
	}
	
	@Test
	public void convertHexToDec_GivenEmptyString_ShouldReturn0()
	{
		String s = "";
		assertEquals("", 0, hc.convertHexToDec(s));
	}
	
	@Test
	public void validateHexString_GivenHexStringLongerThan6_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Hex string needs to be 6 digits or less.");
		hc.validateHexString("3E86353");
	}
	
	@Test
	public void validateHexString_GivenHexContaingNon0toF_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String contained non-0123456789ABCDEF values.");
		hc.validateHexString("3E8Y3F");
	}
	
	// BIN to DEC
	@Test
	public void convertBinToDec_GivenBinString110101000000110111001101_ShouldReturn13897165()
	{
		assertEquals("", 13897165, hc.convertBinToDec("110101000000110111001101"));
	}
	
	@Test
	public void convertBinToDec_GivenBinString1_ShouldReturn1()
	{
		assertEquals("", 1, hc.convertBinToDec("1"));
	}
	
	@Test
	public void convertBinToDec_GivenBinString111111111111111111111111_ShouldReturn16777215()
	{
		assertEquals("", 16777215, hc.convertBinToDec("111111111111111111111111"));
	}
	
	@Test
	public void convertBinToDec_GivenNullString_ShouldReturn0()
	{
		String s = null;
		assertEquals("", 0, hc.convertBinToDec(s));
	}
	
	@Test
	public void convertBinToDec_GivenEmptyString_ShouldReturn0()
	{
		String s = "";
		assertEquals("", 0, hc.convertBinToDec(s));
	}
	
	@Test
	public void validateBinString_GivenBinStringLongerThan24_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Binary string needs to be 24 digits or less.");
		hc.validateBinString("1101010000001101110011011"); // 25 digits
	}
	
	@Test
	public void validateBinString_GivenBinStrixContaingNon0to1_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String contained non-binary values.");
		hc.validateBinString("102");
	}
	
	// DEC to HEX
	public void convertDecToHex_GivenInt4097589_ShouldReturn3E8635()
	{
		assertEquals("", "3E8635", hc.convertDecToHex(4097589));
	}
	
}
