package oppa.example.midtermproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import oppa.example.midtermproject.RecyclerViewAdapter.NoteAdapter;

public class ReadNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        setUp();
    }
    public void setUp() {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        Intent intent = getIntent();
        taskname.setText(intent.getStringExtra("taskname"));
        datetime.setText(intent.getStringExtra("date"));
        detail.setText(intent.getStringExtra("detail"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
    }
}
