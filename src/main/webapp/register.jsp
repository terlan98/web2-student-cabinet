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

            <h1>Registration</h1>

            <input type="text" placeholder="First Name" name="firstName" required />
            <input type="text" placeholder="Last Name" name="lastName" required />
            <input type="number" min="1" placeholder="Age" name="age" required />
            <input type="text" placeholder="City" name="city" required />
            <input type="text" placeholder="Country" name="country" required />
            <select placeholder="Gender" name="gender" required>
                <option value="" disabled selected>Gender</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
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
