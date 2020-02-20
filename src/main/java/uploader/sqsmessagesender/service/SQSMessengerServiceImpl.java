package uploader.sqsmessagesender.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import uploader.sqsmessagesender.util.ClientBuilderManager;
import uploader.sqsmessagesender.util.PropertyManager;

/**
 * Sends a message to a given SQS.
 *  
 * @author theja.kotuwella
 *
 */
public class SQSMessengerServiceImpl implements ISQSMessengerService {
	private static Logger log = Logger.getLogger(SQSMessengerServiceImpl.class.getName());
	
	private static final String ISBN_METADATA_ATTRIBUTE = "isbn";
	private static final String ISBN_METADATA_TYPE 		= "String";
	private static final String MESSAGE_BODY 			= "Updating book inventory index";
	
	/**
	 * Sends a message to SQS queue with ISBN in its attributes
	 * 
	 * @param isbn ISBN of the book being saved as the index in ES
	 */
	@Override
	public void sendBookInventoryIndexMessage(String isbn) {
		SendMessageRequest sendMessageRequest = new SendMessageRequest()
    	        							.withQueueUrl(PropertyManager.SQS_URL)
    	        							.withMessageBody(MESSAGE_BODY);
		
		Map<String, MessageAttributeValue> indexMessage = createIndexingMessage(isbn);
		
    	sendMessageRequest.setMessageAttributes(indexMessage);
    	
    	ClientBuilderManager.getSQSClient().sendMessage(sendMessageRequest);
    	
    	log.info("SQSMessengerServiceImpl: Notification sent to SQS");
	}
	
	private Map<String, MessageAttributeValue> createIndexingMessage(String isbn) {
			Map<String, MessageAttributeValue> msgAttributes = 
									new HashMap<String, MessageAttributeValue>();
			
			MessageAttributeValue name = new MessageAttributeValue();
			name.setDataType(ISBN_METADATA_TYPE);
	    	name.setStringValue(isbn);
	    	msgAttributes.put(ISBN_METADATA_ATTRIBUTE, name);
	    	
	    	return msgAttributes;
	}
}
