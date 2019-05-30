<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="<%=request.getRemoteUser()%>"/>

<c:if test="${not empty user}">
    <a href="${pageContext.request.contextPath}/logout">User: ${user}. Click to logout</a>
</c:if>
<ul>
    <li><a href="${path}/main">Main page</a></li>
    <li><a href="${path}/catalog">Show all products</a></li>
    <li><a href="${path}/categories">Show categories</a></li>
    <li><a href="${path}/cart">Product cart</a></li>
    <li><a href="${path}/order">Order</a></li>
</ul>
