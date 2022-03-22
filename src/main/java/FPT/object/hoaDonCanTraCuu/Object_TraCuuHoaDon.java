package FPT.object.hoaDonCanTraCuu;

import com.google.gson.Gson;

public class Object_TraCuuHoaDon {

	public String maSoThueNguoiBan = "";		// stax
	public String dinhDangMuonTraVe = "json";	// type
	public String sidHoaDon = "";				// sid
	public String tuNgay = "";					// fd
	public String denNgay = "";					// td
	public String mauSoHoaDon = "";				// form
	public String kyHieuHoaDon = "" ;			// serial
	public String soHoaDon = "";				// seq
	public String maSoThueNguoiMua = "";		// btax

	public String trangThai = "";				// status

	public String username = "";				// username
	public String password = "";				// password

    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
