<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>
<body>
<div class="blue_gradation" >

<h2>アカウント登録</h2>
<form action="${f:url('signup')}" method="post">
メンバーID
<input id="memberId" type="text" tooltip="ユーザー名を入力してください。" 
	${f:text("memberId")} class="${f:errorClass('memberId', 'error')}" />
&nbsp;${f:h(errors.memberId)}

<br />
パスワード
<input id="password" type="password" tooltip="パスワードを入力して下さい" 
	${f:text("password")} class="${f:errorClass('password', 'error')}" />
&nbsp;${f:h(errors.password)}<br />
<input id="login" type="submit" value="Login" tooltip="ログインします"/> <br />
</form>

<ul>
<li><a href="${signupUrl}">Googleアカウントで登録</a></li>
</ul>

</div>

</body>
</html>