<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<form action="${f:url('login')}" method="post">
UserID
<input type="text" ${f:text("userId")} class="${f:errorClass('memberId', 'error')}"/>
&nbsp;${f:h(errors.memberId)}<br/>
Password
<input type="text" ${f:text("password")} class="${f:errorClass('password', 'error')}"/>
&nbsp;${f:h(errors.password)}<br/>
<a href="${loginUrl}">Google</a><br>
<input type="submit" value="Login"/>
</form>
</body>
</html>
