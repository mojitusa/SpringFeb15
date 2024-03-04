<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="head.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	function linkPage(pageNo){
		location.href = "./board?pageNo="+pageNo+"&perPage=${perPage}&search=${search}";
	}
	$(function(){
		#('perPage').change(function(){
			location.href="./board?pageNo=${pageNo}&perPage="+$('#perPage').val()+"$search=${search}";
		});
		$('#searchBtn').click(function(){
			location.href="./board?pageNo=1$perPage=${peraPage}&search="$('#search').val();
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
						<select name="perPage" id="perPage" class="form-control">
							<option value="1">10</option>
							<option value="2">20</option>
							<option value="3">30</option>
							<option value="4">40</option>
							<option value="5">50</option>
							<option value="10">1000</option>
						</select>
						<div class="input-group col-6">
							<input type="text" name="search" id="search" class="form-control" value="${search }">
							<button type="button" class="btn btn-secondary" id="searchBtn">검색</button>
						</div>
						<button class="btn btn-info col-4">초기화</button>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>글번호</th>
								<th>제목</th>
								<th>글쓴이번호</th>
								<th>닉네임</th>
								<th>글 쓴 날짜</th>
								<th>아이피</th>
								<th>삭제여부</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="row">
								<tr>
									<td>${row.board_no }</td>						
									<td>${row.board_title }</td>						
									<td>${row.mno }</td>						
									<td>${row.mname }</td>						
									<td>${row.board_date }</td>						
									<td>${row.board_ip }</td>						
									<td>${row.board_del }</td>						
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div class="m-2">
						<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage"/>
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