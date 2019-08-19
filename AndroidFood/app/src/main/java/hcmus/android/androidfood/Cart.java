package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hcmus.android.androidfood.module.LVHelper;
import hcmus.android.androidfood.module.Room;

public class Cart extends AppCompatActivity {
    String foodName;
    Integer foodId;
    ArrayList<String> ingredient;
    ArrayList<Float> amount = new ArrayList<Float>();
    ArrayList<String> unit;
    TextView foodText;
    TextView numIndex;
    ListView ingredientList;
    ArrayAdapter<String> adapter;
    ArrayList<String> itemList;
    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        foodName = getIntent().getStringExtra("foodName");
        foodId = getIntent().getIntExtra("foodId", 0);
        ingredient = getIntent().getStringArrayListExtra("ingredient");
        amount = (ArrayList<Float>) getIntent().getSerializableExtra("amount");
        unit = getIntent().getStringArrayListExtra("unit");
        numIndex = (TextView) findViewById(R.id.numIndex);
        foodText = (TextView) findViewById(R.id.foodCart);
        ingredientList = (ListView) findViewById(R.id.listIngredientCart);
        foodText.setText(foodName);
        itemList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
        for (int i = 0; i < ingredient.size(); i++) {
                itemList.add(String.valueOf(amount.get(i)) + " " + unit.get(i) + " " + ingredient.get(i));
                adapter.notifyDataSetChanged();
                ingredientList.setAdapter(adapter);
                LVHelper.getListViewSize(ingredientList);
            }
        }

    public void increaseNum(View view) {
        Integer index = Integer.parseInt(numIndex.getText().toString());
        index += 1;
        numIndex.setText(String.valueOf(index));
        itemList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
        for (int i = 0; i < ingredient.size(); i++) {
            itemList.add(String.valueOf(amount.get(i)*index) + " " + unit.get(i) + " " + ingredient.get(i));
            adapter.notifyDataSetChanged();
            ingredientList.setAdapter(adapter);
            LVHelper.getListViewSize(ingredientList);
        }
    }

    public void decreaseNum(View view) {
        Integer index = Integer.parseInt(numIndex.getText().toString());
        if (index >=2 ) {
            index--;
            numIndex.setText(String.valueOf(index));
            itemList = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
            for (int i = 0; i < ingredient.size(); i++) {
                itemList.add(String.valueOf(amount.get(i)*index) + " " + unit.get(i) + " " + ingredient.get(i));
                adapter.notifyDataSetChanged();
                ingredientList.setAdapter(adapter);
                LVHelper.getListViewSize(ingredientList);
            }
        }
    }

    public void saveCart(View view) {
        Integer index = Integer.parseInt(numIndex.getText().toString());
        SimpleDateFormat key = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String keyId = key.format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:hh:mm");
        String date = sdf.format(new Date());
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Room.insertIntoCartTable(mDatabase, foodId, date, index, keyId);
        Intent mainIntent = new Intent(this, Splash.class);
        startActivity(mainIntent);
    }
}
