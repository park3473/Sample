<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$.ajax({
		type : 'POST',
		url : '${pageContext.request.contextPath}/view/menu.do',
		cache : false,
		success : function(result , status , xhr){
			console.log(result);
		}
	})
</script>

<div id="menu">
	<!-- 메뉴 생성 -->
</div>

<!-- 에시 -->
<!-- <div>
	<ul>
		<li>메뉴 1</li>
	</ul>
</div>
 -->