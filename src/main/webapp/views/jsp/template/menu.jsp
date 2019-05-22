<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<ul>
    <li><a href="${path}/main">main page</a></li>
    <li><a href="${path}/catalog">catalog</a></li>
    <li><a href="${path}/product">products</a></li>
    <li><a href="${path}/cart">product cart</a></li>
    <li><a href="${path}/order">order</a></li>
</ul>