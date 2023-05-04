<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="../include/header.jsp" %>
</head>

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
                    <form action="./update.do" method="post" name="updateForm" id="updateForm" >
                        <input type="hidden"  name="csrf" value="${CSRF_TOKEN}" />
                        <input type="hidden" name="idx" value="${model.view.idx }" />
                        <input type="hidden" name="type" value="${model.view.type }" />
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>회원 등록</span>
                            </div>
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                            <span class="list_t">회원 분류</span>
                                            <input type="radio" name="level" id="normal" value="1" <c:if test="${model.view.level == '1' }" >checked="checked"</c:if> >
                                            <label for="normal">일반</label>
                                            <input type="radio" name="level" id="admin" value="3" <c:if test="${model.view.level == '3' }" >checked="checked"</c:if> >
                                            <label for="admin">관리자</label>
                                        </li>
                                        <li>
                                            <span class="list_t">회원 아이디</span>
                                            <input class="input_size mr" type="text" name="" id="member_id" value="${model.view.member_id }">
                                            <button type="button" value="중복체크" class="btn_03" onclick="IdCheck()">중복 체크</button>
                                            <a class="relate_c" id="member_id_check" >아이디 변경 X</a>
                                        </li>
                                        <li>
                                            <span class="list_t">회원 이름</span>
                                            <input class="input_size mr" type="text" name="name" id="name" value="${model.view.name }">
                                        </li>
                                        <li>
                                            <span class="list_t">회원 전화번호</span>
                                            <input class="input_address mr" type="text" name="phone" id="phone" placeholder="전화번호(-를 제외한 번호만 입력해주세요.)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" value="${model.view.phone }" >
                                        </li>
                                        <li>
                                            <span class="list_t">회원 이메일</span>
                                            <input class="input_size mr" type="text" name="email" id="email" value="${model.view.email }"> @ 
                                            <select class="input_size mr" id="email_address" name="email_address">
                                                <option value="" <c:if test="${model.view.email_address == '' }" >selected="selected"</c:if> >이메일을 선택하여주세요.</option>
                                                <option value="naver.com" <c:if test="${model.view.email_address == 'naver.com' }" >selected="selected"</c:if> >naver.com</option>
                                                <option value="google" <c:if test="${model.view.email_address == 'google.com' }" >selected="selected"</c:if> >google.com</option>
                                                <option value="daum.net" <c:if test="${model.view.email_address == 'daum.net' }" >selected="selected"</c:if> >daum.net</option>
                                            </select>
                                        </li>
                                        <li>
											<div class="address_01">
												<span class="list_t">집주소</span>
                                        		<input class="input_address" type="text" name="address" id="address" readonly  value="${model.view.address }" >
                                              	<button type="button" onClick="zipCode()">주소 검색</button>
                                   			</div>
                                    		<div class="address_02">
                                    			<input class="input_address" type="text" name="address_detail" id="address_detail" value="${model.view.address_detail }" >
                                         	</div>
                                         </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con">
                                <a class="storage" href="javascript:updateClick()">회원 수정</a>
                                <c:if test="${model.view.type == '1' }">
                                <a class="cancel" href="javascript:deleteClick()">회원 삭제</a>
                                </c:if>
                                <c:if test="${model.view.type == '2' }">
                                <a class="cancel" href="javascript:backUpClick()">회원 복구</a>
                                </c:if>
                                <a class="storage" href="javascript:history.back()">취소하기</a>
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

```

<!--  JQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/member.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/admin.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/admin/jquery.datetimepicker.full.min.js"></script>


</body>

</html>

<script type="text/javascript">

$(document).ready(function () {
	
	$(".adm_menu_con > li").eq(0).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(0).css({
	    backgroundColor: "#fff"
	});
});


function updateClick(){
	
	$('#member_id').attr('name' , 'member_id');
	$('form[name=updateForm]').submit();
	
}

function backUpClick(){
	
	console.log('복구하기');
	var result = confirm('해당 회원을 정말 복구하시겠습니까?');
	if(result){
		
		console.log('복구');
		$('input[name=type]').val('1');
		$('#member_id').attr('name' , 'member_id');
		$('form[name=updateForm]').submit();
		
	}else{
		
		console.log('삭제 안함');
		
	}
	
}

//중복 아이디 체크
function IdCheck(){
	
	var member_id = $('#member_id').val();
	
	if(member_id == ''){
		
		alert('아이디를 입력하여 주세요.');
		$('#member_id').focus();
		return;
	}
	
	console.log(member_id);
	
	$.ajax({
		url : '/view/IdCheck.do',
		type : 'POST',
		data : ({
			member_id : member_id
		}),
		success : function(data , status){
			console.log(data);
			
			if(data == 'false'){
				alert('이미 가입된 아이디 입니다.');
				
			}else if(data == 'true'){
				alert('가입 가능한 아이디 입니다.');
				$('#member_id').attr('name' , 'member_id');
				$('#member_id_check').html('아이디 변경 O');
				
			}
			
		}
	})
	
}

//집 주소 검색
function zipCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            console.log(data);
            
            var full_address = data.address + "(" + data.buildingName + ")";
            $('input[name=address]').val(full_address);
        }
    }).open();
}

//회원 삭제
function deleteClick(){
	
	console.log('삭제하기');
	var result = confirm('해당 회원을 정말 삭제하시겠습니까?');
	if(result){
		
		console.log('삭제');
		$('form[name=updateForm]').attr('action', './delete.do');
		$('form[name=updateForm]').submit();
		
	}else{
		
		console.log('삭제 안함');
		
	}
	
}

</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>