<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8"/>
    </head>
    <body>
    <%
	String code = request.getParameter("code");
    String name = request.getParameter("name");
	%>
     <h1><center><b><font size=18><%= name %> (<%= code %>)を削除してもいいですか？</font></center></b></h1>
      <p><center>
        <a style="font-weight:bold;
                  font-size:21pt;
                  color:red"
           href="servletcrl?select=del&code=<%=code %>&name=<%= name %>">はい</a>
        <a style="font-weight:bold;
                  font-size:21pt;
                  color:blue"
           href="card_list.jsp">いいえ</a>
      <p>
      <center><img src="imgs/del_pic1.jpg" height="400" width="400"></center>
      </p>
        </center></p>
    </body>
</html>