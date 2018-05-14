package ricardojudo.captcha.model;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static ricardojudo.captcha.model.CaptchaTestUtils.*;

import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

import ricardojudo.captcha.impl.SimpleCaptchaGenerator;
import ricardojudo.captcha.impl.TextEncoder;
import ricardojudo.captcha.model.Captcha;
import ricardojudo.captcha.model.CaptchaException;
import ricardojudo.captcha.model.CaptchaGenerator;
import ricardojudo.captcha.model.CaptchaValidation;

public class CaptchaValidationTest {

	private TextEncoder encoder;

	@Before
	public void setUp() throws Exception {
		encoder = new TextEncoder();
	}

	@Test
	public void propertiesTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		assertThat(captchaValidation, hasProperty("text"));
		assertThat(captchaValidation, hasProperty("captchaSecret"));
		assertThat(captchaValidation, hasProperty("captchaToken"));
	}

	@Test
	public void definitionTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		assertThat(captchaValidation, instanceOf(Serializable.class));
	}

	@Test
	public void testValidateSuccessfullyTest() {
		String text = "asdfgh";
		String secret = encoder.generateSecret(text);
		CaptchaValidation captchaValidation = new CaptchaValidation(text, secret);
		assertThat(captchaValidation.getText(), notNullValue());
		assertThat(captchaValidation.getCaptchaSecret(), notNullValue());
		assertThat(captchaValidation.isValid(), equalTo(true));
	}

	@Test
	public void testValidateNotSuccessfullyTest() {
		String text = "asdfgh";
		String secret = encoder.generateSecret("qwerty");
		CaptchaValidation captchaValidation = new CaptchaValidation(text, secret);
		assertThat(captchaValidation.getText(), notNullValue());
		assertThat(captchaValidation.getCaptchaSecret(), notNullValue());
		assertThat(captchaValidation.isValid(), equalTo(false));

	}

	@Test
	public void testValidateNullTextTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		captchaValidation.setCaptchaSecret("qwertyuiop");
		assertThat(captchaValidation.getText(), nullValue());
		assertThat(captchaValidation.getCaptchaSecret(), notNullValue());
		assertThat(captchaValidation.isValid(), equalTo(false));
	}

	@Test
	public void testValidateNullSecretTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		captchaValidation.setText("algo");
		assertThat(captchaValidation.getText(), notNullValue());
		assertThat(captchaValidation.getCaptchaSecret(), nullValue());
		assertThat(captchaValidation.isValid(), equalTo(false));
	}

	@Test
	public void testValidateEmptyTextTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		captchaValidation.setCaptchaSecret("     ");
		assertThat(captchaValidation.getText(), nullValue());
		assertThat(captchaValidation.getCaptchaSecret(), notNullValue());
		assertThat(captchaValidation.isValid(), equalTo(false));
	}

	@Test
	public void testValidateEmptySecretTest() {
		CaptchaValidation captchaValidation = new CaptchaValidation();
		captchaValidation.setText("     ");
		assertThat(captchaValidation.getText(), notNullValue());
		assertThat(captchaValidation.getCaptchaSecret(), nullValue());
		assertThat(captchaValidation.isValid(), equalTo(false));
	}

	@Test
	public void testValidateSuccessfullyFullTest() throws CaptchaException {
		CaptchaGenerator generator = new SimpleCaptchaGenerator();
		Captcha captcha = generator.generate();
		CaptchaValidation captchaValidation = new CaptchaValidation(captcha.getText(), captcha.getCaptchaSecret());
		assertThat(captchaValidation.getText(), notNullValue());
		assertThat(captchaValidation.getCaptchaSecret(), notNullValue());
		assertThat(captchaValidation.isValid(), equalTo(true));
	}

	// @Test
	public void testMultipleValidation() throws InterruptedException {
		runMultiple(3, 100000, () -> {
			testValidateNotSuccessfullyTest();
		});
	}

	// @Test
	public void testMultipleFullValidation() throws InterruptedException {
		runMultiple(3, 10000, () -> {
			testValidateSuccessfullyFullTest();
		});
	}

}
