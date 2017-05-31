<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CREATE YOUR BOARD!</title>
</head>
<h1>Create Your Own Board!</h1>
<body>
	<input type="hidden" id="bSize" value="${bSize}">
	<form id="bForm"
		action="${pageContext.request.contextPath}/spring/game/start"
		method="post">
		<div id="main" style="display: inline-table; text-align: center;">
			<div style="border-left: 0px">
				<!-- Player -->
				<h2>Player's Board</h2>
				<table border="1" style="width: 15%;">
					<c:forEach var="i" begin="0" end="${bSize}">
						<tr>
							<c:forEach var="j" begin="0" end="${bSize}">
								<td><input type="checkbox" name="0_${i}_${j}"></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<!-- /Player -->
			</div>

			<div style="float: right;">

				<!-- CPU -->
				<h2>CPU's Board</h2>
				<table border="1" style="width: 15%;">
					<c:forEach var="i" begin="0" end="${bSize}">
						<tr>
							<c:forEach var="j" begin="0" end="${bSize}">
								<td><input type="checkbox" name="1_${i}_${j}"></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<!-- /CPU -->
			</div>
		</div>
		<br />
		<br />  
		
		<input type="submit" value="Create Board & Start!" />
	</form>
</body>
</html>