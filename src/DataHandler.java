import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataHandler {
	
	/**
	 * 	Krav, datainnsamlingsklassen:
	 * 
		-En fil skal kunne åpnes
		-Vi skal kunne lese en linje med måledata
		-Vi skal kunne finne ut om det er mer data å lese
		
		Når ei linje med måledata leses, skal følgende skje:
		- Måledata id skal brukes som nøkkel for lagring og gjenfinning av bearbeidede måledata i en
				passende Java collection klasse
		- Feil antall argumenter skal kaste en exception (fra fil, korrupt data?)
		- Operasjon (bitwise OR eller AND) skal utføres på måledata (bearbeidingen)
		- Originale måledata samt resultat av operasjon (bearbeidede måledata) skal lagres, både som
				bitstrenger og int verdier, og skal kunne gjenfinnes ved hjelp av måledata id
		- Vi skal logge data med duplikate måledata-id, samt måledata med feil bitoperasjon (alt annet
				enn 1 eller 2 er å anse som en feil), dette skjer på enkleste måte ved at hele tekstlinja 
				som er lest lagres unna i en liste (en passende Java Collection klasse)
	 */
	
	private List errorLog = new ArrayList();
	private Map<String, MeasuredData> data = new HashMap<String, MeasuredData>();
	private HexBinConverter hbc = new HexBinConverter();
	
	// constructor
	public DataHandler ()
	{

	}
	
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

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(line);
			bufferedWriter.close();
			
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void readFile(/*BufferedReader r*/)
	{
		BufferedReader bufferedReader = null;
		
		try {
			bufferedReader = new BufferedReader(new FileReader("MeasuredData.txt"));
			readLines(bufferedReader);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readLines(BufferedReader br)
	{		
//		List<String> lines = new ArrayList<String>();
		try
		{
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) 
			{
//				lines.add(currentLine);
				saveData(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		return lines;
	}
	
	public boolean saveData(String line) 
	{
		if (validateEntry(line))
		{
			String[] split = line.split("\\s+"); // splits by space
			
			String id = split[0];
			int bitwiseModifier = Integer.parseInt(split[1]);
			String s1 = split[2];
			String s2 = split[3];
			String bitwiseModifiedString = "";
			int convertedToDec = 0;
			
			// bitwiseModifiedString
			if (bitwiseModifier == 1) // AND
			{
				bitwiseModifiedString = hbc.applyBitwiseAND(split[2], split[3]);
			}
			if (bitwiseModifier == 2) // OR
			{
				bitwiseModifiedString = hbc.applyBitwiseOR(split[2], split[3]);
			}
			
			// convertedToDec
			convertedToDec = hbc.convertBinToDec(bitwiseModifiedString);
			
			// check if hex-value exists, then log the error (set.add returns false)
			if (!data.containsValue(id))
			{
				data.put(id, new MeasuredData(id, bitwiseModifier, s1, s2, bitwiseModifiedString, convertedToDec));
				
				// checks if a new entry was added to the data-map
				if (!data.containsValue(id))
					return true;
				else
					return false;
			}
			else
			{
				// TODO: LOG ILLEGAL ENTRY IN CASE OF DUPLICATE KEY
				return false;
			}
		}
		else
			return false;
	}
	
	public boolean validateEntry(String s)
	{
		if (s == null)
		{
			throw new IllegalArgumentException("String was null.");
		}
		
		if (s.isEmpty())
		{
			throw new IllegalArgumentException("String was empty.");
		}		
				
		// check for invalid bitwiseModifier, then log the error
		String bitwiseModExp = "[0-9a-fA-F]{1,6} [0-9] [01]{1,24} [01]{1,24}";
		if (s.matches(bitwiseModExp))
		{	
			// if a valid string (bitwiseOperatorMod excluded) passes, check the bitwiseOperatorMod
			String expression = "[0-9a-fA-F]{1,6} [12] [01]{1,24} [01]{1,24}";
			if (s.matches(expression))
			{
				// checks if any values are 0
				String[] split = s.split("\\s+"); // splits by space				
				for (int i = 0; i < split.length; i++)
				{
					if (split[i].compareTo("0") == 0)
					{
						throw new IllegalArgumentException("No values can be 0.");
					}
				}
				return true; // validation complete
			}
			else
			{
				// TODO: LOG ILLEGAL ENTRY IN CASE OF ILLEGAL BITWISE OPERATION
				throw new IllegalArgumentException("Illegal value for bitwiseAND/OR operation. Entry logged.");
			}
		}
		else
		{
			throw new IllegalArgumentException("Invalid string. Accepted Format: 1-6 hex value <space> 1 or 2 <space> 1-24 binary values <space> 1-24 binary values");			
		}
	}
	
	public MeasuredData getMeasuredDataById(String id)
	{
		return data.get(id);
	}
}
