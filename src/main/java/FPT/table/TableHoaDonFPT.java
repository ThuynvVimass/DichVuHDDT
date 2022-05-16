package FPT.table;

import FPT.objectResponse.Root;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class TableHoaDonFPT {
	
	public static final String TABLE_NAME = "ThongTinHoaDonFPT";

	public static final String sidHoaDon = "sidHoaDon";				// PK
	public static final String maSoThueNguoiBan = "maSoThueNguoiBan";
	public static final String maSoThueNguoiMua = "maSoThueNguoiMua";
	public static final String thoiGianFPT = "thoiGianFPT";
	public static final String thoiGian = "thoiGian";
	public static final String soHoaDon = "soHoaDon";
	public static final String maTraCuuHoaDon = "maTraCuuHoaDon";
	public static final String mauSoHoaDon = "mauSoHoaDon";
	public static final String kiHieuHoaDon = "kiHieuHoaDon";
	public static final String maSoDuThuong = "maSoDuThuong";
	public static final String soTien = "soTien";
	public static final String coQuanThue = "coQuanThue";
	public static final String linkPDF = "linkPDF";
	public static final String linkXML = "linkXML";
	public static final String dsHHDV = "dsHHDV";
	public static final String tenDoangNghiepMua = "tenDoangNghiepMua";
	public static final String tenDoangNghiepBan = "tenDoangNghiepBan";
	public static final String emailKH = "emailKH";

	public static ObjectTableHoaDonFPT layDuLieu(String sidHoaDon)
	{
		String TAG = "TableHoaDonFPT-layDuLieu";
		ObjectTableHoaDonFPT idKQ = null;
		try {
			String strSqlSelect = "SELECT * FROM " + TABLE_NAME;
			strSqlSelect += " WHERE "
									+ TableHoaDonFPT.sidHoaDon + " = '" + sidHoaDon + "'"
									+ ";";

			Data.ghiLogRequest(TAG + "\tselect:" + strSqlSelect);

			Connection connect = null;
			Statement statement = null;

			connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			statement = connect.createStatement();;

			ResultSet rs = statement.executeQuery(strSqlSelect);

			ObjectTableHoaDonFPT objectHoaDon = new ObjectTableHoaDonFPT();
			while (rs.next()) {
				objectHoaDon.sidHoaDon = rs.getString("sidHoaDon");
				objectHoaDon.thoiGianFPT = rs.getString("thoiGianFPT");
				objectHoaDon.soHoaDon = rs.getString("soHoaDon");
				objectHoaDon.mauSoHoaDon = rs.getString("mauSoHoaDon");
				objectHoaDon.kiHieuHoaDon = rs.getString("kiHieuHoaDon");
				objectHoaDon.maTraCuuHoaDon = rs.getString("maTraCuuHoaDon");
				objectHoaDon.maSoDuThuong = rs.getString("maSoDuThuong");
				objectHoaDon.linkPDF = rs.getString("linkPDF");
				objectHoaDon.linkXML = rs.getString("linkXML");
				objectHoaDon.tenDoangNghiepMua = rs.getString("tenDoangNghiepMua");
				objectHoaDon.tenDoangNghiepBan = rs.getString("tenDoangNghiepBan");
				objectHoaDon.emailKH = rs.getString("emailKH");

			}
			Data.ghiLogRequest(TAG + "\tkq:" + objectHoaDon);
			if (!objectHoaDon.sidHoaDon.equals("")) {
				idKQ = objectHoaDon;
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return idKQ;
	}

	public static String taoDuLieu(Root r)
	{
		String TAG = "TableHoaDonFPT-taoDuLieu";
		String idKQ = "";
		long timenow = new Date().getTime();
		try {
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
										  + " ("
										  + sidHoaDon + ", "
										  + maSoThueNguoiMua + ", "
										  + thoiGianFPT + ", "
										  + thoiGian + ", "
										  + maSoThueNguoiBan + ", "
										  + maTraCuuHoaDon + ", "
										  + maSoDuThuong + ", "
										  + soTien + ", "
										  + coQuanThue + ", "
										  + soHoaDon + ", "
										  + mauSoHoaDon + ", "
										  + kiHieuHoaDon + ", "
										  + linkPDF + ", "
										  + linkXML + ", "
										  + dsHHDV  + ", "
										  + tenDoangNghiepBan + ", "
										  + tenDoangNghiepMua + ", "
										  + emailKH
										  + " ) VALUES ("
										  + "N'" + r.sid + "',"
										  + "N'" + r.btax + "',"
										  + "N'" + r.idt + "',"
										  + timenow + ","
										  + "N'" + r.stax + "',"
										  + "N'" + "" + "',"
										  + "N'" + "" + "',"
										  + r.sumv + ","
										  + "N'" + "" + "',"
										  + "N'" + r.seq + "',"
										  + "N'" + r.form + "',"
										  + "N'" + r.serial + "',"
										  + "N'" + "" + "',"
										  + "N'" + "" + "',"
										  + "N'" + r.getListHHDV() + "',"
										  + "N'" +  r.sname + "',"
										  + "N'" +  r.bname + "',"
										  + "N'" + r.bmail + "'"
										  + ");";
			
			Data.ghiLogRequest(TAG + "\tinsert:" + strSqlInsert);
			
			PreparedStatement statement = null;
			Connection connect = null;

			connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			statement = (PreparedStatement) connect
					.prepareStatement(strSqlInsert);
			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				idKQ = sidHoaDon;
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi========" + e.getMessage());
		}
		return idKQ;
	}

	public static int getMaxSoHoaDon(String maSoThue) 
	{
		int kq = 0;
		PreparedStatement statement = null;
		Connection connect = null;		
		ResultSet result = null;
		try {
			connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			String strSelect = "select count(*) as maxHD from  "
									   + TABLE_NAME
									   + " where "
									   + maSoThueNguoiBan + " = N'" + maSoThue + "'"
									   + ";";
			Data.ghiLogRequest("getMaxSoHoaDon - select=" + strSelect);
			statement = (PreparedStatement) connect.prepareStatement(strSelect);
			result = statement.executeQuery();			
			while (result.next()) {				
				kq = result.getInt("maxHD");
			}
		} catch (Exception e) {

			Data.ghiLogRequest("getMaxSoHoaDon - Exception 2="
					+ e.getMessage());
		}
		return kq;
	}


}
