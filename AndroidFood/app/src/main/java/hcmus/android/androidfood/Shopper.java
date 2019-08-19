package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import hcmus.android.androidfood.module.RecyclerShopper;
import hcmus.android.androidfood.module.Room;
import hcmus.android.androidfood.module.Shoppers;

public class Shopper extends AppCompatActivity implements RecyclerShopper.OnNoteListener {
    RecyclerView mRecyclerView;
    RecyclerShopper mRcvAdapter;
    ArrayList<String> nameShopper;
    ArrayList<String> addressShopper;
    ArrayList<String> phoneShopper;
    ArrayList<Double> latShopper;
    ArrayList<Double> longShopper;

    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerShopper);
        nameShopper = new ArrayList<String>();
        addressShopper = new ArrayList<String>();
        phoneShopper = new ArrayList<String>();
        longShopper = new ArrayList<Double>();
        latShopper = new ArrayList<Double>();
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ArrayList<Shoppers> shoppers = Room.retrieveShopperTable(mDatabase);
        for (int i = 0; i< shoppers.size(); i++) {
            nameShopper.add(shoppers.get(i).getName());
            addressShopper.add(shoppers.get(i).getAddress());
            phoneShopper.add(shoppers.get(i).getPhone());
            latShopper.add(shoppers.get(i).getLat());
            longShopper.add(shoppers.get(i).getLongT());
        }

        if (nameShopper.size() != 0) {
            mRcvAdapter = new RecyclerShopper(nameShopper, addressShopper, latShopper, longShopper, phoneShopper, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(Shopper.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mRcvAdapter);
        }
    }

    public void addShoppers(View view) {
        Intent addNoteIntent = new Intent(this, AddShopper.class);
        startActivityForResult(addNoteIntent,6);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("lat",latShopper.get(position));
        intent.putExtra("long",longShopper.get(position));
        startActivity(intent);
    }
}
