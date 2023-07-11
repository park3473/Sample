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
                        <input type="hidden" name="idx" value="${model.view.idx }" />
                        <input type="hidden" name="select_change_bool" value="false" />
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
                                        <li id="select_type_li">
                                            <span class="list_t">답안 타입</span>
                                            <select name="select_type" id="select_type" onchange="select_type_change()" disabled="disabled">
                                            	<option value="false">타입을 선택해 주세요</option>
                                            	<option value="0" <c:if test="${model.view.select_type == '0' }">selected="selected"</c:if> >OX 퀴즈</option>
                                            	<option value="1" <c:if test="${model.view.select_type == '1' }">selected="selected"</c:if> >다지선다</option>
                                            </select>
                                            <button type="button" onclick="select_change()">답안 수정</button>
                                        </li>
                                        <li id="select_val_li">
                                            <span class="list_t">답안</span>
                                            <select name="select_val" id="select_val" onchange="select_form_open()">
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
                    <div id="select_box">
                    	<form action="./insert.do" method="post" name="select_insertForm" id="select_insertForm" enctype="multipart/form-data">
                    		<input type="hidden" name="select_confrim" value="false">
                    		<div class="member_register_wrap">
	                    		<div class="title">
	                                <span>답안 등록</span>
	                            </div>
	                            <div class="member_input_wrap" id="select_input_warp">
	                            	<c:forEach items="${model.list}" var="item" varStatus="status">
		                            	<ul class="member_input" id="select_ul_${status.index }">
		                            		<li>번호 : <input type="text" name="seq" value="${item.seq }" readonly="readonly"/></li>
		                            		<li>내용 : <input type="text" name="content" value="${item.content }" readonly="readonly" /></li>
		                            		<c:if test="${item.image != ''}">
		                            			<li>이미지 : <input type="text" name="image" value="${item.image }" readonly="readonly" /> <button type="button" onclick="image_change(this , '${status.index}')">이미지 변경</button></li>
		                            		</c:if>
		                            		<c:if test="${item.image == ''}">
		                            			<li>이미지 : <input type="file" name="image" value=""></li>
		                            		</c:if>
		                            	</ul>
	                            	</c:forEach>
	                            </div>
                            </div>
                    	</form>
                    	<!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con" id="admin_button">
                                <button class="storage" onclick="updateClick()">문제 업데이트</button>
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
    <select name="select_val" id="select_val" onchange="select_form_open()">
    <option value="0">답안을 선택하여주세요</option>
	<option value="O">O</option>
	<option value="X">X</option>
</select>`;

const admin_select_val_1 = `<span class="list_t">답안</span>
    <select name="select_val" id="select_val" onchange="select_form_open()">
    <option value="0">답안을 선택하여주세요</option>
	<option value="1">1</option>
	<option value="2">2</option>
 <option value="3">3</option>
 <option value="4">4</option>
 <option value="5">5</option>
</select>`;

const admin_select_type_1 = `<div id="select_type_cnt_box"><br><span class="list_t">갯수</span>
<select name="select_type_cnt" id="select_type_cnt" onchange="select_form_open()">
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

//답안 수정 관련 변경
function select_change(){
	
	var bool = confirm('해당 답안을 변경하시겠습니까?');
	
	if(bool){
		$('#select_input_warp input[type="text"]').attr('readonly',false);
		$('[name=select_change_bool]').val('true');	
		$('[name=select_type]').attr('disabled' , false);
		$('#select_type_li').append(admin_select_type_1);
	}else{
		return;
	}
	
	
}

//답안 form 작성
function select_form_open(){
	
	var change_val = $('#question_insertForm [name=select_type]').val();

	if(change_val == 'false'){
		
		alert('답안을 선택해주세요.');
		return;
		
	}
	
	if($('#question_insertForm [name=select_type]').val() == '0'){
		
		var length = $('#select_input_warp ul').length;
		if(length < 2){
			select_list_append('2','0','0');	
		}
		$('#select_insertForm').show();
		
	}else if($('#question_insertForm [name=select_type]').val() == '1'){
	
		select_list($('#question_insertForm [name=select_type_cnt]').val());
		$('#select_insertForm').show();
		
	}
	
}

//답안 부분 갯수 정리
function select_list(count){
	
	var length = $('#select_input_warp ul').length;
	var select_type = $('#question_insertForm [name=select_type]').val();
	console.log('count : ' + count);
	console.log('length : ' + length);
	console.log('select_type : ' + select_type)
	
	if(length > count){
		//빼는거
		count = length - count;
		console.log(count);
		
		select_list_delete(count);
		
	}else if(length == count){
		//아무행동 X
	}else{
		count = count - length
		console.log(count);
		
		select_list_append(count , length , select_type);
	}
	
}

//답안 부분 생성
function select_list_append(count , length , select_type){
	
	for(var i = 0; i < count; i ++){
		var ul = Number(i) + Number(length);
		//넣을 html 생성
		var html = `<ul class="member_input" id="select_ul_`+ul+`">`;
		html += `<li>`;
		if(select_type == '0'){
			if(i == 0){
				var OX = '1'
			}else if(i == 1){
				var OX = '2'
			}
			html += `번호<input type="text" name="seq" value="`+OX+`">`;	
		}else if(select_type == '1' || select_type == '2' || select_type == '3'){
			html += `번호<input type="text" name="seq" value="`+(i+1)+`">`;
		}
		html += `</li>`;
		
		html += `<li>`;
		if(select_type == '0'){
			if(i == 0){
				var OX = 'O'
			}else if(i == 1){
				var OX = 'X'
			}
			html += `내용<input type="text" name="content" value="`+OX+`">`;	
		}else if(select_type == '1' || select_type == '2' || select_type == '3'){
			html += `내용<input type="text" name="content" value="">`;
		}
		html += `</li>`;
		
		
		if(select_type == '1' || select_type == '2' || select_type == '3'){
		
			html += `<li>`;
			
			if(select_type == '1'){
				html += `이미지<input type="file" name="image" value="">`;
			}else if(select_type == '2' || select_type == '3'){
				html += `이미지<input type="text" name="image" value="">`;
				if(select_type == '3'){
					html += `<button type="button" onclick="image_change(this , '`+i+`')">이미지 변경</button>`;
				}
			}
			html += `</li>`;
			
		}
		
		html += `</ul>`;
		
		$('#select_input_warp').append(html);
		
		$('#select_insertForm').show();
		
	}
	
}

//답안 부분 삭제
function select_list_delete(count){
	
	for(i = 0; i < count; i ++){
		
		$('#select_input_warp ul:last').remove();
		
	}
	
}

//업데이트
//문제 및 답안 등록
function updateClick(InsertToConnectType)
{

	if($('#question_insertForm [name=exam_idx]').val() != 'false' && $('#question_insertForm [name=seq]').val() == '')
	{
		alert('문항을 입력 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=type]').val() == ''){
		
		alert('타입을 입력 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=name]').val() == ''){
		
		alert('제목을 입력 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=objectives]').val() == ''){
		
		alert('진단 목표를 입력 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=select_type]').val() == ''){
		
		alert('답안 타입을 설정 하여 주세요.');
		return;
	}else if($('#question_insertForm [name=select_val]').val() == ''){
		
		alert('답안 정답을 입력 하여 주세요.');
	}else if($('#question_insertForm [name=solution]').val() == ''){
		
		alert('해설을 입력 하여 주세요.');
		return;
	}
	
	if($('#select_insertForm #select_input_warp ul').length <= 0){
	
		alert('답안 작성을 해주세요.');
		return;
		
	}
	
	var select_cnt = $('#select_insertForm #select_input_warp ul').length;
	
	for(i = 0; i < select_cnt; i++){
		
		if($('#select_ul_'+i+' [name=seq]').val() == ''){
			alert('답안을 모두 작성 해주세요.');
			return;
			
		}
		
		if($('#select_ul_'+i+' [name=content]').val() == ''){
			alert('답안을 모두 작성 해주세요.');
			return;
			
		}
		
	}

	//방식 변경 => question form submit 으로 보내는것이 아닌
	//ajax 로 보내는것으로 변경
	//$('#question_insertForm').submit();
	$('#question_insertForm [name=content]').val(ckeditorInstance.getData());
	
	var questionForm = $('#question_insertForm').serialize();
	
	// /admin/question/AjaxInsert.do
	$.ajax({
		url : '/admin/question/AjaxUpdate.do',
		type : 'POST',
		data : questionForm,
		success : function(status , xhr){
			
			console.log('question_insert : success');
			console.log('question_idx : ' + $('[name=idx]').val());
			
			var question_idx = $('[name=idx]').val();
			var select_cnt = $('#select_insertForm #select_input_warp ul').length;
			
			//업데이트 이므로 답안 수정이 안됬을시에는 넘어가기
			if($('[name=select_change_bool]').val() == 'true'){
				
				var question_idx = $('[name=idx]').val();
				//먼저 수정시 기존 답안들 전부 삭제
				$.ajax({
					url : '/admin/select/allDelete.do',
					type : 'POST',
					data : ({
						question_idx : question_idx,
					}),
					success : function(status , xhr){
						//삭제 이후 현 답안들 등록
						for(i = 0; i < select_cnt; i++){
							
							var seq = $('#select_ul_'+i+' [name=seq]').val();
							var content = $('#select_ul_'+i+' [name=content]').val()
							var image = '';
							var image_boolean = 'false';
							
							var SelectForm = new FormData();
							SelectForm.append('seq', seq);
							SelectForm.append('content', content);
							
							if($('#select_ul_'+i+' [name=image][type=file]').val() != null && $('#select_ul_'+i+' [name=image][type=file]').val() != ''){
								var image_boolean = 'true';
								
								SelectForm.append('file', $('#select_ul_'+i+' [name=image]')[0].files[0]); // 파일 입력 필드에서 파일을 가져와 추가합니다
							}else{
								
								if($('#select_ul_'+i+' [name=image][type=text]').val() != null && $('#select_ul_'+i+' [name=image][type=text]').val() != ''){
									
									SelectForm.append('image', $('#select_ul_'+i+' [name=image][type=text]').val());
									
								}else{
								
									SelectForm.append('image', '');
									
								}
								
							}
							SelectForm.append('image_boolean', image_boolean);
							SelectForm.append('question_idx', question_idx);
							
							console.log(i+'번째 답안 보내기');
							
							$.ajax({
								url : '/admin/select/insert.do',
								type : 'POST',
								processData:false,
								contentType :false,
								data : SelectForm,
								success : function(status , xhr){
									
									console.log(i+'번째 답안 보내기 성공');
									
									
								},
								error : function(error , status , xhr){
									
									console.log(i+'번째 답안 보내기 실패');
									
									
								}
								
							})
							
						}
						
					},
					error : function(error , status , xhr){
						
					}
				})
				
				
			}else{
				
				//해당 답안 수정
				
				
				console.log('수정 완료');
				alert('해당 문제가 수정되었습니다.');
				location.href = '/admin/question/list.do';	
				
			}
			
			
		},
		error : function(error , status , xhr){
			
			console.log('error');
			
		}
		
	})	
	
	
}

</script>