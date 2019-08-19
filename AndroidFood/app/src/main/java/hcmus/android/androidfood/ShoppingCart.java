package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import hcmus.android.androidfood.module.Cart;
import hcmus.android.androidfood.module.RecyclerDate;
import hcmus.android.androidfood.module.Room;

public class ShoppingCart extends AppCompatActivity implements RecyclerDate.OnNoteListener {
    RecyclerView mRecyclerView;
    RecyclerDate mRcvAdapter;
    ArrayList<String> date;
    ArrayList<Integer> foodIds;
    ArrayList<Integer> numbers;
    ArrayList<ArrayList<Integer>> foodIdDate = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> numberDate = new ArrayList<ArrayList<Integer>>();
    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerDate);
        date = new ArrayList<String>();
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ArrayList<Cart> carts = Room.retrieveCartTable(mDatabase);
        for (int i = 0; i< carts.size(); i++) {
            date.add(carts.get(i).getDate());
        }

        ArrayList<String> newList = new ArrayList<String>();

        // Traverse through the first list
        for (String element : date) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        for (int i = 0; i < newList.size(); i++) {
            foodIds = new ArrayList<Integer>();
            numbers = new ArrayList<Integer>();
            for (int j = 0; j < carts.size(); j++) {
                Log.e("food", String.valueOf(carts.get(j).getFoodId()));
                Log.e("food", String.valueOf(carts.get(j).getDate()));
                if (carts.get(j).getDate().equals(newList.get(i))) {
                    Log.e("food", String.valueOf(carts.get(j).getFoodId()));
                    Log.e("date", String.valueOf(newList.get(i)) );
                    foodIds.add(carts.get(j).getFoodId());
                    numbers.add(carts.get(j).getNumber());
                }
                Log.e("date", String.valueOf(newList.get(i)) );
            }
            foodIdDate.add(foodIds);
            numberDate.add(numbers);
        }

        if (date.size() != 0) {
            mRcvAdapter = new RecyclerDate(foodIdDate, newList, numberDate, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(ShoppingCart.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mRcvAdapter);
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, ShoppingNoteDate.class);
        intent.putExtra("foodIds",foodIdDate.get(position));
        intent.putExtra("numbers",numberDate.get(position));
        startActivity(intent);
    }
}
