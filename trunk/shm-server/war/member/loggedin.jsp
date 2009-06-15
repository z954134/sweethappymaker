<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>user Login</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
Name: ${f:h(userName)}</br>
isAdmin: ${f:h(isAdmin)}</br>
isLoggedIn: ${f:h(isLoggedIn)}</br>
domain: ${f:h(domain)}</br>
email: ${f:h(email)}</br>
nickname: ${f:h(nickname)}</br>
<a href="${f:url('login')}">${f:url('login')}</a>
</body>
</html>
