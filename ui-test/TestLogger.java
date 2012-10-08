package org.exoplatform.selenium;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;

/**
 *
 */
public class TestLogger {

	public static void log(String message, Level level) {
		Throwable t = new Throwable(); 
		String logMessage = message;
		StackTraceElement[] elements = t.getStackTrace();
		Logger logger = Logger.getLogger(TestBase.class);
		String Filename = elements[2].getFileName();
		String sClassName = Filename.substring(0, Filename.length() - 5);//remove .java
		String sMethodName = elements[2].getMethodName();
		logMessage = String.format("[%-20s][%s] %s", sClassName, sMethodName, message);
		logger.log(level, logMessage);
	}

    public static void trace(String message) {
        log(message, Level.TRACE);
    }
    
    public static void debug(String message) {
        log(message, Level.DEBUG);
    }

    public static void info(String message) {
        log(message, Level.INFO);
    }

    public static void warn(String message) {
        log(message, Level.WARN);
    }

    public static void error(String message) {
        log(message, Level.ERROR);
    }
}