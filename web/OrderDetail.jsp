<%-- 
    Document   : OrderDetail
    Created on : Jun 4, 2022, 10:12:56 AM
    Author     : Hoang Anh
--%>

<%@page import="dto.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
        <header>
            <c:import url="header_loginedUser.jsp"/>
        </header>
        <section>
            <div class="content">
                <a  class="view" href="personalPage">View all Orders</a>
                <c:forEach var="order" items="${requestScope.list}">
                    <table class="order">
                        <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td></td><td>Price</td><td>Quantity</td></tr>
                        <tr><td>${order.getOrderID()}</td><td>${order.getPlantID()}</td>
                            <td>${ order.getPlantName()}</td>
                            <td><img src='${order.getImgPath()}' class='plantimg' /></td>
                            <td> ${ order.getPrice()} </td>
                            <td>${order.getQuantity()}</td>
                        <input type="hidden" value=" ${requestScope.money =requestScope.money+order.getPrice()*order.getQuantity()}"/>
                        </tr>
                    </table>
                </c:forEach>
                <h3>Total money:${requestScope.money}</h3>
                <c:if test="${requestScope.list == null}">
                    <p>You don't have any order</p>
                </c:if>
            </div>
        </section>
        <footer>
            <%@include file="footer.jsp" %>  
        </footer>
    </body>
</html>
