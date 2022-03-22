package CMC.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectHoaDon {
	
	public String NBan;
	public String NMua;
	public String soHoaDon;
	public String maHoaDon;
	public String maSoDuThuong;
	public String linkPDF;
	public String linkXML;
	public String emailKH;
	public String checkSum;
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}

}
