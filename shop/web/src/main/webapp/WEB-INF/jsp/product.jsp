<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"/>

<h1>Product Details:</h1>
<table class="table">
    <tr>
        <td>${product.productId}<td>
    </tr>
    <tr>
        <td>${product.productName}<td>
    </tr>
    <tr>
        <td>${product.description}<td>
    </tr>
</table>

<jsp:include page="footer.jsp"/>