<%-- 
    Document   : productDetail.jsp
    Created on : Jun 16, 2022, 1:40:38 PM
    Author     : Hoang Anh
--%>

<%@page import="dto.Plant"%>
<%@page import="DAO.PlanDAO"%>
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
            <c:import url="header.jsp"/>
        </header>
        <section>
            <jsp:useBean id="plantObj" class="dto.Plant" scope="request"/>
            <table class="order">
                <tr><td rowspan="8"><img  class="plantimg"src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>"</td></tr>
                <tr><td>ID: <jsp:getProperty name="plantObj" property="id"></jsp:getProperty></td>
                <tr><td>Product Name:<jsp:getProperty name="plantObj" property="name"></jsp:getProperty></td></tr>
                 <tr><td>Price:<jsp:getProperty name="plantObj" property="price"></jsp:getProperty></td></tr>
                  <tr><td>Description:<jsp:getProperty name="plantObj" property="description"></jsp:getProperty></td></tr>
                   <tr><td>Status:<jsp:getProperty name="plantObj" property="status"></jsp:getProperty></td></tr>
                    <tr><td>Cate ID:<jsp:getProperty name="plantObj" property="cateid"></jsp:getProperty></td></tr>
                     <tr><td>Category:<jsp:getProperty name="plantObj" property="catename"></jsp:getProperty></td></tr>
            </table>
            <table class="order">
                <tr><td rowspan="8"><img class="plantimg" src="${plantObj.imgpath}"</td></tr>
            <tr><td>ID:${plantObj.id}</td>
            <tr><td>Product Name:${plantObj.name}</td></tr>
            <tr><td>Price:${plantObj.price}</td></tr>
            <tr><td>Description:${plantObj.description}</td></tr>
            <tr><td>Status:${plantObj.status}</td></tr>
            <tr><td>Cate ID:${plantObj.cateid}</td></tr>
            <tr><td>Category:${plantObj.catename}</td></tr>
                     </table>
 <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
