<%-- 
    Document   : index
    Created on : Jun 1, 2022, 4:14:26 PM
    Author     : Hoang Anh
--%>

<%@page import="DAO.PlanDAO"%>
<%@page import="dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <header>
            <c:import url="header.jsp"/>
            <div id="slider">
           <img src="images/2.jpg"/>
       </div>
        
        </header>
        
        <section>
              <h1>PRODUCTS</h1>
               <c:forEach var="plant" items="${requestScope.planList}">
               <div class='product'>
                            <div class=" item">
                                 <p><img src="${plant.getImgpath() }" class="plantimg"></p>
                           <p><c:out value="${plant.getId()}"></c:out></p>
                           <h3><c:out value="${plant.getName()}"></c:out></h3>
                     <p>Price:<c:out value="${plant.getPrice()}"></c:out></p>
                     <p>Status:<c:choose >
                             <c:when test="${plant.getStatus() eq 0}">Out Of Stock</c:when>
                             <c:otherwise>Available</c:otherwise>       
                     </c:choose></p>
                     <p>Category:<c:out value="${plant.getCatename()}"></c:out> </p>
                       <div class="addtobuy">
                                    <br/>
                                <p><a href="addToCart?pid=${plant.getId()}">Add To Cart</a></p>
                       <br/>
                                </div>
            </div>
                               </div>
                            </c:forEach>
                     
               <c:forEach var="plant" items="${requestScope.plantList}">
               <div class='product'>
                            <div class=" item">
                                 <p><img src="${plant.getImgpath() }" class="plantimg"></p>
                           <p><c:out value="${plant.getId()}"></c:out></p>
                           <h3><c:out value="${plant.getName()}"></c:out></h3>
                     <p>Price:<c:out value="${plant.getPrice()}"></c:out></p>
                     <p>Status:<c:choose >
                             <c:when test="${plant.getStatus() eq 0}">Out Of Stock</c:when>
                             <c:otherwise>Available</c:otherwise>       
                     </c:choose></p>
                     <p>Category:<c:out value="${plant.getCatename()}"></c:out> </p>
                       <div class="addtobuy">
                                    <br/>
                                <p><a href="addToCart?pid=${plant.getId()}">Add To Cart</a></p>
                       <br/>
                                </div>
            </div>
                               </div>
                            </c:forEach>
        </section>
             
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
