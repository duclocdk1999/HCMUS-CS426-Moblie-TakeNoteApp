package hcmus.android.androidfood.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Room {
    private static Room instance = null;
    private Room() {

    }
    //--------------------------------------------------------------
    public static Room getInstance() {
        if (instance == null) {
            instance = new Room();
        }
        return instance;
    }
    //--------------------------------------------------------------
    public static void createFoodTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "create table if not exists Food(" +
                "   foodID integer primary key not null," +
                "   foodName nvarchar(50) not null," +
                "   imgUrl nvarchar(100) not null" +
                "   );";
        mDatabase.execSQL(sqlcmd);
    }
    //--------------------------------------------------------------
    public static void insertIntoFoodTable(SQLiteDatabase mDatabase, String foodName, String imgUrl) {
        String sqlcmd = "insert into Food(foodName, imgUrl) values (?, ?);";
        mDatabase.execSQL(sqlcmd, new String[] {foodName, imgUrl});
    }
    //--------------------------------------------------------------
    public static void createIngredientTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "create table if not exists Ingredient(" +
                "       foodId int," +
                "       ingredientName nvarchar(50) not null," +
                "       amount int," +
                "       unit varchar(50)," +
                "       primary key (foodId, ingredientName)," +
                "       foreign key (foodId) references Food(foodId)" +
                ");";
        mDatabase.execSQL(sqlcmd);
    }
    //--------------------------------------------------------------
    public static void insertIntoIngredientTable(SQLiteDatabase mDatabase,
                                                 int foodId,
                                                 String ingredientName,
                                                 float amount,
                                                 String unit) {

        String sqlcmd = "insert into Ingredient(foodId, ingredientName, amount, unit) values (?, ?, ?, ?)";
        mDatabase.execSQL(sqlcmd, new String[] {String.valueOf(foodId), ingredientName, String.valueOf(amount), unit});
    }
    public static void createCartTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "create table if not exists Cart(" +
                "       foodId int," +
                "       dateCart nvarchar(50) not null," +
                "       number int," +
                "       keyId nvarchar(50) not null," +
                "       primary key (keyId)," +
                "       foreign key (foodId) references Food(foodId)" +
                ");";
        mDatabase.execSQL(sqlcmd);
    }
    //--------------------------------------------------------------
    public static void insertIntoCartTable(SQLiteDatabase mDatabase,
                                                 int foodId,
                                                 String date,
                                                 int number,
                                                 String keyId
                                                 ) {

        String sqlcmd = "insert into Cart(foodId, dateCart, number, keyId) values (?, ?, ?, ?)";
        mDatabase.execSQL(sqlcmd, new String[] {String.valueOf(foodId), date, String.valueOf(number), keyId });
    }
    //--------------------------------------------------------------
    public static void createRecipeTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "create table if not exists Recipe(" +
                "   foodId int," +
                "   stepNum int," +
                "   detail nvarchar(100)," +
                "   primary key (foodId, stepNum)," +
                "   foreign key (foodId) references Food(foodId)" +
                ");";
        mDatabase.execSQL(sqlcmd);
    }
    //--------------------------------------------------------------
    public static void insertIntoRecipeTable(SQLiteDatabase mDatabase, int foodId, int stepNum, String detail) {

        String sqlcmd = "insert into Recipe(foodId, stepNum, detail) values (?, ?, ?);";
        mDatabase.execSQL(sqlcmd, new String[] {String.valueOf(foodId), String.valueOf(stepNum), detail});
    }
    //--------------------------------------------------------------
    public static ArrayList<Food> retrieveFoodTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select rowid, foodName, imgUrl from Food;";
        ArrayList<Food> foods = new ArrayList<Food>();
        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String imgUri = cursor.getString(2);
                Food chicken = new Food(id, name, imgUri);
                foods.add(chicken);
                Log.i("oppa", chicken.toString());
            } while (cursor.moveToNext());
            return foods;
        }
        return foods;
    }
    //--------------------------------------------------------------
    public static ArrayList<Ingredient> retrieveIngredientTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select foodId, ingredientName, amount, unit from Ingredient;";
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String ingredientName = cursor.getString(1);
                float amount = cursor.getFloat(2);
                String unit = cursor.getString(3);

                Ingredient note = new Ingredient(foodId, ingredientName, amount, unit);
                ingredients.add(note);
                Log.i("oppa", note.toString());

            } while (cursor.moveToNext());
            return ingredients;
        }
        return ingredients;
    }
    //--------------------------------------------------------------
    public static ArrayList<Recipe> retrieveRecipeTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select foodId, stepNum, detail from Recipe;";
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                int stepNum = cursor.getInt(1);
                String detail = cursor.getString(2);

                Recipe recipe = new Recipe(foodId, stepNum, detail);
                recipes.add(recipe);
                Log.i("oppa", recipe.toString());
            } while (cursor.moveToNext());
            return recipes;
        }
        return recipes;
    }
    //--------------------------------------------------------------
    public static ArrayList<Cart> retrieveCartTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select foodId, dateCart, number from Cart;";
        ArrayList<Cart> carts = new ArrayList<Cart>();
        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                int number = cursor.getInt(2);
                String date = cursor.getString(1);

                Cart cart = new Cart(foodId, number, date);
                carts.add(cart);
                Log.i("oppa", cart.toString());
            } while (cursor.moveToNext());
            return carts;
        }
        return carts;
    }
    //--------------------------------------------------------------
    public static int getLastFoodIdFromFoodTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select MAX(foodId) from Food;";

        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            int foodId = cursor.getInt(0);
            return foodId;
        }

        Log.d("oppa", "error in getLastFoodIdFromTable");
        return -1;
    }
}
