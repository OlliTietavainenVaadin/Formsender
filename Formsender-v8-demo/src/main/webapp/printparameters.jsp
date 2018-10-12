<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Parameter printing page</title>
</head>
<body>
<h1>Parameters</h1>

<ul>
    <c:forEach var="pageParameter" items="${param}">
        <c:choose>
            <c:when test="${empty pageParameter.value}">
                <li><c:out value="${pageParameter.key}"/> = NO VALUE</li>
            </c:when>
            <c:otherwise>
                <li><c:out value="${pageParameter.key}"/> = <c:out value="${pageParameter.value}"/></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>

</body>
</html>