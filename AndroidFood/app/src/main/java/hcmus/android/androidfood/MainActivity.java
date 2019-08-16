package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import hcmus.android.androidfood.module.Room;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;

    String DATABASE_NAME = "FoodAppDatabase";
    //--------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        mDatabase.setForeignKeyConstraintsEnabled(true);

        Room.createFoodTable(mDatabase);

        Room.createIngredientTable(mDatabase);

        Room.createRecipeTable(mDatabase);

/*        Room.insertIntoFoodTable(mDatabase, "Chicken", "wallpaper01.jpg");
        Room.insertIntoFoodTable(mDatabase, "Pig", "wallpaper02.jpg");
        Room.insertIntoFoodTable(mDatabase, "vegetables", "unknown");
        Room.retrieveFoodTable(mDatabase);

        Room.insertIntoIngredientTable(mDatabase, 1, "legs", (float) 10.9, "gram");
        Room.insertIntoIngredientTable(mDatabase, 2, "fish", (float) 20.5, "kilogam");
        Room.retrieveIngredientTable(mDatabase);

        Room.createRecipeTable(mDatabase);
        Room.insertIntoRecipeTable(mDatabase, 1, 1, "kill the chicken");
        Room.retrieveRecipeTable(mDatabase);

        Log.d("oppa", "the max id is " + String.valueOf(Room.getLastFoodIdFromFoodTable(mDatabase)));
*/
    }

    public void getPlace(View view) {
    }

    public void getFood(View view) {

    }

    public void addRecipe(View view) {
        Intent addNoteIntent = new Intent(this, AddRecipe.class);
        startActivity(addNoteIntent);
    }

    public void getNote(View view) {
    }
}
