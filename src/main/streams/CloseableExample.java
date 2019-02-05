package streams;

import java.io.IOException;

public class CloseableExample implements AutoCloseable
{
	StringBuffer stringBuffer;

	public CloseableExample(StringBuffer externalStringBuffer){
		stringBuffer = externalStringBuffer;
		stringBuffer.append(CloseableExample.class.getName()).append(' ');
	}

	public void doSomething()
	throws IOException
	{
		stringBuffer.append("doSomething() ");
	}

	@Override
	public void close()
	throws IOException
	{
		stringBuffer.append("close()");
	}
}
