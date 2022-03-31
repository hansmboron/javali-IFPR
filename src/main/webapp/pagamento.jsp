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
<title>JaVaLi - Pagamento</title>
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
					<li class="nav-item"><a class="nav-link active"
						href="EstacionamentoServlet?pagina=pag">Pagamento</a></li>
					<li class="nav-item"><a class="nav-link"
						href="EstacionamentoServlet?pagina=saida">Saída</a></li>
					<li class="nav-item"><a class="nav-link"
						href="EstacionamentoServlet?pagina=list">Listar</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container my-3">
		<div class="row justify-content-center">
			<div class="card col-11 col-sm-10 col-md-8 col-lg-6 col-xl-5 shadow">
				<div class="card-body">
					<h5 class="card-title text-center text-danger fw-bold">Pagamento</h5>

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

					<form method="POST" action="UpdateServlet">
						<div class="mb-3">
							<input class="form-control my-2" id="placa" name="placa"
								value="${estaciona.placa}" placeholder="Placa"
								readonly="readonly"> <label for="preco"
								class="form-label">Preço:</label> <input class="form-control"
								id="preco" name="preco" value="${estaciona.pagamento}"
								placeholder="Preço" aria-describedby="Campo de preço">
							<div id="preco_help" class="form-text fst-italic text-success">Digite
								preço a pagar em reais!</div>
							<input type="hidden" name="id" value="${estaciona.id}"
								readonly="readonly">
						</div>
						<button type="submit"
							class="btn btn-lg btn-outline-danger border border-2 border-danger w-100 mt-3">
							<svg style="width: 24px" fill="none" stroke="currentColor"
								viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
								<path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2"
									d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
							Confirmar
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="container mt-3 mb-5">
		<div class="card small shadow">
			<div class="card-body">
				<table class="table table-sm table-striped">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Placa</th>
							<th scope="col">Entrada</th>
							<th scope="col">Pagamento</th>
							<th scope="col">Selecionar</th>
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
								<td><a class="btn btn-sm btn-success py-0"
									href="EstacionamentoServlet?pagina=pag&id=${f.id}">Selecionar</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<footer class="small text-center fixed-bottom text-light bg-dark py-2">
		<span>&copy by Hans M. Boron</span><br> <span>2022</span>
	</footer>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/jquery.mask.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>