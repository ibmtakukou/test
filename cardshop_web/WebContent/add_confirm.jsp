<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>confirm</title>
</head>
<body>
<h2><center>入力した情報を確認してください</center></h2>
<form action="servletcrl?select=add" method = "POST">
<center>
<table border = "1">
<tr>
	<td>コード</td><td>名前</td><td>レア</td><td>価格</td>
</tr>
<%
String code = request.getParameter("code");
String name = request.getParameter("name");
String rare = request.getParameter("rare");
String price = request.getParameter("price");
%>
<tr>

	<td><%= code %></td>
	<td><%= name %></td>
	<td><%= rare %></td>
	<td><%= price %></td>
</tr>
</table>
</center>
<center>
<a href="add_input.html">修正</a>
<a href="servletcrl?select=add&code=<%=code %>&name=<%=name %>&rare=<%=rare%>&price=<%=price%>">確認 </a>
</center>
</form>
</body>
</html>