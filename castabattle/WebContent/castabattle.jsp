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
	<c:if test="${pBoard != null}">
		<table style="width:100%" border="1">
			<tr>
				<th>
					<c:forEach var="i" begin="0" end="9">
						<p>
						<c:out value="${i+1}"></c:out>
							<c:forEach var="j" begin="0" end="9">
										<c:out value="${pBoard.getBoardGame()[i][j] }"></c:out>
							</c:forEach>
						</p>
					</c:forEach>
				</th>
			
			<th>
		
				<c:forEach var="i" begin="0" end="9">
					<p>
					<c:out value="${i+1}"></c:out>
						<c:forEach var="j" begin="0" end="9">
									<c:out value="${eBoard.getBoardGame()[i][j]}"></c:out>
						</c:forEach>
					</p>
				</c:forEach>
				</th>
			</tr>
			
			<tr>
				<th> Enemy's Board (Player's Fleet)</th>
				<th> Player's Board (Enemy's Fleet) </th>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${pBoard == null}">
		<c:if test="${mensagem == 'GAME OVER! VOCÊ VENCEU!'}">
			<img alt="castaBattleWin" src="<c:url value='../../pswebproj/rf.jpg' />" style="width:128px;height:128px;" />
		</c:if>
		<c:if test="${mensagem != 'GAME OVER! VOCÊ VENCEU!'}">	
			<img alt="castaBattleLose" src="<c:url value='../../pswebproj/rf2.jpg' />" style="width:128px;height:128px;" />
		</c:if>
	</c:if>
	
	<p>
		Mensagem: <c:out value="${mensagem}"></c:out>
	</p>
	
	
	
	<%/*
		if (request.getAttribute("eBoard") == null)
		{
			response.sendRedirect
			      (request.getContextPath()+"/spring/game/start");
		}
		else
		{
			//Enemy's Board
			Board eBoard = (Board)request.getAttribute("eBoard");
			out.print(eBoard);
			
			//Player's Board
			Board pBoard = (Board)request.getAttribute("pBoard");
			out.print(pBoard);
		}		*/	
	%>
	<hr />
	<c:if test="${pBoard != null}">
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
					<option value="A" selected>A-1
					<option value="B">B-2
					<option value="C">C-3
					<option value="D">D-4
					<option value="E">E-5
					<option value="F">F-6
					<option value="G">G-7
					<option value="H">H-8
					<option value="I">I-9
					<option value="J">J-10			
				</select>
			</h4>
			<input type="submit" value="Fogo!">					
		</form>
	</c:if>
</body>
</html>