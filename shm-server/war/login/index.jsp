<%@page pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<jsp:include page="/header.jsp">
	<jsp:param name="title" value="Index" />
</jsp:include>
<body>
<form action="${f:url('login')}" method="post">
<div class="blue_gradation">
<h3>ログイン</h3>
MemberID <input id="memberId" type="text" title="ユーザー名を入力してください。"
	${f:text("memberId")} class="${f:errorClass('memberId', 'error')}" />
&nbsp;${f:h(errors.memberId)}<br />
Password <input id="password" type="password"
	${f:text("password")} class="${f:errorClass('password', 'error')}" />
&nbsp;${f:h(errors.password)}<br />
<input type="submit" value="Login" /> <br />
<br />

Googleアカウントでログイン <input type="button" value="go"
	onclick="javascript:location.href='${loginUrl}'" /></div>
</form>
<div class="blue_gradation" style="background-color: #DDDDDD">
<h3>アカウント登録</h3>
<a href="${f:url('/signup')}">登録</a><br />
<a href="${signupUrl}">Googleアカウントで登録</a></div>

<script type='text/javascript'>
	$(function() {
    	$('input').inputHintBox({className:'simple_box',source:'attr',attr:'title',incrementLeft:50});
	});
</script>

</body>
</html>
