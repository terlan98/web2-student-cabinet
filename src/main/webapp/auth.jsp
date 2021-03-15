<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Authentication | Student Personal Cabinet</title>
    <link rel="stylesheet" href="resources/auth.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>

<body>
<script src="resources/auth.js"></script>

<h1 class="container-title">Student Personal Cabinet</h1>

<div class="container" id="container">

    <div class="form-container sign-up-container">
        <form action="/studentCabinet/auth" method="POST">
            <h1>Create Account</h1>
            <input type="email" placeholder="Email" name="email" required/>
            <input type="password" placeholder="Password" name="password" required/>
            <button type="submit" name="buttonType" value="signUp">Sign Up</button>
        </form>
    </div>

    <div class="form-container registration-container">
        <form action="/studentCabinet/auth" method="POST">
            <h1>Sign in</h1>
            <input type="email" placeholder="Email" name="email" required/>
            <input type="password" placeholder="Password" name="password" required/>
            <button type="submit" name="buttonType" value="signIn">Sign In</button>
        </form>
    </div>

    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>Already have an account? Sign in to access your personal cabinet</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello!</h1>
                <p>Don't have an account? Sign up and start using your personal cabinet</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>

<footer>
    <p>
        Created with <i class="fa fa-heart"></i> by Tarlan Ismayilsoy
    </p>
</footer>


<%--SHOW ERRROR MESSAGES (IF ANY)--%>
<c:if test="${sessionScope.errMsg != null}">
    <script>
        alert("${sessionScope.errMsg}");
    </script>
    <c:set var="errMsg" scope="session" value="${null}"/>
</c:if>

</body>

</html>
