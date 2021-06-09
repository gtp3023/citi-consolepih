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
    
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

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
            
    		var token = $('meta[name="_csrf"]').attr('content');
        	var header = $('meta[name="_csrf_header"]').attr('content');
        	 
        	$(document).ajaxSend(function(e, xhr, options) {
        	    xhr.setRequestHeader(header, token);
        	});
    	    
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
                            <a onclick="document.forms['logoutForm'].submit()" href="javascript:void(0)"><spring:message code="cpih.general.logout"/></a>
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
    
    <div id="modalDelete" class="modal fade" aria-labelledby="myModalDeleteLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content error">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                   	<span aria-hidden="true">&times;</span>
	                </button>
	                
	                <em class="material-icons">warning</em>
	                
	                <h4><span id="myModalDeleteLabel"><spring:message code="cpih.modal.delete.title"/></span></h4>
				
	                <div class="eliminarTitle">
	                    <label><spring:message code="cpih.modal.delete.msg"/></label>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button id="btnDelete" type="button" class="btn btn_error"><spring:message code="cpih.general.button.accept"/></button>
		            <button data-dismiss="modal" type="button" class="btn btn_error_line"><spring:message code="cpih.general.button.cancel"/></button>
	            </div>
			</div>
		</div>
	</div>
	
	<div id="modalMsg" class="modal fade" aria-labelledby="modalMsgTitle">
		<div class="modal-dialog" role="document">
		    <div class="modal-content info">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                   	<span aria-hidden="true">&times;</span>
	                </button>
	                
	                <div class="modal_title">
	                    <h3><span id="modalMsgTitle"></span></h3>
	                </div>
					
	                <div class="eliminarTitle">
	                    <label id="modalMsgText"></label>
	                </div>
	            </div>
	            <div class="modal-footer">
		            <button data-dismiss="modal" type="button" class="btn btn_primary"><spring:message code="cpih.general.button.accept"/></button>
	            </div>
			</div>
		</div>
	</div>
    
    <form id="logoutForm" method="POST" action="logout">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
    
</body>
</html>