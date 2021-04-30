<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%! int counter = 1; %>
<%! int yPosition = 0; %>
<%! int xPosition = 0; %>
<html>
	<head>

		<link rel="icon" type="images/png" href="images/icons/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="libs/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/game.css"/>

		<!-- jQuery and JS bundle w/ Popper.js -->
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
		<script type="text/javascript" src="libs/js/jquery-3.5.1.js"></script>

		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Juego Sopa de Letras</title>
	</head>
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<div class="col-11">
	    		<a class="navbar-brand" href="#">JAVAitOS</a>
			</div>
			<div class="col-1">
				<button type="button" class="btn btn-light">
					<a href="<c:url value='j_spring_security_logout' />">Logout</a>
			 	</button>
			</div>
	 	</nav>
	<body>
		<div class="div-message"></div>
			<div class="container margin-top-50">
				<div class="row">
					<div class="col-10">
				  	<h1>Sopa de letras</h1>
				  </div>
			 </div>
		  <div class="row">
		    <div class="col-2 margin-top-50">
           		<div>
            		<div class="font-weight-bold">Lista de palabras:</div>
					<div class="words-search margin-top-10"></div>
           		</div>
           	  </div>
			  <div class="col-7 margin-top-50">
			  	 <div id="puzzle">

			  	 </div>
			  </div>
           	  <div class="col-3">
           		<div class="div-word-selected">
           			<div class="font-weight-bold">La palabra seleccionada:</div>
           			<div class="word-selected margin-top-10"></div>
            	</div>
              </div>
			  
		  </div>
		</div>
	</body>
</html>
<script  type="text/javascript" src="./js/game.js"></script>