<%-- 
    Document   : header_loginedUser
    Created on : Jun 1, 2022, 4:43:02 PM
    Author     : Hoang Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css" />
    </head>
    <body>
       <div id="wrapper">
       <div id="header1">
            <ul id="nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="dangxuat">Logout</a></li>
                <li><a href="changProfile.jsp">Change profile</a></li>
                 <li><a href="#">Orders</a>
                  <ul class="subnav">
                    <li><a href="completed">Completed </a></li>
                    <li><a href="canceled">Canceled </a></li>
                    <li><a href="order">Processing</a></li>
                 </ul>
                 </li>
                 <li class="search-btn">from<input type="date" name="from"> to <input type="date" name="to">
                <input class="search-icon" type="submit" value="search">
                </li>
            </ul>
       </div>
       </div>
    </body>
</html>
