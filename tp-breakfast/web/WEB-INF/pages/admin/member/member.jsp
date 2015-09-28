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
	<form:form action="save.html" commandName="memberMaker" method="POST" class="form-horizontal">
		<form:hidden path="id" />

		<div class="form-group<c:if test="${not empty fbLogin}"> ${fbLogin}</c:if>">
			<label class="control-label col-sm-2" for="login">Login :</label>
			<div class="col-sm-10">
				<form:input id="login" path="login" class="form-control" placeholder="Login" />
			</div>
		</div>
		<div class="form-group<c:if test="${not empty fbPassword}"> ${fbPassword}</c:if>">
			<label class="control-label col-sm-2" for="password">Mot de passe :</label>
			<div class="col-sm-10">
				<form:input id="password" path="password" type="password" class="form-control" placeholder="Mot de passe" />
			</div>
		</div>
		<div class="form-group<c:if test="${not empty fbPasswordMatch}"> ${fbPasswordMatch}</c:if>">
			<label class="control-label col-sm-2" for="passwordMatch">Confirmation :</label>
			<div class="col-sm-10">
				<form:input id="passwordMatch" path="passwordMatch" type="password" class="form-control" placeholder="Mot de passe" />
			</div>
		</div>
		<div class="form-group<c:if test="${not empty fbEnabled}"> ${fbEnabled}</c:if>">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label><form:checkbox id="enabled" path="enabled" />Activé</label>
				</div>
			</div>
		</div>
		<div class="form-group<c:if test="${not empty fbRole}"> ${fbRole}</c:if>">
			<label class="control-label col-sm-2" for="role">Role : </label>
			<div class="col-sm-10">
				<form:select id="role" class="form-control" path="role">
					<form:option value="">Sélectionner un role</form:option>
					<form:option value="ROLE_USER">User</form:option>
					<form:option value="ROLE_ADMIN">Admin</form:option>
				</form:select>
			</div>
		</div>
							
		
		<div class="form-group<c:if test="${not empty fbFirstName}"> ${fbFirstName}</c:if>">
			<label class="control-label col-sm-2" for="firstname">Prénom :</label>
			<div class="col-sm-10">
				<form:input id="firstname" path="firstName" class="form-control" placeholder="Prénom" />
			</div>
		</div>
		<div class="form-group<c:if test="${not empty fbLastName}"> ${fbLastName}</c:if>">
			<label class="control-label col-sm-2" for="lastName">Nom :</label>
			<div class="col-sm-10">
				<form:input id="lastname" path="lastName" class="form-control" placeholder="Prénom" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2" for="preference">Préférence : </label>
			<div class="col-sm-10">
				<form:select id="preference" class="form-control" path="preference">
					<form:option value="">J'aime tout</form:option>
					<form:option value="SWEET">Sucré</form:option>
					<form:option value="SALTED">Salé</form:option>
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












