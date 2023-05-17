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
                    <form action="./insert.do" method="post" name="insertForm" id="insertForm" enctype="multipart/form-data">
                        <input type="hidden"  name="csrf" value="${CSRF_TOKEN}" />
                        <input type="hidden" name="idx" value="${model.view.idx }" />
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>배너 등록</span>
                            </div>
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                            <span class="list_t">배너 명</span>
                                            <input class="input_size mr" type="text" name="name" id="name" value="">
                                        </li>
                                        <li>
                                            <span class="list_t">순서</span>
                                            <input class="input_size mr" type="text" name="seq" id="seq" value="" >
                                        </li>
                                        <li>
                                            <span class="list_t">타입</span>
                                            <select class="" name="type">
                                            	<option value="TRUE" selected="selected">사용</option>
                                            	<option value="FALSE">비사용</option>
                                            </select>
                                        </li>
                                        <li>
                                            <span class="list_t">링크</span>
                                            <input class="input_address" type="text" name="link" id="link" value="" >
                                        </li>
                                        <li>
                                            <span class="list_t">배너 이미지</span>
                                            <div>
                                            	<img id="preview_img" alt="배너 사진 미리보기" src="">
                                            </div>
                                            <input type="file" id="image" name="file" onchange="changeValue(this)"> 
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            
                            <!--저장하기 버튼-->
	                        <div class="register_btn_area">
	                            <div class="register_btn_con">
	                                <a class="storage" href="javascript:insertClick()">배너 등록</a>
	                                <a class="storage" href="javascript:history.back()">취소하기</a>
	                            </div>
	                        </div>
	                        <!--저장하기 버튼 end-->
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
	
	$(".adm_menu_con > li").eq(4).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(4).css({
	    backgroundColor: "#fff"
	});
});


function insertClick(){
	

	
}

//프로필 사진 미리보기
function preview_img(input){
	const reader = new FileReader();
	reader.onload = e => {
		const previewImage = document.getElementById('preview_img');
		previewImage.src = e.target.result
	}
	
	reader.readAsDataURL(input.files[0]);
	
}

//프로필 사진 등록 함수 이벤트 설정
const inputImage = document.getElementById('image')
inputImage.addEventListener('change' , e => {
	preview_img(e.target);
})


</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>