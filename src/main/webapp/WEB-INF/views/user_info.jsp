<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>user_info</title>
    <script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function login(){
            location.href = "/loginForm";
        }

        function logout(){
            location.href = "/logout";
        }

        function register(){
            location.href = "/registerForm";
        }
    </script>
</head>
<body>
    <div class="user_info_box">
        <c:if test="${principal == null}" >
            <span onclick="register()">회원가입</span>
            <span>|</span>
            <span onclick="login()">로그인</span>
        </c:if>

        <c:if test="${principal != null}" >
            <span>${principal.getName()}</span>
            <span>|</span>
            <span onclick="logout()">로그아웃</span>
        </c:if>
    </div>
</body>
</html>