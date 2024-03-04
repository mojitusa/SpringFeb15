<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>jQuery Element Position</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<style>
		#divBox { 
			width: 100px;
			height: 50px;
			border: 1px solid lightgray;
			margin: 10px;
			padding: 5px;
		}
		#para {
			font-weight: bold;
			margin-left: 15px;
		}
	</style>
	<script>
		$(function() {
			$("button").on("click", function() {
				var str = "";
				var offsetPosition = $("#para").offset();		// offset() 메소드를 사용해 id가 "para"인 요소의 위치 정보
				str += "offset() 메소드를 사용하여 구한 p 요소의 위치는<br>";
				str += "left가 " + offsetPosition.left + "픽셀이고, ";		// id가 "para"인 요소의 left 위치를 반환함.
				str += "top이 " + offsetPosition.top + "픽셀입니다.<br>";	// id가 "para"인 요소의 top 위치를 반환함.
				var posPosition = $("#para").position();		// position() 메소드를 사용해 id가 "para"인 요소의 위치 정보
				str += "position() 메소드를 사용하여 구한 p 요소의 위치는<br>";
				str += "left가 " + posPosition.left + "픽셀이고, ";			// id가 "para"인 요소의 left 위치를 반환함.
				str += "top이 " + posPosition.top + "픽셀입니다.";			// id가 "para"인 요소의 top 위치를 반환함.
				$("#text").html(str);
			});
		});
	</script>
</head>

<body>

	<div id="divBox">
		<p id="para">p 요소</p>
	</div>
	<button>요소의 위치 정보</button>
	<p id="text"></p>
	
</body>

</html>