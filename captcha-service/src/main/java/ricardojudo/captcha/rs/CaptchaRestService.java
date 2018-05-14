package ricardojudo.captcha.rs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ricardojudo.captcha.model.Captcha;
import ricardojudo.captcha.model.CaptchaException;
import ricardojudo.captcha.model.CaptchaGenerator;
import ricardojudo.captcha.model.CaptchaValidation;

@RestController
@RequestMapping("/captchas")
@CrossOrigin(origins= {"*"})
public class CaptchaRestService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaRestService.class);

	@Autowired
	private CaptchaGenerator captchaGenerator;

	@PostConstruct
	protected void initialize() {
		LOGGER.debug("Iniciando Captcha Rest Service");
	}

	@RequestMapping
	public Captcha get() throws CaptchaException {
		Captcha captcha = captchaGenerator.generate();
		return captcha;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> validate(@RequestBody CaptchaValidation validation) {
		LOGGER.debug("Validation: "+ validation.toString());
		ResponseEntity<Void> response = null;
		if (validation.isValid()) {
			response = ResponseEntity.ok().build();
		} else {
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}
	

}
