<%-- 
    Document   : ManageAccounts
    Created on : Jun 30, 2022, 1:23:22 PM
    Author     : Hoang Anh
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dto.Account"%>
<%@page import="DAO.AccountDAO"%>
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
                <form action="searchAccounts" method="post" class="search-btn">
                        <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch")==null)?"":request.getParameter("txtsearch")%>">
                            <select name="txtsearchby">
                                 <option value="byname">by name</option>
                                 <option value="bycate">by email</option>
                            </select>
                                <input  class="search-icon"type="submit" value="searchAccounts" name="action" >
                        </form>
          </div>
        </div>
        <h1 class="accounts">Accounts</h1>
               <table class="order">
            <tr><th>ID</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>Status</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:forEach var="acc" items="${requestScope.accList}">
                <tr>
                    <td><c:out value="${acc.getAccID()}"></c:out></td>
                    <td><c:out value="${acc.getEmail()}"></c:out></td>
                     <td><c:out value="${acc.getFullname()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                             <c:otherwise>Inactive</c:otherwise>
                     </c:choose></td>
                     <td><c:out value="${acc.getPhone()}"></c:out> </td>
                     <td>
                     <c:choose >
                         <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                         <c:otherwise>User</c:otherwise>
                     </c:choose>
                     </td>
                     <td>
                     <c:if test="${acc.getRole() eq 0}">
                         <c:url var="mylink" value="updateStatusAccount">
                             <c:param name="email" value="${acc.getEmail()}"></c:param>
                             <c:param name="status" value="${acc.getStatus()}"></c:param>
                         </c:url>
                         <a href="${mylink}">Block/Unblock</a>
                     </c:if>
                     </td>
                </tr>
            </c:forEach>
            <c:forEach var="acc" items="${requestScope.accountList}">
                <tr>
                    <td><c:out value="${acc.getAccID()}"></c:out></td>
                    <td><c:out value="${acc.getEmail()}"></c:out></td>
                     <td><c:out value="${acc.getFullname()}"></c:out></td>
                     <td><c:choose >
                             <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                             <c:otherwise>Inactive</c:otherwise>
                     </c:choose></td>
                     <td><c:out value="${acc.getPhone()}"></c:out> </td>
                     <td>
                     <c:choose >
                         <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                         <c:otherwise>User</c:otherwise>
                     </c:choose>
                     </td>
                     <td>
                     <c:if test="${acc.getRole() eq 0}">
                         <c:url var="mylink" value="updateStatusAccount">
                             <c:param name="email" value="${acc.getEmail()}"></c:param>
                             <c:param name="status" value="${acc.getStatus()}"></c:param>
                         </c:url>
                         <a href="${mylink}">Block/Unblock</a>
                     </c:if>
                     </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </body>
</html>
