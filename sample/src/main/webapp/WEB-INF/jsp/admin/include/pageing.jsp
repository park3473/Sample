<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<ul class="page">

			<c:if test="${model.page > 0}"> 
				<c:if test="${model.itempagestart-model.itemcount > 0}">
	                 <li id="first_a"><a href="javascript:pageChanged(0, 0);"><img src="${pageContext.request.contextPath}/resources/img/first_arrow.png" alt="맨처음"></a></li>
	                 <li id="prev_a"><a href="javascript:pageChanged(${model.page-1}, ${model.itempageend});"><img src="${pageContext.request.contextPath}/resources/img/prev_arrow.png" alt="뒤로"></a></li>
				</c:if>
				<c:if test="${model.itempagestart-model.itemcount <= 0}">
					<li id="first_a"><a href="javascript:pageChanged(0,0);"><img src="${pageContext.request.contextPath}/resources/img/first_arrow.png" alt="맨처음"></a></li>
					<li id="prev_a"><a href="javascript:pageChanged(${model.page-1}, ${model.itempageend});"><img src="${pageContext.request.contextPath}/resources/img/prev_arrow.png" alt="뒤로"></a></li>
				</c:if>
			</c:if>

			<c:if test="${model.page <= 0}">
				  <li id="first_a"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/first_arrow.png" alt="맨처음"></a></li>
			      <li id="prev_a"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/prev_arrow.png" alt="뒤로"></a></li>
			</c:if>
			
			<c:if test="${model.itempageend > 0}">
				<c:forEach var="i" begin="${model.itempagestart}" end="${model.itempageend}">
					<c:choose>
						<c:when test="${model.page == i }">
							<li class="number page_active"><a href="#">${i+1}</a></li>
						</c:when>
						<c:otherwise>
							<li class="number"><a href="javascript:pageChanged(${i}, ${model.itempageend});">${i+1}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>

			<c:if test="${model.itempageend == 0}">
				<li class="number">1</li>
			</c:if>
			<c:if test="${model.itempageend < 0}">
				<li class="number">1</li>
			</c:if>
			
			<c:if test="${model.itemcount < model.itemtotalcount/(model.page+1)}">
				<li id="next_a"><a href="javascript:pageChanged(${model.page+1}, ${model.itemtotalpage});"><img src="${pageContext.request.contextPath}/resources/img/next_arrow.png" alt="다음"></a></li>
			</c:if>
			<c:if test="${model.itemcount >= model.itemtotalcount/(model.page+1)}">
				<li id="next_a"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/next_arrow.png" alt="다음"></a></li>
			</c:if>

			<fmt:parseNumber var="lastPage" value="${(model.itemtotalcount % model.itemcount)==0? ((model.itemtotalcount/model.itemcount)-1) : (model.itemtotalcount / model.itemcount)}" integerOnly="true" />
			<c:choose>
				<c:when	test="${model.itemcount < model.itemtotalcount/(page+1)}">
				<li id="end_a"><a href="javascript:pageChanged(${model.itemtotalpage-1}, ${model.itemtotalpage-1});"><img src="${pageContext.request.contextPath}/resources/img/end_arrow.png" alt="맨끝"></a></li>
					
				</c:when>
				<c:otherwise>
				<li id="end_a"><a href="#"><img src="${pageContext.request.contextPath}/resources/img/end_arrow.png" alt="맨끝"></a></li>
				</c:otherwise>
			</c:choose>
			
			</ul>
			
			
			<script>			
function pageChanged(page, endpage)
{
	if (page < 0) 
	{
		page = 0;
	}
	if (endpage < 0){
		endpage = 0;
	}
	if (page >= endpage) {
		page = endpage;
	}
	
	
	var URL = "${requestURI}?PAGE="+page;

	
	if('${model.beforeDomain.ITEM_COUNT}' == '')
	{
		URL = URL + "&ITEM_COUNT=" + '10';
	}else
	{
		URL = URL + "&ITEM_COUNT=" + '${model.beforeDomain.ITEM_COUNT}';
	}
	

	URL = URL + "&SEARCH_TEXT=" + encodeURI('${model.beforeDomain.SEARCH_TEXT}');
	URL = URL + "&SEARCH_TYPE=" + '${model.beforeDomain.SEARCH_TYPE}';
	
	
	location.href = URL;
}


</script>
	