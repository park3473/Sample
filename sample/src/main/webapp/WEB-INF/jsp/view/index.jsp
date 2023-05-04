<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--삭제금지-->
<script src="${pageContext.request.contextPath}/resources/sweetalert/jquery-1.12.4.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->

<!--삭제금지-->

<c:if test="${ssion_langage == 'kr'}">KOR</c:if>
<c:if test="${ssion_langage != 'kr'}">ENG</c:if>

<!--공통 헤더 시작-->
<%@ include file="../include/user//header.jsp" %>
<%@ include file="../include/user/menu.jsp" %>
<!--공통 헤더 끝-->
<c:if test="${sessionScope.Login != 'OkOk' }">
	<a href="${pageContext.request.contextPath}/view/login.do">로그인 페이지 이동</a>
</c:if>
<c:if test="${sessionScope.Login == 'OkOk' }">
	<a href="${pageContext.request.contextPath}/view/login.do">로그아웃</a>
	<a href="${pageContext.request.contextPath}/admin/index.do">관리자</a>
</c:if>

<!--공통하단-->
<%@ include file="../include/user/footer.jsp" %>
<script type="text/javascript">