package log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringJoiner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class SimpleLoggerTest extends TestCase
{
	LogManager logManager;
	Logger logger;

	@Override
	protected void setUp()
	throws Exception {
		super.setUp();

		// LogManager seems to follow the Singleton Pattern
		logManager = LogManager.getLogManager();

		String loggerName = Logger.GLOBAL_LOGGER_NAME;
		logger = logManager.getLogger(loggerName);
	}

	@Override
	protected void tearDown()
	throws Exception {
		super.tearDown();

	}

	@Test
	public void testGetClassAndMethodNames(){
		SimpleLogger simpleLogger = new SimpleLogger();

		Set<String> classMethodNames = simpleLogger.getClassAndMethodNames();
		assertTrue(classMethodNames.contains("log.SimpleLogger:getClassAndMethodNames"));
		assertTrue(classMethodNames.contains("log.SimpleLogger-getClassAndMethodNames"));
		assertTrue(classMethodNames.contains("log.SimpleLogger&getClassAndMethodNames"));
	}

	@Test
	public void testLoggerLevelALL(){

		Logger loggerSpy = Mockito.spy(logger);

		loggerSpy.setLevel(Level.ALL);

		loggerSpy.log(Level.INFO, "Logging this message");
		loggerSpy.finest(() -> "finest: " + LocalDateTime.now());
		loggerSpy.finer(() -> "finer: " + LocalDateTime.now());
		loggerSpy.fine(() -> "fine: " + LocalDateTime.now());
		loggerSpy.info(() -> "info: " + LocalDateTime.now());
		loggerSpy.warning(() -> "warning: " + LocalDateTime.now());
		loggerSpy.severe(() -> "severe: " + LocalDateTime.now());

		Mockito.verify(loggerSpy, Mockito.times(7)).log(Mockito.any());
	}

	/**
	 * Precise logger its the same as the regular log but takes an extra two paramenters: sourceClass and sourceMethod.
	 */
	@Test
	public void testPreciseLogger(){
		Logger loggerSpy = Mockito.spy(logger);
		loggerSpy.logp(Level.INFO
					, "package.and.class.name"
					, "methodName"
					, "message to display");
		Mockito.verify(loggerSpy, Mockito.times(1)).logp(Mockito.any()
																					, Mockito.anyString()
																					, Mockito.anyString()
																					, Mockito.anyString());
	}

	/**
	 * Resource bundle logger allows you to use a resounce bundle holding a set of key/value pairs like a property file.
	 * Each file contains should contain the same set of keys with values in different languages.
	 */
	@Test
	public void testResourceBundleLogger(){

		ArgumentCaptor<LogRecord> captor = ArgumentCaptor.forClass(LogRecord.class);
		Logger loggerSpy = Mockito.spy(logger);

		loggerSpy.logrb(Level.SEVERE
			, this.getClass().getName()
			, new Exception().getStackTrace()[0].getMethodName()
			, getResourceBundle()
			, "EN");

		Mockito.verify(loggerSpy, Mockito.times(1)).log(captor.capture());
		LogRecord logRecord = captor.getValue();
		assertEquals(logRecord.getMessage(), "EN");

		loggerSpy.logrb(Level.SEVERE
			, this.getClass().getName()
			, new Exception().getStackTrace()[0].getMethodName()
			, getResourceBundle()
			, "SP");

		Mockito.verify(loggerSpy, Mockito.times(2)).log(captor.capture());
		logRecord = captor.getValue();
		assertEquals(logRecord.getMessage(), "SP");

	}

	/**
	 * Get a "Stub" of a ResourceBundle
	 * @return
	 */
	private ResourceBundle getResourceBundle(){
		// Creating an anonymous class extending the abstract class ResourceBundle
		return new ResourceBundle()
		{
			private final Map<String,String> map = new HashMap<>();

			// Declaring instance initialized
			{
				map.put("EN", "Message in the bottle, yeah!");
				map.put("SP", "Mensaje en la botella, yeah!");
			}

			@Override
			protected Object handleGetObject(String key)
			{
				return map.get(key);
			}

			@Override
			public Enumeration<String> getKeys()
			{
				// Applying Adapter pattern from Iterator to Enumeration
				return new Enumeration<String>()
				{
					private final Iterator<String> iterator = map.keySet().iterator();

					@Override
					public boolean hasMoreElements()
					{
						return iterator.hasNext();
					}

					@Override
					public String nextElement()
					{
						return iterator.next();
					}
				};
			}
		};
	}

	/**
	 * See table Table: setLevel() vs. isLoggable() in README.md
	 */
	@Test
	public void testGuardedLogging(){

		logger.setLevel(Level.WARNING);
		if(logger.isLoggable(Level.WARNING)){

			logger.logp(Level.INFO
				, "class_name"
				, "method_name"
				, "message"
				, new Object[]{"1", "key"});

		}

		logger.setLevel(Level.INFO);
		assertTrue(logger.isLoggable(Level.INFO));
	}

	@Test
	public void testEnteringAndExitingLog(){

		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINER);
		logger.addHandler(consoleHandler);

		//logger.setLevel(Level.FINER);

		doIt("MyKey", "MyValue");

		logger.removeHandler(consoleHandler);
	}

	private String doIt(String key, String value){
		String className = this.getClass().getName();
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		logger.entering(className, methodName,  new Object[] {key, value});

		logger.finest(() -> "[finest: " + LocalDateTime.now() + "]");
		logger.finer(() -> "[finer: " + LocalDateTime.now()+ "]");
		logger.fine(() -> "[fine: " + LocalDateTime.now()+ "]");
		logger.info(() -> "[info: " + LocalDateTime.now()+ "]");
		logger.warning(() -> "[warning: " + LocalDateTime.now()+ "]");
		logger.severe(() -> "[severe: " + LocalDateTime.now()+ "]");

		String result = "";
		try{
			result += "Some text";
			//throw new Exception("AAAAARRGGGHHH!!!!");
		}catch(Exception e){
			logger.throwing(getClass().getName().toString(), "doIt", e);
			logger.log(Level.SEVERE, "Severe Error", e);
		}

		logger.exiting(className, methodName, result);
		return result;
	}


	@Test
	public void testLoggerWithCustomHandler(){

		Formatter formatter = new SimpleFormatter();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Handler handler = new StreamHandler(outputStream, formatter);
		logger.addHandler(handler);

		String bottleContent = "Message in the bottle... yeah!";
		try{
			logger.info(bottleContent);

			handler.flush();

			String message = outputStream.toString();

			assertNotNull(message);

			//Jan 15, 2019 3:23:51 PM log.SimpleLoggerTest testLoggerWithCustomHandler
			//INFO: Message in the bottle... yeah!
			assertTrue(message.contains(this.getClass().getName()));
			assertTrue(message.contains(new Exception().getStackTrace()[0].getMethodName()));
			assertTrue(message.contains("INFO:"));
			assertTrue(message.contains(bottleContent));
		}finally{
			logger.removeHandler(handler);
		}

	}

	@Test
	public void testFileHandler()
	throws IOException
	{

		/**
		 * Platform slash\backslash: /
		 * User's Home Directory: %h
		 * Temp Directory: %t
		 * Rotating Log Generation: %g
		 */
		String path = new File("./resources/logs").getAbsolutePath();

		String filenameFormat = "warning_log_%g.log";
		String totalPath = path + "//" + filenameFormat;
		int numberOfLines = 1000;
		int numberOfRotations = 4;
		FileHandler warningFileHandler = new FileHandler(totalPath, numberOfLines, numberOfRotations);
		warningFileHandler.setFormatter(new SimpleFormatter());
		warningFileHandler.setLevel(Level.WARNING);
		logger.addHandler(warningFileHandler);

		filenameFormat = "info_log_%g.log";
		totalPath = path + "//" + filenameFormat;
		FileHandler infoFileHandler = new FileHandler(totalPath, numberOfLines, numberOfRotations);
		infoFileHandler.setFormatter(new SimpleFormatter());
		infoFileHandler.setLevel(Level.INFO);
		logger.addHandler(infoFileHandler);

		filenameFormat = "finest_log_%g.log";
		totalPath = path + "//" + filenameFormat;
		FileHandler finestFileHandler = new FileHandler(totalPath, numberOfLines, numberOfRotations);
		finestFileHandler.setFormatter(new SimpleFormatter());
		finestFileHandler.setLevel(Level.FINEST);
		logger.addHandler(finestFileHandler);



		logger.setLevel(Level.ALL);

		logger.log(Level.SEVERE, "Log at level SEVERE");
		logger.log(Level.WARNING, "Log at level WARNING");
		logger.log(Level.INFO, "Log at level INFO");
		logger.log(Level.CONFIG, "Log at level CONFIG");
		logger.log(Level.FINE, "Log at level FINE");
		logger.log(Level.FINER, "Log at level FINER");
		logger.log(Level.FINEST, "Log at level FINEST");
	}

	@Test
	public void testHandlerWithCustomFormatter(){

		// Disable default handler
		logger.setUseParentHandlers(false);

		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(getCustomFormatter());
		consoleHandler.setLevel(Level.INFO);
		logger.addHandler(consoleHandler);
		logger.info("MESSAGE LEVEL INFO 1");
		logger.info("MESSAGE LEVEL INFO 2");
	}

	public Formatter getCustomFormatter(){
		// I could create a class that extends Formatter but I am lazy.
		return new Formatter(){
			private final DateFormat dateFormat;

			{
				dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
			}

			@Override
			public String getHead(Handler h)
			{
				return super.getHead(h);
			}

			@Override
			public String getTail(Handler h)
			{
				return super.getTail(h);
			}

			@Override
			public synchronized String formatMessage(LogRecord record)
			{
				return super.formatMessage(record);
			}

			@Override
			public String format(LogRecord record)
			{
				StringJoiner stringJoiner = new StringJoiner("} | {", "[{", "}]");
				stringJoiner.add(dateFormat.format(new Date(record.getMillis())));
				stringJoiner.add(record.getSourceClassName());
				stringJoiner.add(record.getSourceMethodName());
				stringJoiner.add(record.getLevel().toString());
				return stringJoiner.toString().concat("\n\t").concat(formatMessage(record)).concat("\n");
			}
		};
	}

}
