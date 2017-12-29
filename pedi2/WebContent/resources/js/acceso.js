$(document).ready(function() {
	
	//Foco en login
	$("#login").focus();
	
	//Asociar el evento enter a la caja password, para no tener que hacer click en el boton
	$("#password").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#btnAcceso").click();
	    }
	});
	
	//NUEVO USUARIO	
	$('#noRegistrado').click(function(event){
		
		event.preventDefault();		
		$('#modalRegistro').modal({});
		event.stopImmediatePropagation();
		
	});
	
	$('#btnNuevoRegistro').click(function(e){
		
		e.preventDefault();
		e.stopImmediatePropagation();
		
		var valorMatricula			= $('#reg_matricula').val();				
		var valorNombre				= $('#reg_nombre').val();
		var valorCorreo				= $('#reg_email').val();
		var valorPassword			= $('#reg_password').val();
		var valorPassword2			= $('#reg_password2').val();
		
		var selector				= document.getElementById("selAgenciaEmpleado");
		var valorAgencia			= selector.options[selector.selectedIndex].value;
		
		if(valorMatricula.length>0){			
			if(valorAgencia!="0"){				
				if(valorNombre.length>0){						
					if(valorCorreo.length>0){							
						if(valorPassword.length>6){
							if(valorPassword === valorPassword2){
						
								var $this = $(this);
								$this.button('loading');
							
								$.ajax({
						        	url		 : 'nuevoUsuario',        		
						    		type	 : "post",
						    		async	 : true,
						    		data     : 'matricula='+valorMatricula+'&nombre='+valorNombre+'&correo='+valorCorreo+'&password='+valorPassword+'&agencia='+valorAgencia,
						    		dataType : "json",
						    		success  : function( data, textStatus, jqXHR) {
						    			
						    			if(data.mensaje!=null){					        				
					        				alert(data.mensaje); //Error
					        				$this.button('reset');
					        			}else{					        				
					        				alert("OK. Sus datos se han registrado correctamente. Ya puede acceder a la aplicación.");
					        				$(":input","#formularioRegistro").val("");
					        				$("#modalRegistro .close").click();
					        			}
						    		}
						   		 });	
					
							}else{
								alert("Los password no coinciden.");
								$('#reg_password').focus();			
							}			
						}else{
							alert("Por favor, introduzca una contraseña de al menos 7 caracteres.");
							$('#reg_password').focus();								
						}
					}else{							
						alert("Por favor, introduzca su correo electrónico.");
						$('#reg_email').focus();							
					}							
				}else{
					alert("Por favor, introduzca su nombre y apellidos.");
					$('#reg_nombre').focus();
				}					
			}else{					
				alert("Por favor, seleccione su agencia/centro.");
				$('#selAgenciaEmpleado').focus();					
			}			
		}else{
			alert("Por favor, introduzca su matrícula de GEFCO");	
			$('#reg_matricula').focus();
		}				
					
	});	
	
	//LOGIN
	$('#btnAcceso').click(function(event){
				
		event.preventDefault();
		
		var valorLogin  	= $('#login').val();
		var valorPass   	= $('#password').val();
		
		var $this = $(this);
		$this.button('loading');

        $.ajax({
        	url		 : 'login',        		
    		type	 : "post",
    		async	 : true,        		
    		data     : 'login='+valorLogin+'&password='+valorPass,
    		dataType : "json",
    		success  : function( data, textStatus, jqXHR) {
    			
    					if(data.mensaje!=null){					        				
    						alert(data.mensaje); //Error
    						$this.button('reset');
    						$("#login").focus();
    					}else{        						
    						$(location).attr('href','inicio');    						
    					}
    					
    		}
        });	
        event.stopImmediatePropagation();
		
	});
	
});
