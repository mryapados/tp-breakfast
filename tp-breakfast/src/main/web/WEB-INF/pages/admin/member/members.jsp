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

	<div><a class="btn btn-primary" href="new.html">Ajouter un membre</a></div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Loging</th>
				<th>Activé</th>
				<th>Role</th>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Préférence</th>
				<th>Outils</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${not empty members}">
				<c:forEach items="${members}" var="value">
					<tr>
						<td>${value.login}</td>
						<td>${value.enabled}</td>
						<td>${value.role}</td>
						<td>${value.firstName}</td>
						<td>${value.lastName}</td>
						<td>${value.preference}</td>
						<td>
							<a class="btn btn-primary" href="edit.html?id=${value.id}">Edit</a>
							<a class="btn btn-danger" href="del.html?id=${value.id}">Del</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>




		</tbody>
	</table>


</div>



<jsp:include page="/WEB-INF/pages/templates/footer.jsp"/>












