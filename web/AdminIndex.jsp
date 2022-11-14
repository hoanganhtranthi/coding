<%-- 
    Document   : AdminIndex
    Created on : Jun 28, 2022, 9:09:56 PM
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
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <div class="container">
                <section class="welcome-sec" id="welcome-section">

                    <div class="welcome-part">

                        <div class="photo-div"><img src="images/3.jpg" alt="profile-picture" class="photo"/></div>
                        <h1>Welcome<span> ${sessionScope.name}</sp</h1>
                    <p>Nice to meet you</p>

                </div>
            </section>
        </div>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
