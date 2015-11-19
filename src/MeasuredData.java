
public class MeasuredData {
	private String id;
	private int bitwiseModifier;
	private String s1;
	private String s2;
	private String bitwiseModifiedString;
	private int convertedToDec;
	
	/**
	 * 
	 * @param id
	 * @param bitwiseModifier
	 * @param s1
	 * @param s2
	 * @param bitwiseModifiedString
	 * @param convertedToDec
	 */
	public MeasuredData(String id, int bitwiseModifier, String s1, String s2, String bitwiseModifiedString, int convertedToDec)
	{
		this.id = id;
		this.bitwiseModifier = bitwiseModifier;
		this.s1 = s1;
		this.s2 = s2;
		this.bitwiseModifiedString = bitwiseModifiedString;
		this.convertedToDec = convertedToDec;
	}
	
	public String getId() {
		return id;
	}

	public int getBitwiseModifier() {
		return bitwiseModifier;
	}

	public String getS1() {
		return s1;
	}

	public String getS2() {
		return s2;
	}

	public String getBitwiseModifiedString() {
		return bitwiseModifiedString;
	}

	public int getConvertedToDec() {
		return convertedToDec;
	}
}
