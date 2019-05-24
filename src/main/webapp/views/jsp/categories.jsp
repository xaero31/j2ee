<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>

<body>

<h2>Categories</h2>

<jsp:include page="template/menu.jsp"/>

    <c:forEach items="${categories}" var="category">
        <form method="post" action="${pageContext.request.contextPath}/categories">
            <input type="hidden" name="categoryId" value="${category.id}"/>
            <input type="submit" value="${category.name}"/>
        </form>
    </c:forEach>

</body>
</html>
