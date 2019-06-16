package hcmus.android.a1751012;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Button> mButton = new ArrayList<>();
    private ArrayList<ArrayList<String>> mFilms1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> mFilms2 = new ArrayList<>();
    private ArrayList<ArrayList<String>> mFilms3 = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> mStates1 = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> mStates2 = new ArrayList<>();
    private ArrayList<ArrayList<Boolean>> mStates3 = new ArrayList<>();
    private Context mContext;
    public RecyclerView mR1;
    public RecyclerView mR2;
    public RecyclerView mR3;
    public Integer mPosition = null;
    public String mDate = null;
    public Integer mOrder = 0;

    public RecyclerViewAdapter( Context context, ArrayList<String> names,ArrayList<ArrayList<Boolean>> state1,ArrayList<ArrayList<Boolean>> state2,ArrayList<ArrayList<Boolean>> state3,ArrayList<ArrayList<String>> film1,ArrayList<ArrayList<String>> film2,ArrayList<ArrayList<String>> film3, RecyclerView r1, RecyclerView r2, RecyclerView r3, int order) {
        mNames = names;
        mContext = context;
        mFilms1 = film1;
        mFilms2 = film2;
        mFilms3 = film3;
        mStates1 = state1;
        mStates2 = state2;
        mStates3 = state3;
        mR1 = r1;
        mR2 = r2;
        mR3 = r3;
        mOrder = order;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.name.setText(mNames.get(position));
        if (position == mOrder) {
            holder.name.setBackgroundColor(Color.BLACK);
            mDate = mNames.get(position);
            mPosition = position;
            mButton.add(holder.name);
        }
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    for (int i = 0; i < mButton.size(); i++ ) {
                        mButton.get(i).setBackgroundColor(Color.WHITE);
                    }
                    mDate = mNames.get(position);
                    mPosition = position;
                    holder.name.setBackgroundColor(Color.BLACK);
                    mButton.add(holder.name);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                    RecyclerView recyclerViewFilm = mR1.findViewById(R.id.recyclerViewFilm);
                    recyclerViewFilm.setLayoutManager(layoutManager);
                    RecyclerViewAdapterFilm adapter = new RecyclerViewAdapterFilm(mStates1.get(position),mFilms1.get(position), mR2, mR3, 0);
                    recyclerViewFilm.setAdapter(adapter);
                    RecyclerView recyclerViewFilm2 = mR2.findViewById(R.id.recyclerViewFilm2);
                    recyclerViewFilm.setLayoutManager(layoutManager);
                    RecyclerViewAdapterFilm adapter2 = new RecyclerViewAdapterFilm(mStates2.get(position),mFilms2.get(position), mR1, mR3,0);
                    recyclerViewFilm2.setAdapter(adapter2);
                    RecyclerView recyclerViewFilm3 = mR3.findViewById(R.id.recyclerViewFilm3);
                    recyclerViewFilm.setLayoutManager(layoutManager);
                    RecyclerViewAdapterFilm adapter3 = new RecyclerViewAdapterFilm(mStates3.get(position),mFilms3.get(position), mR1, mR2,0);
                    recyclerViewFilm3.setAdapter(adapter3);
            }});}

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.button_text);
        }
    }

}
