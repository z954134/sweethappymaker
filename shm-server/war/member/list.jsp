<?xml version="1.0" encoding="utf-8"?>
<memberList>
<c:forEach var="e" items="${memberList}">
    <member key="${e.key}" memberId="${f:h(e.memberId)}" password="${f:h(e.password)}" email="${f:h(e.email)}" />
</c:forEach>
</memberList>