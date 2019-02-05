package streams;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class ReadWithStreams
{
	public static final String READ_RESOURCE_FILENAME = "./resources/read.txt";

	public ReadWithStreams(){}

	public byte[] readingOneByteAtATime()
	throws IOException
	{
		InputStream input = new FileInputStream(READ_RESOURCE_FILENAME);
		byte[] bytes = new byte[input.available()];
		int readValue;
		for (int index = 0; (readValue = input.read()) >= 0; index++){
			bytes[index] = (byte) readValue;
		}
		return bytes;
	}

	public char[] readingOneCharacterAtATime()
	throws IOException
	{
		StringBuffer stringBuffer = new StringBuffer();
		Reader reader = new FileReader(READ_RESOURCE_FILENAME);
		int readValue;
		while ((readValue = reader.read()) >= 0){
			stringBuffer.append((char) readValue);
		}
		return stringBuffer.toString().toCharArray();
	}

	public byte[] readingArrayOfBytes(int byteBufferSize)
	throws IOException
	{
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		InputStream input = new FileInputStream(READ_RESOURCE_FILENAME);
		byte[] byteBuffer = new byte[byteBufferSize];
		int length;
		while((length = input.read(byteBuffer)) >= 0){
			for(int index = 0; index < length; index++){
				result.write(byteBuffer[index]);
			}
		}
		return result.toByteArray();
	}

	public char[] readingArrayOfCharacters(int charBufferSize)
	throws IOException
	{
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		Reader reader = new FileReader(READ_RESOURCE_FILENAME);
		char[] charBuffer = new char[charBufferSize];
		int length;
		while((length = reader.read(charBuffer)) >= 0){
			for(int index = 0; index < length; index++){
				result.write(charBuffer[index]);
			}
		}
		return result.toString().toCharArray();
	}
}
