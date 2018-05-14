package ricardojudo.captcha.model;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

public class CaptchaTest {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void propertiesTest() {
		Captcha captcha = new Captcha();
		assertThat(captcha, hasProperty("image"));
		assertThat(captcha, hasProperty("captchaSecret"));
		assertThat(captcha, hasProperty("captchaToken"));
	}
	
	@Test
	public void definitionTest(){
		Captcha captcha= new Captcha();
		assertThat(captcha, instanceOf(Serializable.class));
	}

}
