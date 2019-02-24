<%-- 
    Document   : test
    Created on : 27 Feb, 2016, 4:22:46 PM
    Author     : vijay
--%>

<%@page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String pass="6656";
            byte baa[] = pass.getBytes();
            String b64 = Base64.encode(baa);
            out.print(b64+"<br>");
            out.println(new String(Base64.decode(b64)));  
            %>
    </body>
</html>
