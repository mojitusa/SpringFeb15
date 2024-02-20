<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Trinity Company - Home</title>
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
		<link rel="icon" type="image/png" sizes="192x192"  href="assets/android-icon-192x192.png">
		<link rel="icon" type="image/png" sizes="32x32" href="assets/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="96x96" href="assets/favicon-96x96.png">
		<link rel="icon" type="image/png" sizes="16x16" href="assets/favicon-16x16.png">
		<link rel="manifest" href="assets/manifest.json">
		<meta name="msapplication-TileColor" content="#ffffff">
		<meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
		<meta name="theme-color" content="#ffffff">
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <%@ include file="menu.jsp"%>
        
        <!-- login -->
		<section class="page-section" id="login" style="display: flex; justify-content: center; align-items: center; height: 100vh;">
		    <div class="container" style="border: 1px solid #3498db; width: 50%; padding: 20px; text-align: center; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
		        <form action="./login" method="post" style="width: 100%;">
		            <div style="margin-bottom: 20px;">
		                <input type="text" name="id" placeholder="아이디" style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px;">
		            </div>
		            <div style="margin-bottom: 20px;">
		                <input type="password" name="pw" placeholder="비밀번호" style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 5px;">
		            </div>
		            <div style="margin-bottom: 20px;">
		                <button type="reset" id="reset" style="width: 100%; padding: 10px; background-color: #3498db; color: #fff; border: none; border-radius: 5px; cursor: pointer;">초기화</button>
		            </div>
		            <div style="margin-bottom: 20px;">
		                <button type="submit" id="login" style="width: 100%; padding: 10px; background-color: #3498db; color: #fff; border: none; border-radius: 5px; cursor: pointer;">로그인</button>
		            </div>
		            <div>
		                <button type="button" id="join" style="width: 100%; padding: 10px; background-color: #2ecc71; color: #fff; border: none; border-radius: 5px; cursor: pointer;">가입하기</button>
		            </div>		            
		        </form>
		    </div>
		</section>

		
<!-- 		<section class="page-section" id="login" >
		    <div class="container">
		    	<div class="w-25 center-block text-center">
		    		<img alt="login" src="./img/login.png" width="250px;">
			        <div class="mb-3 row">
			            <label for="staticEmail" class="col-sm-3 col-form-label">I D</label>
		                <div class="col-sm-8">
			            	<input type="text" class="form-control" id="id" placeholder="아이디">    	
		            	</div>
			        </div>	
			        <div class="mb-3 row">
			            <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
		                <div class="col-sm-8">
			            	<input type="password" class="form-control" id="pw" placeholder="비밀번호">    	
		            	</div>
			        </div>			         
			     </div> 
		    </div>
		</section> -->

        
        <!-- Services-->
        <section class="page-section" id="services">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">트리니티 코인이란</h2>
                    <h3 class="section-subheading text-muted">작은 노력, 큰 수익! 굼뜬 당신을 위한 코인</h3>
                </div>
                <div class="row text-center">
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">쉬운 구매</h4>
                        <p class="text-muted">지금 당장 1원으로 10코인을 살 수 있습니다. 이래도 투자 안 할 것인가?</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">언제 어디서던 확인</h4>
                        <p class="text-muted">내 코인이 어디에 있나? 내 코인이 어디에 쓰이고 있나? 내 코인을 누구에게 양도했나 다 확인 가능한 트리니티 코인</p>
                    </div>
                    <div class="col-md-4">
                        <span class="fa-stack fa-4x">
                            <i class="fas fa-circle fa-stack-2x text-primary"></i>
                            <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                        </span>
                        <h4 class="my-3">밀어서 잠금</h4>
                        <p class="text-muted">코인을 현금화 하고 싶다면 자금을 잠금하세요.</p>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
