package streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * All the java.io file streams are deprecated.
 * Therefore, we will focus on the new IO file features provided by java.nio
 *
 * This new java.nio provides a bunch of features such as:
 *  - File system support
 *  - Scalability
 *  - Improved exception reporting
 *
 * Plus, it provides the Path and Paths files which give us too, some great features at our disposition.
 */
public class BetterIOFile
{

	public static final String READ_RESOURCE_FILENAME = "./resources/readWithNewLineAtEOF.txt";
	public static final String WRITE_RESOURCE_FILENAME = "./resources/write.txt";

	public BetterIOFile(){}

	public String filesNewBufferedReader()
	throws IOException
	{
		StringBuffer stringBuffer = new StringBuffer();
		Path path = Paths.get(READ_RESOURCE_FILENAME);
		try(BufferedReader bufferedReader = Files.newBufferedReader(path)){
			String readValue;
			while((readValue = bufferedReader.readLine()) != null){
				stringBuffer.append(readValue).append(System.getProperty("line.separator"));
			}
		}
		return stringBuffer.toString();
	}

	public String filesReadAllLines()
	throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		Path path = Paths.get(READ_RESOURCE_FILENAME);
		List<String> allLines = Files.readAllLines(path);
		for(String string: allLines){
			stringBuffer.append(string).append(System.getProperty("line.separator"));
		}
		return stringBuffer.toString();
	}


}
