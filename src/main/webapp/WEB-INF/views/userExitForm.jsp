<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        function checkPW(){
            $.ajax({
                url:"/passwordCheck",
                type:'post',
                data: JSON.stringify({userId:$('#userId').val(), pw:$('#pw').val()}),
                contentType: 'application/json',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success:function(equal){
                    if(equal){
                        alert("비밀번호가 일치합니다.")
                        $("#submit").removeAttr("disabled");
                    } else {
                        alert("비밀번호가 틀렸습니다.");
                    }
                },
                error:function() {
                    alert("에러가 발생하였습니다.");
                }
            });
        }
    </script>
    <title>회원 탈퇴</title>
<body>
<div class="wrapper">
    <form method="post" id="exit" action="${pageContext.request.contextPath}/userExit" style="width: 100%; height: 100%">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
        <input type="text" name="userId" id="userId" value="${principal.getName()}" readonly/>
        <input type="password" name="pw" id="pw" placeholder="Password" pattern=".*\S+.*" maxlength="10" required/>
        <button type="button" onclick="checkPW()">비밀번호 확인</button>
        <input type="submit" id="submit" value="회원 탈퇴" disabled/>
    </form>
</div>
</body>
</html>