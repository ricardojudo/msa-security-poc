package ricardojudo.captcha.wso2;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class PermisiveSSLRequestFactory extends HttpComponentsClientHttpRequestFactory {

	public PermisiveSSLRequestFactory() throws GeneralSecurityException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] arg0, String arg1) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		this.setHttpClient(httpClient);
	}

}
