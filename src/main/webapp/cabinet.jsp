<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Personal Cabinet</title>
    <link rel="stylesheet" href="resources/cabinet.css">
</head>

<body>
<table>
    <tr>
        <td><b>First Name</b></td>
        <td><c:out value="${sessionScope.fName}"/></td>
    </tr>
    <tr>
        <td><b>Last Name</b></td>
        <td><c:out value="${sessionScope.lName}"/></td>
    </tr>
    <tr>
        <td><b>Age</b></td>
        <td><c:out value="${sessionScope.age}"/></td>
    </tr>
    <tr>
        <td><b>City</b></td>
        <td><c:out value="${sessionScope.city}"/></td>
    </tr>
    <tr>
        <td><b>Country</b></td>
        <td><c:out value="${sessionScope.country}"/></td>
    </tr>
    <tr>
        <td><b>Gender</b></td>
        <td style="text-transform: capitalize"><c:out value="${sessionScope.gender}"/></td>
    </tr>
</table>
</body>

</html>
