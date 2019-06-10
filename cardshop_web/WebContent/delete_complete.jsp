<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "java.sql.*" %>
<%@ page import= "DAO.CardsDAO" %>
<%@ page import= "java.util.ArrayList" %>
<%@ page import= "DTO.Card" %>
<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8"/>
    </head>
    <script type="text/javascript">
		onload = function(){
			setInterval(go,1000);
		};
		var x = 5;
		function go(){
			x --;
			if (x>=0){
				document.getElementById("sp").innerHTML=x+"秒後、トップページを戻ります。";
			}
			else{
				location.href = "Top.html";
			}
		}
		</script>
    <body>
    <%
    String code = request.getParameter("code");
    String name = request.getParameter("name");
    %>
     <center><b><font size=21><%= name %>(<%= code %>)を削除しました</font></center></b>
       <center><h2><div id = "sp"></div></h2></center>
       <p><center><image src="imgs/del_pic3.jpg" height="400" width="400"></center>
      </p>
      <p><center>
        <a style="font-weight:bold;
                  font-size:21pt;"
           href="Top.html">トップに戻る</a>
        </center></p>
    </body>
</html>