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
<title><c:out value="${pageTitle}" default="종목정보-리스트" /></title>
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

    <h2><i class="fa-solid fa-cube my-3"></i> 주식종목정보관리</h2>
    
    <div class="container-lg p-3 border border-2 rounded-1">
    	<input type="text" class="form-control w-50 d-inline align-middle" placeholder="검색어(종목코드/종목명/단축코드)를 입력하세요" id="searchText" name="searchText" value="${paramsearchText }">
    	<a class="btn d-inline align-middle btn-primary btnRetrieve"><i class="fa-solid fa-search"></i>조회</a>
    	<a class="btn d-inline align-middle btn-secondary btnInit"><i class="fa-solid fa-backspace"></i>초기화</a>
    	<a class="btn d-inline align-middle btn-success" href="/item/insert"><i class="fa-solid fa-pencil-alt"></i>등록</a>
    </div>
    
    <table class="table table-sm table-hover"
    
</main>
</body>
</html>