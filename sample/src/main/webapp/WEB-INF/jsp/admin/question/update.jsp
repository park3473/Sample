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
                        <input type="hidden" name="idx" value="${model.view.idx }" />
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
                                        	<input class="input_size mr" type="text" name="type" id="type"  value="${model.view.type }" >
                                        </li>
                                        <li>
                                            <span class="list_t">문제 제목</span>
                                            <input class="input_title" type="text" id="name" name="name" value="${model.view.name }" >
                                        </li>
                                        <li>
                                            <span class="list_t">진단 목표</span>
                                            <input class="input_title" type="text" id="objectives" name="objectives" value="${model.view.objectives }" >
                                        </li>
                                        <li>
                                            <span class="list_t">답안 타입</span>
                                            <select name="select_type" id="select_type" onchange="select_type_change()">
                                            	<option value="false">타입을 선택해 주세요</option>
                                            	<option value="0" <c:if test="${model.view.select_type == '0' }">selected="selected"</c:if> >OX 퀴즈</option>
                                            	<option value="1" <c:if test="${model.view.select_type == '1' }">selected="selected"</c:if> >다지선다</option>
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
                                        	<textarea name="content" id="editor">${model.view.content }</textarea>
                                        </li>
                                        <li>
                                            <span class="list_t">해설</span>
                                            <input class="input_title" type="text" id="solution" name="solution" value="${model.view.solution }"  >
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
		                            			<li>이미지 : <input type="text" name="image" value="${item.image }"> <button type="button" onclick="image_change(this , '${status.index}')">이미지 변경</button></li>
		                            		</c:if>
		                            		<c:if test="${item.image == ''}">
		                            			<li>이미지 : X</li>
		                            		</c:if>
		                            	</ul>
	                            	</c:forEach>
	                            </div>
                            </div>
                    	</form>
                    	
                    	<!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con" id="admin_button">
                                <button class="storage" onclick="select_form_open()">문제 업데이트</button>
    							<button class="storage" onclick="history.back()">뒤로 가기</button>
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
	
if($('#select_type').val() == '0'){
		$('#select_val_li').html(admin_select_val_0);
		$('[name=select_val]').val('${model.view.select_val}');
	}else if ($('#select_type').val() == '1'){
		$('#select_val_li').html(admin_select_val_1);
		$('[name=select_val]').val('${model.view.select_val}');
		
	}
	
	
});

//select box 3개
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

const admin_select_type_1 = `<div id="select_type_cnt_box"><br><span class="list_t">갯수</span>
<select name="select_type_cnt" id="select_type_cnt">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
</select></div>`;

//타입 변경시
function select_type_change(){
	
	var change_val = $('#question_insertForm [name=select_type]').val();

	if(change_val == 'false'){
		
		alert('답안을 선택해주세요.');
		return;
		
	}
	
	if($('#select_input_warp ul').length > 0){
		
		alert('변경시 작성했던 답안들은 사라집니다.');
		$('#select_input_warp ul').remove();
		$('#select_insertForm').hide();
		
		$('#admin_button').html(admin_button_1);
		
	}
	
	switch (change_val) {
	case '0':
		$('#select_val_li').html(admin_select_val_0);
		$('#select_type_cnt_box').remove();
		break;
	case '1':
		$('#select_val_li').html(admin_select_val_1);
		$('#select_type_li').append(admin_select_type_1);
		break;
	}
}

function img_modal(image){
	
	window.open('/resources/upload/ckeditor/'+image , '이미지 확인', 'width=500, height=500');
	
}

//이미지 변경
function image_change(e,list_idx){
	
	console.log(e);
	console.log($('#select_ul_'+list_idx+''));
	$(e).before('<input type="file" name="image">');
	$('#select_ul_'+list_idx+' [name=image][type=text]').remove();
	$(e).remove();
	
}

//업데이트


</script>