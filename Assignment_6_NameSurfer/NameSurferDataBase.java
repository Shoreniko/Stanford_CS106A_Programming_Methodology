
/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.HashMap;

public class NameSurferDataBase implements NameSurferConstants {
	
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	
	// The job of this method is to read each line from the specific file and
	// eventually create a data base.

	public NameSurferDataBase(String filename) {
		fileData = new HashMap<String, String>();
		
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			creatingTheDataBase(rd);
			rd.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// The job of this method is to create a data base from each line of the file.

	private void creatingTheDataBase(BufferedReader rd) throws IOException {
		String line = rd.readLine();

		while (line != null) {
			String name = extractNameFromLine(line);
			fileData.put(name, line);
			line = rd.readLine();
		}
	}

	// The job of this method is to separate the name from the whole line string.

	private String extractNameFromLine(String line) {
		int charPosition = 0;
		String name = "";
		while (line.charAt(charPosition) != ' ') {
			name += line.charAt(charPosition);
			charPosition++;
		}
		return name;
	}

	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	
	// The job of this method is to find whether the name entered by the user exists
	// in the data base.

	public NameSurferEntry findEntry(String name) {
		String userInput = userInputTransformation(name);
		if (fileData.containsKey(userInput)) {
			NameSurferEntry correctEntry = new NameSurferEntry(fileData.get(userInput));
			return correctEntry;
		} else {
			return null;
		}
	}

	// The job of this method is to transform the user's input into a standard form
	// of a string. Meaning, the first letter of the string is uppercase and the
	// rest is lowercase. After the transformation, we can compare the user input to
	// the data properly.

	private String userInputTransformation(String name) {
		char firstChar = Character.toUpperCase(name.charAt(0));
		String userInput = firstChar + name.substring(1).toLowerCase();
		return userInput;
	}
	
	// Private instance variable.
	
	private HashMap<String, String> fileData;
	
}
