package ricardojudo.captcha.model;

public interface CaptchaGenerator {
	Captcha generate() throws CaptchaException;
}
