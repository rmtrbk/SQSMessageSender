package uploader.sqsmessagesender.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads the properties in the properties file.
 * 
 * @author theja.kotuwella
 *
 */
public class PropertyManager {
	private final String PROPERTIES_FILE 			= "/Users/theja.kotuwella/Wellcom/Theja/SQSMessageSender/src/main/resources/properties";	
	private static final String REGION_PROP 		= "aws.region";
	private static final String ACCESS_KEY_PROP 	= "aws.accessKey";
	private static final String SECRET_PROP 		= "aws.secret";
	private static final String SQS_URL_PROP		= "aws.sqs";
	
	public static String REGION_ATTRIBUTE 			= "region";
	public static String ACCESS_KEY_ATTRIBUTE 		= "key";
	public static String SECRET_ATTRIBUTE 			= "secret";
	public static String SQS_URL 					= "url";
	
	private Properties readPropertiesFile() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return prop;
	}
	
	public static void loadProperties() {
		PropertyManager propertyLoader = new PropertyManager();
		Properties prop = propertyLoader.readPropertiesFile();
		
		REGION_ATTRIBUTE 		= prop.getProperty(REGION_PROP);
		ACCESS_KEY_ATTRIBUTE 	= prop.getProperty(ACCESS_KEY_PROP);
		SECRET_ATTRIBUTE 		= prop.getProperty(SECRET_PROP);
		SQS_URL 				= prop.getProperty(SQS_URL_PROP);
	}
}
