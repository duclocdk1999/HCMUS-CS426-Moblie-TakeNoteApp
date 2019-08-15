package hcmus.android.androidfood;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hcmus.android.androidfood.module.LVHelper;

public class AddRecipe extends AppCompatActivity{
    private static final int RESULT_LOAD_IMAGE = 1;
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter;
    ArrayList<String> itemStep;
    ArrayAdapter<String> adapterStep;
    EditText amount;
    EditText unit;
    EditText ingredient;
    EditText step;
    Button addIngredientButton;
    Button addStepButton;
    ListView lv;
    ListView lvStep;
    ImageView uploadImage;
    Button uploadImageBtn;
    String imgUri;
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
                        itemList.add(amount.getText().toString() + " " + unit.getText().toString() + " " + ingredient.getText().toString());
                        amount.setText("");
                        unit.setText("");
                        ingredient.setText("");
                        adapter.notifyDataSetChanged();
                        lv.setAdapter(adapter);
                        LVHelper.getListViewSize(lv);
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
//        uploadImage.setOnClickListener();
    }
    public void saveNote(View view) {

        setResult(Activity.RESULT_OK);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadImage.setImageURI(selectedImage);
        }
    }
}

