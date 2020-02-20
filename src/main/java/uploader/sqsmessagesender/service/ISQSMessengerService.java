package uploader.sqsmessagesender.service;

/**
 * Messenger services interface.
 * 
 * @author theja.kotuwella
 *
 */
public interface ISQSMessengerService {
	void sendBookInventoryIndexMessage(String isbn);
}
