<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.*" %>
<% UserService userService = UserServiceFactory.getUserService(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<% if (userService.getCurrentUser() == null) { %>
		<p><a href="<%= userService.createLoginURL("/") %>">Se connecter</a></p>
	<% }
	else { %>
		<p>Bonjour <%= userService.getCurrentUser().getNickname() %></p>
		<a href="testjsp">SmartR</a>	
		<p><a href="<%= userService.createLogoutURL("/") %>">Se déconnecter</a></p>
	<% } %>

</body>
</html>