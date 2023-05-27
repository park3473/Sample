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
                                <span>회원 관리</span>
                            </div>
                            <div class="table_wrap">
                                <table id="bootstrap-data-table">
                                    <tr>
                                        <th class="check"><input type="checkbox" class="" name="chk_calc_all" id="chk_calc_all" value=""></th>
                                        <th class="number">번호</th>
                                        <th class="type">상태</th>
                                        <th class="level">분류</th>
                                        <th class="member_id">아이디</th>
                                        <th class="name">이름</th>
                                        <th class="create">회원 생성 일자</th>
                                        <th class="setting">관리</th>
                                    </tr>
                                    <c:forEach var="item" items="${model.list}" varStatus="status">
                                    <tr data-role="button" data-id="${item.idx}"  >
                                        <td><input type="checkbox" value="${item.idx}" name="chk_calc" data-id="${item.idx}"></td>
                                        <td>${model.itemtotalcount - (status.index + model.page *  model.itemcount)}</td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${item.type == '2' }">삭제</c:when>
                                        		<c:when test="${item.type == '1' }">승인</c:when>
                                        		<c:when test="${item.type == '0' }">미승인</c:when>
                                        	</c:choose>
                                        </td>
                                        <td>
                                        	<c:choose>
                                        		<c:when test="${item.level == '1' }">일반</c:when>
                                        		<c:when test="${item.level == '3' }">관리자</c:when>
                                        	</c:choose>
                                        </td>
                                        <td>${item.member_id }</td>
                                        <td>${item.name}</td>
                                        <td>
                                            ${fn:substring(item.create_tm,0,11)}
                                        </td>
                                        <td>
                                        	<button type="button" onclick="location.href='/admin/member/view.do?idx=${item.idx}'" >회원 관리</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </div>

                            <!--관리자 버튼-->
                            <div class="page_seach">
                                <div>
                                    <select id="SEARCH_TYPE" name="SEARCH_TYPE">
                                        <option value="ALL">전체</option>
                                        <option value="type" <c:if test="${model.before.SEARCH_TYPE == 'type'}">selected</c:if>>상태</option>
                                        <option value="level"  <c:if test="${model.before.SEARCH_TYPE == 'level'}">selected</c:if>>분류</option>
                                        <option value="member_id" <c:if test="${model.before.SEARCH_TYPE == 'member_id'}">selected</c:if>>아이디</option>
                                        <option value="name" <c:if test="${model.before.SEARCH_TYPE == 'name'}">selected</c:if>>이름</option>
                                    </select>
                                    <input style="width: 191px;" type="text" value="${model.before.SEARCH_TEXT }" name="SEARCH_TEXT" id="SEARCH_TEXT" >
                                    <button type="button" value="검색" onClick="searchBtnClick()">검색</button>
                                </div>
                                <div class="adm_btn_wrap stats_btn_area">
                                    <ul>
                                    <li class="delete">
                                        <a href="javascript:deleteArrClick()">선택삭제</a>
                                    </li>
                                    <li class="delete">
                                        <a href="./insert.do">회원 등록</a>
                                    </li>
                                </ul>
                                </div>
                            </div>

                            <!--관리자 버튼 end-->


                            <!--페이지 넘버-->
                            <nav class="paging_number">
                                <ul class="page">
                                   <%@ include file="../include/pageing.jsp" %>
                                </ul>
                            </nav>
                            <!--페이지 넘버 end-->
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
	
	$(".adm_menu_con > li").eq(0).find(".sub_menu_con").show();
	$(".adm_menu_con > li").eq(0).css({
	    backgroundColor: "#fff"
	});
});

</script>

</html>

