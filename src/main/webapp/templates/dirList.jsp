<%@ page import="java.io.File" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<File> dirs = (ArrayList<File>)request.getAttribute("dirs");
    ArrayList<File> files = (ArrayList<File>)request.getAttribute("files");
    String parentPath = (String)request.getAttribute("parent");
    String URI = (String)request.getAttribute("uri");
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
                    out.println("<a href='" + URI + "?path=" + parentPath +"'>");
                    out.println("<span>" + parentPath + "</span>");
                    out.println("</a>");
                }
                else {
                    out.println("<span> You are in root dir </span>");
                }
                out.println("</div><hr/>");
            %>
        <div class='allfiles'>
            <div class='dirs'>
                <%
                    for(File dir : dirs){
                        out.println("<div class='dir'><a href='" + URI + "?path=" + dir.getAbsolutePath() + "'>");
                        out.println("<p class='dirname'>" + dir.getName() + "</p>");
                        out.println("</a></div>");
                    }
                %>
            </div>
            <div class='files'>
                <%
                    for(File file : files){
                        out.println("<div class='file'><p>" + file.getName() + "</p></div>");
                    }
                %>
            </div>
        </div>
    </body>
</html>