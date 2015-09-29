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

	<div><a class="btn btn-primary" href="new.html">Ajouter un petit déjeuné</a></div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Date</th>
				<th>Nom</th>
				<th>Ingrédients</th>
				<th>Commentaire</th>
				<th>Organisateur</th>
				<th>Outils</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${not empty breakfasts}">
				<c:forEach items="${breakfasts}" var="value">
					<tr>
						<td>${value.date}</td>
						<td>${value.name}</td>
						<td>
							<c:if test="${not empty value.ingredients}">
								<ul class="list-unstyled">
									<c:forEach items="${value.ingredients}" var="ingredient">
										<li>${ingredient.name}</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
						<td>${value.comment}</td>
						<td>${value.organizer.name}</td>
						<td>
							<c:if test="${value.allowEdit}">
								<a class="btn btn-primary" href="edit.html?id=${value.id}">Edit</a>
							</c:if>
							<c:if test="${value.allowDel}">
								<a class="btn btn-danger" href="del.html?id=${value.id}">Suppr</a>
							</c:if>
							<c:if test="${value.allowRegister}">
								<a class="btn btn-success" href="reg.html?id=${value.id}">S'inscrire</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>




		</tbody>
	</table>


</div>



<jsp:include page="/WEB-INF/pages/templates/footer.jsp"/>












