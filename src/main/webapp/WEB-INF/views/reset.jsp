<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet">
    <title>사이트 초기화</title>
</head>
<body>
    <button onclick=reset()>누르지마세요</button>
    <script>
        function reset(){
            location.href = "/resetExecute";
        }
    </script>
</body>
</html>