<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <style>
        .wrapper {
            width: 80%;
            height: 100%;
        }

        table, tr, td, th {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <title>로그인</title>
<body>
<div class="wrapper">
    <form method="post" id="login" action="${pageContext.request.contextPath}/loginCheck" style="width: 100%; height: 100%">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
        <input type="text" name="userId" placeholder="UserID" maxlength="10"/>
        <input type="password" name="pw" placeholder="Password" maxlength="10"/>
        <label for="remember-me" style>로그인 유지</label>
        <input type="checkbox" id="remember-me" name="remember-me" />
        <input type="submit" value="로그인"/>
    </form>
</div>
</body>
</html>