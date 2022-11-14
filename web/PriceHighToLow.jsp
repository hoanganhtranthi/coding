<%-- 
    Document   : PriceHighToLow
    Created on : Jul 9, 2022, 6:24:13 PM
    Author     : Hoang Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <div class="search">
          <div class="search-btn">
                <form action="searchOrders" method="post" class="search-btn">
                        <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"":request.getParameter("txtsearch")%>">
                          <select name="txtsearchby">
                                 <option value="byorderID">by Order Id</option>
                                 <option value="byaccountID">by Account ID</option>
                            </select>
                         <input  class="search-icon"type="submit" value="searchOrders" name="action" >
                        </form>
          </div>
        </div>
               <br/>    
        <h1 class="accounts">Orders</h1>
        <section>
            <div class="sort_order">
                Filter based on:
                <span>
                     <div class="order_sort">
                         <a href="sortPriceLowToHigh?email=${sessionScope.email}">Price Low To High </a>
                    </div>
                     <div class="order_sort_active">
                        <a href="sortPriceHighToLow?email=${sessionScope.email}">Price High To Low </a>
                    </div>
                </span>
            </div>
                    <table class="order">
                          <tr><th>Order ID</th> <th>Order Date</th><th>Ship Date</th><th>Order's status</th><th>Account ID</th></tr>
                   <c:forEach var="order" items="${requestScope.list}">  
                <tr>
                    <td><c:out value="${order.getOrderID()}"></c:out></td>
                    <td><c:out value="${order.getOrderDate()}"></c:out></td>
                     <td><c:out value="${order.getShipDate()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${order.getStatus() eq 1}">Processing
                             </c:when>
                             <c:when test="${order.getStatus() eq 2}">Completed</c:when>
                             <c:otherwise>Canceled</c:otherwise>
                     </c:choose></td>    
                      <td><c:out value="${order. getAccID()}"></c:out> </td>
                     <td><a href="OrderDetail?orderid=${order.getOrderID()}">Detail</a></td>
            </tr>
            </c:forEach>
            </table>
                 
                  <c:if test="${requestScope.list.size() ==0 || requestScope.list==null}">           
            <p>Don't have any order</p>
                  </c:if>
        </section>
        
<footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
