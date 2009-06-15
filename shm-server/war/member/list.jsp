<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<?xml version="1.0" encoding="utf-8"?>

<userList>
<c:forEach var="e" items="${memberList}">
    <blog key="${e.key}" title="${f:h(e.name)}" content="${f:h(e.email)}"/>
</c:forEach>
</userList>