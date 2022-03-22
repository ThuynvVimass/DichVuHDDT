package connect;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

public class VMStringXML  extends StringEntity{


	public VMStringXML(String data) throws UnsupportedEncodingException
	{
		super(data, "utf-8");
		this.setContentType("text/xml");
	}
}
