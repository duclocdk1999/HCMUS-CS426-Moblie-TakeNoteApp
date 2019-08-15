package hcmus.android.androidfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
