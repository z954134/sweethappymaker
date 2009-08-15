<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<form action="${f:url('signup')}" method="post">
UserID
<input type="text" ${f:text("userId")} class="${f:errorClass('userId', 'error')}"/>
&nbsp;${f:h(errors.userId)}<br/>
Password
<input type="text" ${f:text("password")} class="${f:errorClass('password', 'error')}"/>
&nbsp;${f:h(errors.password)}<br/>
<input type="submit" value="登録"/>
</form>
</body>
</html>