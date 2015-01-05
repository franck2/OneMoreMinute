<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>One More Minute</title>
    	<link href="css/heure.css" rel="stylesheet">
    	<link href="css/timeTo.css" rel="stylesheet">
</head>
<body>
	<div id="clock" class="dark">
		<div class="display">
        		<div class="weekdays"></div>
        		<div class="ampm"></div>
        		<div class="alarm"></div>
        		<div class="digits"></div>
       			<div class="cpt"> 
            		 <c:choose>
		<c:when test="${erreur!=null }">
			<p>Entrer le code: <strong>${id }</strong></p>
		</c:when>
		<c:when test="${heure_reveil!=null }">
			<p>Prochain réveil: <strong>${heure_reveil}</strong></p>
		</c:when>
		<c:otherwise>
			<p>Grasse matinée jusqu'a nouvel ordre !</p>
		</c:otherwise>
    </c:choose>
    <div id="countdown" align="center"></div>
    </div>
    </div>

    
</div>

<div class="zero">
    <span class="d1"></span>
    <span class="d2"></span>
    <span class="d3"></span>
    <span class="d4"></span>
    <span class="d5"></span>
    <span class="d6"></span>
    <span class="d7"></span>
</div>



<audio id="myAudio" src="musiques/${musique }.ogg" loop></audio>

            <input type="hidden" id="heure_reveil" name="heure_reveil" value ="${heure_reveil }"/>
    
	<form method="get" name="form" action="ServletReveil">
		<input type="hidden" id="id" name="id" value='${id }'>
	
		<input type="hidden" id="maj" name="maj" value="false">
	</form>
	<script src="js/jquery.js"></script>
	
	<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
	<script src="js/timeTo.js"></script>
	<script src="js/heure.js"></script>
	<script src="js/reveil.js"></script>
</body>
</html>
