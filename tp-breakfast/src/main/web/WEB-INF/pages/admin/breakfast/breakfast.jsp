<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	<form:form action="save.html" commandName="breakfast" method="POST" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="organizer" />
		
		<div class="form-group<c:if test="${not empty fbDate}"> ${fbDate}</c:if>">
			<label class="control-label col-sm-2" for="name">Date :</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${breakfast.date}" type="date" pattern="dd/MM/yyyy" var="formattedDate" />
				<form:input type="date" path="date" value="${formattedDate}" class="form-control" placeholder="Date" />
			</div>
		</div>
		
		<div class="form-group<c:if test="${not empty fbName}"> ${fbName}</c:if>">
			<label class="control-label col-sm-2" for="name">Nom :</label>
			<div class="col-sm-10">
				<form:input id="name" path="name" class="form-control" placeholder="Nom" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2" for="comment">Commentaire :</label>
			<div class="col-sm-10">
				<form:textarea id="comment" path="comment" class="form-control" placeholder="Commentaire" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="login">Ingrédients :</label>
			<div class="col-sm-10 checkboxes-block">
				<form:checkboxes items="${ingredientsCache}" itemValue="id" itemLabel="name" path="ingredients" />
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












