<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>
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
<a href="${f:url('/signup')}">登録</a><br/>
<a href="${signupUrl}">Googleアカウントで登録</a>
</body>
</html>
