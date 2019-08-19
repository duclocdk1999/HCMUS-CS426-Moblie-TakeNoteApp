package hcmus.android.androidfood.module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hcmus.android.androidfood.R;

public class RecyclerDate extends RecyclerView.Adapter<RecyclerDate.RecyclerViewHolder> {
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> foodId = new ArrayList<ArrayList<Integer>>();
    private ArrayList<ArrayList<Integer>> number = new ArrayList<ArrayList<Integer>>();
    private OnNoteListener mOnNoteListener;


    public RecyclerDate( ArrayList<ArrayList<Integer>> foodId, ArrayList<String> date, ArrayList<ArrayList<Integer>> number, OnNoteListener onNoteListener) {
        this.date = date;
        this.foodId = foodId;
        this.number = number;
        this.mOnNoteListener = onNoteListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.date, parent, false);
        return new RecyclerViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtFoodName.setText(date.get(position));
    }

    @Override
    public int getItemCount() {
        return foodId.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView txtFoodName;
        OnNoteListener onNoteListener;
        public RecyclerViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            txtFoodName = (TextView) itemView.findViewById(R.id.dateShopping);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
