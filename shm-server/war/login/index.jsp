<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<form action="${f:url('login')}" method="post">
MemberID
<input type="text" ${f:text("memberId")} class="${f:errorClass('memberId', 'error')}"/>
&nbsp;${f:h(errors.memberId)}<br/>
Password
<input type="password" ${f:text("password")} class="${f:errorClass('password', 'error')}"/>
&nbsp;${f:h(errors.password)}<br/>
<input type="submit" value="Login"/>
</form>
<a href="${loginUrl}">Googleアカウントでログイン</a><br>
<a href="${f:url('/signup')}">サインアップ</a>
<a href="${signupUrl}">Googleアカウントでサインアップ</a>
</body>
</html>
