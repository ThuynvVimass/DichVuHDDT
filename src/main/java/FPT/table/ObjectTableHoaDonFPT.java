package FPT.table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectTableHoaDonFPT {

	public String sidHoaDon;
	public String maSoThueNguoiBan;
	public String maSoThueNguoiMua;
	public String thoiGianFPT;
	public String thoiGian;
	public String soHoaDon;
	public String maTraCuuHoaDon;
	public String mauSoHoaDon;
	public String kiHieuHoaDon;
	public String maSoDuThuong;
	public String soTien;
	public String coQuanThue;
	public String linkPDF;
	public String linkXML;
	public String dsHHDV;
	public String tenDoangNghiepMua;
	public String tenDoangNghiepBan;
	public String emailKH;
	@Override
	public String toString() {
		Gson gson = new GsonBuilder()
			    .disableHtmlEscaping()
			    .create();
				return gson.toJson(this);
	}

}
