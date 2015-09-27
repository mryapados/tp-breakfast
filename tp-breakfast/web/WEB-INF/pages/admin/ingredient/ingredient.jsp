<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${action} un ingrédient</title>
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>


	<header>
		<h1>${action} un ingrédient</h1>
	</header>

	<article>


		<c:if test="${not empty error}">
			<label style="color: red;"><c:out value="${error}" /></label>
		</c:if>
		<form:form action="save.html" commandName="ingredient" method="POST" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="control-label col-sm-2" for="name">Nom :</label>
				<div class="col-sm-6">
					<form:input id="name" path="name" class="form-control" placeholder="Nom" />
				</div>
			</div>

	
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-default" value="valider" />
				</div>
			</div>
		</form:form>




	</article>






</body>
</html>