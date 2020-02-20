package uploader.sqsmessagesender;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import uploader.sqsmessagesender.service.SQSMessengerServiceImpl;
import uploader.sqsmessagesender.util.PropertyManager;

/**
 * Entry point to the application.
 * Main method allows to send a message to a queue.
 * 
 * @author theja.kotuwella
 *
 */
public class App {
	private static final String isbn = "978-1-56619-909-4";
	
	private SQSMessengerServiceImpl sqsMessengerService = null;
	
	App(){
		initLog();
		
		PropertyManager.loadProperties();
		
		sqsMessengerService = new SQSMessengerServiceImpl();
	}
	
	public static void main(String[] arg) {
		App app = new App();
		
		app.sendBookInventoryIndexMessage(isbn);
	}
	
	private void sendBookInventoryIndexMessage(String isbn) {
		sqsMessengerService.sendBookInventoryIndexMessage(isbn);
	}
	
	private static void initLog() {
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);

		PatternLayout layout = new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n");
		rootLogger.addAppender(new ConsoleAppender(layout));
		
		try {
			RollingFileAppender fileAppender = new RollingFileAppender(layout, "SQSMessageSender.log");

			rootLogger.addAppender(fileAppender);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
