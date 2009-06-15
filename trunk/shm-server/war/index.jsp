<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Index(Slim3)</title>
<link rel="stylesheet" type="text/css" href="/css/global.css" />
</head>
<body>
<div id="header"/>
<jsp:include page="/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>
</div>
<jsp:include page="/menu.jsp" />
<div id="body">

<h1>Welcome</h1>


<form action="${f:url('login')}" method="post">
memberId<br />
<input type="text" name="memberId" value="${f:h(title)}" class="${f:errorClass('memberId', 'error')}"/>${f:h(errors.title)}<br />

password<br />
<input type="password"" name="password" value = "${f:h(title)}"/>
</form>
</div>
<jsp:include page="/footer.jsp" />
</body>
</html>