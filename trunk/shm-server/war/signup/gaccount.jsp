<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.google.appengine.api.users.User"%>
<%@page import="shm.common.user.UserServiceUtil"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>

<form action="${f:url('/signup/registerId')}" method="post">
Googleアカウント
<input type="text" 
       value="<%= UserServiceUtil.getCurrentUser().getEmail() %>"
       readonly="readonly"
       ${f:text("gaccount")}
	   class="${f:errorClass('gaccount', 'error')}"/></br>
MemberID
<input type="text" ${f:text("memberId")} class="${f:errorClass('memberId', 'error')}"/>
&nbsp;${f:h(errors.memberId)}<br/>
<input type="submit" value="登録"/>
</form>
</body>
</html>