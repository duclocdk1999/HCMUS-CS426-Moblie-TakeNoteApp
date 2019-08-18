package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hcmus.android.androidfood.module.Food;
import hcmus.android.androidfood.module.Ingredient;
import hcmus.android.androidfood.module.Recipe;
import hcmus.android.androidfood.module.RecyclerFood;
import hcmus.android.androidfood.module.Room;

public class GetFood extends AppCompatActivity implements RecyclerFood.OnNoteListener {
    RecyclerView mRecyclerView;
    RecyclerFood mRcvAdapter;
    ArrayList<String> foodName;
    ArrayList<String> imgUri;
    ArrayList<Integer> foodIds;
    private static final String DATABASE_NAME = "FoodAppDatabase";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerFood);
        foodName = new ArrayList<String>();
        imgUri = new ArrayList<String>();
        foodIds = new ArrayList<Integer>();
        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        ArrayList<Food> foods = Room.retrieveFoodTable(mDatabase);
        ArrayList<Ingredient> ingredients = Room.retrieveIngredientTable(mDatabase);
        ArrayList<Recipe> recipes = Room.retrieveRecipeTable(mDatabase);
        for (int i = 0; i< foods.size(); i++) {
            foodName.add(foods.get(i).getFoodName());
            imgUri.add(foods.get(i).getImgUri());
            foodIds.add(foods.get(i).getFoodId());
//            for (int index1 = 0; index1 < ingredients.size(); index1++) {
//                if (ingredients.get(index1).getFoodId() == index)
//                    Log.i("ingredient", ingredients.get(index1).toString());
//            }
//
//            for (int index2 = 0; index2 < recipes.size(); index2++) {
//                if (recipes.get(index2).getFoodId() == index)
//                    Log.i("recipe", recipes.get(index2).toString());
//            }
        }
        Collections.reverse(foodName);
        Collections.reverse(imgUri);
        Collections.reverse(foodIds);

        if (imgUri.size() != 0) {
            mRcvAdapter = new RecyclerFood(foodName, imgUri, foodIds, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            mRecyclerView.setAdapter(mRcvAdapter);
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, FoodInfo.class);
        intent.putExtra("foodId",foodIds.get(position));
        startActivity(intent);
    }
}
