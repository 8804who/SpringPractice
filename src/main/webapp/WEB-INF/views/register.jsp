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
    <title>회원가입</title>
<body>
<div class="wrapper">
    <form method="post" id="register" action="${pageContext.request.contextPath}/register" style="width: 100%; height: 100%">
        <input type="text" name="username" placeholder="Username"/>
        <input type="password" name="password" placeholder="Password"/>

        <label for="role">회원 등급</label>
        <select name="user_role" id="role">
            <option value="member">일반 회원</option>
            <option value="admin">관리자</option>
        </select>
        <input type="submit" value="회원가입"/>
    </form>
</div>
</body>
</html>