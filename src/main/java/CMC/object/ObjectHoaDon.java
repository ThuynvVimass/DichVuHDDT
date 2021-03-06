package CMC.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectHoaDon {

	public String keyHoaDon;
	public String NBan;
	public String NMua;
	public String soHoaDon;
	public String maTraCuuHoaDon;
	public String mauSoHoaDon;
	public String maSoDuThuong;
	public String linkPDF;
	public String linkXML;
	public String emailKH;
	public String checkSum;
	public String tenDoangNghiepBan;
	public String tenDoangNghiepMua;

	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}

}
