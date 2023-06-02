<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="../include/header.jsp" %>
</head>

<style>
	#bootstrap-data-table tr th{
	
	text-align: center;
	
	}
</style>

<body>

    <!--본문-->
    <section id="adm_sc">
        <div id="adm_sc_area">
            <div id="adm_sc_con">
                <div class="adm_sc_size">

                    <!--본문 내용-->
                    <section class="adm_sc_txt" style="width:auto !important" >
                        <div class="sc_con">
                            <div class="title">
                                <span></span>
                                <span>답안 리스트</span>
                            </div>
                            
                            <c:if test="${model.question.select_type == '1' }">
                            <!--관리자 버튼-->
                            <div class="page_seach">
                                <div class="adm_btn_wrap stats_btn_area">
                                        <select style="width:200px !important" name="select_count" id="select_count" onchange="select_append(this)"> 
                                        	<option value="0">문항 갯수를 선택하여 주세요</option>
                                        	<option value="1">1</option>
                                        	<option value="2">2</option>
                                        	<option value="3">3</option>
                                        	<option value="4">4</option>
                                        	<option value="5">5</option>
                                        </select>
                                </div>
                            </div>
                            </c:if>
                            <!--관리자 버튼 end-->
                            
                            <div class="table_wrap">
                                <table id="bootstrap-data-table">
                                	<tbody id="table_body">
                                	<c:if test="${model.question.select_type == '0' }">
                                    <tr>
                                        <th class="number">답안 번호</th>
                                        <th class="image">이미지</th>
                                        <th class="setting">비고</th>
                                    </tr>
                                    </c:if>
                                    <c:if test="${model.question.select_type == '1' }">
                                    <tr>
                                        <th class="number">답안 번호</th>
                                        <th class="content">내용</th>
                                        <th class="image">이미지</th>
                                        <th class="setting">비고</th>
                                    </tr>
                                    </c:if>
                                    
                                    <!-- 답안 타입 OX 일 경우 OX 세팅 -->
                                    <c:if test="${model.question.select_type == '0' && model.list.size() <= 0 }">
                                    	<tr data-role="button" id="select_tr_O" select_idx="" select_seq="O" select_content="" select_image="" file_change_type="false" >
	                                    	<td>O</td>
	                                        <td>
	                                        	<input type="file" name="image" id="image">
	                                        </td>
	                                        <td>
	                                        	
	                                        </td>
	                                    </tr>
	                                    <tr data-role="button" id="select_tr_X" select_idx="" select_seq="X" select_content="" select_image="" file_change_type="false" >
	                                    	<td>X</td>
	                                        <td>
	                                        	<input type="file" name="image" id="image">
	                                        </td>
	                                        <td>
	                                        	
	                                        </td>
	                                    </tr>
                                    </c:if>
                                    
                                    <!-- 답안 타입 다지선다 일 경우 다지선다 세팅 -->
                                    <c:if test="${model.question.select_type == '1' && model.list.size() <= 0 }">
                                    	
                                    </c:if>
                                    
                                    <!-- 답안 목록이 있는 경우 -->
                                    <c:if test="${model.list.size() > 0}">
                                    <c:forEach var="item" items="${model.list}" varStatus="status">
                                    <tr data-role="button" id="select_tr_${status.index }" select_idx="${item.idx }" select_seq="${item.seq }" select_content="${item.content }" select_image="${item.image }" file_change_type="false" >
                                    	<td>${item.seq }</td>
                                    	<c:if test="${model.question.select_type == '1' }">
                                        <td>${item.content }</td>
                                        </c:if>
                                        <c:if test="${item.image == '' }">
                                        	<td>
                                        		<input type="file" name="image" id="image">
                                        	</td>
                                        </c:if>
                                        <c:if test="${item.image != '' }">
                                        	<td id="select_image_${status.index }">${item.image }<button type="button" onclick="imageChange(this , '${item.idx}' , '${status.index }')" >이미지 변경</button>></td>
                                        </c:if>
                                        <td>
                                        	<button type="button" onclick="">삭제</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>

							 <!--저장하기 버튼-->
	                        <div class="register_btn_area">
	                            <div class="register_btn_con" id="admin_button">
	                                <a class="storage" href="javascript:void(0)" onclick="OpenerSelectInsert_${model.question.select_type}()">답안 저장</a>
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

</body>
<script>

$(document).ready(function () {
	
	$(".adm_menu_con > li").eq(3).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(3).css({
	    backgroundColor: "#fff"
	});
});

const append_tr_html = `
	<td>
		<input class="input_title" type="text" name="content" placeholder="내용을 작성하여 주세요.">
	</td>
    <td>
	<input type="file" name="image" id="image">
</td>
<td>

</td></tr>`

function select_append(e){
	
	append_count = $(e).val();
	var length = $('#table_body tr').length;
	length -= 1;
	
	console.log("append_count : " + append_count);
	console.log("lenght : " + length);
	
	if(length > append_count){
		//뺴는거
		append_count = length - append_count
		console.log(append_count);
		
		delete_tr(append_count);
		
	}else if(length == append_count){
		//아무 행동 X
	}else{
		//더하는거
		append_count = append_count - length
		console.log(append_count);
		
		//tr 더하기
		append_tr(append_count , length);
		
	}
	
}

function append_tr(count , length){
	
	for(i = 0; i < count;i++){
		
		var html = `<tr id="select_tr_`+(i+1+length)+`" ><td><input type="text" placeholder="문항 번호를 작성하여 주세요." value="`+(i+1+length)+`" readonly="readonly"></td>`
		html += append_tr_html;
		$('#table_body').append(html);
		
	}
	
}

function delete_tr(count){
	
	for(i = 0; i < count;i++){
		
		$('#table_body tr:last').remove();
		
	}
	
}

function OpenerSelectInsert_0(){
	
	var length = $('#table_body tr').length;
	
	
	for(i = 0; i < length; i ++){
	
		//값 불러오기
			
		
		//넣을 html 생성
		var html = `<ul class="member_input" id="select_ul_`+i+`">`;
		html += `<li>`
		html += `</li>`
		html += `</ul>`;
		
		opener.document.getElementId('select_input_warp').append()
		
	}
	
	
}

function OpenerSelectInsert_1(){
	
	var length = $('#table_body tr').length;
	length -= 1;
	
	for(i = 0; i < length; i ++){
	
		var j = i+1;
		console.log(j);
		//값 불러오기
		var seq = $('#select_tr_'+j+' [name=seq]').val();
		var content = $('#select_tr_'+j+' [name=content]').val();

		var image_file_count = $('#select_tr_'+j+' [name=image]')[0].files.length;
		console.log(image_file_count);
		if (image_file_count > 0){
			var file = $('#select_tr_'+j+' [name=image]')[0].files[0];
		}
		
		//넣을 html 생성
		var html = `<ul class="member_input" id="select_ul_`+i+`">`;
		html += `<li>`;
		html += `<input type="text" name="seq" value="`+seq+`">`;
		html += `</li>`;
		
		html += `<li>`;
		html += `<input type="text" name="content" value="`+content+`">`;
		html += `</li>`;
		
		html += `<li>`;
		html += `<input type="file" name="image" value="">`;
		html += `</li>`;
		
		html += `</ul>`;
		
		opener.document.getElementId('select_input_warp').append(html);
		
	}
	
	
}

</script>

</html>

