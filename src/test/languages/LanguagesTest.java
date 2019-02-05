package languages;

import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.TestCase;
import org.junit.Test;

public class LanguagesTest extends TestCase {

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();
	}

	@Test
	public void testResourceBundleInEnglish(){

		// Locale seems to be a Singleton which is used by ResourceBundle
		Locale locale = Locale.getDefault();
		if (locale.getLanguage() != Locale.US.getLanguage())
		{
			locale.setDefault(new Locale(Locale.US.getLanguage(), "US"));
		}

		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

		assertEquals(resourceBundle.getString("greeting"), "Hello World!");
	}

	@Test
	public void testResourceBundleInSpanish(){

		Locale locale = Locale.getDefault();

		// Locale doesn't have Spanish! What the heck!
		if (locale.getLanguage() != "SP")
		{
			// No problem. Lets add it!
			locale.setDefault(new Locale("SP", "Spain"));
		}

		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

		assertEquals(resourceBundle.getString("greeting"), "Hola Mundo!");
	}

}
