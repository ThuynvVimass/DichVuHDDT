package connect;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;

public class VMString  extends StringEntity{


	public VMString(String data) throws UnsupportedEncodingException
	{
		super(data, "utf-8");
		this.setContentType("application/json");
	}
}
