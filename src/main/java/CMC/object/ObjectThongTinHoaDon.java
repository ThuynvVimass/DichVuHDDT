package CMC.object;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectThongTinHoaDon {
	
	public String KeyOld = ""; 		// Gửi khi lập hoá đơn điều chỉnh hoặc thay thế
	
	public int kieuPhatHanh = 0;
	public List<ObjectHangHoaDichVu> listHHDV = new ArrayList<ObjectHangHoaDichVu>();
	public String MSTNBan = "";
	public String MSTNMua = "";
	public String EmailNMua = "";
	public ThongTinDonVi NBan = new ThongTinDonVi();
	public ThongTinDonVi NMua = new ThongTinDonVi();
		
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}
	
	public String getListHHDV()
	{
		return new Gson().toJson(listHHDV);
	}
}
