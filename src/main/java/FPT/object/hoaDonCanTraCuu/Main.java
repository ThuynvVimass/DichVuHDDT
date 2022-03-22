package FPT.object.hoaDonCanTraCuu;

import java.util.ArrayList;

import FPT.object.hoaDonCanTraCuu.objectHoaDonDonGian.Inv;
import FPT.object.hoaDonCanTraCuu.objectHoaDonDonGian.Object_ListHoaDon;
import FPT.object.hoaDonCanTraCuu.objectResponse.Response_TC;
import FPT.object.hoaDonCanTraCuu.objectResponse.Root_TC;

public class Main {

	public static Object_ListHoaDon objectResponseToObjectHoaDonDonGian(Response_TC r) {
	
		ArrayList<Inv> listHoaDon = new ArrayList<Inv>();
		
		for (Root_TC root_TC : r.listHD) {	
			Inv inv =  new Inv ();
			inv.thongTinNguoiBan.sname = root_TC.doc.sname;
			
			
			listHoaDon.add(inv);
		}
		
		Object_ListHoaDon obj = new  Object_ListHoaDon (listHoaDon);
		return obj;
	}
}
	
