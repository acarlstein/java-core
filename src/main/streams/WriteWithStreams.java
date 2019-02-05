package streams;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class WriteWithStreams
{
	public static final String WRITE_RESOURCE_FILENAME = "./resources/write.txt";

	public WriteWithStreams(){}

	public void writingWithBytes(byte[] bytes)
	throws IOException
	{
		OutputStream output = new FileOutputStream(WRITE_RESOURCE_FILENAME);
		output.write(bytes);
		output.close();
	}

	public void writingWithCharacters(char[] chars)
	throws IOException
	{
		Writer writer = new FileWriter(WRITE_RESOURCE_FILENAME);
		writer.write(chars);
		writer.close();
	}
}
