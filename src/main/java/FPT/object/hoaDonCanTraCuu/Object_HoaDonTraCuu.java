package FPT.object.hoaDonCanTraCuu;

import com.google.gson.Gson;

public class Object_HoaDonTraCuu {

	public String maSoThueNguoiBan;		// stax
	public String dinhDangMuonTraVe;	// type
	public String sidHoaDon;			// sid
	public String tuNgay;				// fd -- Định dạng Năm tháng ngày 2022/03/16 / 2022-03-04
	public String denNgay;				// td
	public String mauSoHoaDon;			// form
	public String kyHieuHoaDon;			// serial
	public String soHoaDon;				// seq
	public String maSoThueNguoiMua;		// btax

	public String username;				// username
	public String password;				// password

    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
