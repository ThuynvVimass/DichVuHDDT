package share.object;

import FPT.object.hoaDonCanTraCuu.objectResponse.Item_TC;
import FPT.object.hoaDonCanTraCuu.objectResponse.Response_TC;
import FPT.object.hoaDonCanTraCuu.objectResponse.Root_TC;
import share.object.objectHoaDonDonGian.Item;
import share.object.objectHoaDonDonGian.Root;
import share.object.objectHoaDonDonGian.Object_ListHoaDon;
import share.object.objectHoaDonDonGian.ThongTinHoaDon;

import java.util.ArrayList;

public class ObjectMapper {

    public static Object_ListHoaDon responseFPT_toObject_ListHoaDon (Response_TC response_tc) {
        Object_ListHoaDon kq = new Object_ListHoaDon();

        ArrayList<Root> listHoaDon = new ArrayList<>();

        for (Root_TC root_tc : response_tc.listHD){
            Root root =  new Root();
            // Thong tin nguoi ban
            root.thongTinNguoiBan.tenCty = root_tc.doc.sname;
            root.thongTinNguoiBan.nganHang = root_tc.doc.sbank;
            root.thongTinNguoiBan.mail = root_tc.doc.smail;
            root.thongTinNguoiBan.diaChi = root_tc.doc.saddr;
            root.thongTinNguoiBan.maSoThue = root_tc.doc.stax;

            // Thong tin nguoi mua
            root.thongTinNguoiMua.code = root_tc.doc.bcode;
            root.thongTinNguoiMua.tenCty = root_tc.doc.bname;
            root.thongTinNguoiMua.tenNgMua = root_tc.doc.buyer;
            root.thongTinNguoiMua.maSoThue = root_tc.doc.btax;
            root.thongTinNguoiMua.diaChi = root_tc.doc.baddr;
            root.thongTinNguoiMua.sdt = root_tc.doc.btel;
            root.thongTinNguoiMua.mail = root_tc.doc.bmail;
            root.thongTinNguoiMua.soTaiKhoan = root_tc.doc.bacc;
            root.thongTinNguoiMua.nganHang = root_tc.doc.bbank;

            // Thong tin chi tiet hoa don
            ThongTinHoaDon thongTinHoaDon = new ThongTinHoaDon();
            thongTinHoaDon.sid = root_tc.doc.sid;
            thongTinHoaDon.sec = root_tc.doc.sec;
            thongTinHoaDon.seq = root_tc.doc.seq;
            thongTinHoaDon.idt = root_tc.doc.idt;

            thongTinHoaDon.loaiHoaDon = root_tc.doc.type;
            thongTinHoaDon.mauHoaDon = root_tc.doc.form;
            thongTinHoaDon.kyHieuHoaDon = root_tc.doc.serial;

            thongTinHoaDon.loaiHinhThanhToan = root_tc.doc.paym;
            thongTinHoaDon.maTienTe = root_tc.doc.curr;
            thongTinHoaDon.tiGia = root_tc.doc.exrt;

            thongTinHoaDon.note = root_tc.doc.note;
            thongTinHoaDon.sumv = root_tc.doc.sumv;
            thongTinHoaDon.sum = root_tc.doc.sum;
            thongTinHoaDon.vatv = root_tc.doc.vatv;
            thongTinHoaDon.vat = root_tc.doc.vat;
            thongTinHoaDon.word = root_tc.doc.word;
            thongTinHoaDon.totalv = root_tc.doc.totalv;
            thongTinHoaDon.total = root_tc.doc.total;
            thongTinHoaDon.tradeamount = root_tc.doc.tradeamount;
            thongTinHoaDon.discount = root_tc.doc.discount;
            thongTinHoaDon.aun = root_tc.doc.aun;
            thongTinHoaDon.sign = root_tc.doc.sign;
            thongTinHoaDon.type_ref = root_tc.doc.type_ref;
            thongTinHoaDon.listdt = root_tc.doc.listdt;
            thongTinHoaDon.sendtype = root_tc.doc.sendtype;

            ArrayList<Item> items = new ArrayList<Item>();
            for (Item_TC item_tc : root_tc.doc.items) {
                Item item = new Item();
                item.id = item_tc.id;
                item.type = item_tc.type;
                item.vrt = item_tc.vrt;
                item.code = item_tc.code;
                item.name = item_tc.name;
                item.unit = item_tc.unit;
                item.price = item_tc.price;
                item.quantity = item_tc.quantity;
                item.perdiscount = item_tc.perdiscount;
                item.amtdiscount = item_tc.amtdiscount;
                item.vat = item_tc.vat;
                item.total = item_tc.total;

                items.add(item);
            }
            thongTinHoaDon.items = items;
            root.thongTinHoaDon = thongTinHoaDon;

            listHoaDon.add(root);
        }
        kq.listHoaDon =  listHoaDon;
        return kq;
    }

}
