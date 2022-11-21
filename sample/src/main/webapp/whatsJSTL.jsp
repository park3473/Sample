<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSP문서에서 JSTL을 사용하기 위해서 선언 필요 
	 c라는 프리픽스로 시작하는 테그는 아래의 url에서 가저옴-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	1 c:set
	변수를 선언하는 테그
	이렇게 선언하면 ${변수이름} 으로 사용할 수 있음 
		<c:set var="변수이름" value="값">
		</c:set>
	
	2 c:remove
	변수를 제거할 때 사용
		<c:remove var="변수이름"></c:remove>
	
	3 c:out
	변수내용을 출력할 때 사용 
		<c:out value="${변수이름}"></c:out>
	
	4 c:if
	자바의 if문과 동일하지만 else 구문이 없음 
	테스트 결과를 var과 scope속성을 통해 범위 변수로 할당
		<c:set var="income" scope="session" value="${4000*4}"/>  
		<c:if test="${income > 8000}">  
	   		<p>My income is: <c:out value="${income}"/><p>  
		</c:if> 
		
	5 c:choose
	스위치문 이라고 생각하면 되며 아래 when과 otherwise를 같이 사용함
	6 c:when
	스위치문 안에 있는 case
	7 c:otherwise 
	스위치문 안에 있는 default
		<c:set var="income" scope="session" value="${4000*4}" />
		<p> Your income is : <c:out value="${income}" /> </p>
		<c:choose>
			<c:when test="${income <= 1000}">  
	       Income is not good.  
	    </c:when>
			<c:when test="${income > 10000}">  
	        Income is very good.  
	    </c:when>
			<c:otherwise>  
	       Income is undetermined...  
	    </c:otherwise>
		</c:choose>

	8.c:forEach
	자바의 for문과 비슷함
	
		아래 예제대로 한다면 1부터 10까지 출력됨 
		<c:forEach var="임시변수명" begin="1" end="10">
			${임시변수명}<br>
		</c:forEach>
	
		배열의 내용을 순서대로 출력하려면
		<c:forEach var="list" items="${배열이름}">
			${list} <br />
		</c:forEach>
	
	9 c:forTokens
	문자열에 포함된 토큰을 분리해서 각각 토큰에 대한 반복처리를 수행 
		
		아래 예제 처럼 items는 ,로 구분되어있는데 delims에 ,를 지정해두면
		각각 따로 분리가됨 
		<c:forTokens var="temp" items="abc, def, ghi" delims=",">
			${temp}<br>
		</c:forTokens>
 

</body>
</html>