<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<style type="text/css">
.astyle{
	padding: 20px;
}
</style>	
</head>
<body>
<h1>회원가입 페이지</h1>
	<div>
		회원가입 페이지
		<form action="/user/regUserInfoProc.do" method="post">
			<div>
				No : <input type="text" name="no"> 
			</div>
			<div>
				ID : <input type="text"name="id">
			</div>
			<div>
				PWD : <input type="text"name="pwd">
			</div>
			<div>
				NAME : <input type="text"name="name">
			</div>
			<div>
				<button type="submit">회원가입</button>
			</div>
		</form>
	</div>
</body>
