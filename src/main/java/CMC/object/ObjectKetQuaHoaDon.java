package CMC.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectKetQuaHoaDon {
	public String errorCode = "";
	public String idHoaDon = "";
	public String mauSoHoaDon = "";
	public String kyHieuHoaDon = "";
	public String SoHoaDon = "";
	public String maTraCuuHoaDon = "";
	public String thongTinThem = "";

	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}
}
