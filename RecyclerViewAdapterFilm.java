package hcmus.android.a1751012;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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

public class RecyclerViewAdapterFilm extends RecyclerView.Adapter<RecyclerViewAdapterFilm.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mHours = new ArrayList<>();
    private ArrayList<Boolean> mStates = new ArrayList<>();
    private Context mContext;
    private ArrayList<Button> mButton = new ArrayList<>();
    public RecyclerView mR1;
    public RecyclerView mR2;
    public Integer mPosition = null;
    public String mTime = null;
    public Integer mOrder = 0;

    public RecyclerViewAdapterFilm(ArrayList<Boolean> states, ArrayList<String> hours, RecyclerView v1, RecyclerView v2, int order) {
        mHours = hours;
        mR1 = v1;
        mR2 = v2;
        mStates = states;
        mOrder = order;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_filmitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.hour.setText(mHours.get(position));
        if (mStates.get(position) == false) {
            holder.hour.setBackgroundColor(Color.RED);

        }
        if ((mOrder - 1 >=0) && (position == mOrder - 1)) {
            holder.hour.setBackgroundColor(Color.BLACK);
            mButton.add(holder.hour);
            mPosition = position+1;
            mTime = mHours.get(position);
        }
        holder.hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable current_button = holder.hour.getBackground();
                if (((ColorDrawable)current_button).getColor() != Color.RED ) {
                Log.d(TAG,"hello");

                for (int i = 0; i < mButton.size(); i++ ) {
                    mButton.get(i).setBackgroundColor(Color.WHITE);
                    if (mButton.get(i) == holder.hour) {
                        mButton = new ArrayList<>();
                        return;
                    }
                }
                mTime = mHours.get(position);
                mPosition = position + 1;
                holder.hour.setBackgroundColor(Color.BLACK);
                mButton = new ArrayList<>();
                mButton.add(holder.hour);
                RecyclerViewAdapterFilm adapt1 = (RecyclerViewAdapterFilm) mR1.getAdapter();
                if (adapt1.mButton.size() == 1) {
                    adapt1.mButton.get(0).setBackgroundColor(Color.WHITE);
                    adapt1.mButton = new ArrayList<>();
                    adapt1.mPosition = null;
                    adapt1.mTime = null;
                }
                RecyclerViewAdapterFilm adapt2 = (RecyclerViewAdapterFilm) mR2.getAdapter();
                if (adapt2.mButton.size() == 1) {
                    adapt2.mButton.get(0).setBackgroundColor(Color.WHITE);
                    adapt2.mButton = new ArrayList<>();
                    adapt2.mPosition = null;
                    adapt2.mTime = null;
                }}

            }});}


    @Override
    public int getItemCount() {
        return mHours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button hour;
        public ViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.image_hour);
        }
    }

}
