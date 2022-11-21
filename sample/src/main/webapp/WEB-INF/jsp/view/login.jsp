<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--삭제금지-->
<script src="${pageContext.request.contextPath}/resources/sweetalert/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->

<!--삭제금지-->

<c:if test="${ssion_langage == 'kr'}">한글</c:if>
<c:if test="${ssion_langage != 'kr'}">영어</c:if>

<!--공통 헤더 시작-->
<%@ include file="./include/header.jsp" %>
<%@ include file="./include/menu.jsp" %>
<!--공통 헤더 끝-->

<form action="${pageContext.request.contextPath }/view/login.do" method="POST">
	<input type="text" name="member_id">
	<input type="text" name="password">
	<button type="button" onclick="login()">전송</button>
</form>

<!--공통하단-->
<%@ include file="./include/footer.jsp" %>
<script type="text/javascript">