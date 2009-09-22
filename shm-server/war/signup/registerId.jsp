<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
${shm:css('global')}
</head>
<body>
<form action="${f:url('login')}" method="post">
UserID
<input type="text" ${f:text("userId")} class="${f:errorClass('memberId', 'error')}"/>
&nbsp;${f:h(errors.memberId)}<br/>
Password
<input type="text" ${f:text("password")} class="${f:errorClass('password', 'error')}"/>
&nbsp;${f:h(errors.password)}<br/>
<input type="submit" value="Login"/>
</form>
<a href="${loginUrl}">Googleアカウントでログイン</a><br>
</body>
</html>