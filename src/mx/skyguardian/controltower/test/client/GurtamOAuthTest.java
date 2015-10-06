package mx.skyguardian.controltower.test.client;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class GurtamOAuthTest {
//http://hosting.wialon.com/login.html?client_id=wialon&access_type=0x100&activation_time=0&duration=3600&lang=en&flags=0x1&user=tescobedo
	/*public static void main1(String[] args) throws Exception {
	        BasicCookieStore cookieStore = new BasicCookieStore();
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore)
	                .build();
	        
	        HttpUriRequest login = RequestBuilder.post()
	                    .setUri(new URI("http://hosting.wialon.com/login.html?"))
	                    .addParameter("client_id", "wialon")
	                    .addParameter("access_type", "0x100")
	                    .addParameter("activation_time", "0")
	                    .addParameter("duration", "3600")
	                    .addParameter("lang", "en")
	                    .addParameter("flags", "7")
	                    .addParameter("user", "tescobedo666")
	                    .build();
	            CloseableHttpResponse response2 = httpclient.execute(login);
	            try {
	                HttpEntity entity = response2.getEntity();
	                

	                System.out.println("Login form get: " + entity.getContent());
	                EntityUtils.consume(entity);

	                System.out.println("Post logon cookies:");
	                List<Cookie> cookies = cookieStore.getCookies();
	                if (cookies.isEmpty()) {
	                    System.out.println("None");
	                } else {
	                    for (int i = 0; i < cookies.size(); i++) {
	                        System.out.println("- " + cookies.get(i).toString());
	                    }
	                }
	            } finally {
	                response2.close();
	            }
	        
	    }*/
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		HttpResponse<String> request = Unirest.post("http://hosting.wialon.com/login.html")
				  .header("Content-Type", "application/x-www-form-urlencoded")
				  .queryString("apiKey", "123")
				  .field("client_id", "wialon")
				  .field("access_type", "0x100")
				  .field("activation_time", "0")
				  .field("duration", "3600")
				  .field("lang", "en")
				  .field("flags", "7")
				  .field("user", "tescobedo")
				  .field("passw", "skype1023")
				  .asString();
		
		System.out.println(request.getBody());
	
	}

}
