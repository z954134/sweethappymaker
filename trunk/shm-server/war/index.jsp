
<jsp:include page="/header.jsp">
	<jsp:param name="title" value="Index" />
</jsp:include>

<div id="center"/>
<jsp:include page="/menu.jsp" />
<div id="body">
<h1>Welcome</h1>


<form action="${f:url('login')}" method="post">memberId<br />
<input type="text" name="memberId" value="${f:h(title)}"
	class="${f:errorClass('memberId', 'error')}" />${f:h(errors.title)}<br />

password<br />
<input type="password" " name="password" value="${f:h(title)}" /></form>
</div>
</div>
<jsp:include page="/footer.jsp" />
