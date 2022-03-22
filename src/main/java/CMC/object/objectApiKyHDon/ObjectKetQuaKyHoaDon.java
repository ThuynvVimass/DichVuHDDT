package CMC.object.objectApiKyHDon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectKetQuaKyHoaDon {
	
	public String maBase64;
	
	public String errorCode = "";
	public String thongTinThem = "";
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}
}
