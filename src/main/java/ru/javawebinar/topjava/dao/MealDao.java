package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealDao {

    private static List<Meal> meals = new ArrayList<>();

    static {
        meals.add(new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static final int CALORIESPERDAY = 2000;


    public void add(Meal meal) {
        int maxMealId = meals.stream()
                .map(Meal::getMealId)
                .max(Comparator.naturalOrder())
                .get();
        meal.setMealId(maxMealId + 1);
        meals.add(meal);
    }

    public void delete(int mealId) {
        meals.removeIf(meal -> meal.getMealId().equals(mealId));
    }

    public Meal getById(int mealId) {
        return meals
                .stream()
                .filter(meal -> meal.getMealId() == mealId)
                .findFirst()
                .get();
    }

    public void update(Meal newMeal) {
        meals
                .stream()
                .filter(meal -> meal.getMealId().equals(newMeal.getMealId()))
                .findFirst()
                .ifPresent(meal -> {
                    meal.setCalories(newMeal.getCalories());
                    meal.setDateTime(newMeal.getDateTime());
                    meal.setDescription(newMeal.getDescription());
                });

    }


    public int getCaloriesPerDay() {
        return CALORIESPERDAY;
    }

    public List<Meal> getAll() {
        return meals;
    }

    public List<MealTo> getAllMealTo() {
        return MealsUtil.filteredByStreams(getAll(), LocalTime.MIN, LocalTime.MAX, getCaloriesPerDay());
    }


}
