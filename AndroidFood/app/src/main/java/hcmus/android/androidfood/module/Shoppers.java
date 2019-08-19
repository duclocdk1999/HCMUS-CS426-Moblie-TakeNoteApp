package hcmus.android.androidfood.module;

public class Shoppers {
    String name;
    String address;
    String phone;
    Double lat;
    Double longT;

    //---------------------------------------------
    public Shoppers(String name, String address, String phone, Double lat, Double longT) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.lat = lat;
        this.longT = longT;
    }

    public String getName() {
        return name;
    }
    public String getAddress() { return address;}
    public String getPhone() { return phone;}
    public Double getLat() {return lat;}
    public Double getLongT() { return  longT;}
}
