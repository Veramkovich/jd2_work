<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<h1>Random Number</h1>
<%! String s = "Hello "; %>
<% s = s + " world!"; %>
<h1><%=s%></h1>
<h2><%=Math.random()%></h2>

<c:set var="salary" scope="session" value="${300*3}"/>
<c:out value="${salary}"/>

<c:choose>
	<c:when test="${salary>1000}">
		I am rich
	</c:when>
	<c:otherwise>
		I am poor
	</c:otherwise>
</c:choose>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>