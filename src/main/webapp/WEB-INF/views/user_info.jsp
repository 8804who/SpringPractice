<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>user_info</title>
    <script>
        function login(){
            location.href = "/loginForm";
        }

        function logout(){
            location.href = "/logout";
        }

        function register(){
            location.href = "/registerForm";
        }

        function userManagement(){
            location.href = "/userManagement"
        }
    </script>
</head>
<body>
    <div class="user_info_box">
        <sec:authorize access="isAnonymous()">
            <span onclick="register()">회원가입</span>
            <span>|</span>
            <span onclick="login()">로그인</span>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <span>${principal.getName()}</span>
            <span>|</span>
            <sec:authorize access="hasAuthority('user_management')">
                <span onclick="userManagement()">회원 관리</span>
                <span>|</span>
            </sec:authorize>
            <span onclick="logout()">로그아웃</span>
        </sec:authorize>
    </div>
</body>
</html>