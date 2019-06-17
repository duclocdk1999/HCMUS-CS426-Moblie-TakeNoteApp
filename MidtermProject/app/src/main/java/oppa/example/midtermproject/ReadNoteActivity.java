package oppa.example.midtermproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import oppa.example.midtermproject.RecyclerViewAdapter.NoteAdapter;

public class ReadNoteActivity extends AppCompatActivity {
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        setUp();
    }
    //----------------------------------------------------------------------------------------------
    public void setUp() {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox state = (CheckBox) findViewById(R.id.checkbox);

        Intent intent = getIntent();

        position = intent.getIntExtra("position",0);
        taskname.setText(intent.getStringExtra("taskname"));
        datetime.setText(intent.getStringExtra("date"));
        detail.setText(intent.getStringExtra("detail"));
        phone.setText(intent.getStringExtra("phone"));
        email.setText(intent.getStringExtra("email"));
        if (intent.getBooleanExtra("state",false)) {
            state.setChecked(true);
        }
        else {
            state.setChecked(false);
        }
    }
    //----------------------------------------------------------------------------------------------
    public void editNote(View view) {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox state = (CheckBox) findViewById(R.id.checkbox);

        boolean checked = state.isChecked();

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("position",position);
        mainIntent.putExtra("taskname", taskname.getText().toString());
        mainIntent.putExtra("taskname", taskname.getText().toString());
        mainIntent.putExtra("date",datetime.getText().toString());
        mainIntent.putExtra("content",detail.getText().toString());
        mainIntent.putExtra("phone",phone.getText().toString());
        mainIntent.putExtra("email",email.getText().toString());

        if (checked)
            mainIntent.putExtra("state",true);
        else
            mainIntent.putExtra("state",false);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    //----------------------------------------------------------------------------------------------
    public void deleteNote(View view) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("position",position);
        setResult(1, mainIntent);
        finish();
    }
    //----------------------------------------------------------------------------------------------
    public void checkTask(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        checked = !checked;
    }
}
