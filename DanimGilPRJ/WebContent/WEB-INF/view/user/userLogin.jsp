<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>UserLogin</title>


   
    <style>
*{
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family:sans-serif;

}

body {
  background-image: #34495e;
  background-color : #8CD7E4 ;
}

.loginForm {
  position:absolute;
  width:300px;
  height:400px;
  padding: 30px, 20px;
  background-color:none;
  text-align:center;
  top:50%;
  left:50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
}

.loginForm h2{
  text-align: center;
  margin: 30px;
}

.idForm{
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}

.passForm{
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}

.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.pw {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 20px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.btn:hover {
  background-position: right;
}

.bottomText {
  text-align: center;
  color : black;
}
 a{
   color : ;
   margin : 2px;
   font-size : 13px;
}

img{
   margin : o auto;
}

      
    </style>

    
   <html>
  <head>
    <link rel="stylesheet" type="text/css" href="./style.css">
  </head>
  <body width="100%" height="100%">
    <form action="/user/userLoginProc.do" method="post" class="loginForm">
     <img src="/resources/image/Log.png" width="200px">
     <h4> 소경서 </h4>
      <div class="idForm">
        <input type="text" class="id" placeholder="ID" name = "id">
      </div>
      <div class="passForm">
        <input type="password" class="pw" placeholder="PW" name = "pwd">
      </div>
      <button type="submit" class="btn" >
        LOG IN
      </button>

      <div class="bottomText">
         <div>
            <a href="/kakaoLoginProc.do"> <img src="/resources/image/kakao_login.png" class="btn"></a>
         </div>
      
         <a href="#">아이디 찾기 </a>|
         <a href="#">비밀번호 찾기 </a>|
         <a href="#">회원가입</a>
      </div>


   </form>
  </body>
</html>