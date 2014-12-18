<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="http://www.supportduweb.com/page/js/flashobject.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
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
                   
                    <li><a href="#">Github</a></li>
                    <li><a href="#">Readme</a></li>
                 
                </ul> <!-- navbar-nav -->

                 <ul class="nav navbar-nav navbar-right">
                 
                 	<% if (userService.getCurrentUser() != null) { %>
						
		 			 <li>	
                         <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="fa fa-user"> Bienvenue! <%= userService.getCurrentUser().getNickname() %></span></a>
                            
                      </li>
                      <li>
                      <a href="<%= userService.createLogoutURL("/") %>" class="btn btn-default"><span class="glyphicon glyphicon-star" aria-hidden="true"></span>Déconnexion</a>
                      
                      </li>
                      
					<% 	} %>
                   
                   
                </ul>



            </div><!--navbar-collapse -->
        </div> <!--container -->
    </nav> <!-- navbar-time -->

    <!-- Carousel Home -->
    <div class="wrap-home-header">
        <div class="container">
            <div class="lcd">
               
                <section id="affiche-home" >
                  
                        <article class="item active">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="container">
                                         <div id="clock_2995" class=" col-sm-offset-5"> 
                                         
                                        
												<script type="text/javascript">
												var flashvars_2995 = {};
												var params_2995 = {
												quality: "high",
												wmode: "transparent",
												bgcolor: "#ffffff",
												allowScriptAccess: "always",
												};
												var attributes_2995 = {};
												flashObject("http://flash.supportduweb.com/horloges/horloge26.swf", "clock_2995", "150", "150", "8", false, flashvars_2995, params_2995, attributes_2995);
												</script>
                                         </div>
                                    </div>
                             
                                     
                                                <div class="container">
                                                	
                                                	<div class=" col-sm-offset-5 btncon">
                                                	<% if (userService.getCurrentUser() == null) { %>
														<a href="<%= userService.createLoginURL("/Accueil.jsp") %>"  class="btn btn btn-warning btn-lg">Click Here</a>
													<% }%>
                                                	</div>
                                                	
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


