package hcmus.android.androidfood.module;

public class Ingredient {
    int foodId;                 // primary key
    String ingredientName;      // primary key
    float amount;
    String unit;
    //----------------------------------------------------------------------------------------------
    public Ingredient(int foodId, String ingredientName, float amount, String unit) {
        this.foodId = foodId;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.unit = unit;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return amount + " " + unit + " " + ingredientName;
    }
}
