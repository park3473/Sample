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
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>게시판관리</span>
                            </div>
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                            <span class="list_t">게시판 등급</span>
                                            <input type="radio" name="level" id="normal" value="1" <c:if test="${model.view.level == '1' }">checked="checked"</c:if> >
                                            <label for="normal">일반</label>
                                            <input type="radio" name="level" id="notice" value="2" <c:if test="${model.view.level == '2' }">checked="checked"</c:if> >
                                            <label for="notice">공지</label>
                                            <input type="radio" name="level" id="admin" value="3" <c:if test="${model.view.level == '3' }">checked="checked"</c:if> >
                                            <label for="admin">관리자</label>
                                        </li>
                                        <li>
                                            <span class="list_t">게시판 명</span>
                                            <input class="input_size mr" type="text" name="name" id="name" value="${model.view.name }">
                                        </li>
                                        <li>
                                            <span class="list_t">댓글 여부</span>
                                            <input type="radio" name="reply" id="reply" value="TRUE" <c:if test="${model.view.reply == 'TRUE' }">checked="checked"</c:if> >
                                            <label>유</label>
                                            <input type="radio" name="reply" id="reply" value="FALSE" <c:if test="${model.view.reply == 'FALSE' }">checked="checked"</c:if> >
                                            <label>무</label>
                                        </li>
                                        <li>
                                            <span class="list_t">썸네일 여부</span>
                                            <input type="radio" name="type" id="type" value="0"  <c:if test="${model.view.type == '0' }">checked="checked"</c:if> >
                                            <label>일반</label>
                                            <input type="radio" name="type" id="type" value="1" <c:if test="${model.view.type == '1' }">checked="checked"</c:if> >
                                            <label>썸네일</label>
                                        </li>
                                        <li>
                                            <span class="list_t">파일 여부</span>
                                            <input type="radio" name="file" id="file" value="TRUE" <c:if test="${model.view.file == 'TRUE' }">checked="checked"</c:if> >
                                            <label>유</label>
                                            <input type="radio" name="file" id="file" value="FALSE" <c:if test="${model.view.file == 'FALSE' }">checked="checked"</c:if> >
                                            <label>무</label>
                                        </li>
                                        <li>
                                            <span class="list_t">파일 갯수</span>
                                            <select class="input_size mr" id="file_cnt" name="file_cnt">
                                                <option value="1" <c:if test="${model.view.file_cnt == '1' }">selected="selected"</c:if> >1</option>
                                                <option value="2" <c:if test="${model.view.file_cnt == '2' }">selected="selected"</c:if> >2</option>
                                                <option value="3" <c:if test="${model.view.file_cnt == '3' }">selected="selected"</c:if> >3</option>
                                            </select>
                                            <span class="relate_c">파일 갯수를 선택해주세요.</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con">
                                <a class="storage" onclick="updateClick()">게시판 수정</a>
                                <a class="cancel" onclick="deleteClick()">삭제하기</a>
                                <a class="storage" href="javascript:history.back()">뒤로가기</a>
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
	
	$(".adm_menu_con > li").eq(1).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(1).css({
	    backgroundColor: "#fff"
	});
});


function updateClick(){
	
	console.log('수정하기');
	$('form[name=updateForm]').submit();
	
}

function deleteClick(){
	
	console.log('삭제하기');
	var result = confirm('해당 게시판을 정말 삭제하시겠습니까?\n 삭제된 데이터는 복구가 불가능합니다.');
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