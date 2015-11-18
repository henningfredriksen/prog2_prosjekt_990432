/**
 * Utility class that handles conversion back and forth from hexidecimal and binary to decimal,
 * as well as being able to apply bitwise AND/OR operations to bitstrings.
 * 
 * Validation methods are public to allow for direct testing.
 * 
 * @author Henning Fredriksen (990432)
 */
public class HexBinConverter {

	
	/**
	 *  Constructor
	 */
	public HexBinConverter()
	{
		
	}	
	
	/**
	 * Performs a bitwise OR operation on two strings of equal length.
	 * 
	 * @param s1 Bitstring.
	 * @param s2 Bitstring.
	 * @return The resulting string of having a bitwise OR operation applied to the input strings.
	 */
	public String applyBitwiseOR(String s1, String s2)
	{		
		// checks if either string is null
		if (s1 == null || s2 == null)
			return "0";
		
		// checks if either string is empty
		if (s1.isEmpty() || s2.isEmpty())
			return "0";
		
		// check if the strings are equal length
		if (s1.length() != s2.length())
			throw new IllegalArgumentException("BitStrings need to be the same length.");
		
		// validates each string seperately, checks if they exceed the max string size or
		// contain other characters than 0 and 1
		validateBinString(s1);
		validateBinString(s2);
		
		StringBuilder sb = new StringBuilder();
		
		// iterates throught both strings, using the bitwise OR logic to
		// build a new string. https://en.wikipedia.org/wiki/Bitwise_operation
		for (int i = 0; i < s1.length(); i++)
		{
			if (s1.charAt(i) == '0' && s2.charAt(i) == '0')
			{
				sb.append(0);
			}
			else
			{
				sb.append(1);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Performs a bitwise OR operation on two strings of equal length.
	 * 
	 * @param s1 Bitstring.
	 * @param s2 Bitstring.
	 * @return The resulting string of having a bitwise AND operation applied to the input strings.
	 */
	public String applyBitwiseAND(String s1, String s2)
	{
		// checks if either string is null
		if (s1 == null || s2 == null)
			return "0";
		
		// checks if either string is empty
		if (s1.isEmpty() || s2.isEmpty())
			return "0";
		
		// check if the strings are equal length
		if (s1.length() != s2.length())
			throw new IllegalArgumentException("BitStrings need to be the same length.");
		
		// validates each string seperately, checks if they exceed the max string size or
		// contain other characters than 0 and 1
		validateBinString(s1);
		validateBinString(s2);
		
		// iterates throught both strings, using the bitwise AND logic to
		// build a new string. https://en.wikipedia.org/wiki/Bitwise_operation		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s1.length(); i++)
		{
			if (s1.charAt(i) == '1' && s2.charAt(i) == '1')
			{
				sb.append(1);
			}
			else
			{
				sb.append(0);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Converts a decimal number to a bitstring.
	 * 
	 * @param input A decimal number.
	 * @return A decimal number converted to a bitstring.
	 */
	public String convertDecToBin(int input)
	{	
		// checks if the number is 0
		if (input == 0)
			return "0";
		
		StringBuilder sb = new StringBuilder();
		String legitBinValues = "01"; // allowed characters, binary
		
		// ex. lets start with input as 13
		// Iteration:
		// 1: adds 1 to front of the sb since 13 is not divisible by 2, 1 is subtracted, halved input is now 6
		// 2: adds 0 since 6 is divisible by 2, halved input is now 3
		// 3: adds 1 to the front of the sb since 3 is not divisible by 2, 1 is subtracted, halved input is now 1
		// 4: adds 1 to the front of the queue since 1 is not divisible by 2, 1 is subtracted,
		// input is 0 now, halting the while-loop. Result is 1101.
		while (input > 0)
		{
			int number = input % 2; // gets the furthest right number not divisible by 2 (1 or 0)
			input = input - number; // subtracts the number from input, now that the overflow has been saved
			sb.insert(0, (legitBinValues.charAt(number))); // inserts the number at the front of the string
			input = input / 2; // halves the input
		}		
		return sb.toString();
	}
	/**
	 * Converts a decimal number to a hex-string.
	 * 
	 * @param input A decimal number.
	 * @return A decimal number converted to a hexstring.
	 */
	public String convertDecToHex(int input)
	{		
		// checks if the number is 0
		if (input == 0)
			return "0";
		
		StringBuilder sb = new StringBuilder();
		String legitHexValues = "0123456789ABCDEF"; // allowed characters, hex
		
		// same principle as convertDecToBin(), detailed there
		while (input > 0)
		{
			int number = input % 16; // gets the furthest right number not divisible by 16
			input = input - number; // subtracts the number from input, now that the overflow has been saved
			sb.insert(0, (legitHexValues.charAt(number))); // inserts the number at the front of the string
			input = input / 16; // divides the input by 16
		}		
		return sb.toString();
	}
	
	/**
	 * Converts a bitstring to a decimal number
	 * 
	 * @param input bitstring
	 * @return bitstring converted to a decimal number
	 */
	public int convertBinToDec(String input)
	{
		// checks if the bitstring is null
		if (input == null)
			return 0;
		
		// checks if the bitstring is empty
		if (input.isEmpty())
			return 0;
		
		// validates bitstring, checks if it exceed the max string size or
		// contain other characters than 0 and 1
		validateBinString(input);
		
		int result = 0;
		
		// ex. lets start with 1010
		// Iteration:
		// 1: result(0) is multiplied by 2 and 1 is added to it. =1
		// 2: result(1) is multiplied by 2 and 0 is added to it. =2
		// 3: result (2) is multiplied by 2 and 1 is added to it. = 5
		// 4: result (5) is multiplied by 2 and 0 is added to it = 10
		// result is the decimal number 10.
		
		// iterates through each character of the string
		for (int i = 0; i < input.length(); i++)
		{
			// for each iteration, the result is multiplied by 2 and
			// the rightmost character is read and added to the result
			result = (result * 2) + input.charAt(i) - '0';	
		}
		return result;
	}
	
	/**
	 * Validates a bitstring, checking if it's length exceeds 24 or if it contains any other characters
	 * than 1's and 0's
	 * 
	 * @param input Bitstring.
	 */
	public void validateBinString(String input)
	{
		// checks if the length of the bitstring exceeds 24, if it does it throws an IllegalArgumentException
		if (input.length() > 24)
			throw new IllegalArgumentException("Binary string needs to be 24 digits or less.");
		
		// checks if any of the characters in the string are non-binary values, if it does
		// it throws an IllegalArgumentException
		for (int i = 0; i < input.length(); i++)
		{
			if (!(input.charAt(i) >= '0' && input.charAt(i) <= '1'))
			{
				throw new IllegalArgumentException("String contained non-binary values.");
			}
		}
	}
	
	/**
	 * Converts a hexadecimal number in string form to a decimal number.
	 * 
	 * @param input Hexadecimal number in string form.
	 * @return The hexidecimal input as a decimal number. 
	 */
	public int convertHexToDec(String input)
	{
		// checks if the hexstring is null
		if (input == null)
			return 0;
		
		// checks if the hexstring is empty
		if (input.isEmpty())
			return 0;
		
		// validates hexstring, checks if it exceed the max string size or
		// contain other characters than 0123456789ABCDEF
		validateHexString(input);
		
		String s = input.toUpperCase(); // converts the string to uppercase since we are interested in relative values
		
		int result = 0;
		
		// iterates through each character of the string, see convertBinToDec for details, same principle
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
			{
				result = (result * 16) + s.charAt(i) - '0';
			}
			if (s.charAt(i) >= 'A' && s.charAt(i) <= 'F')
			{
				result = (result * 16) + (10 + s.charAt(i) - 'A');
			}
		}		
		return result;
	}
	
	/**
	 * Validates a hexstring, checking if it's length exceeds 6 or if it contains any other characters
	 * than 0123456789ABCDEF.
	 * 
	 * @param input A hexidecimal number in string form.
	 */
	public void validateHexString(String input)
	{
		// checks if the length of the hexstring exceeds 6, if it does it throws an IllegalArgumentException
		if (input.length() > 6)
			throw new IllegalArgumentException("Hex string needs to be 6 digits or less.");
		
		String s = input.toUpperCase();
		
		// checks if any of the characters in the string are non-hexadecimal values, if it does
		// it throws an IllegalArgumentException		
		for (int i = 0; i < s.length(); i++)
		{
			if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F')))
			{
				throw new IllegalArgumentException("String contained non-0123456789ABCDEF values.");
			}
		}
	}
}
