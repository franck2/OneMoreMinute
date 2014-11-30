<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="com.google.appengine.api.users.*" %>
    <% UserService userService = UserServiceFactory.getUserService(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>One More Time</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="shortcut icon" type="image/x-icon" href="">
    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="css/font-awesome.css">

    <link href="css/animations.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
    

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
</head>
<body>
	<!-- navbar navbar-time -->
    <nav class="navbar navbar-time navbar-static-top">
        <div class="container">
            <header class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">One More <span>Time</span></a>
            </header> <!-- navbar-header -->

           	<div class="collapse navbar-collapse navbar-ex1-collapse" role="navigation">
                <ul class="nav navbar-nav navbar-nav-time ">
                    <li class="active"><a href="#">HOME</a></li>
                    <li><a href="#">Team</a></li>
                    <li><a href="#">Readme</a></li>
                 
                </ul> <!-- navbar-nav -->

                 <ul class="nav navbar-nav navbar-right">
                 
                 
                 	<% if (userService.getCurrentUser() == null) { %>
						<p><a href="<%= userService.createLoginURL("/") %>">Se connecter</a></p>
					<% }
					else { %>
		
		 				<li>
                        
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="fa fa-user"> Bienvenue! <%= userService.getCurrentUser().getNickname() %><b class="caret"></b></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="Accueil"><i class="fa fa-pencil fa-fw"></i> SmartR</a></li>
                                <li><a href="<%= userService.createLogoutURL("/") %>"><i class="fa fa-pencil fa-fw"></i> Se d�connecter</a></li>
                                
                                <li class="divider"></li>
                                <li><a href="#"><i class="i"></i> Make admin</a></li>
                            </ul>
                      </li>
					<% 	} %>
                   
                   
                </ul>



            </div><!--navbar-collapse -->
        </div> <!--container -->
    </nav> <!-- navbar-time -->


	<div class="wrap-home-header">
        <div class="container">
            <div class="lcd">
               
                <section id="affiche-home" >
                  
                  <article class="item active">

  
		 			<form id="main-contact-form"  method="post" name="form" action="Compte"" role="form" >
		 				<div class="row">
		 					<div class="col-sm-6">
		 						<div class="panel panel info">
		 							<div class="panel-heading">gestion du temps</div>
		 							<div class="panel-body">
		 							
		 							<div class="form-group">
      									<label class="col-sm-6 control-label">Temps pour le bain</label>
      									<div class="col-sm-6">
         									<input type="text" id="laver" name="laver" class="form-control"  value=${client.laver}>
      									</div>
   									</div>
		 							<div class="form-group">
      									<label class="col-sm-6 control-label">Temps pour se maquiller</label>
      									<div class="col-sm-6">
         									<input type="text" id="maquiller" name="maquiller" value=${client.maquiller}  class="form-control" >
      									</div>
   									</div>
   									<div class="form-group">
      									<label class="col-sm-6 control-label"> Temps pour geeker</label>
      									<div class="col-sm-6">
         									<input type="text" id="geeker" name="geeker" value=${client.geeker} class="form-control" >
      									</div>
   									</div>
   									<div class="form-group">
      									<label class="col-sm-6 control-label"> temps pour manger</label>
      									<div class="col-sm-6">
         									 <input type="text" id="manger" name="manger" value=${client.manger} class="form-control" >
      									</div>
   									</div>
   									<div class="form-group">
      									<label class="col-sm-6 control-label">lever</label>
      									<div class="col-sm-6">
         									 <input type="text" id="lever" name="lever" value=${client.lever} class="form-control" >
      									</div>
   									</div>
   									<div class="form-group">
      									<label class="col-sm-6 control-label">divers</label>
      									<div class="col-sm-6">
         									<input type="text" id="divers" name="divers" value=${client.divers} class="form-control" >
      									</div>
   									</div>
   									
		 							</div>
		 						</div>
		 					
		 					
		 					</div>
		 					
		 				
		 					
		 			<div class="col-sm-6">
		 				<div class="row">
		 					<div class="col-sm-12">
		 						<div class="panel panel info">
		 							<div class="panel-heading">Informations Trajet</div>
		 							<div class="panel-body">
		 							
		 							<div class="form-group">
      									<label class="col-sm-2 control-label">D�part</label>
      									<div class="col-sm-10">
         									<input type="text" id="depart" name="depart" value="${client.trajet.nom_depart}" class="form-control" >
      									</div>
   									</div>
		 							<div class="form-group">
      									<label class="col-sm-2 control-label">Destination</label>
      									<div class="col-sm-10">
         									<input type="text" id="arrivee" name="arrivee" value="${client.trajet.nom_arrivee}" class="form-control" >
      									</div>
   									</div>
   									
   									<div class="form-group">
      									 <c:if test="${message_depart!=null }">
    	        							<p>${message_depart}</p>
    									</c:if>
   									</div>
   									<div class="form-group">
      									<c:if test="${message_trajet!=null }">
    	        							<p>${message_trajet}</p>
    									</c:if>
   									</div>
		 							</div>
		 						</div>
		 					
		 					
		 					</div>
		 				</div>
		 				
		 				<div class="row">
		 					<div class="col-sm-12">
		 						<div class="panel panel info">
		 							<div class="panel-heading">Je veux voyager</div>
		 							<div class="panel-body">
		 							
		 								 <label class="checkbox-inline">
      										<input type="radio" name="transport" id="voiture" value="voiture"> En Voiture
   										</label>
   										 <label class="checkbox-inline">
      										<input type="radio" name="transport" id="velo" value="velo">A V�lo
   										</label>
   										 <label class="checkbox-inline">
      										<input type="radio" name="transport" id="velo" value="velo"> Par la TAN
   										</label>
   										 <label class="checkbox-inline">
      										<input type="radio" name="transport" id="pied" value="pied"> A Pied
   										</label>
   
		 							
		 							</div>
		 						</div>
		 					
		 					
		 					</div>
		 				</div>
		 				
		 				
		 				
		 					
		 			</div>	
		 					
		 							
		 	</div>
		 					        
           <div class="row">
              <div class="col-sm-12">
                 <div class="panel panel info">
		 			<div class="panel-heading">Je veux voyager</div>
		 			<div class="panel-body">
		 			<div class="row">
		 				 <div class="col-sm-12">
		 				 
		 				 <div class="form-group">
   							 <label for="name">lien emploi du temps</label>
   							 <input type="text" class="form-control"  id="edt" name="edt" value="${client.calendrier.url}">
 						 </div>
		 				 </div>
		 			</div>
		 			<div class="row">
		 				 <div class="col-sm-6">
		 				 	<div class="form-group">
      							<label class="col-sm-8 control-label">Nom utilisateur</label>
      							<div class="col-sm-4">
         							<input type="text" id="user" name="user" value="${client.calendrier.user}" class="form-control" >
      							</div>
   							</div>
		 				 </div>
		 				 <div class="col-sm-6">
		 				 
		 				 <div class="form-group">
      							<label class="col-sm-8 control-label">Mot de passe pour acc�der � l'emploi du temps (ne rien mettre si il n'y en a pas besoin)</label>
      							<div class="col-sm-4">
         							<input type="text" id="mdp" name="mdp" value="${client.calendrier.mdp}" class="form-control" >
      							</div>
   						</div>
		 				 
		 				 </div>
		 			</div>
		 			
		 			<div class="row">
		 				 <div class="col-sm-12">
		 				
		 				 	<c:if test="${message_edt!=null }">
    	        				<p>${message_edt}</p>
    						</c:if>
		 				 </div>
		 			</div>
		 			
		 			
		 			</div>
                                   	
                                   
                  </div>
                 </div>
                
                </div>
                 <div class="row">
		 			<div class="col-sm-12">
		 				<input type="button" value="Calculer l'itin�raire" onclick="javascript:calculate()">
		 			</div>
		 		</div>         
               
              </form>
			</article>
		</section>
	</div>
	</div>
</div>
		
<footer id="footer">
        <div class="container">
            <p>Web & Cloud 2014-2015</p>
        </div>
    </footer>		
									
		
	<!--
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
    	
    	<label>D�part : </label>
    	<input type="text" id="depart" name="depart" value="${client.trajet.nom_depart}" size="20" maxlength="100"/>
    	
    	<c:if test="${message_depart!=null }">
    	        <p>${message_depart}</p>
    	</c:if>

    	<br/>
    	<label>Arriv�e : </label>
    	<input type="text" id="arrivee" name="arrivee" value="${client.trajet.nom_arrivee}" size="20" maxlength="100"/>
    	<c:if test="${message_arrivee!=null }">
    	        <p>${message_arrivee}</p>
    	</c:if>
    	
    	<c:if test="${message_trajet!=null }">
    	        <p>${message_trajet}</p>
    	</c:if>

		<br/><br/>
		<label>Je veux voyager:</label><br/>
		<input type="radio" name="transport" id="voiture" value="voiture">
		<label>En voiture</label><br/>
		<input type="radio" name="transport" id="tan" value="tan">
		<label>Avec la Tan</label><br/>
		<input type="radio" name="transport" id="velo" value="velo">
		<label>En v�lo</label><br/>
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
        <label>Identifiant pour acc�der � l'emploi du temps (ne rien mettre si il n'y en a pas besoin)</label><br/>
        <input type="text" id="user" name="user" value="${client.calendrier.user}" size="20" maxlength="20" /><br/>
        <label>Mot de passe pour acc�der � l'emploi du temps (ne rien mettre si il n'y en a pas besoin)</label><br/>
        <input type="password" id="mdp" name="mdp" value="${client.calendrier.mdp}" size="20" maxlength="20" /><br/><br/>
        <input type="hidden" id="itineraire" name="itineraire" value =""/>
        <input type="hidden" id="enristrer" name="enregistrer" value ="enregistrer"/>
        <input type="button" value="enregistrer" onclick="calculate()"/>
	</form>
	<form method="get" action="Accueil">
		<input type="submit" id="retour" name="retour" value="retour"  />
	</form>
 -->	

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
	  <script src="http://maps.googleapis.com/maps/api/js?libraries=places&amp;sensor=false&language=fr"></script>
	<script src="functions.js"></script>
	 <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</body>
</html>