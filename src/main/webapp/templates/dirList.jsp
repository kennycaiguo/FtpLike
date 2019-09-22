<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<File> dirs = (ArrayList<File>)request.getAttribute("dirs");
    ArrayList<File> files = (ArrayList<File>)request.getAttribute("files");
    String parentPath = (String)request.getAttribute("parent");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8' />
        <link rel="icon" href="https://icon-library.net/images/icon-folders/icon-folders-8.jpg">
        <!--TODO CSS, MAYBE...-->
        <title>File Tree View</title>
    </head>
    <body>
            <%
                out.println("<div class='back'>");
                if(parentPath != null){
                    out.println("<a href='/?path=" + parentPath +"'>");
                    out.println("<span>" + parentPath + "</span>");
                    out.println("</a>");
                }
                else {
                    out.println("<span> You are in root dir </span>");
                }
                out.println("</div><hr/>");
            %>
        <div class='files'>
            <div class='dir'>
                <%
                    for(File dir : dirs){
                        out.println("<a href='/?path=" + dir.getAbsolutePath() + "/'>");
                        out.println("<p class='dirname'>" + dir.getName() + "</p>");
                        out.println("</a>");
                    }
                %>
            </div>
            <div class='file'>
                <%
                    for(File file : files){
                        out.println("<p>" + file.getName() + "</p>");
                    }
                %>
            </div>
        </div>
    </body>
</html>