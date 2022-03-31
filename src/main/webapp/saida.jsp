<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/x-icon" href="estaciona.png">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>JaVaLi - Saida</title>
</head>

<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand fw-bold fst-italic text-danger"
				href="index.jsp">JaVaLi</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="index.jsp">Entrada</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="EstacionamentoServlet?pagina=pag">Pagamento</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="EstacionamentoServlet?pagina=saida">Saída</a></li>
					<li class="nav-item"><a class="nav-link"
						href="EstacionamentoServlet?pagina=list">Listar</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-3 mb-5">
		<!-- mensagens -->
		<c:if test="${success != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<svg style="width: 24px;" fill="none" stroke="currentColor"
					viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2"
						d="M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
				${success}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>
		<c:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">
				<svg style="width: 24px;" fill="none" stroke="currentColor"
					viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round"
						stroke-width="2"
						d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
				${error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
		</c:if>

		<div class="card small shadow">
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Placa</th>
							<th scope="col">Entrada</th>
							<th scope="col">Pagamento</th>
							<th scope="col">Confirmar Saida</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="f" items="${estacionamentos}">
							<tr>
								<td>${f.id}</td>
								<td class="fw-bold">${f.placa}</td>
								<td>${f.entrada}</td>
								<td class='<c:if test="${f.pagamento <= 0}">text-danger</c:if>'>
									R$ ${f.pagamento}</td>
								<td title="Confirmar saída"><a class="btn btn-sm btn-danger py-0 <c:if test="${f.pagamento <= 0}">disabled</c:if>"
									href="UpdateServlet?id=${f.id}">Confirmar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<footer class="small text-center fixed-bottom text-light bg-dark py-2 mt-4">
		<span>&copy by Hans M. Boron</span><br> <span>2022</span>
	</footer>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>