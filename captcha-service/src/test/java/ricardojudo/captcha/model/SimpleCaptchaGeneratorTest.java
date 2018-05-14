package ricardojudo.captcha.model;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import ricardojudo.captcha.impl.SimpleCaptchaGenerator;
import ricardojudo.captcha.model.Captcha;

public class SimpleCaptchaGeneratorTest {

	private SimpleCaptchaGenerator captchaGenerator;

	@Before
	public void setUp() {
		captchaGenerator = new SimpleCaptchaGenerator();
	}

	@Test
	public void generateCaptchaDefinitionTest() throws Exception {
		assertThat(captchaGenerator.generate(), instanceOf(Captcha.class));
		assertThat(captchaGenerator.generate(), notNullValue());		
	}
	
	@Test
	public void generateCaptchaValidTest() throws Exception{
		Captcha captcha = captchaGenerator.generate();
		assertThat("captchaSecret null",captcha.getCaptchaSecret(), notNullValue());
		assertThat("captchaToken",captcha.getCaptchaToken(), notNullValue());
		assertThat("image", captcha.getImage(), notNullValue());
	}
	

}
