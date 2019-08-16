package hcmus.android.androidfood;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> imgUri;
    ArrayList<String> foodName;
    SQLiteDatabase mDatabase;
    String DATABASE_NAME = "FoodAppDatabase";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgUri = new ArrayList<String>();
        foodName = new ArrayList<String>();
        initImg();
        initFood();


        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        mDatabase.setForeignKeyConstraintsEnabled(true);

        Room.createFoodTable(mDatabase);

        Room.createIngredientTable(mDatabase);

        Room.createRecipeTable(mDatabase);
    }

    private void initImg() {
        imgUri.add("2019-40-16:08:40:38");
        imgUri.add("2019-40-16:08:40:48");
        imgUri.add("2019-40-16:08:40:57");
        imgUri.add("2019-41-16:08:41:06");
        imgUri.add("2019-41-16:08:41:15");
        imgUri.add("2019-41-16:08:41:30");
        imgUri.add("2019-41-16:08:41:41");
        imgUri.add("2019-42-16:08:42:20");
        imgUri.add("2019-42-16:08:42:34");
        imgUri.add("2019-42-16:08:42:43");
        imgUri.add("2019-42-16:08:42:52");
        imgUri.add("2019-43-16:08:43:00");
    }

    private void initFood() {
        foodName.add("Bánh bao");
        foodName.add("Bánh cuốn");
        foodName.add("Cupcakes");
        foodName.add("Xá xíu");
        foodName.add("Hủ tíu");
        foodName.add("Gỏi cuốn");
        foodName.add("Chả giò");
        foodName.add("Gà rán");
        foodName.add("Bánh xèo");
        foodName.add("Bánh mì");
        foodName.add("Cơm tấm");
        foodName.add("Phở");
    }
    public void getPlace(View view) {
    }

    public void getFood(View view) {
        Intent getFoodIntent = new Intent(this, GetFood.class);
        getFoodIntent.putExtra("foodName", foodName);
        getFoodIntent.putExtra("imgUri", imgUri);
        startActivity(getFoodIntent);
    }

    public void addRecipe(View view) {
        Intent addNoteIntent = new Intent(this, AddRecipe.class);
        startActivityForResult(addNoteIntent,2);
    }

    public void getNote(View view) {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            imgUri.add(data.getStringExtra("imgUri"));
            Log.i("data", data.getStringExtra("imgUri"));
            foodName.add(data.getStringExtra("foodName"));
        }
    }


}

