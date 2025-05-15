<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Products</title>
    <link rel="stylesheet" href="/static/style.css">
</head>
<body>
<form action="/secure/products" method="post">
    <fieldset>
        <div><label>
            Name: <input type="text" name="product-name" required>
        </label></div>
        <div><label>
            ImageUrl: <input type="text" name="image-url" required>
        </label></div>
        <button type="submit">Add product</button>
    </fieldset>
</form>

<div>
    <c:forEach var="login" items="${userLogins}">
        <p> ${login}</p>
    </c:forEach>
</div>
<c:forEach var="product" items="${products}">
    <p>${product.name}</p>
    <c:choose>
        <c:when test="${product.imageUrl != null}">
            <img src="${product.imageUrl}">
        </c:when>
        <c:otherwise>
            <img src="Https://cdn-icons-png.flaticon.com/512/4054/4054617.png">
        </c:otherwise>
    </c:choose>
    <form action="/secure/products" method="post">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="product-id" value="${product.id}"/>
        <button type="submit" name="remove-button" style="color: red">Remove</button>
    </form>
</c:forEach>
</body>
</html>