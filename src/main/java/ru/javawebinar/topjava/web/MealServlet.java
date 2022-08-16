package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String LIST_MEAL = "/meals.jsp";
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String forward = "";
        String action = request.getParameter("action");


        if (action.equalsIgnoreCase("meals")) {
            log.debug("getting meals from MealsUtil");

            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMealTo());
        } else if (action.equalsIgnoreCase("delete")) {
            log.debug("deleting meal");

            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMealTo());
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("getting meals to edit");

            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getById(mealId);
            request.setAttribute("meal", meal);
        } else {
            forward = INSERT_OR_EDIT;
        }


        log.debug("forwarding to meals.jsp");
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDescription(req.getParameter("description"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
//        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        meal.setDateTime(dateTime);

        String mealId = req.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            dao.add(meal);
        } else {
            meal.setMealId(Integer.parseInt(mealId));
            dao.update(meal);

        }
        req.setAttribute("meals", dao.getAllMealTo());
        req.getRequestDispatcher(LIST_MEAL).forward(req, resp);
    }
}
