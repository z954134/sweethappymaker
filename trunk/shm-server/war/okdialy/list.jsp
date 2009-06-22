<?xml version="1.0" encoding="utf-8"?>
<okDialyList> <c:forEach var="e" items="${okDialyList}">
	<okDialy key="${e.key}" dialyDate="${e.dialyDateText}"
		itemCount="${e.itemCount}" firstItem="${f:h(e.firstItem)}" />
</c:forEach> </okDialyList>
