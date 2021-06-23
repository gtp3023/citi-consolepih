<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="cpih.general.title"/></title>
    
    <link rel="shortcut icon" href="images/icon.png">

    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/jquery-ui.min.css" />
    <link rel="stylesheet" href="css/fonts.css" />
    <link rel="stylesheet" href="css/style.css" />
    
    <script src="js/jquery.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/jquery.inputmask.js"></script>
    <script src="js/general.js"></script>
    <script src="js/constants.js"></script>
    
    <script>
    	Constants.Context.APP_PATH = '${pageContext.request.contextPath}/';
    	
    	$(document).ready(function() {
    	    
    	    $('#btnMenuConsole').click(function(e) {
    	        e.preventDefault();
    	        genericAjax(e, $(this).attr('url'), null, loadPage, 'html');
    	    });
    	    
    	    $('#btnMenuUser').click(function(e) {
    	        e.preventDefault();
    	        genericAjax(e, $(this).attr('url'), null, loadPage, 'html');
    	    });
        	
    	    function loadPage(data) {
    	    	$('#pageData').html(data);
    	    };
    	    
    	    $('#btnLogout').click(function (e) {
    			e.preventDefault();
    			$('.overlay').show();
       			
       			var form = $('<form></form>')
                   .attr('action', '${pageContext.request.contextPath}/logout')
                   .attr('method', 'post');
       			$('body').append(form);
       			
       			form.submit();
       		});
    	    
        });
    </script>
</head>

<body>            
  	<header>
        <div class="content">
            <div class="row align-items-center">
                <div class="col-12 col-sm-6 col-md-8">
                    <img class="main_logo" src="images/logo_white.png" draggable="false" alt="Logo"/>
                    <h5>Consola de monitoreo PIH</h5>
                </div>
                <div class="col-12 col-sm-6 col-md-4 text-right">
                    <div class="dropdown">
                        <button class="dropdown-toggle" type="button" id="menu_set" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><em class="material-icons">person</em>Hola <sec:authentication property="principal.fullName"/></button>
                        <c:set var="role" scope="session" value='<sec:authentication property="principal.user.role"/>'/>
                        
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="menu_set">
                        	<a id="btnMenuConsole" class="btnMenu" url="console" href="javascript:void(0)">Consola de monitoreo PIH</a>
                        	<sec:authorize access="hasRole('ROLE_ADMIN')">
                            	<a id="btnMenuUser" class="btnMenu" url="user" href="javascript:void(0)">Administración de usuarios</a>
                            </sec:authorize>
                            <hr />
                            <a id="btnLogout" href="javascript:void(0)"><spring:message code="cpih.general.logout"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
            			
	<div id="pageData">
      	<c:import url="console.jsp" />
  	</div>
    
    <div class="overlay">
        <div class="spinner">
            <div class="double-bounce1"></div>
            <div class="double-bounce2"></div>
        </div>
    </div>
    
</body>
</html>