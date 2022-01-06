<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
</head>

<body>

        <!-- Page Content  -->
        <div id="content" class="active">
            <header>
                <div class="row align-items-center">
				
                </div>
            </header>

			<div class="row align-items-center">
				<div class="col-12">
					<img src="images/error.png" draggable="false" alt="Error" />
				</div>
				<div class="col-12">
            		<h1><spring:message code="cpih.error.general"/></h1>
            	</div>
			</div>
						
        </div>

    <div class="bg_black"></div>
	    
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>