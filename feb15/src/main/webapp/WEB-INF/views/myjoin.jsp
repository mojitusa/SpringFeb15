<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>회원가입</title>
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script th:inline="javascript">
       
        	const Toast = Swal.mixin({
        		  toast: true,
        		  position: "top-end",
        		  showConfirmButton: false,
        		  timer: 3000,
        		  timerProgressBar: true,
        		  didOpen: (toast) => {
        		    toast.onmouseenter = Swal.stopTimer;
        		    toast.onmouseleave = Swal.resumeTimer;
        		  }
        	});
        	
            function showMessage() {
                // 클릭 시 메시지를 보여주는 부분
            	document.getElementById('idFocusMsg').style.display = 'block';
                document.getElementById('idCheckMsg1').style.display = 'none';
                
            }
            function validateAndShowErrorMessage() {
                // 아이디 입력값 가져오기
                var username = document.getElementById('input-id').value;
                
                // 정규 표현식을 사용하여 아이디가 조건에 맞는지 확인
                var regex = /^[a-zA-Z0-9_]{4,20}$/;
                
                document.getElementById('idFocusMsg').style.display = 'none';
                
                if (!regex.test(username)) {
                    // 아이디가 조건에 맞지 않으면 오류 메세지를 보여 줌
                    document.getElementById('idCheckMsg1').style.display = 'block';
                    document.getElementById('input-id').style.border = 'solid #d64141';
                    
                } else {
                	document.getElementById('idCheckMsg1').style.display = 'none';
                    document.getElementById('input-id').style.border = '';
                }

            }
            function idDuplicationChecck() {
                // 중복 체크를 위해 사용자가 입력한 아이디 값을 가져옴
                var enteredId = document.getElementById('input-id').value;
                
                // XMLHttpRequest 객체 생성
                var xhr = new XMLHttpRequest();
                
                // POST 방식으로 요청을 보낼 준비
                xhr.open('POST', '/myjoin', true);
                
                // 서버로 보낼 데이터 설정
                var data = 'enteredId=' + encodeURIComponent(enteredId);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                
                // 요청 완료 시 실행되는 콜백 함수
		        xhr.onload = function () {
		        	var response = xhr.responseText;
		        	
		        	alert(response);
		        	alert('ㅠㅠ');
		        	
		            if (response === '사용할 수 있는 아이디입니다.') {
		                alert('사용할 수 있는 아이디입니다.');
		            } else if (response === '이미 사용 중인 아이디입니다.') {
		                alert('이미 사용 중인 아이디입니다.');
		            }							            
		        };

                // 요청 보내기
                xhr.send(data);                
                
            }
            
            document.addEventListener('DOMContentLoaded', function() {
            	// id가 input-id인 요소에 focus 이벤트 리스너 추가
            var idInput = document.getElementById('input-id');
            idInput.addEventListener('focus', showMessage);
            idInput.addEventListener('input', validateAndShowErrorMessage);
            idInput.addEventListener('blur', validateAndShowErrorMessage);
            idInput.addEventListener('blur', idDuplicationChecck);
            });
            
        </script>
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <%@ include file="menu.jsp" %>
        
        <div class="mt-10"> 회원가입</div>
        
        <!-- join-->
        <section class="page-section" id="mail">
        	<h5 style="text-align: center; padding-top: 55px; margin-bottom: 32px;">
        		사람인 아이디로 커리어 관리 끝!
        	</h5>
            <div class="d-flex justify-content-center" >
               	<div class="text-center">
               		<form action="/mail" method="post" style="width: 360px">
               			<div>
               				<div style="text-align: left">
	               				<label>
	               					<strong>아이디</strong>
	               				</label>
	               			</div>
	               			<div class="input-group mb-3">
	  							<input type="text" name="id" class="form-control" id="input-id" aria-describedby="basic-addon3"  style="ime-mode:disabled" placeholder="4~20자리 / 영문, 숫자, 특수문자 '_'사용가능" >
							</div>
							<p class="alert_column focus_txt" id="idFocusMsg" style="display:none; font-size: small; text-align: left">4 ~ 20자의 영문, 숫자와 특수문자'_'만 사용해주세요.</p>
							<em class="msgInvalid" id="idCheckMsg1" style="display: none; margin-bottom: 16px; text-align: left">
								<span class="less_important" style="color: #d64141; font-size: small;">4 ~ 20자의 영문, 숫자와 특수문자 '_'만 사용해주세요.</span>
							</em>
							<!-- 오류 시 텍스트 -->
						</div>
               			<div class="input-group mb-3">
  							<span class="input-group-text" id="basic-addon3">제목</span>
  							<input type="text" name="title" class="form-control" id="basic-url" aria-describedby="basic-addon3">
						</div>
               			<div class="input-group mb-3" style="height: 300px;">
  							<span class="input-group-text">내용</span>
  							<textarea class="form-control" aria-label="With textarea" name="content"></textarea>
						</div>
               			<button type="submit" class="btn btn-primary">보내기</button>
               		</form>
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
