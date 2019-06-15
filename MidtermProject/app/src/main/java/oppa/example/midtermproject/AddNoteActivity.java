package oppa.example.midtermproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

import oppa.example.midtermproject.model.NoteRecord;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }
    public void saveNote(View view) {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("message1", taskname.getText().toString());
        mainIntent.putExtra("message2",datetime.getText().toString());
        mainIntent.putExtra("message3",detail.getText().toString());
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }

}
