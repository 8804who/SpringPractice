<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>header</title>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
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

        function userExit(){
            location.href = "/userExitForm"
        }
    </script>
</head>
<body>
    <div class="user_info_box">
        <sec:authorize access="isAnonymous()">
            <span onclick="register()" style="margin-left: 20%">회원가입</span>
            <span>|</span>
            <span onclick="login()">로그인</span>
            <img src="${pageContext.request.contextPath}/resources/img/profile/anonymous.png" style="width: 50px; height: 50px">
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <span>${principal.getUsername()}</span>
            <span>|</span>
            <sec:authorize access="hasAuthority('user_management')">
                <span onclick="userManagement()">회원 관리</span>
                <span>|</span>
            </sec:authorize>
            <span onclick="userExit()">회원 탈퇴</span>
            <span>|</span>
            <span onclick="logout()">로그아웃</span>
            <c:choose>
                <c:when test="${principal.getProfileImage() == null}">
                    <img src="${pageContext.request.contextPath}/resources/img/profile/anonymous.png" style="width: 50px; height: 50px">
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/resources/img/profile/${principal.getProfileImage()}" style="width: 50px; height: 50px">
                </c:otherwise>
            </c:choose>
        </sec:authorize>
    </div>
</body>
</html>