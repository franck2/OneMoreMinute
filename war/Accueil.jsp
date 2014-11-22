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
	
	<c:choose>
		<c:when test="${client.trajet.detail_Trajet==null }">
			<p>Aucun itinéraire de calculé, choisir une adresse de départ et une adresse d'arrivée pour avoir un itinéraire</p>
		</c:when>
		<c:otherwise>
			<p>Départ: ${client.trajet.heure_depart }</p>
			<p>Arrivée: ${client.trajet.heure_arrivee }</p>
			
			<div>${client.trajet.detail_Trajet}</div>
		</c:otherwise>
	</c:choose>

	<c:if test="${client.calendrier.heure_reveil==null }">
		<p>Aucun calendrier de choisi, en choisir un pour avoir un reveil</p>
	</c:if>

	<form action="/Compte" method="GET">
		<input type="submit" value="Modifier Compte">
	</form>

</body>
</html>