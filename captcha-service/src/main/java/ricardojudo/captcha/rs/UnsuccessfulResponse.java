package ricardojudo.captcha.rs;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnsuccessfulResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2809116433897923669L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UnsuccessfulResponse.class);

	private String message;

	public UnsuccessfulResponse(){
	}
	
	
	public UnsuccessfulResponse(Exception e) {
		this.message = e.getLocalizedMessage();
		LOGGER.error(e.getLocalizedMessage(), e);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
