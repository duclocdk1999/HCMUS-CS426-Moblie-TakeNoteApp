package oppa.example.midtermproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import oppa.example.midtermproject.RecyclerViewAdapter.NoteAdapter;
import oppa.example.midtermproject.model.NoteRecord;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteItemListener {

    ArrayList<NoteRecord> noteRecordList = new ArrayList<>();
    private int requestCode;
    private int resultCode;
    private Intent data;
    NoteAdapter adapterGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        initRecyclerViewNote();
    }
    //------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK) {
                NoteRecord itemNew = new NoteRecord(data.getStringExtra("message1"),data.getStringExtra("message3"), new Date(1,2,3));
                noteRecordList.add(0,itemNew);
                adapterGlobal.notifyItemInserted(0);
                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                recyclerViewNote.setAdapter(adapterGlobal);
                recyclerViewNote.scrollToPosition(0);
            }
        }
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
        adapterGlobal = adapter;
        recyclerViewNote.setAdapter(adapter);
    }
    //----------------------------------------------------------------------------------------------
    public void addNoteOnClick(View view) {
        // this function is implemented when user click on the "add note button" on screen
        Intent addNoteIntent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(addNoteIntent,1);
    }

    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoteItemClick(int position) {
        // this function is implemented when user click on the available note in order to show content

        Intent readNoteIntent = new Intent(this, ReadNoteActivity.class);
        startActivity(readNoteIntent);
    }
}