package connect;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.microsoft.webservices.DefaultServiceLocator;
import com.microsoft.webservices.DefaultServiceSoap;

import vn.vimass.utils.Common;
import vn.vimass.utils.Data;

public class PostBody {

//	public static Client client;

//	public static void createClient() {
//		if(client == null)
//		{
//			client=  ClientBuilder.newClient();
//		}
//	}
	public static String callService(String url, String dataSend) {
//
//		try {
////			Data.ghiLogRequest("callService= url=" + url + "\n"+ dataSend + "\n===========\n");
////			createClient();
//			Client client=  ClientBuilder.newClient();
//			WebTarget cashOutTarget = client.target(url);
//			Response response = cashOutTarget
//					.request()
//					.accept(MediaType.APPLICATION_JSON)
//					.post(Entity.entity(dataSend,
//							MediaType.APPLICATION_JSON), Response.class);
//			String result = response.readEntity(String.class);
////			Data.ghiLogRequest("callService: result=" + result);
//			return result;
//		} catch (Exception e) {
//			e.printStackTrace();
////			Data.ghiLogRequest("callService=" + e.getMessage());
//		}
//		return "";

		return callServiceHTTP(url, dataSend);
	}

	public static String callServiceHTTP(String url, String json) {

		String key = Common.generateSessionKeyLowestCase(12);
		try {
			int timeout = 300000;
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeout);
			HttpConnectionParams.setSoTimeout(httpParameters, timeout);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost post = new HttpPost(url);

			if (url.toLowerCase().contains("https://")) {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

				HttpParams params = new BasicHttpParams();
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("https", sf, 443));

				ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
				httpclient = new DefaultHttpClient(ccm, params);
			}

			VMString entity = null;
			try {
				entity = new VMString(json);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			post.setEntity(entity);
			post.setHeader("Content-type", "application/json;charset=UTF-8");
//			post.setHeader("Content-type", "application/x-www-form-urlencoded");

			long time = new Date().getTime();

			Data.ghiLogRequest(key + " request:" + url);
			Data.ghiLogRequest(key + " request:" + json);

			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			Data.ghiLogRequest(key + " request:" + statusLine.getReasonPhrase());
			Data.ghiLogRequest(key + " request:" + statusLine.toString());
			String content = "";
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				content = new String(out.toByteArray(), "UTF-8");
				Data.ghiLogRequest(key + " time:" + (new Date().getTime() - time) + content);
				return statusLine.getStatusCode() + content;
			}
			if (statusLine.getStatusCode() == HttpStatus.SC_BAD_REQUEST
					|| statusLine.getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				content = new String(out.toByteArray(), "UTF-8");
				Data.ghiLogRequest(key + " time:" + (new Date().getTime() - time) + content);
				return statusLine.getStatusCode() + content;
			} else {
				response.getEntity().getContent().close();
			}

			post.abort();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String callServicePostBody(String url, String xml) {

		try {
			int timeout = 300000;
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, timeout);
			HttpConnectionParams.setSoTimeout(httpParameters, timeout);

			HttpClient httpclient = new DefaultHttpClient(httpParameters);
			HttpPost post = new HttpPost(url);

			if (url.toLowerCase().contains("https://")) {
				KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				SSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
				sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

				HttpParams params = new BasicHttpParams();
				SchemeRegistry registry = new SchemeRegistry();
				registry.register(new Scheme("https", sf, 443));

				ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
				httpclient = new DefaultHttpClient(ccm, params);
			}

			VMStringXML entity = null;
			try {
				entity = new VMStringXML(xml);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			post.setHeader("Content-type", "text/xml;charset=UTF-8");

			post.setEntity(entity);

			long time = new Date().getTime();
			HttpResponse response = httpclient.execute(post);
			StatusLine statusLine = response.getStatusLine();
			Data.ghiLogRequest("request:" + url);
//			Data.ghiLogRequest("request:" + json);
			Data.ghiLogRequest("request:" + statusLine.getReasonPhrase());
			Data.ghiLogRequest("request:" + statusLine.toString());
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				String content = new String(out.toByteArray());
				Data.ghiLogRequest("time:" + (new Date().getTime() - time));
				return content;
			} else {

				response.getEntity().getContent().close();
			}

			post.abort();
		} catch (Exception e) {
			Data.ghiLogRequest("request token ex:" + e.getMessage());
		}
		return "";
	}

}
