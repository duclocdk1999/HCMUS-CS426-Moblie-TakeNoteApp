package hcmus.android.androidfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import hcmus.android.androidfood.module.Room;

public class Splash extends AppCompatActivity {
    ArrayList<String> imgUri;
    ArrayList<String> foodName;
    SQLiteDatabase mDatabase;

    String DATABASE_NAME = "FoodAppDatabase";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgUri = new ArrayList<String>();
        foodName = new ArrayList<String>();


        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        mDatabase.setForeignKeyConstraintsEnabled(true);

        Room.createFoodTable(mDatabase);

        Room.createIngredientTable(mDatabase);

        Room.createCartTable(mDatabase);

        Room.createRecipeTable(mDatabase);
        Room.createShopperTable(mDatabase);
        if (Room.retrieveFoodTable(mDatabase).size() == 0) {
            initFood();
            initShopper();
        }
    }
    private void initFood() {
        int foodId;
        Room.insertIntoFoodTable(mDatabase,"Bánh bao" ,"2019-40-16:08:40:38");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Hành tây", 100, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt heo", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột mì", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Men nở", 10, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Muối", 1, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Trộn nhân thịt");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Pha bột mì với men nở, nước");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Hấp nhân, cán bột");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Tạo hình, hấp");
        Room.insertIntoFoodTable(mDatabase,"Bánh cuốn" ,"2019-40-16:08:40:48");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Hành tây", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt heo", 400, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột gạo", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước mắm", 5, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Củ sắn", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nấm mèo", 5, "Tai");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Xào nhân với thịt, hành tây và củ sắn");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Pha bột gạo với nước");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Tráng bánh, cuốn bánh");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Pha nước mắm");
        Room.insertIntoFoodTable(mDatabase,"Cupcakes","2019-40-16:08:40:57");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột mì", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột nở", 2, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Trứng gà", 5, "quả");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Đường", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Whipping cream", 1, "Chai");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Tách lòng trắng trứng");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Đánh bông lòng trắng trứng");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Trộn bột với trứng, đường");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Nướng bánh trong 30 phút");
        Room.insertIntoFoodTable(mDatabase,"Xá xíu","2019-41-16:08:41:06");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt heo", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Ngũ vị hương", 2, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Dầu hào", 2, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tỏi băm", 2, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Hành băm", 2, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Trộn gia vị, hành, tỏi");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Ướp thịt trong 3 giờ");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Nướng thịt trong nửa tiếng");
        Room.insertIntoFoodTable(mDatabase,"Hủ tíu","2019-41-16:08:41:15");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Xương heo", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tôm sú", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Trứng cút", 10, "quả");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước lọc", 2, "Lít");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Ninh xương trong 2 giờ");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Nêm nước dùng, luộc tôm");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Luộc trứng cút, trụng hủ tíu");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Bày món ăn");
        Room.insertIntoFoodTable(mDatabase,"Gỏi cuốn","2019-41-16:08:41:30");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt ba rọi", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tôm sú", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Hẹ", 100, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Rau sống", 100, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bánh tráng", 1, "Xấp");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bún", 300, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tương đậu", 7, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Luộc thịt, tôm");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Nấu tương, nêm nếm cho vừa ăn");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Cuốn thịt, tôm, bún và rau sống");
        Room.insertIntoFoodTable(mDatabase,"Chả giò","2019-41-16:08:41:41");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt heo xay", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Khoai môn", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Củ sắn", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bánh tráng", 1, "Xấp");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước mắm", 10, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Xắt khoai môn ra thành từng sợi");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Xắt củ sắn, vắt ráo nước");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Trộn tôm, thịt và rau củ");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Cuốn chả giò, chiên vàng");
        Room.insertIntoFoodTable(mDatabase,"Gà rán","2019-42-16:08:42:20");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Đùi gà", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột chiên giòn", 1, "Gói");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tương ớt", 1, "Chai");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Ướp thịt gà với muối và tiêu");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Trộn bột chiên giòn với nước");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Lăn gà qua bột và chiên giòn");
        Room.insertIntoFoodTable(mDatabase,"Bánh xèo","2019-42-16:08:42:34");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột gạo", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước cốt dừa", 500, "ml");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bột nghệ", 1, "Thìa");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt ba rọi", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tép", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Hành lá", 100, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước mắm", 10, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Xắt thịt ra lát mỏng, xào sơ tôm");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Pha bột với bột nghệ, nước cốt dừa, hành lá");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Pha nước mắm");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Chiên bánh");
        Room.insertIntoFoodTable(mDatabase,"Bánh mì","2019-42-16:08:42:43");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bánh mì", 2, "Ổ");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Patê gan", 1, "Hộp");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt phá lấu", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Đồ chua", 50, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Dưa leo", 1, "Trái");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Xẻ đôi bánh mì, nhét thịt");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Nhét thêm dưa leo, đồ chua và ớt");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Xịt nước tương");
        Room.insertIntoFoodTable(mDatabase,"Cơm tấm","2019-42-16:08:42:52");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tấm", 2, "Lon");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Thịt nạc dăm", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bì heo", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Trứng", 2, "Quả");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Nước mắm", 10, "Thìa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Nấu cơm tấm với lá dứa");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Ướp thịt với nước mắm, đường và gia vị");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Pha thịt xay, trứng làm chả");
        Room.insertIntoRecipeTable(mDatabase, foodId, 3, "B4: Trộn bì heo với thịt, thính");
        Room.insertIntoRecipeTable(mDatabase, foodId, 4, "B5: Pha nước mắm");
        Room.insertIntoFoodTable(mDatabase,"Phở","2019-43-16:08:43:00");
        foodId = Room.getLastFoodIdFromFoodTable(mDatabase);
        Room.insertIntoIngredientTable(mDatabase, foodId, "Bánh phở", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Fillet bò", 500, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Xương bò", 1, "Kg");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Rau thơm", 200, "Gr");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Tương đen", 1, "Chai");
        Room.insertIntoIngredientTable(mDatabase, foodId, "Gia vị nấu phở", 1, "Túi");
        Room.insertIntoRecipeTable(mDatabase, foodId, 0, "B1: Ninh xương bò, thịt bò trong 5 tiếng");
        Room.insertIntoRecipeTable(mDatabase, foodId, 1, "B2: Thả hoa hồi, quế, đinh hương, thảo quả");
        Room.insertIntoRecipeTable(mDatabase, foodId, 2, "B3: Múc phở ra bát");
    }
    public void getPlace(View view) {
        Intent getPlace = new Intent(this, Shopper.class);
        startActivityForResult(getPlace,4);
    }

    public void getFood(View view) {
        Intent getFoodIntent = new Intent(this, GetFood.class);
        getFoodIntent.putExtra("foodName", foodName);
        getFoodIntent.putExtra("imgUri", imgUri);
        startActivity(getFoodIntent);
    }
    private void initShopper() {
        Room.insertIntoShopperTable(mDatabase, "American Food Supllier", "227 Đường Nguyễn Văn Cừ, Phường 4, Quận 5", "0909207070", 10.762913, 106.679983);
        Room.insertIntoShopperTable(mDatabase, "Trái Cây Châu Long", "329 Đường Trần Hưng Đạo, Phường Cô Giang, Quận 1","0234567134",10.7597498,106.6861483);
        Room.insertIntoShopperTable(mDatabase,"Sạp thịt heo Đức Lộc", "Số 104, Yersin, Phường Nguyễn Thái Bình, Quận 1", "01652916512", 10.7675084, 106.6942753);
    }

    public void addRecipe(View view) {
        Intent addNoteIntent = new Intent(this, AddRecipe.class);
        startActivityForResult(addNoteIntent,2);
    }

    public void getNote(View view) {
        Intent addNoteIntent = new Intent(this, ShoppingCart.class);
        startActivityForResult(addNoteIntent,3);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
//            imgUri.add(data.getStringExtra("imgUri"));
//            Log.i("data", data.getStringExtra("imgUri"));
//            foodName.add(data.getStringExtra("foodName"));
        }
    }
}
