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
                        <input type="hidden" name="exam_idx" value="${model.exam_idx }">
                        <input type="hidden" name="question_type" value="new">
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>문제 등록</span>
                            </div>
                            
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                    	<c:if test="${model.exam_idx != '' }">
	                                        <li id="list_input_seq">
	                                            <span class="list_t">문항 번호</span>
	                                            <input class="input_size mr" type="text" id="seq" name="seq">
	                                        </li>
                                        </c:if>
                                        <li>
                                        	<span class="list_t">타입</span>
                                        	<input class="input_size mr" type="text" name="type" id="type" list="type_list" >
                                        	<c:if test="${model.TypeList.size() > 0 }">
                                        	<c:forEach items="${model.TypeList }" varStatus="status" var="item">
                                        	<datalist id="type_list">
                                        		<option>${item.type }</option>
                                        	</datalist>
                                        	</c:forEach>
                                        	</c:if>
                                        	<span><button type="button" onclick="question_select()">문제 가져오기</button></span>
                                        </li>
                                        <li>
                                            <span class="list_t">문제 제목</span>
                                            <input class="input_title" type="text" id="name" name="name">
                                        </li>
                                        <li>
                                            <span class="list_t">진단 목표</span>
                                            <input class="input_title" type="text" id="objectives" name="objectives">
                                        </li>
                                        <li>
                                            <span class="list_t">답안 타입</span>
                                            <select name="select_type" id="select_type">
                                            	<option value="0">OX 퀴즈</option>
                                            	<option value="1">다지선다</option>
                                            </select>
                                        </li>
                                        <li>
                                            <span class="list_t">답안</span>
                                            <input class="input_size mr" type="text" id="select_val" name="select_val">
                                            <span class="relate_c">OX 퀴즈일시 OX , 다지선다일 경우 해당 번호</span>
                                        </li>
                                        <li>
                                        	<span class="list_t">문제 내용</span>
                                        	<textarea name="content" id="editor"></textarea>
                                        </li>
                                        <li>
                                            <span class="list_t">해설</span>
                                            <input class="input_title" type="text" id="solution" name="solution">
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con" id="admin_button">
                                <a class="storage" href="javascript:insertClick()">문제 저장</a>
                                <a class="cancel" onclick="">답안 설정</a>
                                <a class="storage" href="javascript:history.back()">뒤로 가기</a>
                            </div>
                        </div>
                        <!--저장하기 버튼 end-->
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

// CKEditor 인스턴스를 저장할 변수를 정의합니다.
window.ckeditorInstance;

$(document).ready(function () {
    editor("#editor").then(editor => {
        // CKEditor 인스턴스를 저장합니다.
        window.ckeditorInstance = editor;

        // some code..
    })
})



</script>
<script type="text/javascript">

$(document).ready(function () {
	
	$(".adm_menu_con > li").eq(3).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(3).css({
	    backgroundColor: "#fff"
	});
	
	console.log($('.ck-editor__editable')[0]);
	
	
});

function insertClick()
{

	if($('[name=seq]').val() == '')
	{
		alert('문항을 입력 하여 주세요.');
		return;
	}else if($('[name=type]').val() == ''){
		
		alert('타입을 입력 하여 주세요.');
	}else if($('[name=name]').val() == ''){
		
		alert('제목을 입력 하여 주세요.');
	}else if($('[name=objectives]').val() == ''){
		
		alert('진단 목표를 입력 하여 주세요.');
	}else if($('[name=select_type]').val() == ''){
		
		alert('답안 타입을 설정 하여 주세요.');
	}else if($('[name=select_val]').val() == ''){
		
		alert('답안 정답을 입력 하여 주세요.');
	}else if($('[name=solution]').val() == ''){
		
		alert('해설을 입력 하여 주세요.');
	}
	

	$('#insertForm').submit();
}

const admin_button_1 = `<a class="storage" href="javascript:insertClick()">문제 저장</a>
    <a class="cancel" onclick="">답안 설정</a>
    <a class="storage" href="javascript:history.back()">뒤로 가기</a>`;   
        
    
function question_select(){
	
	if($('[name=type]').val() == ''){
		alert('해당 타입을 값을 채운뒤 연결을 눌러주세요.');
		return;
	}
	
	//window.open('/admin/question/select_list.do?type='+$('[name=type]').val()+'' , '_blank' ,'width=1200,heigth=1600');
	var typeValue = $('[name=type]').val();
	var exam_idx = $('[name=exam_idx]').val();
	var url = '/admin/question/select_list.do?type=' + typeValue+'&exam_idx='+exam_idx;

	window.open(url, '문제 연결', 'width=1600, height=800');
	
}



</script>