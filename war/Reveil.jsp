<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" id="but">

<audio id="myAudio" src="Game_Of_Thrones.ogg"></audio>
	<c:if test="${erreur!=null }">
		<p>${erreur}</p>
	<c:if>
	<c:if test="${client!=null }">
		<p>Prochain réveil: ${heure_reveil}</p>
        <input type="hidden" id="heure_reveil" name="heure_reveil" value ="${heure_reveil }"/>
	<c:if>
	<form method="post" name=form" action="ServletReveil">
		<input type="hidden" id="maj" value="false">
	
	</form>
	<script src="js/jquery.js"></script>
	<script src="js/reveil.js"></script>
</body>
</html>