<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="Cédric Sevestre">
<link rel="icon" href="../../favicon.ico">

<title>${title}</title>

<!-- Bootstrap core CSS -->
<link href="../../style/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../style/dashboard.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="/WEB-INF/pages/templates/nav-header.jsp"/>

	<div class="container-fluid">
		<div class="row">
		
		<jsp:include page="/WEB-INF/pages/templates/nav-sidebar.jsp"/>