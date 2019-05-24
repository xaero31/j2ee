<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<ul>
    <li><a href="${path}/main">Main page</a></li>
    <li><a href="${path}/catalog">Show all products</a></li>
    <li><a href="${path}/categories">Show categories</a></li>
    <li><a href="${path}/cart">Product cart</a></li>
    <li><a href="${path}/order">Order</a></li>
</ul>