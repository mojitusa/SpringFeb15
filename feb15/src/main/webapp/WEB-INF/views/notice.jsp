<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.time.LocalDate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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
        <link href="css/board.css" rel="stylesheet" />
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<!-- 서머노트 -->
		<script src="/js/summernote/summernote-lite.js"></script>
		<script src="/js/summernote/lang/summernote-ko-KR.js"></script>

		<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
		<!-- Core theme CSS (includes Bootstrap)-->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
        
	        function writeCheck() {
	            let title = document.querySelector("#title");
	            let content = $('#summernote').summernote('code'); // Summernote에서 HTML 콘텐츠 가져오기
	            
	            console.log("Title Length: " + title.value.length);
	            console.log("Content Length: " + content.length);
	
	            if (title.value.length < 5) {
	                alert("제목을 다섯 글자 이상으로 입력해 주세요.");
	                title.focus();
	                return false;
	            }

	            // HTML 콘텐츠 내의 텍스트 길이를 확인하려면 HTML 태그를 제거한 다음 길이를 계산합니다.
	            let plainTextContent = content.replace(/<[^>]*>/g, ''); // HTML 태그 제거
	            if (plainTextContent.length < 10) {
	                alert("본문 내용을 열 글자 이상으로 입력해 주세요.");
	                $('#summernote').summernote('focus'); // 다시 Summernote에 포커스 설정
	                return false;
	            }
	
	        }
        	
        	function detail(no){
        		//swal("Good job!", "상세보기입니다.", "success");
        		/* swal({
        			title: "good job!",
        			text: "번호는 " + no,
        			icon: "success",
        			button: "좋아요"
        		}); //title, text, icon, button */
        		
        		//모달 보이기
        		let detailModal = new bootstrap.Modal('#detail', {}); //{옵션}
        		//$("#modalTitle").text(no);
        		//$("#modalContent").text("변경된 내용입니다.");
        	    //detailModal.show();
        	      
        		$.ajax({
        			url		 : "/restDetail",
        			type	 : "post",
        			dataType : "json",
        			data	 : {'no' : no},
        			success	 : function(data){ //text -> json
        				//alert(data.board_title);
                		$("#modalTitle").text(data.board_title);
                		$("#modalContent").text(data.board_content);
                	    detailModal.show();
        			},
        			error 	 : function(error){
        				alert(error);
        			}
        		});	
        	}
        	
        	//전자정부 페이징 이동하는 스크립트
        	function linkPage(pageNo){
        		location.href = "./notice?pageNo="+pageNo;
        	}  
        	
        	//유투브처럼 몇 시간 전, 몇 일전으로 표시하도록 하는 함수
			function formatTimeAgo(dateString) {
			    const currentDate = new Date();
			    const date = new Date(dateString); // 날짜 포맷 변경
			    const timeDifference = currentDate - date;
			    const seconds = Math.floor(timeDifference / 1000);
			    const minutes = Math.floor(seconds / 60);
			    const hours = Math.floor(minutes / 60);
			    const days = Math.floor(hours / 24);
			    const weeks = Math.floor(days / 7);
			    const months = Math.floor(days / 30);
			    const years = Math.floor(days / 365);
			    
			    console.log("Seconds: " + seconds);
			    console.log("Hours: " + hours);
			
			    if (seconds < 60) {
			        return seconds + "초 전";
			    } else if (minutes < 60) {
			        return minutes + "분 전";
			    } else if (hours < 24) {
			        /* return hours + '시간 전'; */
			        return `${'${hours}'}시간 전`;
			    } else if (days === 1) {
			        return '어제';
			    } else if (days < 7) {
			        return days + "일 전";
			    } else if (weeks < 4) {
			        return weeks + "주 전";
			    } else if (months < 12) {
			        return months + "개월 전";
			    } else {
			        return `${'${years}'}년 전`;
			    }
			}
		
		    document.addEventListener('DOMContentLoaded', function () {
		        const dateElements = document.querySelectorAll('.formattedTime');
		
		        dateElements.forEach(function (element) {
		            const dateString = element.textContent;
		            const formattedTime = formatTimeAgo(dateString);
		            element.textContent = formattedTime;
		        });
		    });       	
        	
        </script>
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <%@ include file="menu.jsp"%>
        
        <!-- 게시판 -->
        <section class="page-section" id="services">
            <div class="container">
                <div class="text-center">
                    <h2 class="section-heading text-uppercase">공지사항</h2>
                </div>
                <div class="row text-center">
                	${fn:length(list) }개가 있습니다. 
                	<c:choose>
                		<c:when test="${fn:length(list) gt 0 }">
                			<table>
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>날짜</th>
										<th>읽음</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="row">
										<tr onclick="location.href='./noticedetail?no=${row.nno}'">
											<td>${row.nno }</td>
											<td>
												${row.ntitle }
												<c:if test="${row.ndate ge LocalDate.now()}"> 
													<span class="badge">N</span>
												</c:if>
											</td>
											<td class="date">
											    <c:set var="formattedTime">
													<fmt:parseDate var="formattedDate" value="${row.ndate}" pattern="yyyy-MM-dd HH:mm:ss"/>
													<fmt:formatDate value="${formattedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
												</c:set>
											    <span class="formattedTime">${formattedTime}</span>
											</td>
											<td>${row.nread }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- paging -->
							<div class="m-2 bg-secondary">
								<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="linkPage"/>					
							</div>							
                		</c:when>
                		<c:otherwise>
                			<h1>출력할 데이터가 없습니다.</h1>
                		</c:otherwise>
                	</c:choose>
                	
					<!-- 글쓰기 버튼 -->
					<c:if test="${sessionScope.mid ne null}">
						<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#write">글쓰기</button>
					</c:if>
                </div>
            </div>
        </section>
        
        <!-- 글쓰기 모달 만들기 -->
        <div class="modal" id="write">
        	<div class="modal-dialog modal-xl">
        		<div class="modal-content">
        			<div class="modal-header">
        				<h5 class="modal-title">글쓰기 창입니다.</h5>
        				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        			</div>
        			<div class="modal-body">
        				<div class="mt-2">
	        				<form action="./write" method="post" onsubmit="return writeCheck()" name="frm">
	        					<input type="text" id="title" name="title" class="form-control bm-2" required="required" placeholder="제목을 입력하세요">
	        					<textarea id="summernote" name="content" class="form-control mb-2 vh-500" required="required"></textarea>
	        					<button type="submit" class="btn btn-info">글쓰기</button>
	        				</form>
        				</div>
        			</div>
        			<div class="modal-footer">
        				모달 푸터
        				2024-02-19 웹 표준 기술 /RESTAPI / RESTFULL
        			</div>
        		</div>
        	</div>
        </div>   
        <!-- 톱아보기 모달 -->
        <div class="modal" id="detail">
        	<div class="modal-dialog modal-xl">
        		<div class="modal-content">
        			<div class="modal-header">
        				<h5 class="modal-title" id="modalTitle">톱아보기</h5>
        				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        			</div>
        			<div class="modal-body">
        				<div class="mt-2" id="modalContent">
        					제목<br>
        					본문내용
        				</div>
        			</div>
        			<div class="modal-footer">
						톱아보기 모달 닫기
        			</div>
        		</div>
        	</div>
        </div>       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        <script type="text/javascript">
        	$(function() {
        		$('#summernote').summernote({
        			lang: 'ko-KR', // default: 'en-US'
        			height: 600,
        			fontNames : ['D2Coding', 'Arial Black', 'Comic Sans MS', 'Courier New'],
        			toolbar: [
        			    // [groupName, [list of button]]
        			    ['style', ['bold', 'italic', 'underline', 'clear']],
        			    /* ['font', ['strikethrough', 'superscript', 'subscript']], */
        			    ['fontname', ['fontname','fontsize', 'color']],
        			    ['para', ['ul', 'ol', 'paragraph']],
        			    /* ['height', ['height']] */
        			    ['table', ['table','link', 'picture', 'video']],
        			    ['view', ['fullscreen', 'codeview', 'help']]
        			  ]
        		});
        	});
        </script>
    </body>
</html>
