package share.object.objectHoaDonDonGian;

import com.google.gson.Gson;

public class Item {

    public String id = "";
    public String type = "";
    public String vrt = "";
    public String code = "";
    public String name = "";
    public String unit = "";
    public double price = 0;
    public double quantity = 0;
    public double perdiscount = 0;
    public double amtdiscount = 0;
    public double amount = 0;
    public double vat = 0;
    public double total = 0;
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
    
}
