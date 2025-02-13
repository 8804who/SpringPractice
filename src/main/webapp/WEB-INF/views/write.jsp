<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet">
    <title>글 쓰기</title>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <%@ include file="../fix/header.jsp" %>
        </div>
        <div class="Post" style="width: 100%; height: 80%">
            <form method="post" id="upload" action="${pageContext.request.contextPath}/post/upload" style="width: 100%; height: 100%">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <%-- csrf 토큰 --%>
                <input type="hidden" name="userNum" value="${principal.getUserNum()}">
                <div class="Title">
                    <input name="postTitle" type="text" maxlength="50" placeholder="제목을 입력하세요." style="border: none; width: 100%; height: 100%; font-size: 30px; font-weight: bold; background: rgba(233,233,233,0.17)" pattern=".*\S+.*" required/>
                </div>

                <div class="UserID">
                    <input name="userId" type="text" maxlength="10" value="${principal.getUsername()}" style="border: none; width: 100%; height: 100%; font-size: 15px; background: rgba(233,233,233,0.17)" pattern=".*\S+.*" readonly required/>
                </div>

                <div class="Contents">
                    <textarea name="postContents" maxlength="1000" placeholder="내용을 입력하세요." style="width: 100%; height: 100%; resize: none; border: none; font-size: 20px; background: rgba(233,233,233,0.17); overflow-y:scroll" pattern=".*\S+.*" required></textarea>
                </div>

                <div class="Button">
                    <button type="button" onclick="quit()" style="width: 49%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">나가기</button>
                    <button type="submit" formaction="/post/upload" style="width: 49%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">제출하기</button>
                </div>
            </form>
        </div>
        <div class="footer">
            <%@ include file="../fix/footer.jsp" %>
        </div>
    </div>
    <script>
        function quit(){
            location.href = "/";
        }
    </script>
</body>
</html>