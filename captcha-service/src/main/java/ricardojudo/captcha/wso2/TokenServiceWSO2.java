package ricardojudo.captcha.wso2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceWSO2 implements ResourceServerTokenServices {

	@Autowired
	private TokenIntrospectWSO2 tokenIntrospectWSO2;

	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {

		try {
			TokenValidationResponse validationResponse = tokenIntrospectWSO2.validateAccessToken(accessToken);
			OAuth2Request oAuth2Request = new OAuth2Request(null, null, null, true, null, null,
					null, null, null);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					validationResponse.getUserName(), null, null);
			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
			return oAuth2Authentication;
		} catch (Exception ex) {
			// Handle exception
		}
		return null;
	}

	public OAuth2AccessToken readAccessToken(String accessToken) {
		// TODO Add implementation
		return null;
	}

}
