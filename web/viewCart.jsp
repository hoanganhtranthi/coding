<%-- 
    Document   : viewCart
    Created on : Jun 12, 2022, 9:38:21 AM
    Author     : Hoang Anh
--%>

<%@page import="DAO.PlanDAO"%>
<%@page import="dto.Plant"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
           <c:import url="header.jsp"/>
        </header>
         <script>
            function warning(){
                var tmp=window.confirm("Ban co muon xoa mat hang nay?");
                if(tmp===true){
                    return true;
                }
                return false;
            }
        </script>
        <section>
            <c:if test="${sessionScope.name!=null}">
                <div class="cart">
                <h3>Welcome${sessionScope.name} Come Back</h3>
                <a href="personalPage.jsp">Personal Page</a>
                  </c:if>
                <font style='color:red'>${(requestScope.WARNING==null)?"":requestScope.WARNING}</font>
                </div>
        </section>
        <section>
            <table width="100%" class="order">
                <tr><td>Product ID</td> <td></td><td>Quantity</td><td>Action</td></tr>
                <c:forEach var="plant" items="${requestScope.list}">
                    <form action="action" method="post">
                        <tr>
                            <td><input type="hidden" value="${plant.getId()}" name="pid"><a href="productDetail?pid=${plant.getId()}">${plant.getId()}</a></td>
                            <td><img src='${plant.getImgpath() }' class='plantimg'/></td>
                            <td><input type="number" value="${requestScope.quantity}" name="quantity"></td>
                            <td class=""><input type="submit" value="update" name="action" action="updateCart" />
                                <input type="submit" value="delete" name="action" onclick="return warning()" action="deleteCart"/>
                            </td>
                        </tr>
                    </form>
                            
                        <input type="hidden" value="${total=total+requestScope.quantity*plant.getPrice()}"/>
                    </c:forEach>
                        <tr><td class="total">Total Money: ${total}</td></tr>
                                    <tr><td>Order Date: <%= (new Date()).toString() %></td></tr>
                                    <tr><td>Ship Date: du kien trong 7 ngay  </td></tr>
                                    <c:if test="${requestScope.list == null}"> <tr><td>Your Cart Is Empty</td></tr></c:if>
                                    
            </table>
        </section>
          <section>
                                        <form action="saveShoppingCart" method="post" class="submit">
                                            <input class="mybtn" type="submit" value="saveOrder" name="action">
                                        </form>
                                    </section>
                                    <footer>
           <%@include file="footer.jsp" %>  
        </footer>
    </body>
</html>
