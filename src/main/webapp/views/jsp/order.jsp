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
        <td>Status</td>
        <td>Product</td>
        <td>Count</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${orders}" var="order">
<%--        <tr>--%>
<%--            <td rowspan="${order.products.size() + 1}">${order.id}</td>--%>
<%--            <td rowspan="${order.products.size() + 1}">${order.state.name}</td>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${order.products}" var="product">--%>
<%--            <tr>--%>
<%--                <td>${product.name}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
        <tr>
            <td rowspan="${order.productMap.entrySet().size() + 1}">${order.id}</td>
            <td rowspan="${order.productMap.entrySet().size() + 1}">${order.state.name}</td>
        </tr>
        <c:forEach items="${order.productMap.entrySet()}" var="entry">
            <tr>
                <td>${entry.key.name}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
        <c:if test="${order.state.toString() == 'IN_PROGRESS'}">
            <tr>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/order/cancel">
                        <input type="hidden" name="orderId" value="${order.id}"/>
                        <input type="submit" value="cancel order"/>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
