<%-- 
    Document   : updatePlant
    Created on : Jul 18, 2022, 6:48:40 AM
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
         <section>
            <div class="content">
            <div class="change">
            <form action="Update" method="post" class="formlogin">
                <h1> Update Plant </h1>
                <table class="profile">
                    <tr>
                        <td> <label>ID: ${requestScope.txtID}</label></td></tr>
                    <tr> <td><label> Full Name</label></td><td><input type="text" name="txtname" required="" value="${(requestScope.txtfullname==null)?"":rerequestScope.txtfullname}" ></td></tr>
                    <tr> <td><label> Price</label></td><td><input type="text" name="txtprice" value="${(requestScope.txtprice==null)?"":requestScope.txtprice}" required="" >
                     <tr> <td><label for="img">Select image:</label></td><td><input type="file" id="img" name="txtimg" accept="image/*" value="${(requestScope.txtimg==null)?"":requestScope.txtimg}" required="" >       
                     <tr> <td><label>Description</label></td><td><input type="text" name="txtdescription" value="${(requestScope.txtdescription==null)?"":requestScope.txtdescription}" required="" >  
                      <tr> <td><label>Categories ID</label></td><td><input type="text" name="txtcateID" value="${(requestScope.txtcateID==null)?"":requestScope.txtcateID}" required="" >
                       </br><font style='color:red'> ${(requestScope.ERROR==null)?"":requestScope.ERROR}</font></td></tr>  
                      <tr><td class='save' colspan="2">
                              <input type = "HIDDEN" name = "txtSearch" value = "${requestScope.searchValue}">  
                      <input type = "HIDDEN" name = "txtSearchBy" value = "${requestScope.searchbyValue}">
                       <input type = "HIDDEN" name = "txtId" value = "${requestScope.txtID}">
                        <input  class="mybtn" type="submit" value="Update" name="action"></td></tr>
                </table>
            </form>
            </div>
            </div>
                     </section>
    </body>
</html>
