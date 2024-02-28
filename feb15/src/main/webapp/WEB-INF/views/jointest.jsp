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
        
        <script type="text/javascript">
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
        	
            // 포커스가 갔을 때 이벤트를 처리하는 함수
            function handleFocus() {
                // 클릭 시 메시지를 보여주는 부분
                document.getElementById('idFocusMsg').style.display = 'block';
            }
            function handleFocusOut() {
                // 클릭 시 메시지를 보여주는 부분
                document.getElementById('idFocusMsg').style.display = 'none';
            }

            // DOM이 로드된 후 실행되는 함수
            document.addEventListener('DOMContentLoaded', function() {
                // id가 input-id인 요소에 focus 이벤트 리스너 추가
                var idInput = document.getElementById('input-id');
                idInput.addEventListener('focus', handleFocus);
                idInput.addEventListener('blur', handleFocusOut);
            });       	
        </script>
</head>
<body>
	<input type="text" name="id" class="form-control" id="input-id" aria-describedby="basic-addon3"  style="ime-mode:disabled" placeholder="4~20자리 / 영문, 숫자, 특수문자 '_'사용가능" >
	<p class="alert_column focus_txt" id="idFocusMsg" style="display:none; font-size: small; text-align: left">4 ~ 20자의 영문, 숫자와 특수문자'_'만 사용해주세요.</p>
</body>
</html>