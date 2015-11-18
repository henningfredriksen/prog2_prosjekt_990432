
public class HexBinConverter {
	
	/**
	 *  Krav, utility klassen:
		BITSTRENGER:
		-En streng kan ha lengde 0 til 24
		-En tom streng skal returnere 0
		-En streng som er lengre enn 24 skal forårsake at en IllegalArgumentException kastes
		-En streng som har andre tegn enn 0 og 1 skal forårsake at en IllegalArgumentException kastes
		-Du skal kunne sende inn en streng med 0 og 1 (kun), heretter kalt bitstreng, og få returnert
				korresponderende int verdi
		-Du skal kunne sende inn en int og få returnert den som en bitstreng med lengde 24
		
		HEXSTRENGER:
		-En streng kan ha lengde 0 til 6
//		-En tom streng skal returnere 0
		-En streng som er lengre enn 6 skal forårsake at en IllegalArgumentException kastes
		-En streng som har andre tegn enn 01234567890ABCDEF / abcdef skal forårsake at en
				IllegalArgumentException kastes
		
		BITOPERASJONER
		Operasjonene bitwise OR og bitwise AND skal kunne utføres på to bitstrenger. Retur er en streng på
		24 tegn.
		
		Det er ikke tillatt å bruke wrapper klasser sin metode parseInt() eller lignende eksisterende metoder
		for å utføre konvertering til / fra bit / hex strenger til / fra int.
		
		03ac0f 1 110101000000110111001101 001000011110011101001111
		ac0e1e 2 001000011110011101001111 000101010101010101111001
	 */
	
	//TODO: two constructors, one takes int, one takes hex
	public HexBinConverter()
	{
		
	}
	
	public HexBinConverter(String input)
	{
		
	}
	
	public HexBinConverter(int input)
	{
		
	}
	
	public String convertDecToHex(int input)
	{
		
		
		return "";
	}
	
	public int convertBinToDec(String input)
	{
		if (input == null)
			return 0;
		
		if (input.isEmpty())
			return 0;
		
		validateBinString(input);
		
		int result = 0;
		
		for (int i = 0; i < input.length(); i++)
		{
			if (input.charAt(i) >= '0' && input.charAt(i) <= '1')
			{
				result = (result * 2) + input.charAt(i) - '0';
			}
		}
		return result;
	}
	
	public void validateBinString(String input)
	{
		if (input.length() > 24)
			throw new IllegalArgumentException("Binary string needs to be 24 digits or less.");
		
		String s = input.toUpperCase();
		
		for (int i = 0; i < s.length(); i++)
		{
			if (!(s.charAt(i) >= '0' && s.charAt(i) <= '1'))
			{
				throw new IllegalArgumentException("String contained non-binary values.");
			}
		}
	}
	
	public int convertHexToDec(String input)
	{
		if (input == null)
			return 0;
		
		if (input.isEmpty())
			return 0;
		
		validateHexString(input);
		
		String s = input.toUpperCase();
		int result = 0;
		
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
	
	public void validateHexString(String input)
	{
		if (input.length() > 6)
			throw new IllegalArgumentException("Hex string needs to be 6 digits or less.");
		
		String s = input.toUpperCase();
		
		for (int i = 0; i < s.length(); i++)
		{
			if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F')))
			{
				throw new IllegalArgumentException("String contained non-0123456789ABCDEF values.");
			}
		}
	}
}
