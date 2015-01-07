<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.appengine.api.users.*" %>
<% UserService userService = UserServiceFactory.getUserService(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="fr">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
    <title>One More Minute</title>
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
                <a class="navbar-brand" href="#">One More <span>Minute</span></a>
        	</header> <!-- navbar-header -->

			<div class="collapse navbar-collapse navbar-ex1-collapse" role="navigation">
            	<ul class="nav navbar-nav navbar-nav-time ">
                	<li class="active"><a href="Accueil">Accueil</a></li>
                	<li><a href="https://github.com/franck2/OneMoreMinute" target="_blank">Github</a></li>
                    <li><a href="readme.jsp">Readme</a></li>
				</ul> <!-- navbar-nav -->
				<div class="nav navbar-nav navbar-right">
               		<% if (userService.getCurrentUser() != null) { %>
						<span class="user"> Bienvenue! <%= userService.getCurrentUser().getNickname() %></span>
						<span><a href="<%= userService.createLogoutURL("/") %>">Déconnexion</a></span>
					<% } %>
				</div>
        	</div><!--navbar-collapse -->
        </div> <!--container -->
    </nav> <!-- navbar-time -->

	<!-- Carousel Home -->
	<div class="wrap-home-header2">
		<div class="container">
			<div class="lcd">
				<section id="affichehome" >
		            <article class="item active">
						<div class="row">
        	       			<div class="col-md-12">
                            	<c:choose>
									<c:when test="${client.trajet.detail_Trajet==null }">
										<p>Aucun itinéraire de calculé, choisir une adresse de départ et une adresse d'arrivée pour avoir un itinéraire</p>
									</c:when>
									<c:when test="${client.calendrier.date_reveil==null }"> 
										<p>Aucun calendrier de choisi, en choisir un pour avoir un reveil</p>
									</c:when>
									<c:otherwise>
										<div class="panel panel-info ">
											<div class="panel-heading">
			  									<div class="row">
			  										<div class="col-md-6"><p>Départ: ${client.trajet.heure_depart }</p></div>
			                        				<div class="col-md-6"><p>Arrivée: ${client.trajet.heure_arrivee }</p></div>
												</div>
											</div>
											<div class="panel-body itineraire">
												<div class="trajet">${client.trajet.detail_Trajet}</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
                			</div>
						</div>
                        <div class="row">
                        	<div class="col-md-12">
                            	<form action="/Compte" method="GET">
									<button type="submit" class="btn btn-success">Modifier Compte</button>
								</form>
							</div>
                        </div>
                	</article>
				</section> <!-- affiche-home -->
        	</div> <!-- lcd -->
        </div> <!-- container -->
    </div> <!-- wrap-header-home -->

    <footer id="footer">
       	<div class="container">
    	   	<p>Web & Cloud 2014-2015</p>
        </div>
    </footer>
    
     <script src="js/jquery.js"></script>
     <script src="js/bootstrap.js"></script>

</body>
</html>