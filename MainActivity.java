package hcmus.android.a1751012;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Integer count = 0;
    private Integer cinema = 0;
    private Integer date = 0 ;
    private Integer time = 0;
    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames= new ArrayList<>();
    private ArrayList<String> mHours1 = new ArrayList<>();
    private ArrayList<String> mHours2 = new ArrayList<>();
    private ArrayList<String> mHours3 = new ArrayList<>();
    private ArrayList<String> mHours4 = new ArrayList<>();
    private ArrayList<String> mHours5 = new ArrayList<>();
    private ArrayList<String> mHours6 = new ArrayList<>();
    private ArrayList<String> mHours7 = new ArrayList<>();
    private ArrayList<Boolean> mState1 = new ArrayList<>();
    private ArrayList<Boolean> mState2 = new ArrayList<>();
    private ArrayList<Boolean> mState3 = new ArrayList<>();
    private ArrayList<Boolean> mState4 = new ArrayList<>();
    private ArrayList<Boolean> mState5 = new ArrayList<>();
    private ArrayList<Boolean> mState6 = new ArrayList<>();
    private ArrayList<Boolean> mState7 = new ArrayList<>();
    private RecyclerView r1;
    private RecyclerView r2;
    private RecyclerView r3;
    private RecyclerView rDate;
    private ImageButton cButton;
    private Button payButton;
    private ArrayList<Button> buttons = new ArrayList<>();
    private static final int[] BUTTON_IDS = {
            R.id.button_seat4,R.id.button_seat40,R.id.button_seat41,R.id.button_seat42,R.id.button_seat43,R.id.button_seat44,R.id.button_seat45,R.id.button_seat46,R.id.button_seat47,R.id.button_seat48,R.id.button_seat49,
            R.id.button_seat3,R.id.button_seat30,R.id.button_seat31,R.id.button_seat32,R.id.button_seat33,R.id.button_seat34,R.id.button_seat35,R.id.button_seat36,R.id.button_seat37,R.id.button_seat38,R.id.button_seat39,
            R.id.button_seat2,R.id.button_seat20,R.id.button_seat21,R.id.button_seat22,R.id.button_seat23,R.id.button_seat24,R.id.button_seat25,R.id.button_seat26,R.id.button_seat27,R.id.button_seat28,R.id.button_seat29,
            R.id.button_seat1,R.id.button_seat10,R.id.button_seat11,R.id.button_seat12,R.id.button_seat13,R.id.button_seat14,R.id.button_seat15,R.id.button_seat16,R.id.button_seat17,R.id.button_seat18,R.id.button_seat19,
            R.id.button_seat5,R.id.button_seat50,R.id.button_seat51,R.id.button_seat52,R.id.button_seat53,R.id.button_seat54,R.id.button_seat55,R.id.button_seat56,R.id.button_seat57,R.id.button_seat58,R.id.button_seat59,
            R.id.button_seat6,R.id.button_seat60,R.id.button_seat61,R.id.button_seat62,R.id.button_seat63,R.id.button_seat64,R.id.button_seat65,R.id.button_seat66,R.id.button_seat67,R.id.button_seat68,R.id.button_seat69,
            R.id.button_seat7,R.id.button_seat70,R.id.button_seat71,R.id.button_seat72,R.id.button_seat73,R.id.button_seat74,R.id.button_seat75,
            R.id.button_seat9,
            R.id.button_seat8,
    };

    String [] hours1 = {"12:00 AM","13:00 PM","14:00 PM","15:00 AM","16:00 AM","17:00 AM","18:00 AM" };
    String [] hours2 = {"20:00 PM","21:00 AM","22:30 PM","23:45 PM","00:00 AM","17:00 AM","18:00 AM" };
    String [] hours3 = {"15:00 PM","16:40 PM","17:20 PM","18:30 PM","16:00 AM","17:00 AM","18:00 AM" };
    String [] hours4 = {"12:00 AM","13:00 PM","14:00 PM","15:00 AM","16:00 AM","17:00 AM","18:00 AM" };
    String [] hours5 = {"20:00 PM","21:00 AM","22:30 PM","23:45 PM","00:00 AM","17:00 AM","18:00 AM" };
    String [] hours6 = {"15:00 PM","16:40 PM","17:20 PM","18:30 PM","16:00 AM","17:00 AM","18:00 AM" };
    String [] hours7 = {"12:00 AM","13:00 PM","14:00 PM","15:00 AM","16:00 AM","17:00 AM","18:00 AM" };
    Boolean [] state1 = {true, true, false, true, true, true, false};
    Boolean [] state2 = {false, true, true, true, false, false, true};
    Boolean [] state3 = {true, true, false, true, true, true, false};
    Boolean [] state4 = {false, true, true, true, false, true, false};
    Boolean [] state5 = {true, true, false, false, true, true, false};
    Boolean [] state6 = {false, true, false, true, true, true, false};
    Boolean [] state7 = {true, true, true, true, true, true, false};

    ArrayList<ArrayList<String>> hoursFilm1 = new ArrayList<>();
    ArrayList<ArrayList<String>> hoursFilm2 = new ArrayList<>();
    ArrayList<ArrayList<String>> hoursFilm3 = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> stateFilm1 = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> stateFilm2 = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> stateFilm3 = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        else
            setContentView(R.layout.activity2_main);

        if (savedInstanceState!=null) {
            cinema = savedInstanceState.getInt("text_cinema");
            time = savedInstanceState.getInt("text_time");
            date = savedInstanceState.getInt("text_date");
        }


        Log.d(TAG,"helloPortrait");
        getDates();
        rDate = findViewById(R.id.recyclerView);
        r1 = findViewById(R.id.recyclerViewFilm); //cgv r.id.cgv
        r2 = findViewById(R.id.recyclerViewFilm2);
        r3 = findViewById(R.id.recyclerViewFilm3);
        cButton = findViewById(R.id.changeLayout);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.seat);
                payButton = findViewById(R.id.pay);
                for(int id : BUTTON_IDS) {
                    final Button button = (Button)findViewById(id);
                    if (id % 10 == 0) {
                        button.setTag(R.drawable.left);
                        button.setBackgroundResource(R.drawable.left);}
                    else {
                        button.setTag(R.drawable.center);
                        button.setBackgroundResource(R.drawable.center);}
                    buttons.add(button);
                }
                for (int i = 0; i < buttons.size(); i++){
                    final int finalI = i;
                    buttons.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG,String.valueOf(finalI));
                            if ((Integer)buttons.get(finalI).getTag() == R.drawable.center) {
                                buttons.get(finalI).setBackgroundResource(R.drawable.right_seat);
                                buttons.get(finalI).setTag(R.drawable.right_seat);
                                count++;
                                payButton.setText("x" + String.valueOf(count) + " = $" + String.valueOf(count*5));
                            }
                            else if ((Integer)buttons.get(finalI).getTag() == R.drawable.right_seat) {
                                buttons.get(finalI).setBackgroundResource(R.drawable.center);
                                buttons.get(finalI).setTag(R.drawable.center);
                                count--;
                                payButton.setText("x" + String.valueOf(count) + " = $" + String.valueOf(count*5));
                            }
                            else
                                Log.d(TAG,"left");
                        }}
                    );
                }
                Button date_button = (Button)findViewById(R.id.date);
                date_button.setText(((RecyclerViewAdapter)rDate.getAdapter()).mDate.replaceAll("\n",""));
                Button time_button = (Button)findViewById(R.id.time);
                Button cinema_button = (Button)findViewById(R.id.cinema);
                if (((RecyclerViewAdapterFilm)r1.getAdapter()).mTime != null) {
                    time_button.setText(((RecyclerViewAdapterFilm) r1.getAdapter()).mTime);
                    cinema_button.setText("Sathyam Cinemas: Royapettah");
                }
                else if (((RecyclerViewAdapterFilm)r2.getAdapter()).mTime != null) {
                    time_button.setText(((RecyclerViewAdapterFilm) r2.getAdapter()).mTime);
                    cinema_button.setText("Escape Cinemas");
                }
                else if (((RecyclerViewAdapterFilm)r3.getAdapter()).mTime != null) {
                    time_button.setText(((RecyclerViewAdapterFilm) r3.getAdapter()).mTime);
                    cinema_button.setText("Cineplex Movies");
                }
                else {
                    time_button.setText("00:00 AM");
                    cinema_button.setText("NOT CHOOSE");
                }
            }
        });

        getHours(mHours1, hours1);
        getHours(mHours2, hours2);
        getHours(mHours3, hours3);
        getHours(mHours4, hours4);
        getHours(mHours5, hours5);
        getHours(mHours6, hours6);
        getHours(mHours7, hours7);
        getHoursCinema(hoursFilm1,mHours1);
        getHoursCinema(hoursFilm1,mHours2);
        getHoursCinema(hoursFilm1,mHours3);
        getHoursCinema(hoursFilm1,mHours4);
        getHoursCinema(hoursFilm1,mHours5);
        getHoursCinema(hoursFilm1,mHours6);
        getHoursCinema(hoursFilm1,mHours7);
        getHoursCinema(hoursFilm2,mHours7);
        getHoursCinema(hoursFilm2,mHours6);
        getHoursCinema(hoursFilm2,mHours5);
        getHoursCinema(hoursFilm2,mHours4);
        getHoursCinema(hoursFilm2,mHours3);
        getHoursCinema(hoursFilm2,mHours2);
        getHoursCinema(hoursFilm2,mHours1);
        getHoursCinema(hoursFilm3,mHours6);
        getHoursCinema(hoursFilm3,mHours2);
        getHoursCinema(hoursFilm3,mHours1);
        getHoursCinema(hoursFilm3,mHours3);
        getHoursCinema(hoursFilm3,mHours3);
        getHoursCinema(hoursFilm3,mHours1);
        getHoursCinema(hoursFilm3,mHours7);
        getStates(mState1, state1);
        getStates(mState2, state2);
        getStates(mState3, state3);
        getStates(mState4, state4);
        getStates(mState5, state5);
        getStates(mState6, state6);
        getStates(mState7, state7);
        getStatesCinema(stateFilm1,mState1);
        getStatesCinema(stateFilm1,mState2);
        getStatesCinema(stateFilm1,mState3);
        getStatesCinema(stateFilm1,mState4);
        getStatesCinema(stateFilm1,mState5);
        getStatesCinema(stateFilm1,mState6);
        getStatesCinema(stateFilm1,mState7);
        getStatesCinema(stateFilm2,mState7);
        getStatesCinema(stateFilm2,mState6);
        getStatesCinema(stateFilm2,mState5);
        getStatesCinema(stateFilm2,mState4);
        getStatesCinema(stateFilm2,mState3);
        getStatesCinema(stateFilm2,mState2);
        getStatesCinema(stateFilm2,mState1);
        getStatesCinema(stateFilm3,mState5);
        getStatesCinema(stateFilm3,mState4);
        getStatesCinema(stateFilm3,mState3);
        getStatesCinema(stateFilm3,mState2);
        getStatesCinema(stateFilm3,mState1);
        getStatesCinema(stateFilm3,mState6);
        getStatesCinema(stateFilm3,mState7);
        initRecyclerView(date);
        if (cinema == 1) {
            initRecyclerViewHour(R.id.recyclerViewFilm, stateFilm1.get(date), hoursFilm1.get(date), r2, r3, time);
            initRecyclerViewHour(R.id.recyclerViewFilm2, stateFilm2.get(date), hoursFilm2.get(date), r1, r3, 0);
            initRecyclerViewHour(R.id.recyclerViewFilm3, stateFilm3.get(date), hoursFilm3.get(date), r1, r2, 0);

        }
        else if (cinema == 2) {
            initRecyclerViewHour(R.id.recyclerViewFilm, stateFilm1.get(date), hoursFilm1.get(date), r2, r3, 0);
            initRecyclerViewHour(R.id.recyclerViewFilm2, stateFilm2.get(date), hoursFilm2.get(date), r1, r3, time);
            initRecyclerViewHour(R.id.recyclerViewFilm3, stateFilm3.get(date), hoursFilm3.get(date), r1, r2, 0);
        }
        else if (cinema == 3) {
            initRecyclerViewHour(R.id.recyclerViewFilm, stateFilm1.get(date), hoursFilm1.get(date), r2, r3, 0);
            initRecyclerViewHour(R.id.recyclerViewFilm3, stateFilm3.get(date), hoursFilm3.get(date), r1, r2, time);
            initRecyclerViewHour(R.id.recyclerViewFilm2, stateFilm2.get(date), hoursFilm2.get(date), r1, r3, 0);
        }
        else {
            initRecyclerViewHour(R.id.recyclerViewFilm, stateFilm1.get(date), hoursFilm1.get(date), r2, r3, 0);
            initRecyclerViewHour(R.id.recyclerViewFilm3, stateFilm3.get(date), hoursFilm3.get(date), r1, r2, 0);
            initRecyclerViewHour(R.id.recyclerViewFilm2, stateFilm2.get(date), hoursFilm2.get(date), r1, r3, 0);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        date = ((RecyclerViewAdapter)rDate.getAdapter()).mPosition;
        outState.putInt("text_date", date);
        if (((RecyclerViewAdapterFilm)r1.getAdapter()).mPosition != null) {
            time = ((RecyclerViewAdapterFilm) r1.getAdapter()).mPosition;
            cinema = 1;
        }
        else if (((RecyclerViewAdapterFilm)r2.getAdapter()).mPosition != null) {
            time = ((RecyclerViewAdapterFilm) r2.getAdapter()).mPosition;
            cinema = 2;
        }
        else if (((RecyclerViewAdapterFilm)r3.getAdapter()).mPosition != null) {
            time = ((RecyclerViewAdapterFilm) r3.getAdapter()).mPosition;
            cinema = 3;
        }
        else {
            time = 0;
            cinema = 0;
        }
        outState.putInt("text_time",time);
        outState.putInt("text_cinema",cinema);
    }



    private void getHours(ArrayList<String> mHours, String[] hours) {
        for (int i = 0; i < hours.length; i++) {
            mHours.add(hours[i]);
        }
    }
    private void getStates( ArrayList<Boolean> mState, Boolean[] states) {
        for (int i = 0; i < states.length; i++) {
            mState.add(states[i]);
        }
    }
    private void getHoursCinema( ArrayList<ArrayList<String>> mHoursCinema, ArrayList<String> hours) {
        mHoursCinema.add(hours);
    }
    private void getStatesCinema( ArrayList<ArrayList<Boolean>> mStatesCinema, ArrayList<Boolean> states) {
        mStatesCinema.add(states);
    }

    private void getDates() {
        Log.d(TAG, "getImages: preparing bitmaps.");
        mNames.add("FRI\n 12");
        mNames.add("SAT\n 13");
        mNames.add("SUN\n 14");
        mNames.add("MON\n 15");
        mNames.add("TUE\n 16");
        mNames.add("WED\n 17");
        mNames.add("THU\n 18");
    }
    private void initRecyclerView(int order) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames, stateFilm1, stateFilm2, stateFilm3, hoursFilm1, hoursFilm2, hoursFilm3, r1, r2, r3, order);
        recyclerView.setAdapter(adapter);
    }
    private void initRecyclerViewHour(int id,ArrayList<Boolean> mState,ArrayList<String> mHour, RecyclerView v1, RecyclerView v2, int order) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFilm = findViewById(id);
        recyclerViewFilm.setLayoutManager(layoutManager);
        RecyclerViewAdapterFilm adapter = new RecyclerViewAdapterFilm(mState, mHour,v1, v2, order);
        recyclerViewFilm.setAdapter(adapter);
    }
}

