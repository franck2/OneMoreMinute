<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Données du compte</title>
	<script type="text/javascript" src="/jquery-2.1.1.min.js"></script>

</head>
<body>

	<form method="get" action="Compte">
		<legend>Informations Temps</legend>
    	<label>Temps pour se laver : </label>
    	<input type="text" id="laver" name="laver" value=${client.douche} size="20" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour manger : </label>
    	<input type="text" id="manger" name="manger" value=${client.manger} size="20" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour se lever : </label>
    	<input type="text" id="lever" name="lever" value=${client.lever} size="20" maxlength="5"/>
    	<br/><br/>
    	
    	<legend>Informations Trajet</legend><br/>
    	
    	<label>Départ : </label>
    	<input type="text" id="depart" name="depart" value="${client.trajet.nom_depart}" size="20" maxlength="100"/>
    	
    	<c:if test="${message_depart!=null }">
    	        <p>${message_depart}</p>
    	</c:if>

    	<br/>
    	<label>Arrivée : </label>
    	<input type="text" id="arrivee" name="arrivee" value="${client.trajet.nom_arrivee}" size="20" maxlength="100"/>
    	<c:if test="${message_arrivee!=null }">
    	        <p>${message_arrivee}</p>
    	</c:if>

		<br/><br/>
		
		<input type="checkbox" id="handicape" name="handicape" value="handicape" />
		<label for="handicape">J'ai besoin d'un accès handicapé pour mon transport</label>

        <br/><br/>
        <input type="submit" id="enregistrer" name="enregistrer" value="enregistrer"  />
	</form>
	<form method="get" action="Accueil">
		<input type="submit" id="retour" name="retour" value="retour"  />
	</form>
    
    <script>
	$('input[name="depart_pa"]').click(function(){
		jQuery('#depart').val(jQuery('label[for="'+jQuery(this).attr('id')+'"]').html());
	});		
	
	$('input[name="arrivee_pa"]').click(function(){
		jQuery('#arrivee').val(jQuery('label[for="'+jQuery(this).attr('id')+'"]').html());
	});
	
	$(function(){
		if(${client.trajet.handicape}){
			document.getElementById('handicape').checked=true;
		}
		
	});
	
	</script>
</body>
</html>