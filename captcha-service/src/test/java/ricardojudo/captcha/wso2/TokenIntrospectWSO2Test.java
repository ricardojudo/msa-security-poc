package ricardojudo.captcha.wso2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TokenIntrospectWSO2Test {

	private TokenIntrospectWSO2 tokenIntrospectWSO2;
	
	@Before
	public void setUp() {
		tokenIntrospectWSO2 = new TokenIntrospectWSO2();
	}
	
	@Test
	public void test() throws Exception {
		
		TokenValidationResponse response = tokenIntrospectWSO2.validateAccessToken("e4fe154a-91e4-3d11-b703-2429a326d6b5");
		assertNotNull(response);
		assertFalse(response.isActive());
		
		
	}
	
	@Test
	public void test2() throws Exception {
		
		TokenValidationResponse response = tokenIntrospectWSO2.validateAccessToken("a5fc836a-6c18-3e96-8c7a-845da72ca326");
		assertNotNull(response);
		assertFalse(response.isActive());
		
		
	}


}
