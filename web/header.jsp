<%-- 
    Document   : header
    Created on : May 28, 2022, 9:36:05 PM
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
        <div id="topnavbar">
            <div id="textright">
                <ul id="nav1">    
                          <div class="search-btn">
                <form action="searchPlants" method="post" class="search-btn">
                    <input type="text" name="txtsearch" value="${(param.txtsearch==null)?"":param.txtsearch}">
                            <select name="txtsearchby">
                                 <option value="byname">by name</option>
                                 <option value="bycate">by category</option>
                            </select>
                                <input  class="search-icon"type="submit" value="search" name="action" >
                        </form>
                
            </div>
                            
                        <li><a href="registration.jsp" id="topbaritem">Register</a></li>
                        <li><a href="login.jsp" id="topbaritem">Login</a></li>
                </ul>
            </div>
        </div>
         <div id="wrapper">
       <div id="header">
            <ul id="nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="viewCart">View Cart</a></li>
            </ul>
          
        </div>
      
       
    </body>
</html>
