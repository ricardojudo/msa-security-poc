package ricardojudo.captcha.wso2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenValidationResponse {

	
	private boolean active;
	
	@JsonProperty("token_type")
	private String tokenType;
	
	private String exp;
	
	private String iat;
	
	@JsonProperty("client_id")
	private String clientId;
	
	@JsonProperty("user_name")
	private String userName;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}



	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "TokenValidationResponse [active=" + active + ", tokenType=" + tokenType + ", exp=" + exp + ", iat="
				+ iat + ", clientId=" + clientId + ", userName=" + userName + "]";
	}
	

	
	
}
