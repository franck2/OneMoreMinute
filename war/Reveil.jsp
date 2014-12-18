<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" id="but">

<audio id="myAudio" src="musiques/${musique }.ogg"></audio>
	<c:choose>
		<c:when test="${erreur!=null }">
			<p>${erreur}</p>
			<p>Pour utiliser ce reveil entrez ce code: "${id }" dans votre compte.</p>
		</c:when>
		<c:when test="${heure_reveil!=null }">
			<p>Prochain réveil: ${heure_reveil}</p>
		</c:when>
		<c:otherwise>
			<p>Grasse matinée jusqu'a nouvel ordre !</p>
		</c:otherwise>
    </c:choose>
            <input type="hidden" id="heure_reveil" name="heure_reveil" value ="${heure_reveil }"/>
    
	<form method="post" name=form" action="ServletReveil">
		<input type="hidden" id="maj" value="false">
	
	</form>
	<script src="js/jquery.js"></script>
	<script src="js/reveil.js"></script>
</body>
</html>