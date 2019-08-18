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

public class RecyclerFood extends RecyclerView.Adapter<RecyclerFood.RecyclerViewHolder> {
        private static final String SERVER_ADDRESS = "http://nam1751012.000webhostapp.com/";
        private ArrayList<String> foodName = new ArrayList<>();
        private ArrayList<String> imgUri = new ArrayList<>();
        private ArrayList<Integer> foodId = new ArrayList<>();
        private OnNoteListener mOnNoteListener;


        public RecyclerFood(ArrayList<String> foodName, ArrayList<String> imgUri, ArrayList<Integer> foodId, OnNoteListener onNoteListener) {
            this.foodName = foodName;
            this.imgUri = imgUri;
            this.foodId = foodId;
            this.mOnNoteListener = onNoteListener;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.dish, parent, false);
            return new RecyclerViewHolder(view, mOnNoteListener);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.txtFoodName.setText(foodName.get(position));
            String url = SERVER_ADDRESS + "pictures/" + imgUri.get(position) + ".JPG";
            Picasso.with(holder.imgFood.getContext()).cancelRequest(holder.imgFood);
            Picasso.with(holder.imgFood.getContext()).load(url).into(holder.imgFood);
//            holder.imgFood.setImageDrawable(null);
//            new DownloadImage(this.imgUri.get(position), holder.imgFood).execute();
        }

        @Override
        public int getItemCount() {
            return foodName.size();
        }


        public class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
            TextView txtFoodName;
            ImageView imgFood;
            OnNoteListener onNoteListener;
            public RecyclerViewHolder(View itemView, OnNoteListener onNoteListener) {
                super(itemView);
                txtFoodName = (TextView) itemView.findViewById(R.id.dishName);
                imgFood = (ImageView) itemView.findViewById(R.id.imageFood);
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

        private class DownloadImage extends AsyncTask<Void, Void, Bitmap> {

            String name;
            ImageView img;
            public DownloadImage(String name, ImageView img) {
                this.name = name;
                this.img = img;
            }
            @Override
            protected  Bitmap doInBackground(Void... params) {
                String url = SERVER_ADDRESS + "pictures/" + name + ".JPG";
                try {
                    URLConnection connection = new URL(url).openConnection();
                    connection.setConnectTimeout(1000*30);
                    connection.setReadTimeout(1000*30);
                    return BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null);

                }catch(Exception e) {
                    e.printStackTrace();
                }

                return null;

            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    this.img.setImageBitmap(bitmap);
                }
            }
        }
}
