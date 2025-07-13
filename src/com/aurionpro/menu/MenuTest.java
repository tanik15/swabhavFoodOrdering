package com.aurionpro.menu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		List<FoodItem> menuList = new ArrayList<>();
		 List<Food> starterItems = new ArrayList<>(Arrays.asList(
		            new Food(1,10,"Paneer Tikka", 150.0, 4.5, "Paneer, Spices, Yogurt",FoodType.VEG),
		            new Food(2,10,"Spring Rolls", 120.0, 4.2, "Vegetables, Wrapper, Soy Sauce",FoodType.VEG),
		            new Food(3,10,"Garlic Bread", 100.0, 4.0, "Bread, Garlic, Butter, Herbs",FoodType.VEG),
		            new Food(4,10,"Chicken Lollipop", 180.0, 4.6, "Chicken, Flour, Spices",FoodType.NONVEG),
		            new Food(5,10,"Hara Bhara Kabab", 130.0, 4.3, "Spinach, Peas, Potato, Spices",FoodType.VEG)
		        ));
		 double discountPercent = 10.0;
	     Starter starterMenu = new Starter(starterItems, discountPercent);
//	     starterMenu.DisplayNonVegFoodItem();
////	     
	     List<Food> mainCourseItems =new ArrayList<>(Arrays.asList(
	    		    new Food(6,10,"Butter Chicken", 250.0, 4.6, "Chicken, Butter, Tomato Gravy, Spices", FoodType.NONVEG),
	    		    new Food(7,10,"Paneer Butter Masala", 220.0, 4.5, "Paneer, Cream, Tomato Gravy, Spices", FoodType.VEG),
	    		    new Food(8,10,"Veg Biryani", 180.0, 4.3, "Rice, Vegetables, Biryani Masala", FoodType.VEG),
	    		    new Food(9,10,"Chicken Biryani", 250.0, 4.7, "Rice, Chicken, Biryani Masala", FoodType.NONVEG),
	    		    new Food(10,10,"Dal Tadka with Jeera Rice", 170.0, 4.2, "Dal, Rice, Jeera, Ghee", FoodType.VEG)
	    		));
////	     
	     MainCourse mainCourse = new MainCourse(mainCourseItems, discountPercent);
//	     mainCourse.DisplayNonVegFoodItem();
////	     
	     List<Food> breadItems = new ArrayList<>(Arrays.asList(
	    		    new Food(11,5,"Butter Naan", 30.0, 4.4, "Flour, Butter, Salt", FoodType.VEG),
	    		    new Food(12,5,"Tandoori Roti", 20.0, 4.2, "Wheat Flour, Ghee", FoodType.VEG),
	    		    new Food(13,5,"Plain Naan", 25.0, 4.1, "Flour, Salt", FoodType.VEG),
	    		    new Food(14,5,"Stuffed Kulcha", 40.0, 4.5, "Flour, Potatoes, Spices", FoodType.VEG),
	    		    new Food(15,5,"Lachha Paratha", 35.0, 4.3, "Wheat Flour, Ghee, Layers", FoodType.VEG)
	    		));
	     List<Food> dessertItems = new ArrayList<>(Arrays.asList(
	    		    new Food(16,5,"Gulab Jamun", 60.0, 4.6, "Milk Solids, Sugar Syrup", FoodType.VEG),
	    		    new Food(17,5,"Rasgulla", 70.0, 4.4, "Chenna, Sugar Syrup", FoodType.VEG),
	    		    new Food(18,5,"Ice Cream (Vanilla)", 80.0, 4.5, "Milk, Sugar, Vanilla", FoodType.VEG),
	    		    new Food(19,5,"Chocolate Brownie", 90.0, 4.3, "Chocolate, Flour, Butter", FoodType.VEG),
	    		    new Food(20,5,"Kheer", 70.0, 4.2, "Rice, Milk, Sugar, Dry Fruits", FoodType.VEG)
	    		));
	     List<Food> beverageItems = new ArrayList<>(Arrays.asList(
	    		    new Food(21,15,"Masala Chai", 30.0, 4.5, "Tea, Spices, Milk", FoodType.VEG),
	    		    new Food(22,15,"Cold Coffee", 80.0, 4.4, "Milk, Coffee, Sugar", FoodType.VEG),
	    		    new Food(23,15,"Soft Drink (Coke)", 40.0, 4.0, "Carbonated Water, Sugar", FoodType.VEG),
	    		    new Food(24,15,"Buttermilk", 25.0, 4.3, "Curd, Water, Spices", FoodType.VEG),
	    		    new Food(25,15,"Lemonade", 30.0, 4.1, "Lemon, Sugar, Salt, Water", FoodType.VEG)
	    		));
////	     System.out.println();
	     Bread breads = new Bread(breadItems, 0);
	     Dessert desserts = new Dessert(dessertItems, 5);
	     Beverages beverages = new Beverages(beverageItems, 15);
////	     breads.DisplayNonVegFoodItem();
////	     System.out.println();
////	     desserts.DisplayNonVegFoodItem();
////	     System.out.println();
////	     beverages.DisplayNonVegFoodItem();
//	     
	     menuList.add(starterMenu);
	     menuList.add(mainCourse);
	     menuList.add(breads);
	     menuList.add(desserts);
	     menuList.add(beverages);
//	     
	     FileOutputStream fos = new FileOutputStream("Menu");
	     ObjectOutputStream oos = new ObjectOutputStream(fos);
	     oos.writeObject(menuList);
	     System.out.println("Serialize");
	     fos.close();
	     oos.close();
	     
//	     FileInputStream fis = new FileInputStream("Menu");
//	     ObjectInputStream ois = new ObjectInputStream(fis);
//	     List<IFoodItem> gotList =(List<IFoodItem>) ois.readObject();
	     
//	     List<List<Food>> menuList =  (List<List<Food>>) oos.readObject();
	     
	}

}
