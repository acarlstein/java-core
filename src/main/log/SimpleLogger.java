package log;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SimpleLogger
{
	static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

	public SimpleLogger(){
	}

	/**
	 * I will show you different ways that you can get the class name and method name
	 * @return
	 */
	public Set<String> getClassAndMethodNames(){

		Set<String> classMethodNames = new HashSet<>();

		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];
		String classMethodName = stackTraceElement.getClassName() + ":" + stackTraceElement.getMethodName();
		classMethodNames.add(classMethodName);
		logger.info(classMethodName);

		stackTraceElement = new Throwable().getStackTrace()[0];
		classMethodName = stackTraceElement.getClassName() + "-" + stackTraceElement.getMethodName();
		classMethodNames.add(classMethodName);
		logger.info(classMethodName);

		String className = this.getClass().getName();
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		classMethodName = className + "&" + methodName;
		classMethodNames.add(classMethodName);
		logger.info(classMethodName);

		return classMethodNames;
	}

	
}
