<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
</head>
<body>
	<div>
		<!-- Top Start-->
		<%@ include file="/WEB-INF/view/user/top.jsp"%>
		<!-- Top END-->
	</div>
	<div>
		로그인 화면
		
		<form action="/user/userLoginProc.do" method="post">
			아이디 : <input type="text" name="id"> 
			
			비밀번호 : <input type="text"name="pwd">
			<button type="submit">로그인</button>
		</form>
	</div>
	
	<div>
		<form>
			<a href=""> <img
				src="/resources/image/kakao_login_medium_narrow.png">
			</a>
		</form>
	</div>
</body>
