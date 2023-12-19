<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="kfs" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="asset"  uri="/WEB-INF/asset-tags/asset.tld"%>
    
<!DOCTYPE html>
<html>
<head>
<!-- =================================================== -->
<jsp:include page="../common/meta_css.jsp" flush="false" />
<!-- =================================================== -->
<title><c:out value="${pageTitle}" default="공통코드관리" /></title>
</head>
<style>
.table tbody tr.highlight td {
  background-color: #EAF0F7;
}
</style>
<body>
<!-- =================================================== -->
<jsp:include page="../common/header.jsp" flush="false" />
<!-- =================================================== -->
<main class="container mx-3 my-3">

	<h2><i class="fa-solid fa-cube my-3"></i> 공통코드관리</h2>
 	<div class="search-area">
 		<!-- 검색부분 조회,초기화 -->
 	</div>
 	<div class="row">
 		<div class="col-6">
 			<table class="table table-sm table-hover comCdTable" style="font-size:small">
			  <thead class="table-light">
			    <tr class="text-center align-middle">
			      <th scope="col" style="width:30px">No</th>
			      <th scope="col">공통코드</th>
			      <th scope="col">공통코드명</th>
			      <th scope="col" style="width:40px">순서</th>
			      <th scope="col" style="width:40px">사용</th>
			      <th scope="col">비고</th>
			      <th scope="col" style="width:75px">
			          <button class="btn btn-success btn-sm btnCategoryInsert"  data-bs-target="#newComCd"><span><i class="fa-regular fa-pen-to-square"></i> 추가</span></button>
		          </th>
			    </tr>
			  </thead>
 			 <tbody class="table-group-divider" >
			  	<c:forEach var="code" items="${listCategory}" varStatus="status">    
			    <tr class="align-middle" >
			      <td scope="row" class="text-center fw-bold">${status.count }</td>
			      <td class="com02ComCd clickableRow">${code.com02ComCd}</td>
			      <td class="clickableRow com02CodeNm">${code.com02CodeNm}</td>
			      <td class="text-center com02Seq">${code.com02Seq}</td>
			      <td class="text-center">${code.com02UseYn}</td>
			      <td class="com02Note">${code.com02Note}</td>
			      <td>		      	
			       <input type="hidden" name="com02UseYn" value="${code.com02UseYn }"/>
	               <button class="btn btn-primary btn-sm btnCategoryModify"   data-bs-target="#newComCd"><span><i class="fa-regular fa-pen-to-square"></i></span></button>
			       <button class="btn btn-danger btn-sm btnCategoryDelete" data-comcd="${code.com02ComCd}" data-comnm="${code.com02CodeNm}"><span><i class="fa-regular fa-trash-can"></i></span></button>
			      </td>
			    </tr>
				</c:forEach>
			  </tbody>
			</table>
 		</div>
 		<div class="col-6">
 			<!-- 상세코드리스트 -->
 		</div> 		
 	</div>
</main>

<div class="modal fade" id="newComCd" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
	<div class="modal-content">
		<form method="post" id="formNewCode" class="validcheck">
		<!-- hidden변수 formNewCode_Mode 로 추가/수정을 구분하려고 함 -->
		<input type="hidden" id="formNewCode_Mode" />
		<input type="hidden" name="com02DtlCd" value="NONE" />
		<input type="hidden" name="com02CodeType" value="C" />
		
		<div class="model-header text-center">
			<h5 class="modal-title fw-bold">공통코드 추가</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		
		<div class="modal-body">
			<div class="mb-3">
				<label for="com02ComCd" class="form-label fw-bold">공통코드</label>
				<input class="form-control is-valid" name="com02ComCd" placeholder="코드(알파벳만)" required pattern="[a-z|A-Z]+" data-v-min-length="3" data-v-max-length="100" />
			</div>
			<div class="mb-3">
				<label for="com02ComNm" class="form-label fw-bold" >공통코드명</label>
				<input type="text" class="form-control is-valid" name="com02CodeNm" placeholder="공통코드명" required />
			</div>
			<div class="mb-3">
				<label for="com02Seq" class="form-label fw-bold">순서</label>
				<input type="text" class="form-control" name="com02Seq" required pattern="[0-9]+" />
			</div>
			<div class="mb-3 align-middle">
				<label for="com02UseYn" class="form-label fw-bold">사용여부</label>&nbsp;
				<input type="radio" name="com02UseYn" id="com02UseYn11" value="true" checked /> <label for="com02UseYn11">사용함</label>&nbsp;
				<input type="radio" name="com02UseYn" id="com02UseYn22" value="false"/> <label for="com02UseYn22"> 사용안함</label>
			</div>
			<div class="mb-3">
	            <label for="com02Note" class="form-label align-top fw-bold">코드설명</label>
	            <textarea class="form-control" name="com02Note" rows="3" cols="50"></textarea>
	        </div>
	    </div>
		<div class="modal-footer d-flex justify-content-center">
	        	<button type="button" class="btn btn-primary" id="btnInsertComCd">저장</button>
	            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			</div>
		</form>
	</div>
  </div>
</div>

<!-- =================================================== -->
<jsp:include page="../common/footer.jsp" flush="false" />
<!-- -================================================== -->
<script>
$(document).ready(function () {
	console.log('ready...');
	
	Handlebars.registerHelper("inc", function(value, options){
		return parseInt(value) + 1;
	});
 	
	$('.btnCategoryInsert').on('click', function(){
		alert('공통코드 추가버튼');
		var $modal = $('#newComCd');
		$modal.modal('show');
	});
	
	$('.btnCategoryModify').on('click', function(){
		alert('공통코드 수정');
	});
	
});
</script>
	
</body>
</html>