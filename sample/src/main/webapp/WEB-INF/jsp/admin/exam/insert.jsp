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
                        <input type="hidden" name="board_idx" value="${model.BoardConfig.idx }"  />
                        <input type="hidden" name="level" value="1"  />
                        <input type="hidden" name="type" value="0"  />
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>자가진단 등록</span>
                            </div>
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                            <span class="list_t">자가진단 명</span>
                                            <input class="input_size mr" type="text" id="name" name="name">
                                        </li>
                                        <li>
                                        	<span class="list_t">대분류</span>
                                        	<input class="input_title" type="text" name="l_category" id="l_category" list="l_category_list" >
                                        	<c:if test="${model.LCategoryList.size() > 0 }">
                                        	<c:forEach items="${model.LCategoryList }" varStatus="status" var="item">
                                        	<datalist id="l_category_list">
                                        		<option>${item.L_CATEGORY }</option>
                                        	</datalist>
                                        	</c:forEach>
                                        	</c:if>
                                        </li>
                                        <li>
                                        	<span class="list_t">중분류</span>
                                        	<input class="input_title" type="text" name="m_category" id="m_category" list="m_category_list" >
                                        	<c:if test="${model.MCategoryList.size() > 0 }">
                                        	<c:forEach items="${model.MCategoryList }" varStatus="status" var="item">
                                        	<datalist id="m_category_list">
                                        		<option>${item.M_CATEGORY }</option>
                                        	</datalist>
                                        	</c:forEach>
                                        	</c:if>
                                        </li>
                                        <li>
                                        	<span class="list_t">개요</span>
                                        	<textarea name="content" id="editor"></textarea>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con">
                                <a class="storage" href="javascript:insertClick()"> 저장</a>
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

function insertClick()
{

	if($('#name').val() == '')
	{
		alert('제목을 입력 하여 주세요');
		return;
	}

	$('#insertForm').submit();
}


</script>