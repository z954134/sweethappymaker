<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="com.google.appengine.api.users.User"%>
<%@page import="shm.cool.common.user.UserServiceUtil"%>

<html>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Google Account"/>
</jsp:include>
<body>
<div id="msg">${f:h(msg)}</div>
<form action="${f:url('/signup/registerId')}" method="post">
<div class="blue_gradation"> 
Googleアカウント <input type="text"
	value="<%= UserServiceUtil.getCurrentUser().getEmail() %>"
	readonly="readonly"
	${f:text("gaccount")}
	   class="${f:errorClass('gaccount', 'error')}" />
&nbsp;${f:h(errors.gaccount)}<br />
MemberID <input type="text"
	${f:text("memberId")} class="${f:errorClass('memberId', 'error')}" />
&nbsp;${f:h(errors.memberId)}<br />
<input type="submit" value="登録" /></form>
</div>
</body>
</html>