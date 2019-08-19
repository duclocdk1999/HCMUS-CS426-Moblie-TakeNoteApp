package hcmus.android.androidfood;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hcmus.android.androidfood.module.Ingredient;
import hcmus.android.androidfood.module.LVHelper;
import hcmus.android.androidfood.module.Room;

public class ShoppingNoteDate extends AppCompatActivity {
    ArrayList<Integer> foodIds;
    ArrayList<Integer> numbers;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    ArrayList<String> ingredientName = new ArrayList<String>();
    ArrayList<Float> amount = new ArrayList<Float>();
    ArrayList<String> unit = new ArrayList<String>();
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    ListView lv;
    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_note_date);
        lv = (ListView)findViewById(R.id.listTotalIngredient);
        foodIds = getIntent().getIntegerArrayListExtra("foodIds");
        numbers = getIntent().getIntegerArrayListExtra("numbers");
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ingredients = Room.retrieveIngredientTable(mDatabase);
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
        for (int i = 0; i < ingredients.size(); i++) {
            for (int j = 0; j < foodIds.size(); j++) {
                if (foodIds.get(j) == ingredients.get(i).getFoodId()) {
                    ingredientName.add(ingredients.get(i).getIngredientName().toLowerCase());
                    if (ingredients.get(i).getUnit().toLowerCase().equals("gr")) {
                        unit.add("kg");
                        amount.add((ingredients.get(i).getAmount() * numbers.get(j)) / 1000);
                    }
                    else if (ingredients.get(i).getUnit().toLowerCase().equals("ml")) {
                        unit.add("l");
                        amount.add((ingredients.get(i).getAmount() * numbers.get(j)) / 1000);
                    }
                    else if (ingredients.get(i).getUnit().toLowerCase().equals("lít") || ingredients.get(i).getUnit().toLowerCase().equals("lit")) {
                        unit.add("l");
                        amount.add((ingredients.get(i).getAmount() * numbers.get(j)));
                    }
                    else {
                        unit.add(ingredients.get(i).getUnit().toLowerCase());
                        amount.add((ingredients.get(i).getAmount() * numbers.get(j)));
                    }
                }
            }
        }
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<String> newUnit = new ArrayList<String>();
        ArrayList<Float> newAmount = new ArrayList<Float>();
        for (int i = 0; i < ingredientName.size(); i++) {
            boolean checked = false;
            for (int j = 0; j < newList.size(); j++) {
                if (newList.get(j).equals(ingredientName.get(i))) {
                    newAmount.set(j,newAmount.get(j) + amount.get(i));
                    checked = true;
                    break;
                }
            }
            if (!checked) {
                newList.add(ingredientName.get(i).toLowerCase());
                newAmount.add(amount.get(i));
                newUnit.add(unit.get(i));
            }
        }

        for (int i = 0; i < newAmount.size(); i++) {
            Log.e("amount", String.valueOf(newAmount.get(i)) );
            if (newUnit.get(i).equals("kg")) {
                if (newAmount.get(i) < 1) {
                    newUnit.set(i,"gr");
                    newAmount.set(i,newAmount.get(i)*1000);
                }
            }
            else if (newUnit.get(i).equals("l")) {
                if (newAmount.get(i) < 1) {
                    newUnit.set(i,"ml");
                    newAmount.set(i,newAmount.get(i)*1000);
                }
            }
            String listItem = String.valueOf(newAmount.get(i)) + " " + newUnit.get(i) + " " + newList.get(i);
            itemList.add(listItem);
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
            LVHelper.getListViewSize(lv);
        }

        // Traverse through the first list
    }
}
