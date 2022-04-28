package CMC.object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ThongTinDonVi {
	
	public String maSoThue = "";
	public int trangThai = 0;
	public long thoiGian = 0;
	public String maNhaCungCap = "";
	public String tenDoanhNghiep = "";
	public String tenGiaoDich = "";
	public String diaChi = "";
	public String coQuanThue = "";		
	public String mauSoHoaDon = "";
	public String kyHieuHoaDon = "";	
	public int maxHD = 0;	
	public String user = "";
	public String password = "";

	public String coQuanThueCapCuc = "";
	public String banQuyen = "";
	public String goiHoaDon = "";
	
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}
	
}
