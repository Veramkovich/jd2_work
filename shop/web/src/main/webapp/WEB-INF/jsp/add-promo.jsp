<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>
<h1>Add New Promo</h1>
<form:form action="/web/promo/add" method="post" modelAttribute="promoDto">

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">Product Name</span>
  <form:input type="text" class="form-control" placeholder="product name"
    aria-label="product name" aria-describedby="basic-addon1"
    name="productName" path="productName"/>
</div>

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon2">Product Description</span>
  <form:input type="text" class="form-control" placeholder="description"
    aria-label="description" aria-describedby="basic-addon2"
    name="description" path="description"/>
</div>

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon3">Promo Description</span>
  <form:input type="text" class="form-control" placeholder="promo description"
    aria-label="promo description" aria-describedby="basic-addon3"
    name="promoDescription" path="promoDescription"/>
</div>

<button type="submit" class="btn btn-primary">Add Promo</button>

</form:form>
<jsp:include page="footer.jsp"/>