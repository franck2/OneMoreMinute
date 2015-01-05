<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<body>
	<!-- navbar navbar-time -->
	<nav class="navbar navbar-time navbar-fixed-top">
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
		                	<li class="active"><a href="Accueil">Accueil</a></li>
		                   	<li><a href="https://github.com/franck2/OneMoreMinute" target="_blank">Github</a></li>
		                   	<li><a href="readme.jsp">Readme</a></li>
		                 
		                </ul> <!-- navbar-nav -->
		
				<div class="nav navbar-nav navbar-right">
		                 	
					<% if (userService.getCurrentUser() != null) { %>
						<span class="user"> Bienvenue! <%= userService.getCurrentUser().getNickname() %></span></a>
						<span></span><a href="<%= userService.createLogoutURL("/") %>">Déconnexion</a></span>
					<% } %>
		                 		
                		</div>
			</div><!--navbar-collapse -->
        	</div> <!--container -->
	</nav> <!-- navbar-time -->


	<div class="wrap-home-header">
        	<div class="container">
        		<div class="lcd">
                		<section id="affiche-home" >
					<article class="item active">
						<form id="main-contact-form"  method="post" name="form" action="Compte" role="form" >
		 					<div class="row">
		 						<div class="col-sm-12">
		 							<div class="panel panel-info">
		 								<div class="panel-heading">Gestion du temps(en minutes)</div>
		 								<div class="panel-body">
		 									<div class="row">
		 										<div class="form-group col-sm-6">
      													<label class="col-sm-6 control-label">Temps pour se laver</label>
      													<div class="col-sm-6">
         													<input type="text" id="laver" name="laver" class="form-control"  value=${client.laver}>
      													</div>
   												</div>
		 										<div class="form-group col-sm-6">
      													<label class="col-sm-6 control-label">Temps pour se maquiller</label>
      													<div class="col-sm-6">
         													<input type="text" id="maquiller" name="maquiller" value=${client.maquiller}  class="form-control" >
      													</div>
   												</div>
   											</div>
											<div class="row">	
   												<div class="form-group col-sm-6">
      													<label class="col-sm-6 control-label"> Temps pour geeker</label>
      													<div class="col-sm-6">
         													<input type="text" id="geeker" name="geeker" value=${client.geeker} class="form-control" >
      													</div>
	      											</div>
	   											<div class="form-group col-sm-6">
	      												<label class="col-sm-6 control-label"> Temps pour manger</label>
	      												<div class="col-sm-6">
	         												<input type="text" id="manger" name="manger" value=${client.manger} class="form-control" >
	      												</div>
	   											</div>
	   										</div>
	   										<div class="row">
	   											<div class="form-group col-sm-6">
	      												<label class="col-sm-6 control-label">Temps pour se lever</label>
	      												<div class="col-sm-6">
	         												 <input type="text" id="lever" name="lever" value=${client.lever} class="form-control" >
	      												</div>
	   											</div>
	   											<div class="form-group col-sm-6">
	      												<label class="col-sm-6 control-label">Divers</label>
	      												<div class="col-sm-6">
	         												<input type="text" id="divers" name="divers" value=${client.divers} class="form-control" >
	      												</div>
	   											</div>  
	   										</div>
	   									</div>
		 							</div>
		 						</div>
		 									
		 						<div class="col-sm-12">
		 							<div class="row">
		 								<div class="col-sm-12">
		 									<div class="panel panel-info">
		 										<div class="panel-heading">Informations Trajet</div>
		 										<div class="panel-body">
		 											<div class="row">
		 												<div class="form-group col-sm-6">
      															<label class="col-sm-2 control-label">Départ</label>
      															<div class="col-sm-10">
         															<input type="text" id="depart" name="depart" value="${client.trajet.nom_depart}" class="form-control" >
      															</div>
   									
   															<label class="msgerror">
	      									 						<c:if test="${message_depart!=null }">
	    	        														<p>${message_depart}</p>
	    															</c:if>
   															</label>
   														</div>
			 											<div class="form-group col-sm-6">
	      														<label class="col-sm-2 control-label">Arrivée</label>
	      														<div class="col-sm-10">
	         														<input type="text" id="arrivee" name="arrivee" value="${client.trajet.nom_arrivee}" class="form-control" >
	      														</div>
	   									
	   														<div class="msgerror">
		      														<c:if test="${message_arrivee!=null }">
							    	        								<p>${message_arrivee}</p>
							    									</c:if>
	   														</div>
   														</div>
   													</div>
	   												<div class="row">
	   													<label class="col-sm-12 msgerror">
						      									<c:if test="${message_trajet!=null }">
						    	        								<p>${message_trajet}</p>
						    									</c:if>
					   									</label>
	   												</div>
		 										</div>
		 									</div>
		 								</div>
		 							</div>
		 							<div class="row">
		 								<div class="col-sm-12">
		 									<div class="panel panel-info">
		 										<div class="panel-heading">Je veux voyager</div>
		 										<div class="panel-body">
		 											<label class="checkbox-inline">
      														<input type="radio" name="transport" id="voiture" value="voiture"> En Voiture
   													</label>
   										 			<label class="checkbox-inline">
      														<input type="radio" name="transport" id="velo" value="velo">A Vélo
   													</label>
   													<label class="checkbox-inline">
			      											<input type="radio" name="transport" id="tan" value="tan"> Avec la TAN
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
                 							<div class="panel panel-info">
		 								<div class="panel-heading">Informations emploi du temps</div>
							 			<div class="panel-body">
								 			<div class="row">
								 				 <div class="form-group col-sm-6">
						   							 <label class="col-sm-5 control-label">lien emploi du temps</label>
						   							 <div class="col-sm-7">
						   							 	<input type="text" class="form-control"  id="edt" name="edt" value="${client.calendrier.url}">
						 						 	</div>
						 						 </div>
		 									</div>
		 									<div class="row">
						 				 		<div class="form-group col-sm-6">
				      									<label class="col-sm-5 control-label">Nom utilisateur</label>
				      									<div class="col-sm-7">
				         									<input type="text" id="user" name="user" value="${client.calendrier.user}" class="form-control" >
				      									</div>
				   								</div>
		 				 
		 										 <div class="form-group col-sm-6">
						      							<label class="col-sm-5 control-label">Mot de passe</label>
						      							<div class="col-sm-7">
						         							<input type="password" id="mdp" name="mdp" value="${client.calendrier.mdp}" class="form-control" >
						      							</div>
						   						</div>
											</div>
							 			
		 									<div class="row">
		 										<div class="col-sm-12 msgerror">
								 				 	<c:if test="${message_edt!=null }">
							    	        					<p>${message_edt}</p>
							    						</c:if>
							 					 </div>
							 				</div>
		 			
		 			
		 								</div>
                                   	
                                   
                 							</div>
                						</div>
					                        <input type="hidden" id="itineraire" name="itineraire" value =""/>
					                        <input type="hidden" id="enristrer" name="enregistrer" value ="enregistrer"/>
                
                					</div>
                
                					<div class="row">
                						<div class="col-sm-12">
		 							<div class="panel panel-info">
		 								<div class="panel-heading">Parametrage du Réveil</div>
		 								<div class="panel-body">
		 									<div class="row">
		 										<div class="form-group col-sm-6">
      													<label class="col-sm-5 control-label">ID du Réveil</label>
      													<div class="col-sm-7">
         													<input type="text" id="reveil" name="reveil" class="form-control"  value=${client.reveil }>
      													</div>
   												</div>
		 										<div class="form-group col-sm-6">
      													<label class="col-sm-5 control-label">Votre Sonnerie</label>
      													<div class="col-sm-7">
         													<select id="musique" name="musique" class="form-control">
															<option selected>dragon ball
															<option>fire-emblem-theme
															<option>Game_Of_Thrones
															<option>gerudo
															<option>hyrule
															<option>Marche imperiale
															<option>smash bros brawl
															<option>star wars
															<option>Stargate SG1
															<option>tetris
															<option>the legend of zelda
															<option>The walking dead
														</select>      
      													</div>
   												</div>
   											</div>
		 								</div>
		 							</div>
		 						</div>
                       					</div>
							<div class="row">
					 			<div class="col-sm-12">
					 				<button type="button" class="btn btn-info" onclick="javascript:calculate()">enregistrer</button>
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
 	<script>

    
   		window.onload = function(){ 
		 	jQuery('#${client.trajet.transport}').prop('checked', 'checked');

		    	comboBox=document.getElementById("musique");
		        if (comboBox) {
		            for(var i=0;i<=comboBox.length-1;i=i+1) {
		                var text=comboBox.options[i].value;
		                if('${client.musique}'==text){
		                    comboBox.selectedIndex=i;
		                    break;
		                }
		            }
		        }
		}
	
	</script>
	<script src="http://maps.googleapis.com/maps/api/js?libraries=places&amp;sensor=false&language=fr"></script>
	<script src="functions.js"></script>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>
