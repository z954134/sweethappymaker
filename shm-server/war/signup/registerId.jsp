<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Google Account"/>
</jsp:include>

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