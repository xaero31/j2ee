<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Current order</title>
</head>

<body>
<h2>Your orders</h2>

<jsp:include page="template/menu.jsp"/>

<table>
    <thead>
    <tr>
        <td>Id</td>
        <td>Products</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td rowspan="${order.products.size + 1}">${order.id}</td>
            <td>Status: ${order.state}</td>
        </tr>
        <c:forEach items="${order.products}" var="product">
            <tr>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <form method="post" action="#">
                    <input type="hidden" value="${order.id}"/>
                    <input type="submit" value="cancel order"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
