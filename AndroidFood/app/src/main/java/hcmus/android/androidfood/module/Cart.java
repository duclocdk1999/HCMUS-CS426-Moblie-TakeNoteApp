package hcmus.android.androidfood.module;

        public class Cart {
            int foodId;             //primary key
            int number;
            String date;

            //---------------------------------------------
            public Cart(int foodId, int number, String date) {
                this.foodId = foodId;
                this.number = number;
                this.date = date;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getFoodId() {
                return foodId;
            }

            public void setFoodId(int foodId) {
                this.foodId = foodId;
            }

            @Override
            public String toString() {
                return date;
            }
        }


