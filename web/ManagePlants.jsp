<%-- 
    Document   : ManagePlants
    Created on : Jul 1, 2022, 4:26:01 PM
    Author     : Hoang Anh
--%>

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
        <c:import url="header_loginedAdmin.jsp"/>
        <div class="search">
          <div class="search-btn">
                <form action="search" method="post" class="search-btn">
                        <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"":request.getParameter("txtsearch")%>">
                          <select name="txtsearchby">
                                 <option value="byname">by Name</option>
                                 <option value="bycate">by Categories</option>
                            </select>
                         <input  class="search-icon"type="submit" value="searchPlants" name="action" >
                        </form>
          </div>
        </div>
        <h1 class="accounts">Plants</h1>
               <table class="order">
            <tr><th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th></th>
                <th>Description</th>
                <th>Status</th>
                <th>Categories ID</th>
                <th>Categories Name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="plant" items="${requestScope.planList}">
                <tr>
                    <td><c:out value="${plant.getId()}"></c:out></td>
                    <td><c:out value="${plant.getName()}"></c:out></td>
                     <td><c:out value="${plant.getPrice()}"></c:out></td>
                     <td><img src="${plant.getImgpath() }" class="plantimg"></td>
                       <td><c:out value="${plant.getDescription()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${plant.getStatus() eq 0}">Out Of Stock</c:when>
                             <c:otherwise>Available</c:otherwise>
                     </c:choose></td>
                     <td><c:out value="${plant.getCateid()}"></c:out> </td>
                     <td><c:out value="${plant.getCatename()}"></c:out> </td>  
                      <td>
                          <c:url var="mylink" value="Delete">
                             <c:param name="txtSearch" value="${requestScope.searchValue}"></c:param>
                             <c:param name="txtSearchBy" value="${requestScope.searchbyValue}"></c:param>
                              <c:param name="txtId" value="${plant.getId()}"></c:param>
                         </c:url>
                         <a href="${mylink}">On Stock/Out Of Stock</a>
                        </td>
                        <td><c:url var="mylink" value="Update">
                             <c:param name="txtSearch" value="${requestScope.searchValue}"></c:param>
                             <c:param name="txtSearchBy" value="${requestScope.searchbyValue}"></c:param>
                              <c:param name="txtId" value="${plant.getId()}"></c:param>
                         </c:url>
                          <a href="${mylink}">Update</a></td>
                </tr>
            </c:forEach>
            <c:forEach var="plant" items="${requestScope.plantList}">
                <tr>
                    <td><c:out value="${plant.getId()}"></c:out></td>
                    <td><c:out value="${plant.getName()}"></c:out></td>
                     <td><c:out value="${plant.getPrice()}"></c:out></td>
                       <td><img src="${plant.getImgpath() }" class="plantimg"></td>
                       <td><c:out value="${plant.getDescription()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${plant.getStatus() eq 0}">Out Of Stock</c:when>
                             <c:otherwise>Available</c:otherwise>
                     </c:choose></td>
                     <td><c:out value="${plant.getCateid()}"></c:out> </td>
                     <td><c:out value="${plant.getCatename()}"></c:out> </td> 
                      <td><c:url var="mylink" value="Delete">
                             <c:param name="txtSearch" value="${requestScope.searchValue}"></c:param>
                             <c:param name="txtSearchBy" value="${requestScope.searchbyValue}"></c:param>
                              <c:param name="txtId" value="${plant.getId()}"></c:param>
                         </c:url>
                          <a href="${mylink}">On Stock/Out Of Stock</a></td>
                     <td><c:url var="mylink" value="Update">
                             <c:param name="txtSearch" value="${requestScope.searchValue}"></c:param>
                             <c:param name="txtSearchBy" value="${requestScope.searchbyValue}"></c:param>
                              <c:param name="txtId" value="${plant.getId()}"></c:param>
                         </c:url>
                          <a href="${mylink}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
