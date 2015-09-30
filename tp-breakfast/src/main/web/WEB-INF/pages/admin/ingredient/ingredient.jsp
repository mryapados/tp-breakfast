<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/pages/templates/header.jsp"/>
		

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h2 id="section_title" class="sub-header">${title}</h2>

	<c:if test="${not empty errors}">
		<ul class="bg-danger">
			<c:forEach items="${errors}" var="value">
				<li><c:out value="${value}"/></li>
			</c:forEach>
		</ul>
	</c:if>
	<form:form action="save.html" commandName="ingredient" method="POST" class="form-horizontal">
		<form:hidden path="id" />

		<div class="form-group<c:if test="${not empty fbName}"> ${fbName}</c:if>">
			<label class="control-label col-sm-2" for="login">Nom :</label>
			<div class="col-sm-10">
				<form:input id="name" path="name" class="form-control" placeholder="Nom" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="type">Type : </label>
			<div class="col-sm-10">
				<form:select id="type" class="form-control" path="type">
					<form:option value="">Selectionner un type</form:option>
					<form:option value="SWEET">Sucré</form:option>
					<form:option value="SALTED">Salé</form:option>
					<form:option value="BOTH">Sucré / salé</form:option>
				</form:select>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" class="btn btn-primary" value="valider" />
			</div>
		</div>
	</form:form>

</div>



<jsp:include page="/WEB-INF/pages/templates/footer.jsp"/>












