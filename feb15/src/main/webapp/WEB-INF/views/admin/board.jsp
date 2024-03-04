<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
	
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	   crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	<!-- Custom fonts for this template-->
	<link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
	
	<!-- Custom styles for this template-->
	<link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">
	
    <jsp:include page="head.jsp"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script type="text/javascript">
    function linkPage(pageNo){
    	location.href = "./board?pageNo="+pageNo+"&perPage=${perPage}&search=${search}";
    }
    
    function postDelToggle(no, del) {
    	location.href="./postDel?no="+no+"&del="+del;
    }
    
    $(function(){
    	$('#perPage').change(function(){
    		location.href="./board?pageNo=1&perPage="+$('#perPage').val()+"&search=${search}"; //여기 search는 model에서 오는 것
    	});
    	$('#searchBtn').click(function(){
    		location.href="./board?pageNo=1&perPage=${perPage}&searchTitle="+$('#searchTitle').val()+"&search="+$('#search').val();
    	});
    	$('#reset').click(function(){
    		location.href="./board";
    	})
    });
    </script>
</head>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <jsp:include page="menu.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <jsp:include page="header.jsp"/>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="m-2 row">
                    	<select name="perPage" id="perPage" class="form-control col-2">
                    		<option value="1" <c:if test="${perPage eq 1 }">selected="selected"</c:if>>10개씩 보기</option>
                    		<option value="2" <c:if test="${perPage eq 2 }">selected="selected"</c:if>>20개씩 보기</option>
                    		<option value="3" <c:if test="${perPage eq 3 }">selected="selected"</c:if>>30개씩 보기</option>
                    		<option value="4" <c:if test="${perPage eq 4 }">selected="selected"</c:if>>40개씩 보기</option>
                    		<option value="5" <c:if test="${perPage eq 5 }">selected="selected"</c:if>>50개씩 보기</option>
                    		<option value="10" <c:if test="${perPage eq 10 }">selected="selected"</c:if>>100개씩 보기</option>
                    	</select>
                    	<div class="input-group col-6">
                    		<select name="searchTitle" id="searchTitle" class="form-control col-3">
                    			<option value="1" <c:if test="${searchTitle eq 1 }">selected="selected"</c:if>>제목 검색</option>
                    			<option value="2" <c:if test="${searchTitle eq 2 }">selected="selected"</c:if>>본문 검색</option>
                    			<option value="3" <c:if test="${searchTitle eq 3 }">selected="selected"</c:if>>작성자 검색</option>
                    		</select>
	                    	<input type="text" name="search" id="search" class="form-control" value="${search }">
    	                	<button type="button" class="btn btn-secondary" id="searchBtn">검색</button>
                    	</div>
                    	<button type="button" id="reset" class="btn btn btn-success col-4">초기화</button>
                    </div>
					<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>글쓴이 번호</th>
							<th>닉네임</th>
							<th>글 쓴 날짜</th>
							<th>조회수</th>
							<th>삭제여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr <c:if test="${row.board_del eq 0}" >style="background-color: lightgray"</c:if>>
								<td onclick="detail(${row.board_no })">${row.board_no }</td>
								<td class="title">
									${row.board_title }
								</td>
								<td>${row.mno }</td>
								<td>
									<a href="./board?searchTitle=3&search=${rwo.mname }">
										${row.mname }
									</a>
								</td>
								<td>${row.board_date }</td>
								<td>${row.board_count }</td>
								<td>
									<c:choose>
										<c:when test="${row.board_del eq 1}">
											<i class="fa fa-eye" aria-hidden="true" onclick="postDelToggle(${row.board_no}, ${row.board_del} )"></i>
										</c:when>
										<c:otherwise>
											<i class="fa fa-eye-slash" aria-hidden="true" onclick="postDelToggle(${row.board_no}, ${row.board_del})"></i>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="m-2 text-center">
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="linkPage"/>
				</div>
				
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <jsp:include page="footer.jsp"/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>