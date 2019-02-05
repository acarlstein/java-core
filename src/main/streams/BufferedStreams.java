package streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class BufferedStreams
{
	public static final String READ_RESOURCE_FILENAME = "./resources/read.txt";
	public static final String WRITE_RESOURCE_FILENAME = "./resources/write.txt";

	/**
	 * The BufferedReader takes care to know which kind of new line breaks to implement based on the operating system
	 * For example:
	 *  - Microsoft Windows utilize "\r\n" (carriage return and new line)
	 *  - Unix and Linux utilize "\n" (new line)
	 *
	 * @return string
	 * @throws IOException
	 */
	public String bufferedReader()
	throws IOException{
		StringBuffer stringBuffer = new StringBuffer();
		try(Reader reader = new FileReader(READ_RESOURCE_FILENAME);
			BufferedReader bufferedReader = new BufferedReader(reader)){
			int readValue;
			while((readValue = bufferedReader.read()) >= 0){
				stringBuffer.append((char) readValue);
			}
		}
		return stringBuffer.toString();
	}

	public String bufferedReaderReadLines()
	throws IOException{
		StringBuffer stringBuffer = new StringBuffer();
		try(Reader reader = new FileReader(READ_RESOURCE_FILENAME);
			BufferedReader bufferedReader = new BufferedReader(reader)){
			String readValue;
			while((readValue = bufferedReader.readLine()) != null){
				stringBuffer.append(readValue);
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * The BufferedWriter takes care of using the correct value based on the platform
	 * plus ensure to use the correct new line.
	 *
	 * @param stringArrays
	 */
	public void bufferedWriter(String[] stringArrays)
	throws IOException{
		try(Writer writer = new FileWriter(WRITE_RESOURCE_FILENAME);
			BufferedWriter bufferedWriter = new BufferedWriter(writer)){
			for (String string: stringArrays){
				bufferedWriter.write(string);

				// Be careful, at the last loop, this will add a new line at the end of your file
				bufferedWriter.newLine();
			}
		}
	}
}
