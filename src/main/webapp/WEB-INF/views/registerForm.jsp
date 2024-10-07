<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
    <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/board.css" rel="stylesheet">
    <title>회원가입</title>
<body>
<div class="wrapper">
    <form method="post" id="register" action="${pageContext.request.contextPath}/register" style="width: 100%; height: 100%">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
        <input type="text" name="userId" onkeydown="disableSubmit()" id="userId" placeholder="UserID" pattern=".*\S+.*" maxlength="10" required/>
        <button type="button" onclick="checkId()">중복 체크</button>
        <input type="password" name="pw" placeholder="Password" pattern=".*\S+.*" maxlength="10" required/>
        <br>
        <label for="role">회원 등급</label>
        <select name="userRole" id="role">
            <option value="member">일반 회원</option>
            <option value="admin">관리자</option>
        </select>

        <label for="image">프로필 이미지
            <input type="file" name="profileImage" id="image" accept="image/png, image/jpeg">
        </label>
        <br>
        <input type="submit" id="submit" value="회원가입" disabled/>
    </form>
</div>
<script>
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    function disableSubmit(){
        $('#submit').attr("disabled",true);
    }

    function checkId(){
        $.ajax({
            url:"/duplicateCheck",
            type:'post',
            data: {userId:$('#userId').val()},
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success:function(cnt){
                if(cnt === 0){
                    alert("사용 가능한 ID입니다");
                    $("#submit").removeAttr("disabled");
                } else {
                    alert("이미 존재하는 ID입니다.");
                }
            },
            error:function() {
                alert("에러가 발생하였습니다.");
            }
        });
    }

    $("input[name=profileImage]").on("change", function(){
        let maxSize = 5 * 1024 * 1024; //* 5MB 사이즈 제한
        let fileExt = this.files[0].name.split(".").pop().toLowerCase(); //업로드한 파일 확장자
        let fileSize = this.files[0].size; //업로드한 파일 용량

        if($.inArray(fileExt, ["jpg", "png"]) === -1){
            alert("jpg와 png 파일만 첨부 가능합니다.");
            $(this).val(''); //업로드한 파일 제거
            return;
        }

        if(fileSize > maxSize){
            alert("파일첨부 사이즈는 5MB 이내로 가능합니다.");
            $(this).val(''); //업로드한 파일 제거
        }
    });
</script>
</body>
</html>