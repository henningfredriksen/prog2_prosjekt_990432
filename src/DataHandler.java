import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that handles reading and writing measurement-data to file,
 * including validation of and storing of read data in a map.
 * 
 * Some methods are protected instead of private to allow for direct testing
 * 
 * Implements the IDataHandler interface to facilitate Mocking
 * 
 * @author Henning Fredriksen (990432)
 */

public class DataHandler implements IDataHandler {

	private IDataHandler idh;	
	// stores duplicate key and non-1/2 bitwise operator attempts	
	private List<String> errorLog = new ArrayList<String>(); 
	private Map<String, MeasuredData> data = new HashMap<String, MeasuredData>();
	private HexBinConverter hbc = new HexBinConverter();
	private BufferedReader bufferedReader = null;	
	
	// getter used for proving that actual reading/writing to disk works
	// see ActualReadWriteTest.java
	public Map<String, MeasuredData> getData() {
		return data;
	}

	
	// constructor
	public DataHandler ()
	{
		
	}
	
	// dependency injection
	public DataHandler(IDataHandler idh) 
	{
		this.idh = idh;
	}

	/**
	 * Opens the log file on disk, stores it in a BufferedReader global variable
	 * 
	 * @Return boolean indicating if the file has been created or not
	 */
	@Override
	public boolean openFile() {		
		try {
			File file = new File("MeasuredData.txt");
			Reader reader = new FileReader(file);
			bufferedReader = new BufferedReader(reader);
			
			if (file.isFile())
				return true;
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Reads all lines in the file, saves each line as a MeasuredData-object in a map (after verification)
	 * 
	 * @Return boolean indicating if all lines were successfully read or not
	 */
	@Override
	public boolean readAllLines() {
		
		String currentLine;
		try {
			while ((currentLine = bufferedReader.readLine()) != null)	
			{
				saveData(currentLine); // verifies the line and adds it to the map (data) in object-form (MeasuredData)				
			}
			return true; // all lines read
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	/**
	 * Creates an empty file
	 */
	public boolean createEmptyFile()
	{
		try 
		{
			File file = new File("MeasuredData.txt");

			// if file doesn't exist, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}

			new FileWriter(file.getAbsoluteFile()); // clears the file
			
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Appends a single line to file
	 * 
	 * @param line input string
	 */
	public boolean writeLineToFile(String line)
	{
		try 
		{
			File file = new File("MeasuredData.txt");

			// if file doesn't exist, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true); // append true
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.close();
			
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Overloaded method for appending multiple files to file at once 
	 * 
	 * @param lines string[] each containing one line
	 * @return
	 */
	public boolean writeLineToFile(String[] lines)
	{
		try 
		{
			File file = new File("MeasuredData.txt");

			// if file doesn't exist, then create it
			if (!file.exists()) 
			{
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true); // append true
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < lines.length; i++)
			{
				bufferedWriter.write(lines[i]);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Saves a single valid line from file in a map,
	 * using the hexvalue as a key and a MeasuredData-object as the value
	 *  
	 * @param line input string
	 * @return boolean indicating if data was successfully stored in the map or not
	 */
	protected boolean saveData(String line) 
	{
		if (validateEntry(line)) // validates the input string
		{
			String[] split = line.split("\\s+"); // splits by space
			
			// gets the various variable from the split string,
			// they are all guaranteed to be here after validation
			String id = split[0];
			int bitwiseModifier = Integer.parseInt(split[1]);
			String s1 = split[2];
			String s2 = split[3];
			String bitwiseModifiedString = "";
			int convertedToDec = 0;
			
			// bitwiseModifiedString
			if (bitwiseModifier == 1) // AND			
				bitwiseModifiedString = hbc.applyBitwiseAND(split[2], split[3]);
			
			if (bitwiseModifier == 2) // OR			
				bitwiseModifiedString = hbc.applyBitwiseOR(split[2], split[3]);
			
			// convertedToDec
			convertedToDec = hbc.convertBinToDec(bitwiseModifiedString);
			
			// check if hex-value exists, then log the error
			if (!data.containsValue(id))
			{
				// puts a new value
				data.put(id, new MeasuredData(id, bitwiseModifier, s1, s2, bitwiseModifiedString, convertedToDec));
				
				// checks if a new entry was added to the map
				if (!data.containsValue(id))
					return true;
				else
					return false;
			}
			else
			{
				errorLog.add(line); // log the attempt to add a duplicate key
				return false;
			}
		}
		else
			return false;
	}
	
	/**
	 * Validates a single string, to match the following criteria
	 * - not null or empty
	 * - must contain 1-6digit hex value, minimum value 1, maximum value FFFFFF
	 * - must contain a bitwise modifier of 1 or 2, any other are logged 
	 * - must contain two bitstrings of 1-24 bit length, minimum value 0, maximum value 111111111111111111111111
	 * 
	 * @param s input string
	 * @return a boolean indicating if the string was validated or not
	 */
	protected boolean validateEntry(String s)
	{
		if (s == null)
			throw new IllegalArgumentException("String was null.");
				
		if (s.isEmpty())
			throw new IllegalArgumentException("String was empty.");			
				
		// regex explained:
		// [0-9a-fA-F]{1,6} - string must contain 1-6 digits with hex values
		// [0-9] 			- string must contain one digit (bitwise operator 1 or 2, 
		//						but we let 0-9 through and check later so we can log the attempt)
		// [01]{1,24}		- string must contain 1-24 binary values		
		String bitwiseModExp = "[0-9a-fA-F]{1,6} [0-9] [01]{1,24} [01]{1,24}";
		if (s.matches(bitwiseModExp))
		{	
			// if a valid string (bitwiseOperatorMod excluded) passes, check the bitwiseOperatorMod
			String expression = "[0-9a-fA-F]{1,6} [12] [01]{1,24} [01]{1,24}";
			if (s.matches(expression))
			{
				// checks if the hex value is 0
				String[] split = s.split("\\s+"); // splits by space
				if (split[0].compareTo("0") == 0)				
					throw new IllegalArgumentException("Hex value can't be 0.");
								
				return true; // validation complete
			}
			else
			{
				errorLog.add(s); // log the bitwise error, if any single digit value of 0,3-9 is caught
				throw new IllegalArgumentException("Illegal value for bitwiseAND/OR operation. Entry logged.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");			
		}
	}
	
	/**
	 * Searches the map based on key, returns the contained value (MeasuredData-object)
	 * 
	 * @param id hex value key
	 * @return MeasuredData-object
	 */
	public MeasuredData getMeasuredDataById(String id)
	{
		return data.get(id);
	}



}
