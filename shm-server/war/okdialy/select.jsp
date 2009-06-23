<?xml version="1.0" encoding="utf-8"?>

<c:if test="${okDialy != null}">
	<okDialy key="${okDialy.key}" dialyDate="${okDialy.dialyDateText}">
	<c:forEach var="item" items="${okDialy.items}">
		<item>${f:h(item)}</item>
	</c:forEach> </okDialy>
</c:if>
