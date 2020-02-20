package uploader.sqsmessagesender.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * Builds the AWS clients.
 * 
 * @author theja.kotuwella
 *
 */
public class ClientBuilderManager {
	static AmazonSQS sqsClient = null;
	
	public static AmazonSQS getSQSClient() {
    	if(sqsClient == null) {
    		sqsClient = AmazonSQSClientBuilder.standard()
    							.withCredentials(getcredentials())
    							.withRegion(Regions.AP_SOUTHEAST_2).build();
    	}
		return sqsClient;
    }
	
	private static AWSStaticCredentialsProvider getcredentials() {
		AWSCredentials credentials 	= new BasicAWSCredentials(PropertyManager.ACCESS_KEY_ATTRIBUTE,
																PropertyManager.SECRET_ATTRIBUTE);
		
		AWSStaticCredentialsProvider credProv = new AWSStaticCredentialsProvider(credentials);
		
		return credProv;
	}
}
