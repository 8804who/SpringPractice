<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>

    <style>
        html,
        body {
            width: 80%;
            height: 100%;
        }
        .wrapper {
            width: 100%;
            height: 100%;
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <title>회원 관리</title>
</head>
<body>
<div class="wrapper" style="margin-left: 30%">
    <div class="userInfo" style="margin-left: 70%">
        <%@ include file="user_info.jsp" %>
    </div>
    <div class="user_list">
        <c:forEach items="${userList}" var="userInfo">
            <p>${userInfo.userId}</p>
        </c:forEach>
    </div>
</div>
</body>
</html>