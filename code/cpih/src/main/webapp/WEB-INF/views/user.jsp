<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="content">
    <div class="row align-items-center title_header">
        <div class="col-6 col-md-8">
            <h3 class="blue">Administración de usuarios</h3>
        </div>
        <div class="col-6 col-md-4 text-right">
            <button id="btnNew" type="button" class="button btn_primary" data-toggle="modal" data-target="#user_create" data-backdrop="static" data-keyboard="false"><em class="material-icons">person_add</em> Crear usuario</button>
        </div>
    </div>
		
	<div id="gridData">
	  	<c:import url="userList.jsp" />
	</div>
</div>

<div id="modal" class="modal fade" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content info">
            
            <div class="modal-body">
            	<input type="hidden" id="userId" name="userId"/>
            	<input type="hidden" id="password" name="password"/>
            		
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                   	<span aria-hidden="true">&times;</span>
                </button>
                    
                <h4><span id="myModalLabel"></span></h4>
                
                <br>
                
                <div class="isNew isUpdate form-group">
                    <label for="name" class="col-12 form_lbl"><spring:message code="cpih.page.user.label.name"/>:</label>
                    <div class="col-12">
                        <input class="form-control" type="text" name="name" id="name" maxlength="50">
                    </div>
                </div>
                <div class="isNew isUpdate form-group">
                    <label for="lastName" class="col-12 control-label"><spring:message code="cpih.page.user.label.lastName"/>:</label>
                    <div class="col-12">
                    	<input class="form-control" type="text" name="lastName" id="lastName" maxlength="50">
                    </div>
                </div>
                <div class="isNew isUpdate form-group">
                    <label for="type" class="col-12 control-label"><spring:message code="cpih.page.user.label.role"/>:</label>
                    <div class="col-12">
                    	<select class="form-control" id="role" name="role">
       			<option value=""><spring:message code="cpih.general.label.select"/></option>
       			<c:forEach items="${listRole}" var="role">
	            	<option value="${role.key}">${role.value}</option>
	        	</c:forEach>
	     	</select>
                    </div>
                </div>
                <div class="isNew isUpdate form-group">
                    <label for="username" class="col-12 control-label"><spring:message code="cpih.page.user.label.username"/>:</label>
                    <div class="col-12">
                        <input class="form-control" type="text" name="username" id="username" maxlength="30">
                    </div>
                </div>
                <div class="isNew isPass form-group">
                    <label for="password" class="col-12 form_lbl"><spring:message code="cpih.page.user.label.pass"/>:</label>
                    <div class="col-12">
                        <input class="form-control" type="password" name="pass" id="pass" maxlength="30">
                    </div>
                </div>
                <div class="isNew isPass form-group">
                    <label for="pass2" class="col-12 form_lbl"><spring:message code="cpih.page.user.label.pass2"/>:</label>
                    <div class="col-12">
                        <input class="form-control" type="password" name="pass2" id="pass2" maxlength="30">
                    </div>
                </div>
                <div class="form-group">
					<label style="color: red;" id="errorText" class="col-sm-12 control-label">${errorText}</label>
                </div>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" class="button btn_primary"><spring:message code="cpih.general.button.save"/></button>
                <button data-dismiss="modal" type="button" class="button btn_primary_line"><spring:message code="cpih.general.button.close"/></button>
            </div>
        </div>
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

<script>	
    $(document).ready(function () {
    	self.createEvents();
    	self.createValidations();
    	self.createGrid();
    });

    var valueTemp;
    var idTemp = '';
    var isNew;
    var isEncoded;

    function createEvents() {
    	$('#btnNew').click(function(e) {
    		self.newRegister();
        });
    	
    	$('#btnSave').click(function(e) {
    		self.validateRegister();
        });
    	
    	$('#btnDelete').click(function(e) {
    		self.deleteRegister();
        });
    };

    function createGrid() {
    	$('.overlay').show();
    	genericAjax(null, 'user/grid', null, self.loadGrid, 'html');
    };
    
    function loadGrid(data) {
    	$('#gridData').html(data);
    	$('.overlay').hide();
    };

    function createValidations() {	
    	$('#name').inputmask('Regex', { regex: Constants.Validation.REGEX_CARACTERES_ALFABETICO });
    	$('#lastName').inputmask('Regex', { regex: Constants.Validation.REGEX_CARACTERES_ALFABETICO });
    	$('#username').inputmask('Regex', { regex: Constants.Validation.REGEX_CARACTERES_ALFANUMERICO_SIN_ESPACIO });
    	$('#pass').inputmask('Regex', { regex: Constants.Validation.REGEX_CARACTERES_ALFANUMERICO_SIN_ESPACIO });
    	$('#pass2').inputmask('Regex', { regex: Constants.Validation.REGEX_CARACTERES_ALFANUMERICO_SIN_ESPACIO });
    };

    function newRegister() {
    	self.clean();
    	
    	self.isNew = true;
    	self.isEncoded = true;
    	$('#myModalLabel').text('Crear cuenta');
    	
    	$('#modal').modal('show');
    	$('.isNew').show();
    };

    function validateRegister() {
    	var name = $('#name').val();
    	var lastName = $('#lastName').val();
    	var role = $('#role').val();
    	var username = $('#username').val();
    	var password = $('#pass').val();
    	var password2 = $('#pass2').val();
    	
    	if(name == null || name == '') {
    		$('#errorText').text('Es requerido capturar el nombre del usuario.');
    		return false;
    	}
    	
    	if(lastName == null || lastName == '') {
    		$('#errorText').text('Es requerido capturar el apellido del usuario.');
    		return false;
    	}
    	
    	if(role == null || role == '') {
    		$('#errorText').text('Es requerido capturar el rol de la cuenta.');
    		return false;
    	}
    		
    	if(username == null || username == '') {
    		$('#errorText').text('Es requerido capturar el nombre de usuario de la cuenta.');
    		return false;
    	}
    	
    	if(self.isEncoded) {
    		if(password == null || password == '') {
    			$('#errorText').text('Es requerido capturar la contrase\u00F1a de la cuenta.');
    			return false;
    		}
    		
    		if(password != password2) {
    			$('#errorText').text('Las contrase\u00F1as no coinciden.');
    			return false;
    		}
    	}
    	
    	$('.overlay').show();
    	
    	if(self.isNew) {
    		var parametros = { username : username };
    		genericAjax(null, 'user/validateUsername', parametros, self.validateResponse, 'json');
    	} else {
    		if(self.valueTemp == username) {
    			self.saveRegister();
    		} else {
    			var parametros = { username : username };
    			genericAjax(null, 'user/validateUsername', parametros, self.validateResponse, 'json');
    		}
    	}
    };

    function validateResponse(data) {
    	$('.overlay').hide();
    	
   		if(data.response) {
   			$('#errorText').text('Ya existe una cuenta con el nombre de usuario capturado.');    			
   		} else {
   			self.saveRegister();
   		}
    };

    function saveRegister() {
    	$('.overlay').show();
    	var user = {};
    	
    	user.userId = $('#userId').val();
    	user.name = $('#name').val();
    	user.lastName = $('#lastName').val();
    	user.role = $('#role').val();
    	user.username = $('#username').val();
    	user.password = self.isEncoded ? $('#pass').val() : $('#password').val();
    	user.encodedPass = self.isEncoded;

    	genericAjaxJson(null, 'user/save', user, self.saveRegisterResponse);
    	$('#modal').modal('hide');
    };

    function saveRegisterResponse(data) {
    	$('.overlay').hide();
    	
    	if(data.response) {
    		self.createGrid();
    		
    		$('#modalMsg').modal('show');
    		$('#modalMsgTitle').text(Constants.Message.SUCCESS);
    		$('#modalMsgText').text(self.isNew ? Constants.Message.SUCCESS_SAVE : Constants.Message.SUCCESS_UPDATE);
    	} else {
    		$('#modalMsg').modal('show');
    		$('#modalMsgTitle').text(Constants.Message.ERROR);
    		$('#modalMsgText').text(data.responseMsg);
    	}
    };

    function updateRegister(id) {
    	self.clean();
    	
    	$('.overlay').show();
    	
    	self.isNew = false;
    	self.isEncoded = false;
    	$('#myModalLabel').text('Modificar cuenta');
    	
    	var parametros = { id : id };
    	genericAjax(null, 'user/findById', parametros, self.updateRegisterResponse, 'json');
    };

    function updatePassword(id) {
    	self.clean();
    	
    	$('.overlay').show();
    	
    	self.isNew = false;
    	self.isEncoded = true;
    	$('#myModalLabel').text('Modificar contrase\u00F1a');
    	
    	var parametros = { id : id };
    	genericAjax(null, 'user/findById', parametros, self.updateRegisterResponse, 'json');
    };

    function updateRegisterResponse(data) {
    	$('.overlay').hide();
    	
    	if(data.response) {
    		self.valueTemp = data.response.username;
    		
    		$('#userId').val(data.response.userId);
    		$('#name').val(data.response.name);
    		$('#lastName').val(data.response.lastName);
    		$('#role').val(data.response.role);
    		$('#username').val(data.response.username);
    		$('#password').val(data.response.password);
    		
    		$('#modal').modal('show');
    		
    		if(self.isEncoded) {
    			$('.isPass').show();
    			$('.isUpdate').hide();
    		} else {
    			$('.isUpdate').show();
    			$('.isPass').hide();
    		}
    	} else {
    		$('#modalMsg').modal('show');
    		$('#modalMsgTitle').text(Constants.Message.ERROR);
    		$('#modalMsgText').text(data.responseMsg);
    	}
    };

    function openModalDelete(id) {
    	self.idTemp = id;
    	console.log('test');
    	$('#modalDelete').modal('show');
    };

    function deleteRegister() {
    	$('.overlay').show();
    	var parametros = { id : self.idTemp };
    	genericAjax(null, 'user/delete', parametros, self.deleteRegisterResponse, 'json');
    	$('#modalDelete').modal('hide');
    };

    function deleteRegisterResponse(data) {
    	$('.overlay').hide();
    	
    	if(data.response) {
    		self.createGrid();
    		
    		$('#modalMsg').modal('show');
    		$('#modalMsgTitle').text(Constants.Message.SUCCESS);
    		$('#modalMsgText').text(Constants.Message.SUCCESS_DELETE);
    	} else {
    		$('#modalMsg').modal('show');
    		$('#modalMsgTitle').text(Constants.Message.ERROR);
    		$('#modalMsgText').text(data.responseMsg);
    	}
    };

    function clean() {
    	$('#userId').val('');
    	$('#name').val('');
    	$('#lastName').val('');
    	$('#role').val('');
    	$('#username').val('');
    	$('#password').val('');
    	$('#pass').val('');
    	$('#pass2').val('');
    	$('#errorText').text('');
    };
</script>