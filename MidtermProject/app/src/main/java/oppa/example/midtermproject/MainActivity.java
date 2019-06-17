package oppa.example.midtermproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        else {
            setContentView(R.layout.activity_main_land);
            LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
            placeHolder.removeAllViewsInLayout();
            getLayoutInflater().inflate(R.layout.activity_add_note2, placeHolder);
        }

        if (savedInstanceState!=null) {
            noteRecordList = new ArrayList<>();
            ArrayList<String> taskname = new ArrayList<>();
            ArrayList<String> content = new ArrayList<>();
            ArrayList<String> phone = new ArrayList<>();
            ArrayList<String> email = new ArrayList<>();
            ArrayList<String> date = new ArrayList<>();
            boolean[] state = new boolean[1000];
            state = savedInstanceState.getBooleanArray("state");
            taskname = savedInstanceState.getStringArrayList("taskname");
            phone = savedInstanceState.getStringArrayList("phone");
            content = savedInstanceState.getStringArrayList("content");
            email = savedInstanceState.getStringArrayList("email");
            date = savedInstanceState.getStringArrayList("date");
            for (int i = 0; i < taskname.size(); i++) {
                NoteRecord note = new NoteRecord(taskname.get(i), content.get(i),email.get(i),phone.get(i), new Date(i, i+1, i+2), state[i]);
                noteRecordList.add(note);
            }
        }
        else
            setData();
        initRecyclerViewNote();
    }
    //------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                NoteRecord itemNew = new NoteRecord(data.getStringExtra("taskname"),data.getStringExtra("content"),data.getStringExtra("email"),data.getStringExtra("phone"), new Date(1,2,3),false);
                noteRecordList.add(0,itemNew);
                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
                adapt.notifyItemInserted(0);
                recyclerViewNote.setAdapter(adapt);
                recyclerViewNote.scrollToPosition(0);
            }
        }
        else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
                adapt.noteRecordList.get(data.getIntExtra("position", 0)).setContent(data.getStringExtra("content"));
                adapt.noteRecordList.get(data.getIntExtra("position", 0)).setTaskName(data.getStringExtra("taskname"));
                adapt.noteRecordList.get(data.getIntExtra("position", 0)).setPhone(data.getStringExtra("phone"));
                adapt.noteRecordList.get(data.getIntExtra("position", 0)).setEmail(data.getStringExtra("email"));
                adapt.noteRecordList.get(data.getIntExtra("position", 0)).setState(data.getBooleanExtra("state",false));
                recyclerViewNote.setAdapter(adapt);
            }
            if (resultCode == 1) {
                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
                int i = data.getIntExtra("position",0);
                adapt.noteRecordList.remove(i);
                adapt.notifyItemRemoved(i);
                adapt.notifyItemRangeChanged(i,adapt.noteRecordList.size());
            }
        }

    }
    //----------------------------------------------------------------------------------------------
    public void setData() {
        for (int i = 0; i < 30; i++) {

            NoteRecord note = new NoteRecord(" #Title " + String.valueOf(i), "Nothing to seen","12","13", new Date(i, i+1, i+2), false);
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
        startActivityForResult(addNoteIntent,1);
    }
    //----------------------------------------------------------------------------------------------
    public void addNoteOnClickLandscape(View view) {
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
        placeHolder.removeAllViewsInLayout();
        getLayoutInflater().inflate(R.layout.activity_add_note2, placeHolder);

    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoteItemClick(int position) {
        // this function is implemented when user click on the available note in order to show content
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent readNoteIntent = new Intent(this, ReadNoteActivity.class);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNote);
            NoteAdapter adapt = (NoteAdapter) recyclerView.getAdapter();
            readNoteIntent.putExtra("position", position);
            readNoteIntent.putExtra("taskname", adapt.noteRecordList.get(position).getTitle());
            readNoteIntent.putExtra("date", adapt.noteRecordList.get(position).getDay());
            readNoteIntent.putExtra("detail", adapt.noteRecordList.get(position).getContent());
            readNoteIntent.putExtra("phone", adapt.noteRecordList.get(position).getPhone());
            readNoteIntent.putExtra("email", adapt.noteRecordList.get(position).getEmail());
            readNoteIntent.putExtra("state", adapt.noteRecordList.get(position).getState());
            startActivityForResult(readNoteIntent, 2);
        }
        else {
            mPosition = position;
            LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
            placeHolder.removeAllViewsInLayout();
            getLayoutInflater().inflate(R.layout.activity_read_note2, placeHolder);
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNote);
            NoteAdapter adapt = (NoteAdapter) recyclerView.getAdapter();
            EditText taskname = (EditText) findViewById(R.id.taskname);
            EditText datetime = (EditText) findViewById(R.id.date);
            EditText detail = (EditText) findViewById(R.id.detail);
            EditText phone = (EditText) findViewById(R.id.phone);
            EditText email = (EditText) findViewById(R.id.email);
            CheckBox state = (CheckBox) findViewById(R.id.checkbox);
            taskname.setText(adapt.noteRecordList.get(mPosition).getTitle());
            detail.setText(adapt.noteRecordList.get(mPosition).getContent());
            datetime.setText(adapt.noteRecordList.get(mPosition).getDay());
            email.setText(adapt.noteRecordList.get(mPosition).getEmail());
            phone.setText(adapt.noteRecordList.get(mPosition).getPhone());
            state.setChecked(adapt.noteRecordList.get(mPosition).getState());
            ImageButton mDialButton = (ImageButton) findViewById(R.id.call);
            final EditText mPhoneNoEt = (EditText) findViewById(R.id.phone);
            mDialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phoneNo = mPhoneNoEt.getText().toString();
                    if(!TextUtils.isEmpty(phoneNo)) {
                        String dial = "tel:" + phoneNo;
                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                    }else {
                        Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> taskname = new ArrayList<>();
        ArrayList<String> content = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        ArrayList<String> date = new ArrayList<>();
        boolean[] state = new boolean[1000];
        for (int i = 0; i < noteRecordList.size(); i++) {
            taskname.add(noteRecordList.get(i).getTitle());
            content.add(noteRecordList.get(i).getContent());
            phone.add(noteRecordList.get(i).getPhone());
            email.add(noteRecordList.get(i).getEmail());
            state[i] = noteRecordList.get(i).getState();

        }
        outState.putBooleanArray("state",state);
        outState.putStringArrayList("taskname",date);
        outState.putStringArrayList("taskname",taskname);
        outState.putStringArrayList("content",content);
        outState.putStringArrayList("phone",phone);
        outState.putStringArrayList("email",email);
    }
    public void editNote2(View view) {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox state = (CheckBox) findViewById(R.id.checkbox);
        boolean checked = state.isChecked();
        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
        adapt.noteRecordList.get(mPosition).setContent(detail.getText().toString());
        adapt.noteRecordList.get(mPosition).setTaskName(taskname.getText().toString());
        adapt.noteRecordList.get(mPosition).setPhone(phone.getText().toString());
        adapt.noteRecordList.get(mPosition).setEmail(email.getText().toString());
        adapt.noteRecordList.get(mPosition).setState(checked);
        recyclerViewNote.setAdapter(adapt);
    }
    public void deleteNote2(View view) {
        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
        adapt.noteRecordList.remove(mPosition);
        adapt.notifyItemRemoved(mPosition);
        adapt.notifyItemRangeChanged(mPosition,adapt.noteRecordList.size());
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
        placeHolder.removeAllViewsInLayout();
        getLayoutInflater().inflate(R.layout.activity_add_note2, placeHolder);
    }
    public void saveNote2(View view) {
        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        NoteRecord itemNew = new NoteRecord(taskname.getText().toString(),detail.getText().toString(),email.getText().toString(),phone.getText().toString(), new Date(1,2,3),false);
        noteRecordList.add(0,itemNew);
        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
        adapt.notifyItemInserted(0);
        recyclerViewNote.setAdapter(adapt);
        recyclerViewNote.scrollToPosition(0);
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
        placeHolder.removeAllViewsInLayout();
        getLayoutInflater().inflate(R.layout.activity_add_note2, placeHolder);
    }
    public void checkTask2(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        checked = !checked;
    }

}