package streams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TryCatchResources
{

	public static final String READ_RESOURCE_FILENAME = "./resources/read.txt";

	public TryCatchResources(){}

	public String tryAndCatchEverything(){
		String result = "";
		Reader reader = null;
		try
		{
			StringBuffer stringBuffer = new StringBuffer();
			reader = getReader();
			int readValue;
			while ((readValue = reader.read()) >= 0){
				stringBuffer.append((char) readValue);
			}
			result = stringBuffer.toString();
		}
		catch (FileNotFoundException e)
		{
			result = e.getMessage();
			e.printStackTrace();
		}
		catch (IOException e)
		{ // Read() Exception
			result = e.getMessage();
			e.printStackTrace();
		}
		finally{
			try
			{
				if (null != reader){
					reader.close();
				}
			}
			catch (IOException e)
			{
				result = e.getMessage();
				e.printStackTrace();
			}
		}
		return result;
	}

	protected FileReader getReader()
	throws FileNotFoundException
	{
		return new FileReader(READ_RESOURCE_FILENAME);
	}

	public String tryWithResources(){
		String result = "";
		try(Reader reader = getReader())
		{
			StringBuffer stringBuffer = new StringBuffer();

			int readValue;
			while ((readValue = reader.read()) >= 0){
				stringBuffer.append((char) readValue);
			}
			result = stringBuffer.toString();
		}
		catch (FileNotFoundException e)
		{
			result = e.getMessage();
			e.printStackTrace();
		}
		catch (IOException e)
		{
			result = e.getMessage();
			e.printStackTrace();
		}

		// The method close of Reader is taken care by the Closeable runnable.
		// The closeable runnable extends the AutoCloseable runnable.

		return result;
	}
}
