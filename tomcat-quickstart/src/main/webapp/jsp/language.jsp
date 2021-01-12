<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
<c:forEach items="${languages}" var="lang">
    <li><c:out value="${lang}"/></li>
</c:forEach>
</ul>
<c:out value="${requestScope.attr2.id}"/><br/>
<c:out value="${sessionScope.attr1}"/><br/>
<c:out value="${applicationScope.attr3}"/><br/>
