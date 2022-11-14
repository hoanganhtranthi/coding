<%-- 
    Document   : personalPage
    Created on : Jun 1, 2022, 4:36:44 PM
    Author     : Hoang Anh
--%>

<%@page import="dto.Account"%>
<%@page import="DAO.AccountDAO"%>
<%@page import="dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
             <c:import url="header_loginedUser.jsp"/>
        </header>
        <section>
            <div class="content">
            <h3>Welcome ${sessionScope.name} Come Back</h3>
            <div class="sort_order">
                Filter based on:
                <span>
                     <div class="order_sort">
                         <a href="sortPriceLowToHigh?email=${sessionScope.email}">Price Low To High </a>
                    </div>
                     <div class="order_sort">
                        <a href="sortPriceHighToLow?email=${sessionScope.email}">Price High To Low </a>
                    </div>
                </span>
            </div>
                     <c:forEach var="order" items="${requestScope.list}">
                     <table class="order">
                          <tr><td>Order ID</td> <td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr>
                    <td><c:out value="${order.getOrderID()}"></c:out></td>
                    <td><c:out value="${order.getOrderDate()}"></c:out></td>
                     <td><c:out value="${order.getShipDate()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${order.getStatus() eq 1}">Processing
                              <br/><a href="cancelOrder?ordid=${order.getOrderID()}">Cancel order</a>
                             </c:when>
                             <c:when test="${order.getStatus() eq 2}">Completed</c:when>
                             <c:otherwise>Canceled</c:otherwise>
                     </c:choose></td>    
                     <td><a href="OrderDetail?orderid=${order.getOrderID()}">Detail</a></td>
            </tr>
            </table>
                 </c:forEach>
                  <c:if test="${requestScope.list.size() ==0 || requestScope.list==null}">           
            <p>You don't have any order</p>
                  </c:if>
             </div>
        </section>
<footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
