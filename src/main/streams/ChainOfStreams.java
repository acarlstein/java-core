package streams;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The chain of streams is possible thanks to the implementation of the Decorator Pattern.
 */
public class ChainOfStreams
{
	public static final String READ_RESOURCE_FILENAME = "./resources/read.txt";

	public String inputStreamChain()
	throws IOException
	{
		StringBuffer stringBuffer = new StringBuffer();
		InputStream inputStream = new FileInputStream(READ_RESOURCE_FILENAME);
		char[] charBuffer = new char[256];
		int length = 0;

		// When try-with-resources closes InputStreamReader, due the Closeable runnable,
		// it will also close the InputStream, in this case the FileInputStream
		try(InputStreamReader inputStreamReader = new InputStreamReader(inputStream)){
			while((length = inputStreamReader.read(charBuffer)) >= 0){
				for (int index = 0; index < length; index++){
					stringBuffer.append(charBuffer[index]);
				}
			}
		}
		return stringBuffer.toString();
	}
}
