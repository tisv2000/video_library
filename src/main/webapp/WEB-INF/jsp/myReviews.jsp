<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3 style="color: darkgreen">All my reviews</h3>
<table style="width: 60%">
    <tr>
        <th>Movie</th>
        <th>Rate</th>
        <th>Review</th>
    </tr>

    <c:forEach var="review" items="${requestScope.reviews}">
        <tr>
            <td>${review.movie.title}</td>
            <td>${review.rate}</td>
            <td>${review.text}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
