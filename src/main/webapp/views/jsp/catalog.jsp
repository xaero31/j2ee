<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>

<body>

<h2>Catalog</h2>

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
    <c:if test="${category != null}">
        <h3>${category}</h3>
    </c:if>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/catalog/buy">
                    <input type="hidden" name="productId" value="${product.id}"/>
                    <input type="hidden" name="category" value="${category}"/>
                    <input type="submit" value="Buy"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
