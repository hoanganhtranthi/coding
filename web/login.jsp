<%-- 
    Document   : login
    Created on : Jun 1, 2022, 4:16:32 PM
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
        <div class="login">
            <form action="dangnhap" method="post" class="formlogin">
                <font style='color:red'> ${(requestScope.WARNING==null)?"":requestScope.WARNING}  </font>
               <h1> Login </h1>
               <table class="information">
                    <tr>
                        <td><label>Email</label></td><td><input type="text" name="txtemail" value="${sessionScope.email}"></td></tr>
                    <tr><td><label>Password</label></td><td><input type="password" name="txtpassword" value="${sessionScope.password}"></td></tr>
                    <tr><td><label>Stayed Signed In</label></td><td colspan="2"><input class="check" type="checkbox" value="savelogin" name="savelogin"></td></tr>
                    <tr><td class="create" colspan="2"><input class="mybtn" type="submit" value="login" name="action"></td></tr>
                   
                </table>
            </form>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
