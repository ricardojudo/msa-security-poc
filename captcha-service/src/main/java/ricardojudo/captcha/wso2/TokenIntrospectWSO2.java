package ricardojudo.captcha.wso2;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenIntrospectWSO2 extends RestTemplateBuilder {

	private static final Logger logger = LoggerFactory.getLogger(TokenIntrospectWSO2.class);
	private String password = "admin";
	private String username = "admin";

	public TokenValidationResponse validateAccessToken(String accessToken)
			throws URISyntaxException, GeneralSecurityException {

		// {"active":true,"token_type":"Bearer","exp":1524869819,"iat":1524866219,"client_id":"3nqjRHovFIw2wZtJg4kErnBVWesa","username":"admin@carbon.super"}

		RestTemplate restTemplate = this.basicAuthorization(username, password)
				.additionalMessageConverters(new FormHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
				.requestFactory(PermisiveSSLRequestFactory.class).build();

		URI url = new URI("https://localhost:9443/oauth2/introspect");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", "application/json");

		RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(
				new TokenValidationRequest(accessToken).toMap(), headers, HttpMethod.POST, url);

		ResponseEntity<TokenValidationResponse> response = restTemplate.exchange(request,
				TokenValidationResponse.class);
		
		logger.debug("TokenValidationResponse {}", response);

		return response.getBody();
	}

}