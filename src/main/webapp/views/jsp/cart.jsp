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
        <th>Count</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${list.entrySet()}" var="entry">
        <tr>
            <td>${entry.key.name}</td>
            <td>${entry.key.price}</td>
            <td>${entry.key.description}</td>
            <td>${entry.value}</td>
            <c:if test="${entry != null}">
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/cart/remove">
                        <input type="hidden" name="productId" value="${entry.key.id}"/>
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${list != null}">
    <form method="post" action="${pageContext.request.contextPath}/cart/submit">
        <input type="submit" value="Submit order"/>
    </form>
</c:if>

</body>
</html>
