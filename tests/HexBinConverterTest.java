import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.junit.Assert.*;

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
	@Test
	public void convertDecToHex_GivenInt4097589_ShouldReturn3E8635()
	{
		assertEquals("", "3E8635", hc.convertDecToHex(4097589));
	}
	
	@Test
	public void convertDecToHex_GivenDecValue1_ShouldReturnStringContent1()
	{
		assertEquals("", "1", hc.convertDecToHex(1));
	}
	
	@Test
	public void convertDecToHex_GivenDecValue16777215_ShouldReturnStringContentFFFFFF()
	{
		assertEquals("","FFFFFF", hc.convertDecToHex(16777215));
	}
	
	@Test
	public void convertDecToHex_GivenDecValue0_ShouldReturnStringContent0()
	{		
		assertEquals("", "0", hc.convertDecToHex(0));
	}
	
	// DEC to BIN
	@Test
	public void convertDecToBin_GivenInt4097589_ShouldReturn1111101000011000110101()
	{
		assertEquals("", "1111101000011000110101", hc.convertDecToBin(4097589));
	}
	
	@Test
	public void convertDecToBin_GivenDecValue1_ShouldReturnStringContent1()
	{
		assertEquals("", "1", hc.convertDecToBin(1));
	}
	
	@Test
	public void convertDecToBin_GivenDecValue16777215_ShouldReturnStringContentFFFFFF()
	{
		assertEquals("","111111111111111111111111", hc.convertDecToBin(16777215));
	}
	
	@Test
	public void convertDecToBin_GivenDecValue0_ShouldReturnStringContent0()
	{		
		assertEquals("", "0", hc.convertDecToBin(0));
	}
	
	// Bitwise OR
	@Test
	public void applyBitwiseOR_GivenBitString1111101000011000110101and1001101010011010111101_ShouldReturnBitString1111101010011010111101()
	{
		assertEquals("", "1111101010011010111101", hc.applyBitwiseOR("1111101000011000110101", "1001101010011010111101"));
	}
	
	@Test
	public void applyBitwiseOR_GivenUnequalLengthStrings_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("BitStrings need to be the same length.");
		hc.applyBitwiseOR("101010", "10101011"); // uneven length
	}
	
	@Test
	public void applyBitwiseOR_GivenEitherStringIsNull_ShouldReturnStringContent0() 
	{
		String s1 = null;
		String s2 = "10";
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
		s1 = "1010";
		s2 = null;
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
		s1 = null;
		s2 = null;
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
	}
	
	@Test
	public void applyBitwiseOR_GivenEitherStringIsEmpty_ShouldReturnStringContent0() 
	{
		String s1 = "";
		String s2 = "10";
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
		s1 = "1010";
		s2 = "";
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
		s1 = "";
		s2 = "10";
		assertEquals("", "0", hc.applyBitwiseOR(s1, s2));
	}
	
	// Bitwise AND
	@Test
	public void applyBitwiseAND_GivenBitString1111101000011000110101and1001101010011010111101_ShouldReturn1001101000011000110101()
	{
		assertEquals("", "1001101000011000110101", hc.applyBitwiseAND("1111101000011000110101", "1001101010011010111101"));
	}
	
	@Test
	public void applyBitwiseAND_GivenUnequalLengthStrings_ShouldThrowIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("BitStrings need to be the same length.");
		hc.applyBitwiseAND("101010", "10101011"); // uneven length
	}
	
	@Test
	public void applyBitwiseAND_GivenEitherStringIsNull_ShouldReturnStringContent0() 
	{
		String s1 = null;
		String s2 = "10";
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
		s1 = "1010";
		s2 = null;
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
		s1 = null;
		s2 = null;
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
	}
	
	@Test
	public void applyBitwiseAND_GivenEitherStringIsEmpty_ShouldReturnStringContent0() 
	{
		String s1 = "";
		String s2 = "10";
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
		s1 = "1010";
		s2 = "";
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
		s1 = "";
		s2 = "10";
		assertEquals("", "0", hc.applyBitwiseAND(s1, s2));
	}
	
}
