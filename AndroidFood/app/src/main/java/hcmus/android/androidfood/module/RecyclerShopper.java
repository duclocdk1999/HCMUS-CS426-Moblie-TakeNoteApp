package hcmus.android.androidfood.module;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hcmus.android.androidfood.R;

public class RecyclerShopper extends RecyclerView.Adapter<RecyclerShopper.RecyclerViewHolder> {
    private ArrayList<String> nameShopper = new ArrayList<>();
    private ArrayList<String> addressShopper = new ArrayList<>();
    private ArrayList<Double> latShopper = new ArrayList<>();
    private ArrayList<Double> longShopper = new ArrayList<>();
    private ArrayList<String> phoneShopper = new ArrayList<>();

    private OnNoteListener mOnNoteListener;


    public RecyclerShopper( ArrayList<String> nameShopper, ArrayList<String> addressShopper, ArrayList<Double> latShopper, ArrayList<Double> longShopper, ArrayList<String> phoneShopper, OnNoteListener onNoteListener) {
        this.nameShopper = nameShopper;
        this.addressShopper = addressShopper;
        this.latShopper = latShopper;
        this.longShopper = longShopper;
        this.phoneShopper = phoneShopper;
        this.mOnNoteListener = onNoteListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shopper, parent, false);
        return new RecyclerViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtNameShopper.setText(nameShopper.get(position));
        holder.txtAddressShopper.setText(addressShopper.get(position));
        holder.txtPhoneShopper.setText(phoneShopper.get(position));
    }

    @Override
    public int getItemCount() {
        return nameShopper.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView txtNameShopper, txtAddressShopper, txtPhoneShopper;
        OnNoteListener onNoteListener;
        public RecyclerViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            txtNameShopper = (TextView) itemView.findViewById(R.id.nameShopper);
            txtAddressShopper = (TextView) itemView.findViewById(R.id.addressShopper);
            txtPhoneShopper = (TextView) itemView.findViewById(R.id.phoneShopper);
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