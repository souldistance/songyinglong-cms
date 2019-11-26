<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<link href="/resource/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="<%=request.getContextPath()%>/resource/css/cms.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
	//返回文章列表
	function back() {
		location = "/";
	}
	$(function () {
		$("#title").html("“"+'${article.title }'+"”");
	})
	function collect() {
		var text='${article.title }';
		var url=location.href;
		$.ajax({
			url:"/index/article/collect",
			data:{text:text,url:url},
			type:"post",
			success:function(flag){
				if(flag.code==0){
					alert("收藏成功!");
					$("#login").html(" ");
					$("#collect").html("<span style='color: red;font-size: 18px;'>★已收藏</span>");
				}else{
					alert(flag.message);
					$("#login").html("<button type='button' class='btn btn-info' onclick='tologin()'>登录</button>");
				}
			},error:function(){
				alert("系统错误!");
			}
		}) 
	}
	function tologin() {
		location='/passport/login';
	}
</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron jumbotron-fluid">
		<div class="container container-sm">
			<h4 class="display-4">${article.title }</h4>
			<p class="lead">
				<fmt:formatDate value="${article.created }"
					pattern="yyyy-MM-dd HH:mm:ss" /><span style="padding-left: 20px">点击量: ${article.hits }</span>
			</p>
			<p class="lead">发布人 · <span>${article.user.username }</span></p>
		</div>
	</div>
	<hr>
	<div style="float: left;" id="collect">
		<c:if test="${collect!=null }">
			<span style="color: red;font-size: 18px;">★已收藏</span>
			
		</c:if>
		<c:if test="${collect==null }">
			<a href="#" onclick="collect()"><span style=";font-size: 18px;">☆收藏</span></a><div id="login" style="float: left;"></div>
		</c:if>
	</div>
	<div style="text-align: right;">
		<button type="button" class="btn btn-dark" onclick="back()">返回</button>
	</div>
	<div class="container" align="center" style="align-content: center;">${article.content }</div>
	</div>
</body>
</html>