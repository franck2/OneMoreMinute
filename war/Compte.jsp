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
    <script src="http://maps.googleapis.com/maps/api/js?libraries=places&amp;sensor=false&language=fr"></script>

	<form method="post" name="form" action="Compte" >
		<legend>Informations Temps</legend>
    	<label>Temps pour se laver : </label>
    	<input type="text" id="laver" name="laver" value=${client.laver} size="5" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour se maquiller : </label>
    	<input type="text" id="maquiller" name="maquiller" value=${client.maquiller} size="5" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour geeker : </label>
    	<input type="text" id="geeker" name="geeker" value=${client.geeker} size="5" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour manger : </label>
    	<input type="text" id="manger" name="manger" value=${client.manger} size="5" maxlength="5"/>
    	<br/>
    	
    	<label>Temps pour se lever : </label>
    	<input type="text" id="lever" name="lever" value=${client.lever} size="5" maxlength="5"/>
    	<br/>
    	
    	<label>Temps divers : </label>
    	<input type="text" id="divers" name="divers" value=${client.divers} size="5" maxlength="5"/>
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
		<label>Je veux voyager:</label><br/>
		<input type="radio" name="transport" id="voiture" value="voiture">
		<label>En voiture</label><br/>
		<input type="radio" name="transport" id="tan" value="tan">
		<label>Avec la Tan</label><br/>
		<input type="radio" name="transport" id="velo" value="velo">
		<label>En vélo</label><br/>
		<input type="radio" name="transport" id="pied" value="pied">
		<label>A pied</label>
		<br/><br/>
		<legend>Informations emploi du temps</legend>
		
        <label>Url de votre emploi du temps (au format ics)</label><br/>
        <input type="text" id="edt" name="edt" value="${client.calendrier.url}" size="50" maxlength="100" />
        <br/>
        <c:if test="${message_edt!=null }">
    	        <p>${message_edt}</p>
    	</c:if>
    	<br/>
        <label>Identifiant pour accéder à l'emploi du temps (ne rien mettre si il n'y en a pas besoin)</label><br/>
        <input type="text" id="user" name="user" value="${client.calendrier.user}" size="20" maxlength="20" /><br/>
        <label>Mot de passe pour accéder à l'emploi du temps (ne rien mettre si il n'y en a pas besoin)</label><br/>
        <input type="password" id="mdp" name="mdp" value="${client.calendrier.mdp}" size="20" maxlength="20" /><br/><br/>
        <input type="hidden" id="itineraire" name="itineraire" value =""/>
        <input type="hidden" id="enristrer" name="enregistrer" value ="enregistrer"/>
        <input type="button" value="enregistrer" onclick="calculate()"/>
	</form>
	<form method="get" action="Accueil">
		<input type="submit" id="retour" name="retour" value="retour"  />
	</form>


    <script>
    window.onload = function(){
    	jQuery('#${client.trajet.transport}').prop('checked', 'checked');
    }
    
	$('input[name="depart_pa"]').click(function(){
		jQuery('#depart').val(jQuery('label[for="'+jQuery(this).attr('id')+'"]').html());
	});		
	
	$('input[name="arrivee_pa"]').click(function(){
		jQuery('#arrivee').val(jQuery('label[for="'+jQuery(this).attr('id')+'"]').html());
	});

	
	</script>
	<script src="functions.js"></script>
</body>
</html>