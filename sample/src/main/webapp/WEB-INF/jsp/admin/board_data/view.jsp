<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<%@ include file="../include/header.jsp" %>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="http://cdn.ckeditor.com/4.6.2/full/ckeditor.js"></script>

<script language="JavaScript">
//ckeditor setting
var ckeditor_config = {
	allowedContent : true,
	

	resize_enabled : false, // 에디터 크기를 조절하지 않음
	enterMode : CKEDITOR.ENTER_BR, // 엔터키를 <br> 로 적용함.
	shiftEnterMode : CKEDITOR.ENTER_P, // 쉬프트 +  엔터를 <p> 로 적용함.
	toolbarCanCollapse : true,
	removePlugins : "elementspath", // DOM 출력하지 않음                        
	filebrowserUploadUrl : '${pageContext.request.contextPath}/ckeditor/file_upload.do', // 파일 업로드를 처리 할 경로 설정.
	height : '500px',
	// 에디터에 사용할 기능들 정의
	toolbar : [
			[  'Source','NewPage', 'Preview' ],
			[ 'Cut', 'Copy', 'Paste', 'PasteText', '-', 'Undo', 'Redo' ],
			[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
					'Superscript' ],
			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight',
					'JustifyBlock' ], '/',
			['Image','Link','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
			['Styles','Format','Font','FontSize'],['TextColor','BGColor'],['Maximize', 'ShowBlocks','-'],
			
			[ 'About' ] ]

};

var editor = null;

jQuery(function() {
	// ckeditor 적용
	editor = CKEDITOR.replace( "content" , ckeditor_config);
});

  CKEDITOR.on('dialogDefinition', function( ev ){
      var dialogName = ev.data.name;
      var dialogDefinition = ev.data.definition;
    
      switch (dialogName) {
          case 'image': //Image Properties dialog
              //dialogDefinition.removeContents('info');
              dialogDefinition.removeContents('Link');
              dialogDefinition.removeContents('advanced');
              break;
      }
  });

</script>

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
                        <input type="hidden" name="image" value="${model.view.image }"  />
                        <input type="hidden" name="member_id" value="${model.view.member_id }"  />
                        <input type="hidden" name="name" value="${model.view.name }"  />
                        <input type="hidden" name="file" value="${model.view.file}"  />
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
                                            	<a class="relate_c" href="javascript:fileDown('${pageContext.request.contextPath}/resources/${pageContext.request.contextPath}/upload/board_data/${item.filename}')">다운로드</a>
                                            	<input type="hidden" id="file"  value="${item.filename}" />
												<!--<a class="relate_c" href="javascript:fileRemove('${item}', '${status.index}')">(삭제)</a>
												-->
												<a class="relate_c" href="javascript:fileRemove('${item.filename}', '${item.idx}')">(삭제)</a>
                                            	&nbsp; &nbsp; 
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
                                        	<textarea name="content" id="content">${model.view.content }</textarea>
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