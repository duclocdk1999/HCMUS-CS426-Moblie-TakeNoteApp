package hcmus.android.androidfood;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hcmus.android.androidfood.module.RecyclerFood;

public class GetFood extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerFood mRcvAdapter;
    ArrayList<String> foodName;
    ArrayList<String> imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerFood);
        foodName = getIntent().getStringArrayListExtra("foodName");
        imgUri = getIntent().getStringArrayListExtra("imgUri");
        if (imgUri.size() != 0) {
            mRcvAdapter = new RecyclerFood(foodName, imgUri);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            mRecyclerView.setAdapter(mRcvAdapter);
        }
    }

}
