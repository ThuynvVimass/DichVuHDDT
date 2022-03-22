package connect;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.bouncycastle.util.encoders.Base64Encoder;

import vn.vimass.utils.Data;

public class ConnectUsingGet {
	private final String USER_AGENT = "Mozilla/5.0";

	public ConnectUsingGet() {

	}
	
	public static Client getUnsecureClient() throws Exception 
	{
	    SSLContext sslcontext = SSLContext.getInstance("TLS");
	    sslcontext.init(null, new TrustManager[]{new X509TrustManager() 
	    {
	            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
	            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
	            public X509Certificate[] getAcceptedIssuers()
	            {
	                return new X509Certificate[0];
	            }

	    }}, new java.security.SecureRandom());


	    HostnameVerifier allowAll = new HostnameVerifier() 
	    {
	        @Override
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    };

	    return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier(allowAll).build();
	}

	
	public static String getContentWithHeader(String url,String user,String pass) {
		try {
			Data.ghiLogRequest("GetMethod:" + url);

			Client client = getUnsecureClient();
			WebTarget cashOutTarget = client.target(url);
			
			
			Invocation.Builder invocationBuilder = cashOutTarget.request();
			String temp = user + ":"+pass;
			String auth = Base64.getEncoder().encodeToString(temp.getBytes());
		    invocationBuilder.header("Authorization", "Basic "+auth);
		    
			Response response = invocationBuilder.get();
			String result = response.readEntity(String.class);
			Data.ghiLogRequest("GetMethod:" + result);
			return result;
		} catch (Exception e) {
			Data.ghiLogRequest("GetMethod: exception:" + e.getMessage());
		}
		return "";
	}

	public String sendGet(String sLink) {

		StringBuffer response = new StringBuffer();
		try {
			String url = "http://www.google.com/search?q=mkyong";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			
			String originalInput = "test input";
			String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
			
			con.setRequestProperty("Authorization", encodedString);

			
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (Exception e) {
			response.append("Loi");
		}
		return response.toString();
	}

	public String sendGet_2(String sLink, String url) {

		StringBuffer response = new StringBuffer();
		try {

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (Exception e) {
			response.append("Loi");
		}
		return response.toString();
	}
	
}