package hcmus.android.androidfood.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

// this static class is established in order to make it easier in retrieving data from database

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
                        "   imgUrl varchar(100)" +
                        "   );";
        mDatabase.execSQL(sqlcmd);
    }
    //--------------------------------------------------------------
    public static void insertIntoFoodTable(SQLiteDatabase mDatabase, String foodName, String imgUrl) {
        String sqlcmd = "insert into Food(foodName, imgUrl) values (?, ?);";
        mDatabase.execSQL(sqlcmd, new String[] {foodName});
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
                                                 String incredientName,
                                                 float amount,
                                                 String unit) {

        String sqlcmd = "insert into Ingredient(foodId, ingredientName, amount, unit) values (?, ?, ?, ?)";
        mDatabase.execSQL(sqlcmd, new String[] {String.valueOf(foodId), incredientName, String.valueOf(amount), unit});
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
    public static void retrieveFoodTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select rowid, foodName, imgUrl from Food;";

        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String imgUri = cursor.getString(2);
                Food chicken = new Food(id, name, imgUri);

                Log.d("oppa", chicken.toString());
            } while (cursor.moveToNext());
        }
    }
    //--------------------------------------------------------------
    public static void retrieveIngredientTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select foodId, ingredientName, amount, unit from Ingredient;";

        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String ingredientName = cursor.getString(1);
                float amount = cursor.getFloat(2);
                String unit = cursor.getString(3);

                Ingredient note = new Ingredient(foodId, ingredientName, amount, unit);
                Log.d("oppa", note.toString());

            } while (cursor.moveToNext());
        }
    }
    //--------------------------------------------------------------
    public static void retrieveRecipeTable(SQLiteDatabase mDatabase) {
        String sqlcmd = "select foodId, stepNum, detail from Recipe;";

        Cursor cursor = mDatabase.rawQuery(sqlcmd, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                int stepNum = cursor.getInt(1);
                String detail = cursor.getString(2);

                Recipe recipe = new Recipe(foodId, stepNum, detail);

                Log.d("oppa", recipe.toString());
            } while (cursor.moveToNext());
        }
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