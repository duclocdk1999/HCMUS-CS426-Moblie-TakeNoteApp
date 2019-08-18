package hcmus.android.androidfood.module;

public class Food {
    int foodId;             //primary key
    String foodName;
    String imgUri;
    //---------------------------------------------
    public Food(int foodId, String foodName, String imgUri) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.imgUri = imgUri;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", imgUri='" + imgUri + '\'' +
                '}';
    }
}

