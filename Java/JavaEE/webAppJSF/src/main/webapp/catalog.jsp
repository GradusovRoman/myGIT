<%--
  Created by IntelliJ IDEA.
  User: Xokyopo
  Date: 29.03.2020
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv=​"Content-Type"​ content=​"text/html; charset=UTF-8"​>
        <title>Catalog</title>
    </head>
    <body>
        <%@include file="navigationBar.jsp"%>
        <jsp:useBean id="productList" scope="request" type="java.util.List"/>
        <h1 align="center">Это каталог товаров</h1>

        <c:if test="${productList.size() > 0}">
            <table align="center" width="40%" border="true">
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="product" items="${productList}">
                    <tr>
                        <td align="right">${product.getId()}</td>
                        <td align="left">${product.getName()}</td>
                        <td align="right">${product.getPrice()}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
