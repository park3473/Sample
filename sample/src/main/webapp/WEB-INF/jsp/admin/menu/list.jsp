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
                        <div class="sc_con">
                            <div class="title">
                                <span></span>
                                <span>메뉴 관리</span>
                            </div>
                            <div class="adm_btn_wrap stats_btn_area" style="margin-bottom:0px">
                                    <ul>
	                                    <li class="delete">
	                                        <a href="#" onclick="javascript:InsertModalOpen('0' , '0' , this)">메뉴 추가 생성</a>
	                                    </li>
                                	</ul>
                          	</div>
                            <div class="table_wrap">
                                <table id="bootstrap-data-table">
                                    <tr>
                                        <th class="name">메뉴</th>
                                        <th class="type">링크</th>
                                        <th class="">순서</th>
                                        <th class="setting">관리</th>
                                    </tr>
                             		<c:forEach var="item" items="${model.list }" varStatus="status">
                             			<tr name="menu_data" menu_seq="${item.seq }">
                             				<td>
                             					<c:if test="${item.depth > 0 }">
                             					<img src="${pageContext.request.contextPath }/resources/img/admin/sub_menu_ico.gif">
                             					</c:if>
                             					${item.name }
                             				</td>
                             				<td>${item.link }</td>
                             				<td>${item.seq }</td>
                             				<td>
                             					<button type="button" onclick="UpdateModalOpen('${item.idx}' , this)" menu_idx="${item.idx }" menu_depth="${item.depth}" menu_name="${item.name }" menu_link="${item.link }" menu_seq="${item.seq }" menu_upper_menu_idx="${item.upper_menu_idx }" >관리</button>
                             					<c:if test="${item.depth == 0 }">
                             					<button type="button" onclick="InsertModalOpen('1' , '${item.idx}' , this)" menu_idx="${item.idx }" menu_depth="${item.depth}" menu_cnt="${item.UNDER_CNT }" menu_seq="${item.seq }">메뉴 추가</button>
                             					</c:if>
                             				</td>
                             			</tr>
                             		</c:forEach>
                                </table>
                            	</div>
                            </div>
                    </section>
                    <!--본문 내용 end-->
                </div>
            </div>
        </div>
    </section>
    <!--본문 end-->

<!--질문 modal 시작-->
	<div class="modal_fade" id="insert_modal" style="display:none;">
		<div class="modal_content">
			<div class="modal_docu_box">
				<form  id="dcmnt_insert">
					<ul class="docu_add_box">
						<input type="hidden" id="insert_menu_idx" class="docu_select_size" name="insert_menu_idx" value="" readonly="readonly">
						<input type="hidden" id="insert_menu_type" class="docu_select_size" name="insert_menu_type" value="TRUE" readonly="readonly">
						<input type="hidden" id="insert_menu_depth" class="docu_select_size" name="insert_menu_depth" value="" readonly="readonly">
						<input type="hidden" id="insert_menu_upper_menu_idx" class="docu_select_size" name="insert_menu_upper_menu_idx" value="" readonly="readonly">
						<input type="hidden" id="insert_menu_seq" class="docu_select_size" name="insert_menu_seq" value="" readonly="readonly">
						<li>
							<p>메뉴 명</p>
							<input type="text" id="insert_menu_name" class="docu_select_size" name="insert_menu_name" value="">
						</li>
						<li>
							<p>링크</p>
							<input type="text" id="insert_menu_link" class="docu_select_size" name="insert_menu_link" value="">
						</li>
					</ul>
				</form>
			</div>
			<div class="docu_modal_btn">
				<button type="button" class="docu_btn docu_btn_01" id="modal_enter" onclick="MenuInsertData()">저장</button>
				<button type="button" class="docu_btn docu_btn_02" id="modal_close" class="modal_close" onclick="modalClose()">취소</button>
				<button type="button" class="docu_btn docu_btn_01" id="modal_delete" onclick="MenuDeleteData()">삭제</button>
			</div>
		</div>
	</div>
<!--질문 modal 끝-->


    <!--푸터-->
    <footer>
	<%@ include file="../include/footer.jsp" %>
    </footer>
    <!--푸터 end-->

</body>
<script>

$(document).ready(function () {
	
	$(".adm_menu_con > li").eq(2).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(2).css({
	    backgroundColor: "#fff"
	});
});

function UpdateModalOpen(idx , e){
	
	modalsetting();
	
	$('[name=insert_menu_idx]').val($(e).attr('menu_idx'));
	$('[name=insert_menu_depth]').val($(e).attr('menu_depth'));
	$('[name=insert_menu_upper_menu_idx]').val($(e).attr('menu_upper_menu_idx'));
	$('[name=insert_menu_seq]').val($(e).attr('menu_seq'));
	$('[name=insert_menu_name]').val($(e).attr('menu_name'));
	$('[name=insert_menu_link]').val($(e).attr('menu_link'));
	
	$('#insert_modal').css('display','block');
	$('#modal_enter').attr('onclick','MenuUpdateData()');
	
}

function InsertModalOpen(depth , idx , e){
	
	modalsetting();
	
	$('[name=insert_menu_depth]').val(depth);
	if(depth == '1'){
		$('[name=insert_menu_upper_menu_idx]').val(idx);
		//해당 같은 하위 메뉴의 제일 마지막의 번호 + 1
		var size = $('[menu_upper_menu_idx='+idx+']').size();
		if(size > 0){
		
			$('[name=insert_menu_seq]').val(parseInt($('[menu_upper_menu_idx='+idx+']').last().attr('menu_seq')) + 1);
			
		}else if(size == 0){
			
			$('[name=insert_menu_seq]').val(parseInt($(e).attr('menu_seq')) + 1);
			
		}
		
	}else if(depth == '0'){
		$('[name=insert_menu_upper_menu_idx]').val('0');
		$('[name=insert_menu_seq]').val(parseInt($('[name=menu_data]').last().attr('menu_seq')) + 1);
		$('[name=insert_menu_title]').val('M'+($('[menu_list=yes]').length + 1));
	}
	
	
	$('#insert_modal').css('display','block');
	$('#modal_enter').attr('onclick','MenuInsertData()');
	
}


//modal 청소
function modalsetting(){
	
	$('[name=insert_menu_idx]').val('');
	$('[name=insert_menu_depth]').val('');
	$('[name=insert_menu_upper_menu_idx]').val('');
	$('[name=insert_menu_seq]').val('');
	$('[name=insert_menu_name]').val('');
	$('[name=insert_menu_link]').val('');
	$('[name=insert_menu_upper_title]').val('');
	
	
}

//moadl 닫기
function modalClose(){
	
	$('#insert_modal').css('display','none');
	
	modalsetting();
	
}

function MenuInsertData(){
	
	var type = $('[name=insert_menu_type]').val();
	var depth = $('[name=insert_menu_depth]').val();
	var upper_menu_idx = $('[name=insert_menu_upper_menu_idx]').val();
	var seq = $('[name=insert_menu_seq]').val();
	var name = $('[name=insert_menu_name]').val();
	var link = $('[name=insert_menu_link]').val();
	
	console.log(type + "," + depth + "," + upper_menu_idx + "," + seq + "," + name + "," + link);
	
	$.ajax({
		url : '/admin/menu/insert.do',
		type : 'POST',
		data : ({
			type : type,
			depth : depth,
			upper_menu_idx : upper_menu_idx,
			seq : seq,
			name : name,
			link : link
		}),
		success : function(data , status , xhr){
			
			console.log('해당 메뉴가 등록되었습니다.');
			if(depth == '0'){
				alert('해당 메뉴가 추가되었습니다.');	
			}else if(depth == '1'){
				alert('해당 하위메뉴가 추가되었습니다.');
			}
			location.reload();
			
		},
		error : function(status , xhr , error){
			
		}
	})
	
}

function MenuUpdateData(){
	
	var idx = $('[name=insert_menu_idx]').val();
	var type = $('[name=insert_menu_type]').val();
	var depth = $('[name=insert_menu_depth]').val();
	var upper_menu_idx = $('[name=insert_menu_upper_menu_idx]').val();
	var seq = $('[name=insert_menu_seq]').val();
	var name = $('[name=insert_menu_name]').val();
	var link = $('[name=insert_menu_link]').val();
	
	console.log(type + "," + depth + "," + upper_menu_idx + "," + seq + "," + name + "," + link);
	
	$.ajax({
		url : '/admin/menu/update.do',
		type : 'POST',
		data : ({
			idx : idx,
			type : type,
			depth : depth,
			upper_menu_idx : upper_menu_idx,
			seq : seq,
			name : name,
			link : link
		}),
		success : function(data , status , xhr){
			
			console.log('해당 메뉴가 등록되었습니다.');
			if(depth == '0'){
				alert('해당 메뉴가 수정되었습니다.');	
			}else if(depth == '1'){
				alert('해당 하위메뉴가 수정되었습니다.');
			}
			location.reload();
			
		},
		error : function(status , xhr , error){
			
		}
	})
	
}

function MenuDeleteData(){
	
	var idx = $('[name=insert_menu_idx]').val();
	var type = $('[name=insert_menu_type]').val();
	var depth = $('[name=insert_menu_depth]').val();
	var upper_menu_idx = $('[name=insert_menu_upper_menu_idx]').val();
	var seq = $('[name=insert_menu_seq]').val();
	var name = $('[name=insert_menu_name]').val();
	var link = $('[name=insert_menu_link]').val();
	
	if(depth == '0'){
		var result = confirm('해당 메뉴를 삭제하시겠습니까?\해당 메뉴 삭제시 하위 메뉴들 전부 삭제됩니다.');
	}else if(depth == '1'){
		var result = confirm('해당 메뉴를 삭제하시겠습니까?');
	}
	
	if(result == false){
		console.log('삭제 X');
		return;
	}
	
	console.log('삭제 O');
	
	$.ajax({
		url : '/admin/menu/delete.do',
		type : 'POST',
		data : ({
			idx : idx,
			type : type,
			depth : depth,
			upper_menu_idx : upper_menu_idx,
			seq : seq,
			name : name,
			link : link,
		}),
		success : function(data , status , xhr){
			
			console.log('해당 메뉴가 삭제되었습니다.');
			alert('해당 메뉴가 삭제되었습니다.');
			location.reload();
			
		},
		error : function(status , xhr , error){
			
		}
	})
	
	
	
}

</script>

</html>

