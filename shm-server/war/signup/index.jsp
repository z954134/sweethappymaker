<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>
<body>
<form action="${f:url('signup')}" method="post">
<div class="blue_gradation"/>
MemberID
<input type="text" ${f:text("memberId")} class="${f:errorClass('memberId', 'error')}"/>
&nbsp;${f:h(errors.memberId)}<br/>
Password
<input type="password" ${f:text("password")} class="${f:errorClass('password', 'error')}"/>
&nbsp;${f:h(errors.password)}<br/>
<input type="submit" value="登録"/>
</div>
</form>
</body>
</html>