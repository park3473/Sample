<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="../include/header.jsp" %>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- ckeditor필요한 부분 -->
<script src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.css">
<script src="https://ckeditor.com/apps/ckfinder/3.5.0/ckfinder.js"></script>
<style>
	/*admin css 와 ckeditor css 충돌나서 바꿔버림*/
	.member_input_wrap .member_input button {
    margin-left: 0px !important;
	}
	.member_input_wrap .member_input textarea {
    	width: 100%;
    	height: 100%;
    	padding: 0px;
    }
</style>
<!-- ckeditor필요한 부분 -->

<body>
<!--헤더-->
<%@ include file="../include/menu.jsp" %>
<!--헤더 end-->


<!--본문-->
<section id="adm_sc">
    <div id="adm_sc_area">
        <div id="adm_sc_con">
            <div class="adm_sc_size">

                <!--본문 내용-->
                <section class="adm_sc_txt">
                	<div>
                    <form action="./insert.do" method="post" name="insertForm" id="insertForm" enctype="multipart/form-data">
                        <input type="hidden"  name="csrf" value="${CSRF_TOKEN}" />
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>문제 등록</span>
                            </div>
                            
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                        	<span class="list_t">타입</span>
                                        	<input class="input_size mr" type="text" name="type" id="type"  value="${model.view.type }" readonly="readonly">
                                        </li>
                                        <li>
                                            <span class="list_t">문제 제목</span>
                                            <input class="input_title" type="text" id="name" name="name" value="${model.view.name }" readonly="readonly" >
                                        </li>
                                        <li>
                                            <span class="list_t">진단 목표</span>
                                            <input class="input_title" type="text" id="objectives" name="objectives" value="${model.view.objectives }" readonly="readonly" >
                                        </li>
                                        <li>
                                            <span class="list_t">답안 타입</span>
                                            <c:if test="${model.view.select_type == '0' }">
                                            	<input class="input_title" type="text" id="select_type" name="select_type" value="OX퀴즈" readonly="readonly" >
                                            </c:if>
                                            <c:if test="${model.view.select_type == '1' }">
                                            	<input class="input_title" type="text" id="select_type" name="select_type" value="다지선다" readonly="readonly" >
                                            </c:if>
                                        </li>
                                        <li>
                                            <span class="list_t">답안</span>
                                            <input class="input_size mr" type="text" id="select_val" name="select_val" value="${model.view.select_val }" readonly="readonly" >
                                        </li>
                                        <li>
                                        	<span class="list_t">문제 내용</span>
                                        	<textarea name="content" id="editor">${model.view.content }</textarea>
                                        </li>
                                        <li>
                                            <span class="list_t">해설</span>
                                            <input class="input_title" type="text" id="solution" name="solution" value="${model.view.solution }" readonly="readonly" >
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                    <div>
                    	<form action="./insert.do" method="post" name="select_insertForm" id="select_insertForm" enctype="multipart/form-data">
                    		<input type="hidden" name="select_confrim" value="false">
                    		<div class="member_register_wrap">
	                    		<div class="title">
	                                <span>답안 등록</span>
	                            </div>
	                            <div class="member_input_wrap" id="select_input_warp">
	                            	<c:forEach items="${model.list}" var="item" varStatus="status">
		                            	<ul class="member_input" id="select_ul_${status.index }">
		                            		<li>번호 : ${item.seq }</li>
		                            		<li>내용 : ${item.content }</li>
		                            		<c:if test="${item.image != ''}">
		                            			<li>이미지 : <span onclick="img_modal('${item.image}')">이미지 보기</span></li>
		                            		</c:if>
		                            		<c:if test="${item.image == ''}">
		                            			<li>이미지 : X</li>
		                            		</c:if>
		                            	</ul>
	                            	</c:forEach>
	                            </div>
                            </div>
                    	</form>
                    	
                    </div>
                </section>
                <!--본문 내용 end-->
            </div>
        </div>
    </div>
</section>
<!--본문 end-->

<!--푸터-->
<footer>
<%@ include file="../include/footer.jsp" %>
</footer>
<!--푸터 end-->

<!--  JQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>



</body>
</html>
<script type="module" >
	import editor from '/resources/ckeditor/editor.js'
    $(document).ready(function () {
        editor("#editor").then(editor => {
        	// some code..
            // then 이후에 받은 editor를 다른 변수로 받아주시는 편이 좋습니다!
        })
    })
</script>
<script type="text/javascript">

$(document).ready(function () {
	
	$(".adm_menu_con > li").eq(3).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(3).css({
	    backgroundColor: "#fff"
	});
	
	
});

function img_modal(image){
	
	window.open('/resources/upload/ckeditor/'+image , '이미지 확인', 'width=500, height=500');
	
}


</script>