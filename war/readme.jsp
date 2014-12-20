<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.appengine.api.users.*" %>
<% UserService userService = UserServiceFactory.getUserService(); %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="fr">
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
                
                <% if (userService.getCurrentUser() != null) { %>
							 <li ><a href="Accueil.jsp">Accueil</a></li>
							  <li><a href="https://github.com/franck2/OneMoreMinute" target="_blank">Github</a></li>>
                    		 <li><a href="readme.jsp">Readme</a></li>	
				<% }else { %>
                			
						<li><a href="https://github.com/franck2/OneMoreMinute">Github</a></li>>
                    	<li><a href="readme.jsp">Readme</a></li>
                    	<li ><a href="<%= userService.createLoginURL("/Accueil.jsp") %>">Sign In</a></li>	
                
                
                 <% } %> 
                    
                 
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

    <!-- Carousel Home -->
    <div class="wrap-home-header2">
        <div class="container">
            <div class="lcd">
               
                <section id="affichehome" >
                  
                        <article class="item active">
                            <div class="row">
                                <div class="col-md-12">
                                 <div class="container readme">
                                	<p>Cette application permet....</p>
                                	
                                	<p>Elle a été réalisée Par:
                                	<ul>
                                	<li>Franck Boncler</li>
                                	<li>Damin Mausson</li>
                                	<li>Roland Houssou</li>
                                	</ul>
                                	
                                	</p>
                                
                           </div>
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


    