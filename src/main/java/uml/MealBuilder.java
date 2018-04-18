package uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 膳食构建器
 */
public class MealBuilder {
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();
		System.out.println(mealBuilder.prepareVegMeal());
		System.out.println(mealBuilder.prepareNonVegMeal());
	}
	
	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());
		meal.addItem(new Coke());
		return meal;
	}
	
	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());
		return meal;
	}
}
