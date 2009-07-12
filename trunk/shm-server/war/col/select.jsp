<?xml version="1.0" encoding="utf-8"?>
<col key="${col.key}">
<c:forEach var="score" items="${col.scores}">
	<score>${score}</score>
</c:forEach>
	<mostImportant>${col.mostImportant}</mostImportant>
	<nextAction>${f:h(col.nextAction)}</nextAction>
	<lastUpdate>${col.lastUpdateText}</lastUpdate>
</col>
