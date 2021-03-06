package FPT.objectResponse;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Root {
	public String sid;
    public String idt;
    public String type;
    public String form;
    public String serial;
    public String seq;
    public String bcode;
    public String bname;
    public String buyer;
    public String btax;
    public String baddr;
    public String btel;
    public String bmail;
    public String paym;
    public String curr;
    public int exrt;
    public String bacc;
    public String bbank;
    public String note;
    public int sumv;
    public int sum;
    public int vatv;
    public int vat;
    public String word;
    public int totalv;
    public int total;
    public int tradeamount;
    public String discount;
    public int aun;
    public int sign;
    public int type_ref;
    public String listnum;
    public String listdt;
    public int sendtype;
    public ArrayList<Item> items;
    public String stax;
    public Org org;
    public int hascode;
    public String name;
    public ArrayList<Tax> tax;
    public int tradeamountv;
    public String sname;
    public String saddr;
    public String smail;
    public String stel;
    public String taxo;
    public String sacc;
    public String sbank;
    public String ot;
    public String on;
    public int ou;
    public String uc;
    public String id;
    public String sec;
    public int status;
    public String ic;
    public String adt;
    public Object sendfile;

    public String getListHHDV()
    {
        return new Gson().toJson(items);
    }
}
