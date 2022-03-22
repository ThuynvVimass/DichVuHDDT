package CMC.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.Gson;

import CMC.object.ThongTinDonVi;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

public class TableDonVi {
	
	public static final String TABLE_NAME = "thongTinDonVi";
	
	public static final String maSoThue = "maSoThue";
	public static final String trangThai = "trangThai";
	public static final String thoiGian = "thoiGian";
	public static final String maNhaCungCap = "maNhaCungCap";
	public static final String tenDoanhNghiep = "tenDoanhNghiep";
	public static final String tenGiaoDich = "tenGiaoDich";
	public static final String diaChi = "diaChi";
	public static final String coQuanThue = "coQuanThue";	
	public static final String coQuanThueCapCuc = "coQuanThueCapCuc";	
	public static final String maSoHoaDon = "maSoHoaDon";
	public static final String kyHieuHoaDon = "kyHieuHoaDon";
	public static final String checkSum = "checkSum";
	public static final String maxHD = "maxHD";
	public static final String banQuyen = "banQuyen";
	public static final String goiHoaDon = "goiHoaDon";
	
	public static final String password = "password";
	public static final String user = "user";

//	private static thongTinDonVi getValue(ResultSet resultSet) {
//		thongTinDonVi item = new thongTinDonVi();
//		
//		try
//		{
//			item.maGiaoDich = resultSet.getString(maGiaoDich);
//			item.trans_id = resultSet.getString(trans_id);
//			item.trans_date = resultSet.getString(trans_date);
//			
//			
//			item.customer_id = resultSet.getString(customer_id);
//
//			item.service_id = resultSet.getString(service_id);
//
//			item.bill_id = resultSet.getString(bill_id);
//
//			item.amount = resultSet.getInt(amount);
//
//			
//			item.timeCreate = resultSet.getLong(timeCreate);
//			item.cks = resultSet.getString(cks);
//			
//		}
//		catch(Exception e)
//		{
//			
//		}
//		
//		return item;
//	}
//	
	
	public static String taoDuLieuDonVi(ThongTinDonVi item) {
		String TAG = "taoDuLieuDonVi=========";
		
		
		String idKQ = "";
		
		String checkSum1 = "";

		try {
			
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
					+ " ("
					+ maSoThue + ", "
					+ trangThai + ", "
					+ thoiGian + ", "
					+ maNhaCungCap + ", "
					+ tenDoanhNghiep + ", "
					+ tenGiaoDich + ", "							
					+ diaChi + ", "
					+ coQuanThue + ", "
							+ coQuanThueCapCuc + ", "
					+ maSoHoaDon + ", "
					+ kyHieuHoaDon + ", "
					+ checkSum + ", "
					+ maxHD + ", "
						+ banQuyen + ", "
						+ goiHoaDon + ", "
					+ password + ", "
					+ user
					+ " ) VALUES ("
					+ "N'" + item.maSoThue + "',"
					+ item.trangThai + ","
					+ item.thoiGian + ","
					+ "N'" + item.maNhaCungCap + "',"
					+ "N'" + item.tenDoanhNghiep + "',"
					+ "N'" + item.tenGiaoDich + "',"
					+ "N'" + item.diaChi + "',"
					+ "N'" + item.coQuanThue + "',"
							+ "N'" + item.coQuanThueCapCuc + "',"
					+ "N'" + item.maSoHoaDon + "',"
					+ "N'" + item.kyHieuHoaDon + "',"
					+ "N'" + checkSum1 + "',"
					+ item.maxHD + ","
							+ "N'" + item.banQuyen + "',"
							+ "N'" + item.goiHoaDon + "',"
					+ "N'" + item.user + "',"
					+ "N'" + item.password + "'"
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
				
				idKQ = item.maSoThue;
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
			Data.ghiLogRequest(TAG + "\tLoi 1=======");
		}
		return idKQ;
	}
	
	public static ThongTinDonVi getThongTinDonVi(String maSoThueInput) 
	{
		ThongTinDonVi kq = new ThongTinDonVi();
		PreparedStatement statement = null;
		Connection connect = null;
		ResultSet resultSet = null;
		try {
			connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			String strSelect = "select * from  "
					+ TABLE_NAME
					+ " where "
					+ maSoThue + " = N'" + maSoThueInput + "'"
					+ ";";
			Data.ghiLogRequest("getThongTinDonVi - select=" + strSelect);
			statement = (PreparedStatement) connect.prepareStatement(strSelect);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				try
				{
					kq.maSoThue = resultSet.getString(maSoThue);
					kq.trangThai = resultSet.getInt(trangThai);
					kq.thoiGian = resultSet.getLong(thoiGian);
					kq.maNhaCungCap = resultSet.getString(maNhaCungCap);
					kq.tenDoanhNghiep = resultSet.getString(tenDoanhNghiep);
					kq.tenGiaoDich = resultSet.getString(tenGiaoDich);
					kq.diaChi = resultSet.getString(diaChi);
					kq.coQuanThue = resultSet.getString(coQuanThue);
						kq.coQuanThueCapCuc = resultSet.getString(coQuanThueCapCuc);
					kq.maSoHoaDon = resultSet.getString(maSoHoaDon);
					kq.kyHieuHoaDon = resultSet.getString(kyHieuHoaDon);
						kq.banQuyen = resultSet.getString(banQuyen);
						kq.goiHoaDon = resultSet.getString(goiHoaDon);
					kq.user = resultSet.getString(user);
					kq.password = resultSet.getString(password);																			
				}
				catch(Exception e)
				{
					Data.ghiLogRequest("getThongTinDonVi - Exception="
							+ e.getMessage());
				}	
			}
		} catch (Exception e) {

			Data.ghiLogRequest("getThongTinDonVi - Exception="
					+ e.getMessage());
		}
		Data.ghiLogRequest("getThongTinDonVi() res: " + new Gson().toJson(kq));
		return kq;
	}
	
	public static String suaDuLieuDonVi(ThongTinDonVi item) {
		String TAG = "suaDuLieuDonVi=========";
		
		
		String idKQ = "";
		
		String checkSum1 = "";

		try {
			
			String strSqlUpdate = "UPDATE " + TABLE_NAME + " SET "
					+ trangThai + " = " + item.trangThai + ", "
					+ thoiGian + " = " + item.thoiGian + ", "
					+ maNhaCungCap + " = N'" + item.maNhaCungCap + "', "
					+ tenDoanhNghiep + " = N'" + item.tenDoanhNghiep + "', "
					+ tenGiaoDich + " = N'" + item.tenGiaoDich + "', "							
					+ diaChi + " = N'" + item.diaChi + "', "
					+ coQuanThue + " = N'" + item.coQuanThue + "', "
							+ coQuanThueCapCuc + " = N'" + item.coQuanThueCapCuc + "', "
					+ maSoHoaDon + " = N'" + item.maSoHoaDon + "', "
					+ kyHieuHoaDon+  " = N'" + item.kyHieuHoaDon + "', "
					+ checkSum + " = N'" + checkSum1 + "', "
					+ maxHD + " = " + item.maxHD + ", "
							+ banQuyen + " = N'" + item.banQuyen + "', "
							+ goiHoaDon + " = N'" + item.goiHoaDon + "', "
					+ password + " = N'" + item.password + "', "
					+ user + " = N'" + item.user + "'";
							
			strSqlUpdate += " WHERE "
					+ maSoThue + " = '" + item.maSoThue + "'"
					+ ";";
			
			Data.ghiLogRequest(TAG + "\tupdate:" + strSqlUpdate);
			
			PreparedStatement statement = null;
			Connection connect = null;

			connect = DbUtil.getConnect(DbUtil.URL, DbUtil.USER, DbUtil.PASS);
			statement = (PreparedStatement) connect
					.prepareStatement(strSqlUpdate);
			int kq = statement.executeUpdate();
			Data.ghiLogRequest(TAG + "\tkq:" + kq);
			if (kq > 0) {
				
				idKQ = item.maSoThue;
			} else {
				Data.ghiLogRequest(TAG + "\tLoi========");
			}
		} catch (Exception e) {
		}
		return idKQ;
	}
	
}
