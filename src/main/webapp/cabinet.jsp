<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Personal Cabinet</title>
    <link rel="stylesheet" href="resources/cabinet.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>

<body>
<table>
    <tr>
        <th colspan="2">Account Information</th>
    </tr>

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
    <tr>
        <td colspan="2">
            <form action="/studentCabinet/cabinet" method="post">
                <button class="courseButton editButton" name="buttonType" value="edit"><i class="fas fa-user-edit"></i>
                    Edit
                </button>
            </form>
        </td>
    </tr>
</table>


<table>
    <tr>
        <th colspan="2">Courses</th>
    </tr>

    <c:forEach var="element" items="${sessionScope.courses}">
        <tr>
            <td><b>${element.name}</b></td>
            <td>
                <form action="/studentCabinet/cabinet" method="post">
                    <c:if test="${!element.enrolled}">
                        <button class="courseButton addButton" name="buttonType"
                                value="+#<c:out value="${element.id}"/>">Add Course
                        </button>
                    </c:if>
                    <c:if test="${element.enrolled}">
                        <button class="courseButton deleteButton" name="buttonType"
                                value="-#<c:out value="${element.id}"/>">Remove Course
                        </button>
                    </c:if>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>

<form class="logOutForm" action="/studentCabinet/cabinet" method="post">
    <button class="logOutButton" name="buttonType" value="logOut"><i class="fas fa-sign-out-alt"></i> Log Out</button>
</form>

</body>

</html>
