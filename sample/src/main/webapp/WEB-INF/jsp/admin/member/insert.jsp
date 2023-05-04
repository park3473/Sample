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
                    <form action="./insert.do" method="post" name="insertForm" id="insertForm" >
                        <input type="hidden"  name="csrf" value="${CSRF_TOKEN}" />
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
                                            <input type="radio" name="level" id="normal" value="1" checked >
                                            <label for="normal">일반</label>
                                            <input type="radio" name="level" id="admin" value="3">
                                            <label for="admin">관리자</label>
                                        </li>
                                        <li>
                                            <span class="list_t">회원 아이디</span>
                                            <input class="input_size mr" type="text" name="member_id" id="member_id">
                                            <button type="button" value="중복체크" class="btn_03" onclick="IdCheck()">중복 체크</button>
                                        </li>
                                        <li>
                                            <span class="list_t">회원 비밀번호</span>
                                            <input class="input_size mr" type="text" name="password" id="password">
                                        </li>
                                        <li>
                                            <span class="list_t">회원 이름</span>
                                            <input class="input_size mr" type="text" name="name" id="name">
                                        </li>
                                        <li>
                                            <span class="list_t">회원 전화번호</span>
                                            <input class="input_address mr" type="text" name="phone" id="phone" placeholder="전화번호(-를 제외한 번호만 입력해주세요.)" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" >
                                        </li>
                                        <li>
                                            <span class="list_t">회원 이메일</span>
                                            <input class="input_size mr" type="text" name="email" id="email"> @ 
                                            <select class="input_size mr" id="email_address" name="email_address">
                                                <option value="naver.com">naver.com</option>
                                                <option value="google">google.com</option>
                                                <option value="daum.net">daum.net</option>
                                            </select>
                                        </li>
                                        <li>
											<div class="address_01">
												<span class="list_t">집주소</span>
                                        		<input class="input_address" type="text" name="address" id="address" readonly>
                                              	<button type="button" onClick="zipCode()">주소 검색</button>
                                   			</div>
                                    		<div class="address_02">
                                    			<input class="input_address" type="text" name="address_detail" id="address_detail">
                                         	</div>
                                         </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con">
                                <a class="storage" href="javascript:insertClick()">회원 생성</a>
                                <a class="cancel" href="javascript:history.back()">취소하기</a>
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


function insertClick(){
	
	$('form[name=insertForm]').submit();
	
}

//중복 아이디 체크
function IdCheck(){
	
	var member_id = $('input[name=member_id]').val();
	
	if(member_id == ''){
		
		alert('아이디를 입력하여 주세요.');
		$('input[name=member_id]').focus();
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
				$('input[name=IdCheck]').attr('value' , 'FALSE');
			}else if(data == 'true'){
				alert('가입 가능한 아이디 입니다.');
				$('input[name=IdCheck]').attr('value' , 'TRUE');
				
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

</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>