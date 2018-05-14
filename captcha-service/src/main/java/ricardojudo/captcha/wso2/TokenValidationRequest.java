package ricardojudo.captcha.wso2;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TokenValidationRequest {

	private String token;
	
	public TokenValidationRequest() {	
	}

	public TokenValidationRequest(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	public MultiValueMap<String, Object> toMap(){
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.set("token", this.token);
		return map;
	}
	
	
}
