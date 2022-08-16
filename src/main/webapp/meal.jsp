<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <link type="text/css"
          href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
    <title>Add new user</title>
</head>
<body>
<script>
    $(function () {
        $('input[name=dateTime]').datepicker();
    });
</script>

<form method="POST" action='meals?action=meals' name="frmAddMeal">
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    Date : <input
        type="datetime-local" name="dateTime"
        value="${meal.dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))}"/> <br/>
    <%--        value="<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${meal.dateTime}" />"/> <br/>--%>
    <input
            type="submit" value="Submit"/>

</form>
</body>
</html>
