package oppa.example.midtermproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;

import oppa.example.midtermproject.RecyclerViewAdapter.NoteAdapter;
import oppa.example.midtermproject.model.NoteRecord;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteItemListener {

    ArrayList<NoteRecord> noteRecordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        initRecyclerViewNote();
    }
    //----------------------------------------------------------------------------------------------
    public void setData() {
        for (int i = 0; i < 30; i++) {

            NoteRecord note = new NoteRecord(" #Title " + String.valueOf(i), "Nothing to seen", new Date(i, i+1, i+2));
            noteRecordList.add(note);
        }
    }
    //----------------------------------------------------------------------------------------------
    void initRecyclerViewNote() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        recyclerViewNote.setLayoutManager(layoutManager);

        NoteAdapter adapter = new NoteAdapter(this, noteRecordList);
        recyclerViewNote.setAdapter(adapter);
    }
    //----------------------------------------------------------------------------------------------
    public void addNoteOnClick(View view) {
        // this function is implemented when user click on the "add note button" on screen

        Intent addNoteIntent = new Intent(this, AddNoteActivity.class);
        startActivity(addNoteIntent);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoteItemClick(int position) {
        // this function is implemented when user click on the available note in order to show content

        Intent readNoteIntent = new Intent(this, ReadNoteActivity.class);
        startActivity(readNoteIntent);
    }
}