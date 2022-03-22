package connect;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class ConnectUsingPost {
	private final String USER_AGENT = "Mozilla/5.0";

	public ConnectUsingPost() {

	}

	public String post(String sLink, String key, String param) {

		String url = sLink;
        Client client = ClientBuilder.newClient();
        WebTarget cashOutTarget = client.target(url);
        Form form = new Form().param(key, param);
        Response response = cashOutTarget.request().post(Entity.form(form));
        String result = response.readEntity(String.class);
        return result;
	}
	// public String sendPost(String sLink, List<NameValuePair> nameValuePairs)
	// {
	//
	// String content = "";
	// try {
	//
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost post = new HttpPost(sLink);
	//
	// // List<NameValuePair> nameValuePairs = new
	// // ArrayList<NameValuePair>(1);
	// // nameValuePairs.add(new BasicNameValuePair(Data.ParamName,
	// // input.toString()));
	// post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	//
	// // Execute HTTP Post Request
	// HttpResponse response = httpclient.execute(post);
	//
	// StatusLine statusLine = response.getStatusLine();
	// // Data.ghiLogRequest("request:" + statusLine.getReasonPhrase());
	// // Data.ghiLogRequest("request:" + statusLine.toString());
	// if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// response.getEntity().writeTo(out);
	// out.close();
	// content = out.toString();
	// // Data.ghiLogRequest("content:" + content);
	// } else {
	// response.getEntity().getContent().close();
	// }
	//
	// post.abort();
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// content = "error";
	// }
	// return content;
	// }

}