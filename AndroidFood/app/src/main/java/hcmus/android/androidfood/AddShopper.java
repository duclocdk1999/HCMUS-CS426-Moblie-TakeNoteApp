package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import hcmus.android.androidfood.module.GeocodingLocation;
import hcmus.android.androidfood.module.Room;

public class AddShopper extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText phone;
    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopper);

    }
    public void saveShopper(View view) {
        name = (EditText) findViewById(R.id.shopName);
        address = (EditText) findViewById(R.id.addressName);
        phone = (EditText) findViewById(R.id.phone);
        GeocodingLocation locationAddress = new GeocodingLocation();
        ArrayList<Double> latlong = locationAddress.getAddressFromLocation(address.getText().toString(),
                getApplicationContext());
        Double lat;
        Double longT;
        if (latlong.size() == 0) {
            lat = new Double(0);
            longT = new Double(0);
        }
        else {
            lat = latlong.get(0);
            longT = latlong.get(1);
        }
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Room.insertIntoShopperTable(mDatabase, name.getText().toString(), address.getText().toString(), phone.getText().toString(),lat,longT );
        Intent mainIntent = new Intent(this, Splash.class);
        startActivity(mainIntent);

    }
}
