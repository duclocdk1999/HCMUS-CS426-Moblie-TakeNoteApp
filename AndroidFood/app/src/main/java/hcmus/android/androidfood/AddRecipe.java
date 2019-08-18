package hcmus.android.androidfood;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hcmus.android.androidfood.module.Food;
import hcmus.android.androidfood.module.LVHelper;
import hcmus.android.androidfood.module.Room;

public class AddRecipe extends AppCompatActivity{
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://nam1751012.000webhostapp.com/";
    private static final String DATABASE_NAME = "FoodAppDatabase";
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    ArrayList<String> itemStep;
    ArrayAdapter<String> adapterStep;
    EditText amount;
    EditText unit;
    EditText ingredient;
    EditText step;
    EditText nameFood;
    Button addIngredientButton;
    Button addStepButton;
    ListView lv;
    ListView lvStep;
    ImageView uploadImage;
    Button uploadImageBtn;
    String imgUri;
    String foodName;
    ArrayList<Integer> amountArray = new ArrayList<Integer>();
    ArrayList<String> unitArray = new ArrayList<String>();
    ArrayList<String> ingredientArray = new ArrayList<String>();
    ArrayList<String> stepArray = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        lv = (ListView)findViewById(R.id.listIngredient);
        lvStep = (ListView)findViewById(R.id.listStep);
        step = (EditText) findViewById(R.id.step);
        amount = (EditText) findViewById(R.id.amount);
        unit = (EditText) findViewById(R.id.unit);
        ingredient = (EditText) findViewById(R.id.ingredient);
        addIngredientButton = (Button) findViewById(R.id.addIngredient);
        addStepButton = (Button) findViewById(R.id.addStep);
        uploadImage = (ImageView) findViewById(R.id.imageToUpload);
        uploadImageBtn = (Button) findViewById(R.id.uploadImage);
        itemStep = new ArrayList<>();
        adapterStep = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemStep);
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemList);
        View.OnClickListener addIngredient = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = amount.getText().toString();
                String unitStr = unit.getText().toString();
                String ingredientStr = ingredient.getText().toString();
                if (amountStr != null && unitStr != null && ingredientStr != null) {
                    try
                    {
                        Integer.parseInt(amountStr);
                        itemList.add(amountStr + " " + unitStr + " " + ingredientStr);
                        amount.setText("");
                        unit.setText("");
                        ingredient.setText("");
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        LVHelper.getListViewSize(lv);
                        amountArray.add(Integer.parseInt(amountStr));  //Thêm vào global amount array
                        unitArray.add(unitStr);                        //Thêm vào global unit array
                        ingredientArray.add(ingredientStr);            //Thêm vào global ingredient array
                        return;

                    } catch (NumberFormatException ex) {
                        return;
                    }
                }
            }
        };
        View.OnClickListener addStep = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stepStr = step.getText().toString();
                if (stepStr.length() != 0) {
                    itemStep.add(step.getText().toString());
                    step.setText("");
                    adapterStep.notifyDataSetChanged();
                    lvStep.setAdapter(adapterStep);
                    LVHelper.getListViewSize(lvStep);
                    stepArray.add(stepStr);
                }
            }
        };
        View.OnClickListener uploadImg = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            }
        };
        addIngredientButton.setOnClickListener(addIngredient);
        addStepButton.setOnClickListener(addStep);
        uploadImageBtn.setOnClickListener(uploadImg);
    }
    public void saveNote(View view) {
        nameFood = (EditText)findViewById(R.id.foodname);
        foodName = nameFood.getText().toString();  // Thêm vào global foodname string
//        imgUri+amountArray+unitArray+ingredientArray+stepArray+foodName sẽ đẩy vào database đống này cho tui
        Bitmap image = ((BitmapDrawable) uploadImage.getDrawable()).getBitmap();
        Date date=new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss");
        String strDate = dateFormat.format(date);
        Log.i("date", strDate);
        imgUri = strDate;
        new UploadImage(image, strDate).execute();


        SQLiteDatabase mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Room.insertIntoFoodTable(mDatabase, foodName, imgUri );

        int foodId = Room.getLastFoodIdFromFoodTable(mDatabase);

        int numberOfIngredients = ingredientArray.size();
        for (int i = 0; i < numberOfIngredients; i++) {
            Room.insertIntoIngredientTable(mDatabase, foodId, ingredientArray.get(i), amountArray.get(i), unitArray.get(i));
        }

        int numberOfSteps = stepArray.size();
        for (int i = 0; i < numberOfSteps; i++) {
            Room.insertIntoRecipeTable(mDatabase, foodId, i, stepArray.get(i));
        }

        ArrayList<Food> foods = Room.retrieveFoodTable(mDatabase);
        for (int i = 0; i< foods.size(); i++) {
            Log.i("food",  foods.get(i).getFoodName());
        }
        Room.retrieveIngredientTable(mDatabase);
        Room.retrieveRecipeTable(mDatabase);



        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("foodName", foodName);
        mainIntent.putExtra("imgUri", imgUri);
        setResult(Activity.RESULT_OK, mainIntent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);//Thêm vào global uri image string
        }
    }

    private class UploadImage extends AsyncTask<Void, Void, Void> {
        Bitmap image;
        String name;

        public UploadImage(Bitmap image, String name) {
            this.image = image;
            this.name = name;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            ArrayList<NameValuePair> dataSend = new ArrayList<>();
            dataSend.add(new BasicNameValuePair("image", encodedImage));
            dataSend.add(new BasicNameValuePair("name", name));

            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "SavePicture.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataSend));
                client.execute(post);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;


        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return httpRequestParams;
    }
}

