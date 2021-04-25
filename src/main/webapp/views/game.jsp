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
	</head>
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

<!-- mirar este ejemplo https://github.com/bunkat/wordfind -->
<script type="text/javascript">
	var selectedSquares = [];
	var arrayHorizontal = [];
	var arrayVertical = [];
	var xLocationFirstLetter = '';
	var yLocationFirstLetter = '';
	var moveVertical = true;
	var moveHorizontal = true;
	var wordSelected = "";
	var renderButtonDelete = true;
	var gameId;
	var ldapUser;
	
	var startTurn = function (ev) {
		if (xLocationFirstLetter == ''){
			xLocationFirstLetter = ev.currentTarget.attributes.x.value;
			yLocationFirstLetter = ev.currentTarget.attributes.y.value;
			arrayHorizontal.push(ev.currentTarget.attributes.x.value);
			arrayVertical.push(ev.currentTarget.attributes.y.value);
		    $(this).addClass('selected');
		    selectedSquares.push(this);
		    wordSelected += ev.target.innerText;
		} else {
			if (moveHorizontal && ev.currentTarget.attributes.x.value != xLocationFirstLetter && ev.currentTarget.attributes.y.value == yLocationFirstLetter){
				if (arrayHorizontal.length != 0) {
					if (arrayHorizontal.length == 1 && arrayHorizontal[0] != 0){
						if(parseInt(arrayHorizontal[0]) + 1 == ev.currentTarget.attributes.x.value){
							$(this).addClass('selected');
							arrayHorizontal.push(ev.currentTarget.attributes.x.value);
							moveVertical = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
						if(parseInt(arrayHorizontal[0]) - 1 == ev.currentTarget.attributes.x.value){
							$(this).addClass('selected');
							arrayHorizontal.push(ev.currentTarget.attributes.x.value);
							moveVertical = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
					} else {
						for (m=0; m < arrayHorizontal.length; m++){
							if (parseInt(arrayHorizontal[m]) + 1 == ev.currentTarget.attributes.x.value || parseInt(arrayHorizontal[m]) - 1 == ev.currentTarget.attributes.x.value){
								$(this).addClass('selected');
								arrayHorizontal.push(ev.currentTarget.attributes.x.value);
								moveVertical = false;
								wordSelected += ev.target.innerText;
							    selectedSquares.push(this);
							}
						}
					}
				} else {
					if (ev.currentTarget.attributes.x.value > xLocationFirstLetter && ev.currentTarget.attributes.x.value - xLocationFirstLetter == 1){
						$(this).addClass('selected');
						moveVertical = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						arrayHorizontal.push(ev.currentTarget.attributes.x.value);
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
					if (ev.currentTarget.attributes.x.value < xLocationFirstLetter && xLocationFirstLetter - ev.currentTarget.attributes.x.value == 1){
						$(this).addClass('selected');
						moveVertical = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						arrayHorizontal.push(ev.currentTarget.attributes.x.value);
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
				}
			} else if(moveVertical && ev.currentTarget.attributes.x.value == xLocationFirstLetter){
				if (arrayVertical.length != 0) {
					if (arrayVertical.length == 1){
						if(parseInt(arrayVertical[0]) + 1 == ev.currentTarget.attributes.y.value){
							$(this).addClass('selected');
							arrayVertical.push(ev.currentTarget.attributes.y.value);
							moveHorizontal = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
						if(parseInt(arrayVertical[0]) - 1 == ev.currentTarget.attributes.y.value){
							$(this).addClass('selected');
							arrayVertical.push(ev.currentTarget.attributes.y.value);
							moveHorizontal = false;
							wordSelected += ev.target.innerText;
						    selectedSquares.push(this);
						}
					} else {
						for (m=0; m < arrayVertical.length; m++){
							if (parseInt(arrayVertical[m]) + 1 == ev.currentTarget.attributes.y.value || parseInt(arrayVertical[m]) - 1 == ev.currentTarget.attributes.y.value){
								$(this).addClass('selected');
								arrayVertical.push(ev.currentTarget.attributes.y.value);
								moveHorizontal = false;
								wordSelected += ev.target.innerText;
							    selectedSquares.push(this);
							}
						}
					}
				} else {
					if (ev.currentTarget.attributes.y.value > yLocationFirstLetter){
						$(this).addClass('selected');
						moveHorizontal = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
					if (ev.currentTarget.attributes.y.value < yLocationFirstLetter){
						$(this).addClass('selected');
						moveHorizontal = false;
						xLocationFirstLetter = ev.currentTarget.attributes.x.value;
						yLocationFirstLetter = ev.currentTarget.attributes.y.value;
						wordSelected += ev.target.innerText;
					    selectedSquares.push(this);
					}
				}
			}
		}
		
		$( ".word-selected" ).html( wordSelected );
		
		if (renderButtonDelete && wordSelected != ''){
			renderButtonDelete = false;
			$( ".div-word-selected" ).append( "<button id='checkWord' type='button' data-toggle=modal data-target=#exampleModal class='btn btn-success margin-right-20 margin-top-50'>Comprobar</button>" );
			$( ".div-word-selected" ).append( "<button id='deleteWord' type='button' class='btn btn-danger margin-top-50'>Borrar</button>" );
			$(document).on('click', '#deleteWord', function() {
				$(".word-selected")[0].innerText = "";
				$("#deleteWord").remove();
				$("#checkWord").remove();
				
				for(p=0; p<selectedSquares.length; p++){
					selectedSquares[p].classList.remove("selected");
				}
				
				selectedSquares = [];
				arrayHorizontal = [];
				arrayVertical = [];
				xLocationFirstLetter = '';
				yLocationFirstLetter = '';
				moveVertical = true;
				moveHorizontal = true;
				renderButtonDelete = true;
				wordSelected = "";
			});
			$(document).on('click', '#checkWord', function() {
		        $.ajax({
		            type: "POST",
		            url: 'soapletters/check',
		            dataType: 'json',
		            contentType: 'application/json',
		            data: JSON.stringify({"idGame":gameId,"word":$(".word-selected")[0].innerText,"userName":"mel"}),
		            xhrFields: {
		                withCredentials: true
		             },
		            success: function(response){
		            	// juego acabado
		            	if (response.gameFinished){
		            		$(".div-message").html('<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">  <div class="modal-dialog" role="document">    <div class="modal-content">      <div class="modal-header">        <h5 class="modal-title" id="exampleModalLabel">¡Enhorabuena!</h5>        <button type="button" class="close" data-dismiss="modal" aria-label="Close">          <span aria-hidden="true">&times;</span>        </button>      </div>      <div class="modal-body">¡Has acertado todas las palabras!</div>      <div class="modal-footer">        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>     </div>    </div>  </div></div>');
		            	}
		            	//palabra acertada
		            	if (!response.gameFinished && response.wordValidated){
		            		$(".div-message").html( '<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">  <div class="modal-dialog" role="document">    <div class="modal-content">      <div class="modal-header">        <h5 class="modal-title" id="exampleModalLabel">¡Otra palabra más!</h5>        <button type="button" class="close" data-dismiss="modal" aria-label="Close">          <span aria-hidden="true">&times;</span>        </button>      </div>      <div class="modal-body">¡Ánimos, ya queda poco!</div>      <div class="modal-footer">        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>     </div>    </div>  </div></div>' );
							$(".selected").addClass( "complete-word" );
							$(".puzzleSquare").removeClass( "selected" );
							
							$(".word-selected")[0].innerText = "";
							$("#deleteWord").remove();
							$("#checkWord").remove();
							
							selectedSquares = [];
							arrayHorizontal = [];
							arrayVertical = [];
							xLocationFirstLetter = '';
							yLocationFirstLetter = '';
							moveVertical = true;
							moveHorizontal = true;
							wordSelected = "";
							renderButtonDelete = true;
		            	}
		            }
		         });
		        
			});
		}
	};
	
    $(document).ready(function() {
		ldapUser = localStorage.getItem('ldap_user');
        $.get( "soapletters", "mel", function( data ) {
        	gameId = data.idGame;
			for (j = 0; j < data.words.length; j++) {
				 $( ".words-search" ).append( "<div>" + data.words[j] + "</div>" );
			}
			var positiony = 0;
			var positionx = 0;
			
			for (m = 0; m < data.table.length; m++) {
				$( "#puzzle" ).append( '<br>' );	
				for (o = 0; o < data.table[m].length; o++){
					 $( "#puzzle" ).append( '<button x="' + o + '" y ="' + m + '" class=puzzleSquare>' + data.table[m][o] + '</button>' );	
				}
			}
			$('.puzzleSquare').mousedown(startTurn);
       	});
    });
</script>