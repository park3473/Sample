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
                    <form action="./update.do" method="post" name="updateForm" id="updateForm" enctype="multipart/form-data">
                        <input type="hidden"  name="csrf" value="${CSRF_TOKEN}" />
                        <input type="hidden" name="idx" value="${model.view.idx }"  />
                        <input type="hidden" name="board_idx" value="${model.view.board_idx }"  />
                        <input type="hidden" name="level" value="${model.view.level }"  />
                        <input type="hidden" name="type" value="${model.view.type }"  />
                        <div class="sc_con" id="div_con">
                            <div class="title">
                                <span></span>
                                <span>게시글 등록</span>
                            </div>
                            <div class="member_register_wrap">
                                <div class="member_input_wrap">
                                    <ul class="member_input">
                                        <li>
                                            <span class="list_t">게시판 명</span>
                                            <input class="input_size mr" type="text" id="name" value="${model.BoardConfig.name }" readonly="readonly">
                                        </li>
                                        <li>
                                        	<span class="list_t">게시글 제목</span>
                                        	<input class="input_title" type="text" name="title" id="title" value="${model.view.title }" >
                                        </li>
                                        <c:if test="${model.BoardConfig.file == 'TRUE' }">
                                        <c:forEach var="item" items="${model.filelist}" varStatus="status">
                                                <c:if test="${item != '' && item != null}">
                                            	<div id="fileDiv_${status.index}">
                                            	<a class="relate_c" href="${pageContext.request.contextPath}/fileDown.do?path=${pageContext.request.contextPath}/resources/upload/file/${item.filename}" >
                                            	${fn:substring(item.filename,13,fn:length(item.filename))} - 다운로드
                                            	</a>
                                            	<input type="hidden" id="file"  value="${item.filename}" />
												<!--<a class="relate_c" href="javascript:fileRemove('${item}', '${status.index}')">(삭제)</a>
												-->
												<button class="" onclick="fileRemove('${item.filename}', '${item.idx}')">삭제</button>
                                            	</div>
                                            	</c:if>
                                        </c:forEach>
                                        <c:forEach var="i" end="${model.BoardConfig.file_cnt - model.filelist.size()}" begin="1">
                                        <li>
                                           	<span class="list_t">파일선택</span>
                                            <input type="file" id="file" name="file${i}" value="${model.filelist[i - 1].filename }" change="FALSE" onchange="fileChange(this)">
                                        </li>
                                        </c:forEach>
                                        </c:if>
                                        <li>
                                        	<span class="list_t">게시글 내용</span>
                                        	<textarea name="content" id="editor">${model.view.content }</textarea>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                         <!--저장하기 버튼-->
                        <div class="register_btn_area">
                            <div class="register_btn_con">
                                <a class="storage" onclick="updateClick()">게시글 수정</a>
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
	
	$(".adm_menu_con > li").eq(1).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(1).css({
	    backgroundColor: "#fff"
	});
});


function updateClick(){
	
	console.log('수정하기');
	
	$('[change=FALSE]').attr('disabled',true);
	
	var fileCnt = $('[change=TRUE]').length;
	
	$('[name=file]').val(fileCnt);
	
	$('form[name=updateForm]').submit();
	
}

function deleteClick(){
	
	console.log('삭제하기');
	var result = confirm('해당 게시글을 정말 삭제하시겠습니까?\n 삭제된 데이터는 복구가 불가능합니다.');
	if(result){
		
		console.log('삭제');
		$('form[name=updateForm]').attr('action', './delete.do');
		$('form[name=updateForm]').submit();
		
	}else{
		
		console.log('삭제 안함');
		
	}
	
}

function fileChange(e){
	
	$(e).attr('change' , 'TRUE');
	
}

function fileRemove(fileName , idx){
	
	var board_idx = '${model.view.board_idx}';
	var board_data_idx = '${model.view.idx}';
	
	var result = confirm('해당 파일을 삭제 하시겠습니까?');
	if(result == true){
		
		console.log('파일 삭제 요청중---');
		$.ajax({
			
			url : '/api/fileDelete.do',
			type : 'POST',
			data : ({
				idx : idx,
				board_idx : board_idx,
				board_data_idx : board_data_idx
			}),
			success : function(data , status , xhr){
				console.log('success');
				location.reload();
			},
			error : function(status , xhr){
				console.log('error');
			}
			
		})
		
	}else{
		
		console.log('삭제 안함');
		
	}
	
}

</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>