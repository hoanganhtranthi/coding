<%-- 
    Document   : ManageCategories
    Created on : Jul 3, 2022, 12:01:25 PM
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
                <form action="searchCate" method="post" class="search-btn">
                        <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"":request.getParameter("txtsearch")%>">
                          <select name="txtsearchby">
                                 <option value="byname">by Name</option>
                                 <option value="byid">by Cate ID</option>
                            </select>
                         <input  class="search-icon"type="submit" value="searchCate" name="action" >
                          <input  class="search-icon"type="submit" value="AddCategories" name="action" >
                        </form>
          </div>
        </div>
        <h1 class="accounts">Categories</h1>
               <table class="order">
            <tr><th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="cate" items="${requestScope.cateList}">
                <tr>
                    <td><c:out value="${cate.getCateID()}"></c:out></td>
                    <td><c:out value="${cate.getCateName()}"></c:out></td>
            </c:forEach>
            <c:forEach var="cate" items="${requestScope.categoriesList}">
                <tr>
                    <td><c:out value="${cate.getCateID()}"></c:out></td>
                    <td><c:out value="${cate.getCateName()}"></c:out></td>
            </c:forEach>
        </table>
    </body>
</html>
