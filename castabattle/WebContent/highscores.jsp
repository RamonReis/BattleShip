<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HighScores</title>
</head>
<body>
	<div id="main" style="display: inline-table; text-align: center;">
		<div style="border-left: 0px">
			<!-- Player -->
			<h2>Player's HighScores</h2>
			<table border="1" style="width: 15%;">
				<c:forEach var="row" begin="0" end="${highscores}">
					<td>
						<c:out value = "${jogador}"/>
						<c:out value = "${pontos}"/>
					</td>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>