<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>

    <sec:authorize access="isAuthenticated()" var="authenticated"/>
    <sec:authorize access="hasAuthority('delete_anything')" var="delete_anything"/>
    <sec:authorize access="hasAuthority('modify_anything')" var="modify_anything"/>

    <style>
        html,
        body {
            width: 80%;
            height: 100%;
        }
        .wrapper {
            width: 80%;
            height: 100%;
        }
    </style>

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
    <script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var authenticated = ${authenticated};

        function quit(){
            location.href = "/";
        }

        function edit(){
            location.href = "/post/edit?postId=${post.postId}";
        }

        function cs(){
            $('.Comment').load(window.location.href+' .Comment > *')
        }

        function comment_upload(){
            if (!authenticated)
            {
                alert("로그인하세요");
                return false;
            }
            if ($("#commentContents").val().trim()===""){
                alert("댓글 내용을 입력하세요");
                return false;
            }

            var data = {userId:$('#userId').val(), commentContents:$('#commentContents').val(), postId:$('#postId').val()}

            $.ajax({
                url:"/comment/upload",
                type: 'post',
                data: JSON.stringify(data),
                contentType: 'application/json',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success: function(){
                    cs();
                },
                error: function(){
                    alert("오류가 발생하였습니다.");
                }
            });
        }
    </script>
    <title>${post.postTitle}</title>
</head>
<body>
<div class="wrapper" style="margin-left: 30%">
    <div class="userInfo" style="margin-left: 70%">
        <%@ include file="user_info.jsp" %>
    </div>
    <div class="Post" style="width: 100%; height: 80%">
        <form method="post" style="width: 100%; height: 100%">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> <%-- csrf 토큰 --%>
            <input type="text" name="postId" id="postId" value="${post.postId}" style = "display: none;"/>
            <div class="Title" style="width: 50%; height: 16%; margin: 3% auto;">
                <input type="text" name="postTitle" value="${post.postTitle}" style="border: none; width: 100%; height: 100%; font-size: 30px; font-weight: bold; background: rgba(233,233,233,0.17)" readonly/>
            </div>

            <div class="UserID" style="width: 50%; height:8%; margin: 3% auto;">
                <input type="text" name="userId" value="${post.userId}" style="border: none; width: 100%; height: 100%; font-size: 15px; background: rgba(233,233,233,0.17)" readonly/>
            </div>

            <div class="Contents" style="width: 50%; height: 50%; margin: 3% auto;">
                <textarea name="postContents" style="width: 100%; height: 100%; resize: none; border: none; font-size: 20px; background: rgba(233,233,233,0.17); overflow-y:scroll" readonly>${post.postContents}</textarea>
            </div>

            <div class="Button" style="width: 50%; height: 8%; margin: 3% auto;">
                <button type="button" onclick="quit()" style="width: 32%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">나가기</button>
                <c:if test="${authenticated}">
                    <c:if test="${post.userId == principal.getName() || modify_anything}">
                        <button type="button" onclick="edit()" style="width: 32%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">수정하기</button>
                    </c:if>
                    <c:if test="${post.userId == principal.getName() || delete_anything}">
                        <button type="submit" formaction="/post/delete" style="width: 32%; height: 100%; background: skyblue; font-size: 30px; background: rgba(233,233,233,0.17);">삭제하기</button>
                    </c:if>
                </c:if>
            </div>
        </form>
    </div>

    <div class="Comment" style="width: 100%; height: 20%">
        <p style="width: 50%; height: 30%; margin: 0% auto; font-size: 20px; font-weight: bold">${commentList.size()}개의 댓글</p>
        <div class="commentWrite" style="width: 50%; height: 70%; margin: 1% auto;">
            <c:if test="${!authenticated}">
                <input type="text" id="userId" value="로그인을 해주세요" style="width: 70%; height: 30%; border: none; font-size: 15px; background: rgba(233,233,233,0.17);" readonly/>
            </c:if>
            <c:if test="${authenticated}">
                <input type="text" id="userId" value="${principal.getName()}" style="width: 70%; height: 30%; border: none; font-size: 15px; background: rgba(233,233,233,0.17);" readonly/>
            </c:if>
            <button type="button" id="commentSubmit" onclick="comment_upload()" style="width: 28%; height: 30%; background: skyblue; font-size: 15px; background: rgba(233,233,233,0.17);">댓글 작성</button>
            <textarea id="commentContents" placeholder="댓글을 작성하세요" style="width: 100%; height: 70%; resize: none; border: none; font-size: 20px; background: rgba(233,233,233,0.17); margin-top: 1%" maxlength="100"></textarea>
        </div>

        <div class="commentRead" style="width: 50%; height: 20%; margin: 5% auto;">
            <c:forEach items="${commentList}" var="comment">
                <script>
                    $(document).on('click', '#commentEdit_${comment.commentId}',function() {
                        if ($("#commentEdit_${comment.commentId}").text() === "댓글 수정")
                        {
                            $("#commentEdit_${comment.commentId}").text("수정 완료");
                            $("#commentContents_${comment.commentId}").attr("readonly",false);
                        }
                        else
                        {
                            $("#commentEdit_${comment.commentId}").text("댓글 수정");
                            $("#commentContents_${comment.commentId}").attr("readonly",true);

                            if ($("#commentContents_${comment.commentId}").val().trim()===""){
                                alert("댓글 내용을 입력하세요");
                                return false;
                            }

                            var data = {commentContents:$('#commentContents_${comment.commentId}').val(), commentId:${comment.commentId}}
                            $.ajax({
                                url:"/comment/update",
                                type: 'post',
                                data: JSON.stringify(data),
                                contentType: 'application/json',
                                beforeSend: function(xhr) {
                                    xhr.setRequestHeader(header, token);
                                },
                                success: function(){
                                    cs();
                                },
                                error: function(){
                                    alert("오류가 발생하였습니다.");
                                }
                            });
                        }
                    });

                    $(document).on('click', '#commentDelete_${comment.commentId}', function() {
                        var data = {commentId:${comment.commentId}};

                        $.ajax({
                            url:"/comment/delete",
                            type: 'post',
                            data: JSON.stringify(data),
                            contentType: 'application/json',
                            beforeSend: function(xhr) {
                                xhr.setRequestHeader(header, token);
                            },
                            success: function(){
                                cs();
                            },
                            error: function(){
                                alert("오류가 발생하였습니다.");
                            }
                        });
                    });
                </script>
                <input type="text" id="commentId_${comment.commentId}" style = "display: none;"/>
                <input type="text" id="userId_${comment.commentId}" value="${comment.userId}" style="border: none; width: 50%; height: 60%; font-size: 12px; font-weight: bold; background: rgba(233,233,233,0.17)" readonly/>
                <c:if test="${authenticated}">
                    <c:if test="${comment.userId == principal.getName() || modify_anything}">
                        <button type="button" id="commentEdit_${comment.commentId}" style="width: 23%; height: 60%; background: skyblue; font-size: 10px; background: rgba(233,233,233,0.17);">댓글 수정</button>
                    </c:if>
                    <c:if test="${comment.userId == principal.getName() || delete_anything}">
                        <button type="button" id="commentDelete_${comment.commentId}" style="width: 23%; height: 60%; background: skyblue; font-size: 10px; background: rgba(233,233,233,0.17);">댓글 삭제</button>
                    </c:if>
                </c:if>
                <textarea id="commentContents_${comment.commentId}" style="width: 100%; height: 100%; resize: none; border: none; font-size: 12px; background: rgba(233,233,233,0.17);" readonly>${comment.commentContents}</textarea>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>