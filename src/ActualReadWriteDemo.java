import java.util.Map;

/**
 * Shows that actual file writing and reading can be done in production code,
 * and not just mocked in the test suite.
 * 
 * @author Henning Fredriksen (990432)
 */
public class ActualReadWriteDemo {


	public static void main(String[] args) {
		
		DataHandler dh = new DataHandler();
		
		dh.createEmptyFile(); // creates empty file
		
		// writes a few lines to a file
		dh.writeLineToFile("b40a1e 1 001000011110011101001111 000101010101010101111001");
		dh.writeLineToFile("ac0e1e 2 001000011110011101001111 000101010101010101111001");
		dh.writeLineToFile("ab1942 1 001000011110011101001111 000101010101010101111001");
		dh.writeLineToFile("acab02 2 001000011110011101001111 000101010101010101111001");
		
		// opens the file
		dh.openFile();
		
		// reads all the lines in the opened file
		dh.readAllLines();
		
		// returns the set containing the read lines as objects
		final Map<String, MeasuredData> data = dh.getData();		
		
		// prints the content of each map entry
		for (Map.Entry<String, MeasuredData> entry : data.entrySet())
		{
		    System.out.println(entry.getKey() + " / " +
		    		entry.getValue().getId() + " - " +
		    		entry.getValue().getBitwiseModifier() + " - " +
		    		entry.getValue().getS1() + " - " +
		    		entry.getValue().getS2() + " - " +
		    		entry.getValue().getBitwiseModifiedString() + " - " +
		    		entry.getValue().getConvertedToDec());
		}
		
		// you can see the file in the filesystem at this point
	}
}
