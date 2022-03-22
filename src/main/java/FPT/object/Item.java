package FPT.object;

import com.google.gson.Gson;

public class Item{
    public int line = 1;
    public String type = "";
    public String vrt = "";
    public String code = "";
    public String name = "";
    public String unit = "";
    public double price = 0;
    public int quantity = 0;
    public int perdiscount = 0;
    public double amtdiscount = 0;
    public double amount = 0;
    public double vat = 0;
    public double total = 0;
    
    @Override
	public String toString() {
		return new Gson().toJson(this);
	}
}