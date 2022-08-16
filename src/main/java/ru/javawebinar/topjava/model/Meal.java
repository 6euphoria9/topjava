package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {

    private volatile Integer mealId;
    private LocalDateTime dateTime;

    private String description;

    public Meal() {

    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this.mealId = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }


    public void setMealId(Integer id) {
        this.mealId = id;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    private int calories;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
