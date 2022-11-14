<%-- 
    Document   : changProfile
    Created on : Jun 10, 2022, 4:15:32 PM
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
        <header>
            <c:import url="header_loginedUser.jsp"/>
        </header>
        <section>
            <div class="content">
                <h3>Welcome ${sessionScope.name} Come Back</h3>
            <div class="change">
            <form action="changeProfile" method="post" class="formlogin">
                <h1> Change Profile </h1>
                <table class="profile">
                    <tr>
                        <td> <label>Email: ${sessionScope.email}</label></td></tr>
                    <tr> <td><label> Full Name</label></td><td><input type="text" name="txtname" required="" value="${(requestScope.txtfullname==null)?"":rerequestScope.txtfullname}" ></td></tr>
                    <tr> <td><label> Phone</label></td><td><input type="text" name="txtphone" value="${(requestScope.txtphone==null)?"":requestScope.txtphone}" required="" >
                            </br><font style='color:red'> ${(requestScope.ERROR==null)?"":requestScope.ERROR}</font></td></tr>
                    <tr><td class='save' colspan="2"><input  class="mybtn" type="submit" value="Save" name="action"></td></tr>
                </table>
            </form>
            </div>
            </div>
                     </section>
            <footer>
                <%@include file="footer.jsp" %>
            </footer>
    </body>
</html>
