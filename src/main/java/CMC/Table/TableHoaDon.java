package CMC.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import CMC.object.ObjectThongTinHoaDon;
import vn.vimass.csdl.utilDB.DbUtil;
import vn.vimass.utils.Data;

public class TableHoaDon {
	
	public static final String TABLE_NAME = "thongTinHoaDon";
	
	public static final String maSoThueNguoiBan = "maSoThueNguoiBan";
	public static final String maSoThueNguoiMua = "maSoThueNguoiMua";
	public static final String thoiGian = "thoiGian";
	public static final String soHoaDon = "soHoaDon";
	public static final String maHoaDon = "maHoaDon";
	public static final String maSoDuThuong = "maSoDuThuong";
	public static final String soTien = "soTien";
	public static final String coQuanThue = "coQuanThue";	
	public static final String keyHoaDon = "keyHoaDon";
	public static final String linkPDF = "linkPDF";
	public static final String dsHHDV = "dsHHDV";
	

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
	
	public static String taoDuLieu(ObjectThongTinHoaDon item,String keyHoaDonInput ,String soHoaDonInput,
			String maHoaDonInput,String maSoDuThuongInput,String coQuanThueInput,
			String linkPDFInput) {
		String TAG = "TableHoaDon-taoDuLieu";
		
		
		String idKQ = "";
		
		long timenow = new Date().getTime();

		try {
			
			String strSqlInsert = "INSERT INTO " + TABLE_NAME + ""
					+ " ("
					+ keyHoaDon + ", "						
					+ maSoThueNguoiMua + ", "
					+ thoiGian + ", "
					+ maSoThueNguoiBan + ", "
					+ maHoaDon + ", "
					+ maSoDuThuong + ", "
					+ soTien + ", "
					+ coQuanThue + ", "	
					+ soHoaDon+ ", "
					+ linkPDF + ", "
					+ dsHHDV 
					+ " ) VALUES ("
					+ "N'" + keyHoaDonInput + "',"
					+ "N'" + item.NMua.maSoThue + "',"
					+ timenow + ","
					+ "N'" + item.NBan.maSoThue + "',"					
					+ "N'" + maHoaDonInput + "',"
					+ "N'" + maSoDuThuongInput + "',"
					+ item.listHHDV.get(0).tgTien + ","
					+ "N'" + coQuanThueInput + "',"					
					+ "N'" + soHoaDonInput + "',"
					+ "N'" + linkPDFInput + "',"					
					+ "N'" + item.getListHHDV() + "'"
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
				
				idKQ = maHoaDonInput;
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
