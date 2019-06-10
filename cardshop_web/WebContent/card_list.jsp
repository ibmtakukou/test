<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.sql.*" %>
<%@ page import= "DAO.CardsDAO" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "DTO.Card" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connect to Postgres Database</title>
</head>
<body>
<h2><center>データベースから情報一覧</center></h2>
<%
CardsDAO cdao = new CardsDAO();
ArrayList<Card> cards = cdao.findAll();
out.println("<center><table border = \"1\">");
out.println("<tr><td>コード</td><td>名前</td><td>レア</td><td>価格</td><td>操作</td></tr>");
for(Card c: cards) {
	out.println("<tr>");
	out.println("<td>"+c.getCode()+"</td><td><a href=\"servletcrl?select=detail&code="+c.getCode()+"\">"+c.getName()+"</a></td><td>"+c.getRare()+"</td><td>"+c.getPrice()+"</td><td><a href=\"delete_confirm.jsp?code="+c.getCode()+"&name="+c.getName()+"\">削除</td>");
	out.println("</tr>");
}
out.println("</table></center>");
/*
try {
    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String username = "postgres";
    Connection myConnection = null;
    Class.forName(driver);
    myConnection = DriverManager.getConnection(url,username,"");
    myConnection.setAutoCommit(false);
	String sql="select * from cards";
	PreparedStatement pstmt=(PreparedStatement) myConnection.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	out.print("<form method=\"POST/GET\">");
	out.print("<table border = \"1\">");
	out.print("<tr><th>コード</th><th>名前</th><th>レア</th><th>価格</th><th>操作</th></tr>");
    while (rs.next()){
    	out.print("<tr><th>");
        out.print(rs.getString(1)+"</th>"+"<th><a href=\"detailservlet?code="+rs.getString(1)+"\" value=\""+rs.getString(1)+"\">"+rs.getString(2)+"</a></th><th>"+rs.getString(3)+"</th><th>"+rs.getString(4)+"</th><th><a href=\"delete_confirm.jsp?code="+rs.getString(1)+"&name="+rs.getString(2)+"\">削除</a></th>");
    	out.print("</tr>");
    }
    out.print("</table>");
    out.print("</form>");
    System.out.print("Opened database successfully");
    }
	catch(ClassNotFoundException e){
	    e.printStackTrace();
	}
catch (SQLException ex) {
    out.print("SQLException: "+ex.getMessage());
    out.print("SQLState: " + ex.getSQLState());
    out.print("VendorError: " + ex.getErrorCode());
}
*/
%>
<h3><center><a href="Top.html">トップに戻る</a></center></h3>
</body>
</html>