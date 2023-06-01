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
                    <form action="./insert.do" method="post" name="question_insertForm" id="question_insertForm" enctype="multipart/form-data">
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
                                    	<c:if test="${model.exam_idx != 'false' }">
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
                                            <select name="select_type" id="select_type" onchange="select_type_change()">
                                            	<option value="0" selected="selected">OX 퀴즈</option>
                                            	<option value="1">다지선다</option>
                                            </select>
                                        </li>
                                        <li id="select_val_li">
                                            <span class="list_t">답안</span>
                                            <select name="select_val" id="select_val">
                                            	<option value="O">O</option>
                                            	<option value="X">X</option>
                                            </select>
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
                        </form>
                    </div>
                    
                    
                    <!-- 답안 부분 -->
                    <div id="select_box">
                    	<form action="./insert.do" method="post" name="select_insertForm" id="select_insertForm" enctype="multipart/form-data" style="display:none">
                    		<input type="hidden" name="select_confrim" value="false">
                    		<div class="member_register_wrap">
                    		<div class="title">
                                <span>답안 등록</span>
                            </div>
                                <div class="member_input_wrap">
                                    <ul class="member_input" id="select_ul">
                                    </ul>
                                </div>
                            </div>
                    	</form>
                    	
                    	                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con" id="admin_button">
                                <a class="storage" onclick="insertClick()">문제 저장</a>
    							<a class="storage" href="javascript:history.back()">뒤로 가기</a>
                            </div>
                        </div>
                        <!--저장하기 버튼 end-->
                    	
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

const admin_select_val_0 = `<span class="list_t">답안</span>
    <select name="select_val" id="select_val">
	<option value="O">O</option>
	<option value="X">X</option>
</select>`;

const admin_select_val_1 = `<span class="list_t">답안</span>
    <select name="select_val" id="select_val">
	<option value="1">1</option>
	<option value="2">2</option>
 <option value="3">3</option>
 <option value="4">4</option>
 <option value="5">5</option>
</select>`;

function select_type_change(){
	
	var change_val = $('#question_insertForm [name=select_type]').val();

	switch (change_val) {
	case '0':
		$('#select_val_li').html(admin_select_val_0);
		break;
	case '1':
		$('#select_val_li').html(admin_select_val_1);
		break;
	}
}


function insertClick()
{

	if($('#question_insertForm [name=exam_idx]').val() != 'false' && $('#question_insertForm [name=seq]').val() == '')
	{
		alert('문항을 입력 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=type]').val() == ''){
		
		alert('타입을 입력 하여 주세요.');
	}else if($('#question_insertForm [name=name]').val() == ''){
		
		alert('제목을 입력 하여 주세요.');
	}else if($('#question_insertForm [name=objectives]').val() == ''){
		
		alert('진단 목표를 입력 하여 주세요.');
	}else if($('#question_insertForm [name=select_type]').val() == ''){
		
		alert('답안 타입을 설정 하여 주세요.');
	}else if($('#question_insertForm [name=select_val]').val() == ''){
		
		alert('답안 정답을 입력 하여 주세요.');
	}else if($('#question_insertForm [name=solution]').val() == ''){
		
		alert('해설을 입력 하여 주세요.');
	}

	
	$('#question_insertForm').submit();
}

function button_change(type){
	
	switch (type) {
	case '1':
		$('#admin_button').html(admin_button_1);
		break;
	case '2':
		$('#admin_button').html(admin_button_2);
		break;
	}
}



const admin_button_1 = `<a class="storage" href="javascript:insertClick()">문제 저장 및 연결</a>
    <a class="storage" href="javascript:history.back()">뒤로 가기</a>`;   

const admin_button_2 = `<a class="storage" href="javascript:ConnectClick()">문제 연결</a>
    <a class="cancel" onclick="">답안 보기</a>
    <a class="storage" href="javascript:history.back()">뒤로 가기</a>`;
    
function question_select(){
	
	if($('#question_insertForm [name=type]').val() == ''){
		alert('해당 타입을 값을 채운뒤 연결을 눌러주세요.');
		return;
	}
	
	//window.open('/admin/question/select_list.do?type='+$('[name=type]').val()+'' , '_blank' ,'width=1200,heigth=1600');
	var typeValue = $('#question_insertForm [name=type]').val();
	var exam_idx = $('#question_insertForm [name=exam_idx]').val();
	var url = '/admin/question/select_list.do?type=' + typeValue+'&exam_idx='+exam_idx;

	window.open(url, '문제 연결', 'width=1600, height=800');
	
}
</script>
<script type="text/javascript">
window.addEventListener('DOMContentLoaded', (event) => {
    var links = document.getElementsByTagName('a'); // 모든 링크를 가져옵니다.

    for (var i = 0; i < links.length; i++) {
        links[i].addEventListener('click', function(e) {
        	
            var confirmed = confirm('정말 해당 페이지를 이동하시겠습니까?\n해당 작성하던 문제는 사라집니다.');

            if (!confirmed) {
                // 사용자가 확인을 선택하지 않은 경우, 페이지 이동을 취소합니다.
                e.preventDefault();
            }
        });
    }
});
</script>