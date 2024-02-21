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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
function deletePost(){
   Swal.fire({
        title: "글을 삭제합니다",
        //text: "post를 삭제합니다.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes",
        cancelButtonText: "No",
      }).then(result => {
        if (result.isConfirmed) {
    	  //java에게 삭제하라고 명령내리겠습니다.
    	  //가상 form = post
    	  let vform = $("<form></form>");
    	  vform.attr("name", "vform");
    	  vform.attr("method", "post");
    	  vform.attr("action", "./postDel");
    	  vform.append($('<input/>', {type:'hidden', name:'no', value:${detail.board_no} }));
    	  vform.appendTo('body');
    	  vform.submit();
          //Swal.fire("삭제했습니다.","", "success");
        }
      });
}
function commentInsert(){
	let comment = $("comment").val();
	if(comment.length < 10){
		Swal.fire("댓글의 길이가 짧습니다.", "댓글은 10글자 이상이어야 합니다.", "warning");
		return false;
	}
}
//jquery start
$(function(){
	//댓글 몇 글자인지 확인하는 코드
	$("#comment").keyup(function(){
		let text = $(this).val();
		if(text.length > 500){
			swal.fire("댓글의 길이가 깁니다.", "댓글은 500자까지 가능합니다.", "warning");
			$(this).val(text.substr(0, 500));
		}
		$("#comment-input").text("댓글쓰기 " + text.length + "/500");
	});
});
/* 	var textareaValue = document.getElementById('comment').value.trim();
	
	if (textareaVAlue === '') {
		alert('내용을 입력하세요.');
		return false;  //폼 전송 중단
	} */
	
	
	

//댓글 삭제 버튼
function deleteComment(no){
	//swal.fire("댓글을 삭제합니다.", no+"번 글을 삭제합니다.", "warning");
	if (confirm("댓글을 삭제하시겠습니까?")) {
		location.href="./deleteComment?no=${detail.board_no}&cno="+no;
	}
}
</script>
</head>
<body id="page-top">
	<!-- Navigation-->
	<%@ include file="menu.jsp"%>

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
							${detail.mname }
							<c:if test="${detail.mid eq sessionScope.mid }">
								<img alt="edit" src="./img/edit.png">
								<img alt="delete" src="./img/delete.png" title="글삭제" onclick="deletePost(${detail.board_no })">
							</c:if>
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
			<!-- 댓글 입력창 - 스크립트로 빈 칸 검사하기 -->
<div>
    <form action="./commentWrite" method="post" onsubmit="return commentInsert()">
        <div class="row">
            <div class="col-xs-8 col-sm-10 col-md-11 col-xl-11" style="height: 100px; width: 100%;">
                <textarea class="form-control" id="comment" name="comment" aria-describedby="comment-input" style="height: 100px; width: 100%;"></textarea>
            </div>
            <div class="col-xs-4 col-sm-2 col-md-1 col-xl-1" style="width: 100%; margin-top: 20px; text-align: right;">
                <button class="btn btn-primary" type="submit" id="comment-input" style="width: auto">댓글쓰기 0/500</button>
            </div>
        </div>
        <input type="hidden" name="no" value="${detail.board_no }">
    </form>
</div>
			<!-- 댓글 출력창 -->
			<div class="mt-2">
				<c:forEach items="${commentsList }" var="c">
					<div class="my-4">
						<div class="bg-warning text-dark row my-2 p-2">
							<div class="col-7">
								${c.mname }
								<c:if test="${c.mid eq sessionScope.mid }">
									<img alt="edit" src="./img/edit.png">
									<img alt="delete" src="./img/delete.png" title="댓글삭제" onclick="deleteComment(${c.no})">
								</c:if>								
							</div>
							<div class="col-2">${c.cip }</div>
							<div class="col-2">${c.cdate }</div>
							<div class="col-1">${c.clike }</div>
						</div>
						<div class="mx-5 mt-1" style="min-height: 80px">${c.comment }</div>
					</div>
				</c:forEach>
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
