<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product cart</title>
</head>
<body>
<h2>Product cart</h2>
<jsp:include page="template/menu.jsp"/>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${list}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <c:if test="${product != null}">
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/cart/remove">
                        <input type="hidden" name="productId" value="${product.id}"/>
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form method="post" action="${pageContext.request.contextPath}/cart/submit">
    <input type="submit" value="Submit order"/>
</form>

</body>
</html>
