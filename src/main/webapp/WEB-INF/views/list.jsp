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
    <script src="src/main/webapp/resources/js/jquery-3.7.1.min.js"></script>
    <title>게시판</title>
<body>
    <div class="wrapper">
        <div class="userInfo" style="margin-left: 70%">
            <%@ include file="user_info.jsp" %>
        </div>
        <div class="pageTitle" style="width: 100%; height: 15%; margin-left: 20%">
            <p style="width: 80%; height: 10%; font-size: 45px; font-weight: bolder; text-align: center;">게시판</p>
        </div>
        <div class="listTable" style="width: 100%; height: 70%; margin-left: 20%">
            <table style="width: 80%; height: 70%;">
                <tr>
                    <th style="width: 80%; height: 5%; font-size: 20px">제목</th>
                    <th style="width: 20%; height: 5%; font-size: 20px">작성자</th>
                </tr>

                <c:forEach items="${postList}" var="post">
                    <tr>
                        <td style="width: 80%; height: 5%; font-size: 20px; text-align: center; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;"><a href="/post/read?postId=${post.postId}" style="text-decoration-line: none;">${post.postTitle}</a></td>
                        <td style="width: 20%; height: 5%; font-size: 20px; text-align: center; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${post.userId}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="underBar" style="width: 50%; height: 30%; margin-top: 1%; margin-left: 40%;">
            <div class="left_button" style="display: inline; padding-left: 2%; margin: 1% auto;">
                <c:if test="${page > 10}">
                    <span onclick = "location.href='/?page='+${page-1-(page-1)%10}"><img src="resources\img\left.png" height="2%" width="2%"></span>
                </c:if>
            </div>

            <div class="navigation" style="display: inline; margin: 1% auto;">
                <c:forEach var="i" begin="${(page-1-(page-1)%10)+1}" end="${(page-1-(page-1)%10)+10}">
                    <c:if test="${(i-1)*10 < postCount}">
                        <c:choose>
                            <c:when test="${i != page}">
                                <span style="font-size: 18px; padding-left: 2%" onclick = "location.href='/?page='+${i}">${i}</span>
                            </c:when>
                            <c:otherwise>
                                <span style="font-size: 18px; padding-left: 2%; color: skyblue" onclick = "location.href='/?page='+${i}">${i}</span>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </div>

            <div class="right_button" style="display: inline; padding-left: 2%; margin: 1% auto;">
                <c:if test="${((page-1-(page-1)%10)+10)*10 < postCount}">
                    <span onclick = "location.href='/?page='+${(page-1-(page-1)%10)+11}"><img src="resources\img\right.png" height="2%" width="2%"></span>
                </c:if>
            </div>

            <div class="write_button" style="display: inline; padding-left: 2%; margin: 1% auto;">
                <button style="width:15%; height: 100%; background: skyblue; border-color: skyblue; color:white; font-size: 20px" onclick = "location.href='/post/write'">글쓰기</button>
            </div>
        </div>
    </div>
</body>
</html>