<?xml version="1.0" encoding="utf-8"?>


<okDialy key="${okDialy.key}" dialyDate="${okDialy.dialyDateText}">
<c:forEach var="item" items="${okDialy.items}" varStatus="status">
	<item num="${status.count}" value="${f:h(item)}"/>
</c:forEach>
</okDialy>

