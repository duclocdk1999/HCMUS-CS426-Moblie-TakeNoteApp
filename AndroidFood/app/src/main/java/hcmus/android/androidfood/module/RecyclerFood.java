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

        public RecyclerFood(ArrayList<String> foodName, ArrayList<String> imgUri) {
            this.foodName = foodName;
            this.imgUri = imgUri;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.dish, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.txtFoodName.setText(foodName.get(position));
            new DownloadImage(this.imgUri.get(position), holder.imgFood).execute();
        }

        @Override
        public int getItemCount() {
            return foodName.size();
        }


        public class RecyclerViewHolder extends RecyclerView.ViewHolder {
            TextView txtFoodName;
            ImageView imgFood;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                txtFoodName = (TextView) itemView.findViewById(R.id.dishName);
                imgFood = (ImageView) itemView.findViewById(R.id.imageFood);
            }
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
