<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title><spring:message code="cpih.general.title"/></title>
    
    <link rel="shortcut icon" href="images/icon.png">
      
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/fonts.css" />
	<link rel="stylesheet" href="css/style.css" />
	
	<script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	
	<script>
    	$(document).ready(function() {
        	
    	    $('#btnLogin').click(function(e) {
    	        e.preventDefault();
    	        $('#errorLogin').text('');
    	        
    	        var username = $('#username').val();
    	        var password = $('#password').val();
    	        
    	        if(username == null || username == '' || password == null || password == '') {
    	    		$('#errorLogin').text('Usuario y contrase\u00F1a requeridos.');
    	    		return false;
    	    	}
    	        
    	        $('#formLogin').submit();
    	    });
    	    
    	    document.querySelector('#password').addEventListener('keyup', event => {
    	        if(event.key !== 'Enter') return;
    	        document.querySelector('#btnLogin').click();
    	        event.preventDefault();
    	    });
    	    
    	    $('.form_password').on('click', '.show', function(){
                var icon = $(this);
                var parent = icon.parent();

                icon.removeClass('show').addClass('hide').html('visibility_off');
                parent.find('.form_input').attr('type', 'text');
            });
            $('.form_password').on('click', '.hide', function(){
                var icon = $(this);
                var parent = icon.parent();

                icon.removeClass('hide').addClass('show').html('visibility');
                parent.find('.form_input').attr('type', 'password');
            });
    	    
        });
    </script>
</head>

<body>
    
    <div class="row login_container">
            <div class="col-12 col-md-4 login_sideBG">
                <div class="side_header">
                    <img class="web" src="images/logo_blue.png" draggable="false" alt="Telcel" />
                    <img class="mobil" src="images/logo_white.png" draggable="false" alt="Telcel" />
                    <h3>Consola de</h3>
                    <h1>Monitoreo PIH</h1>
                </div>
            </div>
            <div class="col-12 col-md-8">
                <form id="formLogin" action="getAccesoLogin" method="POST" class="login_form">
                    <h2>Iniciar sesión</h2>
                    <div class="form_content">
                        <label class="form_lbl">Usuario</label>
                        <input type="text" name="username" id="username" class="form_input" placeholder="<spring:message code="cpih.login.input.username"/>" maxlength="30" autofocus>
                    </div>
                    <div class="form_content">
                        <label class="form_lbl">Contraseña:</label>
                        <div class="form_password">
                            <input type="password" name="password" id="password" class="form_input" placeholder="<spring:message code="cpih.login.input.pass"/>" maxlength="20">
                            <em class="material-icons show">visibility</em>
                            <h2 class="msg-error" id="errorLogin">${error}</h2>
                        </div>
                    </div>

                    <input id="btnLogin" type="button" class="button btn_primary" value="<spring:message code="cpih.login.button.login"/>">
                </form>
            </div>
    </div>
	
  </body>
</html>