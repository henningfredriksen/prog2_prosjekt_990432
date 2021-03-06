import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)  
public class DataHandlerTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	DataHandler dh;
	IDataHandler idh; 
	
	@Before
	public void Setup()
	{
		dh = new DataHandler();		
		idh = mock(IDataHandler.class); // mocked datahandler via interface injection
	}
	
	// VALIDATE_ENTRY tests
	@Test
	public void validateEntry_GivenNullString_ShouldThrowIllegalArgumentException()
	{
		String s = null;
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String was null.");
		dh.validateEntry(s);
	}
	
	@Test
	public void validateEntry_GivenEmptyString_ShouldThrowIllegalArgumentException()
	{
		String s = "";
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("String was empty.");
		dh.validateEntry(s);
	}
	
	@Test
	public void validateEntry_GivenBitwiseANDOROperationMod3_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1e 3 0010000111100111010011 000101010101010101111001";
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal value for bitwiseAND/OR operation. Entry logged.");
		dh.validateEntry(s);
	}
	
	@Test
	public void validateEntry_GivenBitwiseANDOROperationMod0_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1e 0 0010000111100111010011 000101010101010101111001";
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Illegal value for bitwiseAND/OR operation. Entry logged.");
		dh.validateEntry(s);
	}
	
	@Test
	public void validateEntry_GivenBitwiseANDOROperationWithMoreThanOneDigit_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1e 12 0010000111100111010011 000101010101010101111001";
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");
		dh.validateEntry(s);
	}
	
	@Test
	public void validateEntry_GivenMinimalValues_ShouldReturnTrue() 
	{
		String s = "1 1 0 0";
		assertTrue(dh.validateEntry(s));
	}
	
	@Test
	public void validateEntry_GivenMaximumValues_ShouldReturnTrue() 
	{
		String s = "FFFFFF 1 111111111111111111111111 111111111111111111111111";
		assertTrue(dh.validateEntry(s));
	}
	
	@Test
	public void validateEntry_GivenZeroHexValue_ShouldThrowIllegalArgumentException()
	{
		String s = "0 1 0 0"; // bitwiseOperator has separate check, bitstrings are allowed to be 0
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Hex value can't be 0.");
		dh.validateEntry(s);
	}
	
	@Test 
	public void validateEntry_GivenTooLongId_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1eb 1 0010000111100111010011 000101010101010101111001"; // 7 digit key
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");
		dh.validateEntry(s);
	}
	
	@Test 
	public void validateEntry_GivenTooLongS1_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1e 1 0001010101010101011110011 000101010101010101111001"; // 25 digit s1
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");
		dh.validateEntry(s);
	}
	
	@Test 
	public void validateEntry_GivenTooLongS2_ShouldThrowIllegalArgumentException()
	{
		String s = "b40a1e 1 000101010101010101111001 0001010101010101011110011"; // 25 digit s2
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");
		dh.validateEntry(s);
	}
	
	// SAVE_DATA tests	(invalid strings tested via validateEntry marked tests, so few tests needed)
	@Test
	public void saveData_GivenValidString_ShouldReturnTrue_usingMockito()
	{
		// just a demonstration of using Mockito for a simple test, pure JUnit test below
		dh = mock(DataHandler.class);
		
		// define return value for method
		when(dh.saveData("b40a1e 2 001000011110011101001111 000101010101010101111001")).thenReturn(true);
		
		// use mock in test 
		assertEquals(dh.saveData("b40a1e 2 001000011110011101001111 000101010101010101111001"), true);
	}
	
	@Test
	public void saveData_GivenValidString_ShouldReturnTrue()
	{
		String s = "FFFFFF 1 111111111111111111111111 111111111111111111111111";
		assertTrue(dh.saveData(s));
	}	
	
	// GET_MEASURED_DATA_BY_ID tests
	@Test
	public void getMeasuredDataById_GivenExistingId_ShouldReturnCorrectMeasuredDataObj()
	{
		dh.saveData("b40a1e 1 001000011110011101001111 000101010101010101111001");
		
		// compares all the elements in the object with the expected values
		MeasuredData md = dh.getMeasuredDataById("b40a1e");
		assertEquals("", "b40a1e", md.getId());
		assertEquals("", 1, md.getBitwiseModifier());
		assertEquals("", "001000011110011101001111", md.getS1());
		assertEquals("", "000101010101010101111001", md.getS2());
		assertEquals("", "000000010100010101001001", md.getBitwiseModifiedString());
		assertEquals("", 83273, md.getConvertedToDec());
	}
	
	@Test 
	public void getMeasuredDataById_GivenNonExistingId_ShouldReturnNull()
	{
		MeasuredData md = dh.getMeasuredDataById("b40a1e");
		assertEquals("", null, md);
	}
	
	// READ FROM FILE MOCKING (idh mocked via interface injection)
	@Test
	public void openFile_WhenOpenFileMethodRun_ShouldReturnTrue()
	{		
		when(idh.openFile()).thenReturn(true); 
		
		assertTrue(idh.openFile());
	}
		
	@Test
	public void readLine_WhenReadAllLinesMethodRun_ShouldReturnValidString()
	{
		when(idh.readAllLines()).thenReturn(true);
		
		assertTrue(idh.readAllLines());
	}
	
	
}
