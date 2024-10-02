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
    <script src="src/main/webapp/resources/js/jquery-3.7.1.min.js"></script>
    <script>
        function quit(){
            location.href = "/";
        }
    </script>
    <title>회원 관리</title>
</head>
<body>
<div class="wrapper">
    <div class="userInfo" style="margin-left: 70%">
        <%@ include file="user_info.jsp" %>
    </div>
    <div class="pageTitle" style="width: 100%; height: 15%; margin-left: 20%">
        <p style="width: 80%; height: 10%; font-size: 45px; font-weight: bolder; text-align: center;">회원 관리</p>
        <p style="width: 80%; height: 4%; font-size: 15px; font-weight: bolder; text-align: center;" onclick="quit()">홈으로</p>
    </div>
    <div class="listTable" style="width: 100%; height: 70%; margin-left: 20%">
        <table style="width: 80%; height: 70%;">
            <tr>
                <th style="width: 50%; height: 5%; font-size: 20px">유저 ID</th>
                <th style="width: 15%; height: 5%; font-size: 20px">유저 권한</th>
                <th style="width: 30%; height: 5%; font-size: 20px">유저 관리</th>
            </tr>

            <c:forEach items="${userList}" var="userInfo">
                <tr>
                    <td style="width: 50%; height: 5%; font-size: 20px; text-align: center; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${userInfo.userId}</td>
                    <td style="width: 15%; height: 5%; font-size: 20px; text-align: center; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${userInfo.userRole}</td>
                    <td style="width: 30%; height: 5%; text-align: center;">
                        <form method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" name="userId" value="${userInfo.userId}">
                            <c:if test="${userInfo.enable}">
                                <button type="submit" formaction="/userDeactivate" style="text-align: center; font-size: 20px; display :inline-block;">활동 정지</button>
                            </c:if>
                            <c:if test="${!userInfo.enable}">
                                <button type="submit" formaction="/userActivate" style="text-align: center; font-size: 20px; display :inline-block;">정지 해제</button>
                            </c:if>
                            <button type="submit" formaction="/userDelete" style="text-align: center; font-size: 20px; display :inline-block;">회원 탈퇴</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>