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
<title><c:out value="${pageTitle}" default="펀드선택" /></title>
</head>
<style>
.table tbody tr.highlight td {
  background-color: #EAF0F7;
}
</style>
<body>
<main class="container-fluid mt-3">
	<form id="form1" action="/popup/fund" method="get">
		<input type="hidden" name="fundCd"   value="${param.fundCd   }"/>
 		<input type="hidden" name="fundNm"   value="${param.fundNm   }"/>
        <input type="hidden" name="parentYn" value="${param.parentYn }"/>
 		<input type="hidden" name="pageSize" value="${pageAttr.pageSize }"/>
<%--  		<input type="hidden" name="currentPageNumber" value="${pageAttr.currentPageNumber }"/> --%>
 		<input type="hidden" name="currentPageNumber" value="1"/>
 		
 		<div>
 			<input type="text" class="form-control w-50 d-inline align-middle" placeholder="검색어(펀드코드/펀드명)를 입력하세요" id="searchText" name="searchText" value="${param.searchText}">
 			<button class="btn d-inline align-middle btn-primary btnRetrieve"><i class="fa-solid fa-search"></i> 조회</button>
 			<button class="btn d-inline align-middle btn-success btnInit"><i class="fa-solid fa-backspace"></i> 조회</button>
 		</div>
	</form>
	
	<table class="table table-hover table-sm fundTable" style="font-size:small">
		<thead class="table-light">
			<tr class="text-center">
				<th scope="col" style="width:20px">&nbsp;</th>
				<th scope="col" style="width:100px">펀드코드</th>
				<th scope="col">펀드명</th>
			</tr>
		</thead>
	</table>
</main>
</body>
</html>