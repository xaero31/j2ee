<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>

<body>

<h2>Main page</h2>

<c:if test="${user == null}">
    <form method="post" action="${pageContext.request.contextPath}/login_process">
        <input type="text" name="login" placeholder="login"/>
        <input type="password" name="password" placeholder="password"/>
        <input type="submit" value="Login"/>
    </form>
</c:if>

<c:if test="${failed == true}">
    <p>Please login</p>
</c:if>
<c:if test="${WrongPass == true}">
    <p>Entered password is wrong</p>
</c:if>

<jsp:include page="../template/menu.jsp"/>

</body>
</html>
