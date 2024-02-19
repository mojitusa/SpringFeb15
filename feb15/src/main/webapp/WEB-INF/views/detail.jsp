<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>게시판</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="assets/favicon.ico" />
<link rel="apple-touch-icon" sizes="57x57" href="assets/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="assets/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="assets/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="assets/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="assets/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="assets/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="assets/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="assets/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="assets/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192" href="assets/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="assets/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="assets/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="assets/favicon-16x16.png">
<link rel="manifest" href="assets/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/board.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<c:import url="menu.jsp" />

	<!-- detail -->
	<section class="page-section" id="detail">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">톺아보기</h2>
			</div>
			<div class="card mb-4">
				<div class="card-body">
					<div class="h2">
						${detail.board_title }
					</div>
					<div class="row p-2 bg-secondary">
						<div class="col align-middle text-start">
							${detail.board_write }
						</div>
						<div class="col align-middle text-end">
							${detail.board_date}
						</div>
						<div class="mt-4 h-auto">
							${detail.board_content}
						</div>
					</div>
				</div>
			</div>
			<button class="btn btn-warning" onclick="history.back()">게시판으로</button>
			<button class="btn btn-warning" onclick="history.go(-1)">게시판으로</button>
			<hr>
			<div>
				<form action="./commentWrite" method="post">
					<div class="row">
						<div class="col-xs-8 col-sm-10 col-md-11 col-xl-11" style="height: 100px; width: 100%;">
							<textarea class="form-control" name="comment" style="height: 100px; width: 100%;"></textarea>
						</div>
						<br>
						<div class="col-xs-4 col-sm-2 col-md-1 con-xl-1" style="width: 100%; margin-top: 20px; text-align: right;">
							<button class="btn btn-primary" style="width: auto">댓글쓰기</button>
						</div>
					</div>
					<input type="hidden" name="no" value="${detail.board_no }">
				</form>
			</div>
		</div>
	</section>
	<!-- <input type="button" value="게시판으로"> 이렇게도 버튼을 만들 수 있다 -->
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
