<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<title>아이앤시티테스트 - 관리자</title>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min45.css">
<!-- style start-->
<link type="text/css" rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/owl.carousel.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/owl.theme.default.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/admin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin/footer.css">
<!-- style end-->

<!-- script start-->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/imageMapResizer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/navi.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/home_java.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/aos.js"></script>
<!-- script end-->

<script src="${pageContext.request.contextPath}/resources/sweetalert/sweetalert2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/jquery.counterup.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/sweetalert/sweetalert2.min.css">
</head>
<body>

<script>
    AOS.init({
        easing:'ease-out-back',
        duration:1000,
		 once: true
    }); 
</script>  

<!-- Global site tag (gtag.js) - Google Analytics -->
<script src="${pageContext.request.contextPath}/resources/js/gtag.js"></script>
<!-- <script async src="https://www.googletagmanager.com/gtag/js?id=UA-163568943-1"></script> -->
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-163568943-1');
</script>
<script>

$(document).ready(function () {
	
	 $(".adm_menu_con li").click(function () {
	        $(".adm_menu_con >li").find(".sub_menu_con").hide();
	        $(".adm_menu_con >li").css({
	            backgroundColor: "transparent"
	        });
	        $(this).find(".sub_menu_con").show();
	        $(this).css({
	            backgroundColor: "#fff"
	        });
	    });
})

</script>
<body>