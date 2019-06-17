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
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    SQLiteDatabase mDatabase;
    String DATABASE_NAME = "NoteDatabase";

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

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createNotesTableIfNotExists();
        reloadNoteRecordListFromDatabase();

        initRecyclerViewNote();
    }
    //------------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                /*NoteRecord itemNew = new NoteRecord(data.getStringExtra("taskname"),data.getStringExtra("content"),data.getStringExtra("email"),data.getStringExtra("phone"), new Date(1,2,3),false);
                noteRecordList.add(0,itemNew);*/


                String title = data.getStringExtra("taskname");
                String content = data.getStringExtra("content");
                String email = data.getStringExtra("email");
                String phone = data.getStringExtra("phone");
                String time = data.getStringExtra("date");

                insertNoteIntoDatabase(title, content, email, phone, time, 0);
                reloadNoteRecordListFromDatabase();

                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
                adapt.notifyItemInserted(0);
                recyclerViewNote.setAdapter(adapt);
                recyclerViewNote.scrollToPosition(0);

            }
        }
        else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                // edit note

                String title = data.getStringExtra("taskname");
                String content = data.getStringExtra("content");
                String email = data.getStringExtra("email");
                String phone = data.getStringExtra("phone");
                String time = data.getStringExtra("date");
                Boolean state = data.getBooleanExtra("state", false);

                // update data in table
                int item_position = data.getIntExtra("position", 0);
                updateNoteInDatabaseById(noteRecordList.get(item_position).getId(), title, content, email, phone, time, state ? 1:0);

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
                // delete note

                RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
                NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
                int i = data.getIntExtra("position",0);
                deleteNoteInDatabaseById(noteRecordList.get(i).getId());
                adapt.noteRecordList.remove(i);
                adapt.notifyItemRemoved(i);
                adapt.notifyItemRangeChanged(i,adapt.noteRecordList.size());
            }
        }
    }
    //----------------------------------------------------------------------------------------------
    private void createNotesTableIfNotExists() {

        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS Notes(\n" +
                "          title nvarchar(100),\n" +
                "          content nvarchar(500),\n" +
                "          email varchar(100),\n" +
                "          phone varchar(30),\n" +
                "          time varchar(50),\n" +
                "          state int);"
        );
    }
    //----------------------------------------------------------------------------------------------
    private void insertNoteIntoDatabase(String title, String content, String email, String phone, String time, int state) {

        String sqlcmd = "INSERT INTO Notes(title, content, email, phone, time, state) VALUES (?, ?, ?, ?, ?, ?);";
        mDatabase.execSQL(sqlcmd, new String[] {title, content, email, phone, time, String.valueOf(state)});
    }
    //----------------------------------------------------------------------------------------------
    private void updateNoteInDatabaseById(int id, String title, String content, String email, String phone, String time, int state) {

        String sqlcmd = "UPDATE Notes SET title = ?, content = ?, email = ?, phone = ?, time = ?, state = ? WHERE rowid = ?;";

        mDatabase.execSQL(sqlcmd, new String[] {title, content, email, phone, time, String.valueOf(state), String.valueOf(id)});
    }
    //----------------------------------------------------------------------------------------------
    private void deleteNoteInDatabaseById(int id) {

        String sqlcmd = "DELETE FROM Notes WHERE rowid = ?;";
        mDatabase.execSQL(sqlcmd, new String[] {String.valueOf(id)});
    }
    //----------------------------------------------------------------------------------------------
    private void reloadNoteRecordListFromDatabase() {

        Cursor cursorNotes = mDatabase.rawQuery("SELECT rowid, title, content, email, phone, time, state FROM Notes;", null);
        if (cursorNotes.moveToFirst()) {

            noteRecordList.clear();
            do {
                int id = cursorNotes.getInt(0);
                String title = cursorNotes.getString(1);
                String content = cursorNotes.getString(2);
                String email = cursorNotes.getString(3);
                String phone = cursorNotes.getString(4);
                String time = cursorNotes.getString(5);
                int state = cursorNotes.getInt(6);
                noteRecordList.add(new NoteRecord(id, title, content, email, phone, time, state == 1 ? true:false));

                Log.d("nvdloc-id", noteRecordList.get(noteRecordList.size() - 1).getId() + "");
                Log.d("nvdloc-title", noteRecordList.get(noteRecordList.size() - 1).getTitle());
            } while (cursorNotes.moveToNext());
        }
        cursorNotes.close();
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
            readNoteIntent.putExtra("date", adapt.noteRecordList.get(position).getTime());
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
            datetime.setText(adapt.noteRecordList.get(mPosition).getTime());
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
    //----------------------------------------------------------------------------------------------
    public void editNote2(View view) {

        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText email = (EditText) findViewById(R.id.email);
        CheckBox state = (CheckBox) findViewById(R.id.checkbox);
        boolean checked = state.isChecked();

        updateNoteInDatabaseById(noteRecordList.get(mPosition).getId(),
                taskname.getText().toString(),
                detail.getText().toString(),
                email.getText().toString(),
                phone.getText().toString(),
                datetime.getText().toString(),
                checked ? 1:0);

        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
        adapt.noteRecordList.get(mPosition).setContent(detail.getText().toString());
        adapt.noteRecordList.get(mPosition).setTaskName(taskname.getText().toString());
        adapt.noteRecordList.get(mPosition).setPhone(phone.getText().toString());
        adapt.noteRecordList.get(mPosition).setEmail(email.getText().toString());
        adapt.noteRecordList.get(mPosition).setState(checked);
        recyclerViewNote.setAdapter(adapt);
    }
    //----------------------------------------------------------------------------------------------
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
    //----------------------------------------------------------------------------------------------
    public void saveNote2(View view) {

        EditText taskname = (EditText) findViewById(R.id.taskname);
        EditText datetime = (EditText) findViewById(R.id.date);
        EditText detail = (EditText) findViewById(R.id.detail);
        EditText phoneView = (EditText) findViewById(R.id.phone);
        EditText emailView = (EditText) findViewById(R.id.email);
        /*NoteRecord itemNew = new NoteRecord(mPosition,taskname.getText().toString(),detail.getText().toString(),email.getText().toString(),phone.getText().toString(), datetime.getText().toString(),false);
        noteRecordList.add(0,itemNew);*/

        String title = taskname.getText().toString();
        String content = detail.getText().toString();
        String email = emailView.getText().toString();
        String phone = phoneView.getText().toString();
        String time = datetime.getText().toString();

        insertNoteIntoDatabase(title, content, email, phone, time, 0);
        reloadNoteRecordListFromDatabase();

        RecyclerView recyclerViewNote = (RecyclerView) findViewById(R.id.recyclerViewNote);
        NoteAdapter adapt = (NoteAdapter) recyclerViewNote.getAdapter();
        adapt.notifyItemInserted(0);
        recyclerViewNote.setAdapter(adapt);
        recyclerViewNote.scrollToPosition(0);
        LinearLayout placeHolder = (LinearLayout) findViewById(R.id.new_landscape);
        placeHolder.removeAllViewsInLayout();
        getLayoutInflater().inflate(R.layout.activity_add_note2, placeHolder);
    }
    //----------------------------------------------------------------------------------------------
    public void checkTask2(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        checked = !checked;
    }

}