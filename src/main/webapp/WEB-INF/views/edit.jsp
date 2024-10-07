<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet">
    <title>${post.postTitle}</title>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <%@ include file="../fix/header.jsp" %>
        </div>
        <div class="Post" style="width: 100%; height: 80%">
            <form method="post" style="width: 100%; height: 100%">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <%-- csrf 토큰 --%>
                <input type="text" name="postId" value="${post.postId}" style = "display: none;"/>
                <div class="Title">
                    <input type="text" name="postTitle" maxlength="50" value="${post.postTitle}" style="border: none; width: 100%; height: 100%; font-size: 30px; font-weight: bold; background: rgba(233,233,233,0.17)" pattern=".*\S+.*" required/>
                </div>

                <div class="UserID">
                    <input type="text" name="userId" value="${post.userId}" style="border: none; width: 100%; height: 100%; font-size: 15px; background: rgba(233,233,233,0.17)" readonly/>
                </div>

                <div class="Contents">
                    <textarea name="postContents" maxlength="1000" style="width: 100%; height: 100%; resize: none; border: none; font-size: 20px; background: rgba(233,233,233,0.17); overflow-y:scroll" pattern=".*\S+.*" required>${post.postContents}</textarea>
                </div>

                <div class="Button">
                    <button type="button" onclick="quit()" style="width: 49%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">나가기</button>
                    <button type="submit" formaction="/post/update" style="width: 49%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">제출하기</button>
                </div>
            </form>
        </div>
        <div class="footer">
            <%@ include file="../fix/footer.jsp" %>
        </div>
    </div>
    <script>
        function quit(){ // 나가기 버튼
            location.href = "/post/read?postId="+${post.postId};
        }
    </script>
</body>
</html>