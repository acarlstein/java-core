package streams;


import java.io.IOException;

import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class ClosableExampleTest extends TestCase
{

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCloseableExample()
	throws IOException{
		StringBuffer stringBuffer = new StringBuffer();
		try(CloseableExample closeableExample = new CloseableExample(stringBuffer)){
			closeableExample.doSomething();
		}

		String result = stringBuffer.toString();
		Assert.assertThat(result, CoreMatchers.containsString(CloseableExample.class.getName()));
		Assert.assertThat(result, CoreMatchers.containsString("doSomething()"));
		Assert.assertThat(result, CoreMatchers.containsString("close()"));
	}

}
