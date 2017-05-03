<%@ page language="java" 
          contentType="text/html; charset=ISO-8859-1" 
          pageEncoding="ISO-8859-1"%>
                 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.castaware.castabattle.domain.Board" %>
<%@page import="com.castaware.castabattle.domain.CellType" %>

<html>
<head>
<meta http-equiv="Content-Type" 
      content="text/html; charset=ISO-8859-1">
<title>Casta-Battle! v0.1-alpha</title>
</head>
<body>
	<h1>Casta-Battle!</h1>
	<hr />
	<%
		if (request.getAttribute("board") == null)
		{
			response.sendRedirect
			      (request.getContextPath()+"/spring/game/start");
		}
		else
		{
			Board board = (Board)request.getAttribute("board");
			out.print(board);
		}			
	%>
	<hr />
	<form action="${pageContext.request.contextPath}/spring/game/fire" method="get">
		<h4>
			Linha:
			<select name="line">
				<option value="1" selected>1
				<option value="2">2
				<option value="3">3
				<option value="4">4
				<option value="5">5
				<option value="6">6
				<option value="7">7
				<option value="8">8
				<option value="9">9
				<option value="10">10				
			</select>
			Coluna: 
			<select name="column">
				<option value="A" selected>A
				<option value="B">B
				<option value="C">C
				<option value="D">D
				<option value="E">E
				<option value="F">F
				<option value="G">G
				<option value="H">H
				<option value="I">I
				<option value="J">J				
			</select>
		</h4>
		<input type="submit" value="Fogo!">					
	</form>
</body>
</html>