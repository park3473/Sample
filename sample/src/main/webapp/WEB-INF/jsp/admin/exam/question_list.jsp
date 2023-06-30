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
                                <span>자가진단 문제 리스트</span>
                            </div>
                            
                            <!--관리자 버튼-->
                            <div class="page_seach">
                                <div class="adm_btn_wrap stats_btn_area">
                                    <ul>
                                    <li class="delete">
                                        <a href="${pageContext.request.contextPath}/admin/question/insert.do?exam_idx=${model.exam_idx}">문제 등록</a>
                                    </li>
                                </ul>
                                </div>
                            </div>
                            <!--관리자 버튼 end-->
                            
                            <div class="table_wrap">
                                <table id="bootstrap-data-table">
                                    <tr>
                                        <th class="number">문항 번호</th>
                                        <th class="name">제목</th>
                                        <th class="type">문제 유형</th>
                                        <th class="create_tm">생성일시</th>
                                        <th class="update_tm">수정일시</th>
                                        <th class="setting">비고</th>
                                    </tr>
                                    <c:forEach var="item" items="${model.list}" varStatus="status">
                                    <tr data-role="button" data-id="${item.idx}"  >
                                        <td>${item.seq }</td>
                                        <td>${item.NAME }</td>
                                        <td>${item.TYPE }</td>
                                        <td>
                                            ${fn:substring(item.create_tm,0,11)}
                                        </td>
                                        <td>
                                            ${fn:substring(item.update_tm,0,11)}
                                        </td>
                                        <td>
                                        	<button type="button"  onclick="location.href='/admin/question/view.do?idx=${item.question_idx}'" data-idx="${item.idx }" data-exam_idx="${item.exam_idx }" data-seq="${item.seq }" data-question_idx="${item.question_idx }" data-name="${item.name }" data-type="${item.type }" data-objectives="${item.objectives }" data-select_type="${item.select_type}" data-select-val="${item.select_val }">문제 보기</button>
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

</script>

</html>

