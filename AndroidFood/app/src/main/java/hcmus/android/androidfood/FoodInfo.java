package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hcmus.android.androidfood.module.Food;
import hcmus.android.androidfood.module.Ingredient;
import hcmus.android.androidfood.module.LVHelper;
import hcmus.android.androidfood.module.Recipe;
import hcmus.android.androidfood.module.Room;

public class FoodInfo extends AppCompatActivity {
    private static final String DATABASE_NAME = "FoodAppDatabase";
    private static final String SERVER_ADDRESS = "http://nam1751012.000webhostapp.com/";
    int foodId;
    ArrayList<String> step;
    ArrayList<Integer> stepNumber;
    ArrayList<Float> amount;
    ArrayList<String> unit;
    ArrayList<String> ingredient;
    String foodName;
    ImageView imgView;
    TextView foodText;
    ListView ingredientList;
    ListView stepList;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterStep;
    ArrayList<String> itemList;
    ArrayList<String> itemStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        foodId = getIntent().getIntExtra("foodId",0);
        step = new ArrayList<String>();
        stepNumber = new ArrayList<Integer>();
        amount = new ArrayList<Float>();
        unit = new ArrayList<String>();
        ingredient = new ArrayList<String>();
        imgView = (ImageView) findViewById(R.id.imageFood);
        foodText = (TextView) findViewById(R.id.foodText);
        ingredientList = (ListView) findViewById(R.id.listIngredientInfo);
        stepList = (ListView) findViewById(R.id.listStepInfo);
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ArrayList<Food> foods = Room.retrieveFoodTable(mDatabase);
        ArrayList<Ingredient> ingredients = Room.retrieveIngredientTable(mDatabase);
        ArrayList<Recipe> recipes = Room.retrieveRecipeTable(mDatabase);
        itemStep = new ArrayList<String>();
        itemList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
        adapterStep = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemStep);
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getFoodId() == foodId) {
                foodName = foods.get(i).getFoodName();
                String url = SERVER_ADDRESS + "pictures/" + foods.get(i).getImgUri() + ".JPG";
                Picasso.with(imgView.getContext()).load(url).into(imgView);
                foodText.setText(foods.get(i).getFoodName());
                break;
            }
        }
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getFoodId() == foodId) {
                ingredient.add(ingredients.get(i).getIngredientName());
                amount.add(ingredients.get(i).getAmount());
                unit.add(ingredients.get(i).getUnit());
                itemList.add(ingredients.get(i).toString());
                adapter.notifyDataSetChanged();
                ingredientList.setAdapter(adapter);
                LVHelper.getListViewSize(ingredientList);
            }
        }

        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getFoodId() == foodId) {
                step.add(recipes.get(i).getDetail());
                stepNumber.add((recipes.get(i).getStepNum()));
                itemStep.add(recipes.get(i).toString());
                adapterStep.notifyDataSetChanged();
                stepList.setAdapter(adapterStep);
                LVHelper.getListViewSize(stepList);
            }
        }
    }
    public void goToCart(View view) {
        Intent goToCartIntent = new Intent(this, Cart.class);
        goToCartIntent.putExtra("foodName",foodName );
        goToCartIntent.putExtra("foodId",foodId );
        goToCartIntent.putExtra("amount",amount );
        goToCartIntent.putExtra("unit", unit);
        goToCartIntent.putExtra("ingredient", ingredient);
        startActivity(goToCartIntent);
    }
}
