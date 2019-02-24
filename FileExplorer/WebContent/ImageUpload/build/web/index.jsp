<%-- 
    Document   : index
    Created on : 27 Feb, 2016, 3:47:32 PM
    Author     : vijay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="width:968px;margin: 0 auto;">
        <form action="uploadimage" method="POST" enctype="multipart/form-data">
            <fieldset>
                <legend>File Upload</legend>
                <input type="file" name="file" />
                <input type="submit" value="Upload" />
            </fieldset>
        </form>
    </body>
</html>
