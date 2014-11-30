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
                                <li><a href="<%= userService.createLogoutURL("/") %>"><i class="fa fa-pencil fa-fw"></i> Se déconnecter</a></li>
                                
                                <li class="divider"></li>
                                <li><a href="#"><i class="i"></i> Make admin</a></li>
                            </ul>
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
                                    
                                            <script type="text/javascript" src="http://www.supportduweb.com/page/js/flashobject.js"></script>
                                                <div id="clock_7450" style="display:inline-block;">
                                                    <a href="http://get.adobe.com/flashplayer/">You need to install the Flash plugin</a> - <a href="http://www.supportduweb.com/">http://www.supportduweb.com/</a>
                                                </div>
                                            <script type="text/javascript">
                                                    var flashvars_7450 = {};
                                                    var params_7450 = {
                                                        quality: "high",
                                                        wmode: "transparent",
                                                        bgcolor: "#ffffff",
                                                        allowScriptAccess: "always",
                                                    };
                                                    var attributes_7450 = {};
                                                    flashObject("http://flash.supportduweb.com/horloges/horloge07.swf", "clock_7450", "250", "250", "100", false, flashvars_7450, params_7450, attributes_7450);
                                            </script>




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


