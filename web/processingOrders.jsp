<%-- 
    Document   : processingOrders
    Created on : Jun 9, 2022, 8:50:03 PM
    Author     : Hoang Anh
--%>

<%@page import="DAO.OrderDAO"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
                 <h3>Welcome ${requestScope.name} Come Back</h3>
                 <c:forEach var="order" items="${requestScope.orderList}">
                     <table class="order">
                <tr><td>Order ID</td> <td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr>
                    <td><c:out value="${order.getOrderID()}"></c:out></td>
                    <td><c:out value="${order.getOrderDate()}"></c:out></td>
                     <td><c:out value="${order.getShipDate()}"></c:out></td>
                     <td><c:out value="Processing"></c:out>
                     <br/><a href="cancelOrder?ordid=${order.getOrderID()}">Cancel order</a></td>
                    <td><a href="OrderDetail?orderid=${order.getOrderID()}">Detail</a></td>
                </tr>
                 </table>
                 </c:forEach>
            <c:if test="${requestScope.orderList.size() ==0 || requestScope.orderList==null}">           
            <p>You don't have any order</p>
                  </c:if>
             </div>
        </section>
            <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
