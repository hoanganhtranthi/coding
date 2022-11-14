<%-- 
    Document   : addCate
    Created on : Aug 4, 2022, 11:01:01 AM
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
            <form action="AddCategories" method="post" class="formlogin">
                <h1> Add Categories </h1>
                <table class="information">
                    <tr> <td><label> Name </label></td><td><input type="text" name="txtName" required="" value="${(requestScope.txtName==null)?"":rerequestScope.txtName}" ></td></tr>
                    <tr>
                        <td  class='create' colspan="2"><input type="submit" class="mybtn" value="Add"></td></tr>
                </table>
            </form>
        </div>
            </div>
                  </section>
    </body>
</html>
