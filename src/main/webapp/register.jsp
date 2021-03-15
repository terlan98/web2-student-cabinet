<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Registration | Student Personal Cabinet</title>
    <link rel="stylesheet" href="resources/register.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>

<body>
<h1 class="container-title">Student Personal Cabinet</h1>
<div class="container" id="container">

    <div class="form-container registration-container">
        <form action="/studentCabinet/register" method="POST">

            <c:if test="${sessionScope.registrationState == 'register'}">
                <h1>Register</h1>
            </c:if>
            <c:if test="${sessionScope.registrationState == 'edit'}">
                <h1>Edit Profile</h1>
            </c:if>

            <input type="text" placeholder="First Name" name="firstName" value="${sessionScope.fName}" required />
            <input type="text" placeholder="Last Name" name="lastName" value="${sessionScope.lName}" required />
            <input type="number" min="1" placeholder="Age" name="age" value="${sessionScope.age}" required />
            <input type="text" placeholder="City" name="city" value="${sessionScope.city}" required />
            <input type="text" placeholder="Country" name="country" value="${sessionScope.country}" required />
            <select placeholder="Gender" name="gender"  required>
                <c:if test="${sessionScope.gender.equals('male')}">
                    <option value="male" selected>Male</option>
                    <option value="female">Female</option>
                </c:if>
                <c:if test="${sessionScope.gender.equals('female')}">
                    <option value="male" selected>Female</option>
                    <option value="female">Male</option>
                </c:if>
                <c:if test="${sessionScope.gender == null}">
                    <option value="" disabled selected>Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </c:if>
            </select>

            <button>Submit</button>

        </form>
    </div>
</div>

<footer>
    <p>
        Created with <i class="fa fa-heart"></i> by Tarlan Ismayilsoy
    </p>
</footer>
</body>

</html>
