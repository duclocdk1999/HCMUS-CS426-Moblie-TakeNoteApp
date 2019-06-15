package oppa.example.midtermproject.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import oppa.example.midtermproject.R;
import oppa.example.midtermproject.model.NoteRecord;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<NoteRecord> noteRecordList;
    private OnNoteItemListener onNoteItemListener;      // this type is used to support onNoteClick ( I mean when user click on a note )
                                                        // sorry đũy Nam vì khúc này éo biết đặt tên biến sao cho đẹp :)
    //----------------------------------------------------------------------------------------------
    public NoteAdapter(OnNoteItemListener onNoteItemListener, ArrayList<NoteRecord> noteRecordList) {

        this.noteRecordList = noteRecordList;
        this.onNoteItemListener = onNoteItemListener;
    }
    //----------------------------------------------------------------------------------------------
    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item_layout, viewGroup, false);
        return new ViewHolder(view, onNoteItemListener);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {

        viewHolder.note_item_content.setText(noteRecordList.get(i).getTitle());
        viewHolder.note_item_schedule.setText(noteRecordList.get(i).getMonth() + "\n" + noteRecordList.get(i).getTime().getYear());
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public int getItemCount() {

        return this.noteRecordList.size();
    }
    //----------------------------------------------------------------------------------------------
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView note_item_content;
        TextView note_item_schedule;

        OnNoteItemListener onNoteItemListener;

        public ViewHolder(@NonNull View itemView, OnNoteItemListener onNoteItemListener) {
            super(itemView);

            this.note_item_content = (TextView) itemView.findViewById(R.id.note_item_content);
            this.note_item_schedule = (TextView) itemView.findViewById(R.id.note_item_schedule);
            this.onNoteItemListener = onNoteItemListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onNoteItemListener.onNoteItemClick(getAdapterPosition());
        }
    }
    //----------------------------------------------------------------------------------------------
    public interface OnNoteItemListener {

        //this interface class is used to support onNoteClick ( I mean when user click on a note )
        void onNoteItemClick(int position);
    }
}