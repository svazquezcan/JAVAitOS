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
