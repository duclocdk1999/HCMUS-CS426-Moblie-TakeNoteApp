package hcmus.android.androidfood.module;

public class Recipe {
    int foodId;             // primary key
    int stepNum;            // primary key
    String detail;

    public Recipe(int foodId, int stepNum, String detail) {
        this.foodId = foodId;
        this.stepNum = stepNum;
        this.detail = detail;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "foodId=" + foodId +
                ", stepNum=" + stepNum +
                ", detail='" + detail + '\'' +
                '}';
    }
}
