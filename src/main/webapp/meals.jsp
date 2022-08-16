<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <c:forEach items="${meals}" var="meal">

        <tr <c:choose>
            <c:when test="${meal.excess == true}"> style='color:darkred' </c:when>
            <c:otherwise> style='color:green' </c:otherwise>
        </c:choose>>
            <td>${meal.dateTime.format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.excess}</td>
            <td><a href="meals?action=edit&mealId=${meal.mealId}">Update</a></td>
            <td><a href="meals?action=delete&mealId=${meal.mealId}">Delete</a></td>
        </tr>

    </c:forEach>
</table>

<p><a href="meals?action=insert">Add User</a></p>
</body>
</html>
