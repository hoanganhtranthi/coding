<%-- 
    Document   : registration
    Created on : Jun 1, 2022, 4:25:22 PM
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
        <header>
            <c:import url="header.jsp"/>
        </header>
        <div class="register">
            <form action="register" method="post" class="form">
                <h1> Register </h1>
                <table class="information">
                    <tr><td> <label> Email </label></td><td><input type="email" name="txtemail" required="" value="${(requestScope.txtemail==null)?"":requestScope.txtemail}"></td></tr>
                    <tr> <td><label>Full name </label></td><td><input type="text" name="txtfullname" required="" value="${(requestScope.txtfullname==null)?"":rerequestScope.txtfullname}" ></td></tr>
                    <tr> <td><label> Password </label></td><td><input type="password" name="txtpassword" required=""></td></tr>
                    <tr> <td><label>Phone </label></td><td><input type="text" name="txtphone" value="${(requestScope.txtphone==null)?"":requestScope.txtphone}" required="" >
                            </br> <font style='color:red'>${(requestScope.ERROR==null)?"":requestScope.ERROR}</font></td></tr>
                    <tr><td  class='create' colspan="2"><input type="submit" class="mybtn" value="create"></td></tr>
                </table>
            </form>
        </div>
            <footer>
                <%@include file="footer.jsp" %>
            </footer>
    </body>
</html>
