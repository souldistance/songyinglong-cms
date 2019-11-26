<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//模态框 头部回显
	$(function() {
		$("#articleTitle").html("“" + '${article.title }' + "”");
	})
</script>
<div class="jumbotron jumbotron-fluid">
	<div class="container container-sm">
		<h1 class="display-4">${article.title }</h1>
		<p class="lead">
			<fmt:formatDate value="${article.created }"
				pattern="yyyy-MM-dd HH:mm:ss" />
			<span style="padding-left: 20px">点击量: ${article.hits }</span>
		</p>
		<p class="lead">
			发布人 · <span>${article.user.username }</span>
		</p>
	</div>
</div>
<hr>
<div id="carouselExampleCaptions" class="carousel slide"
	data-ride="carousel" style="width: 50%; margin: 0 auto; clear: both;">
	<ol class="carousel-indicators">
		<c:forEach items="${pictures}" var="a" varStatus="i">
			<li data-target="#carouselExampleCaptions"
				data-slide-to="${i.index }" class="${i.index==0?"active":"" }"></li>
		</c:forEach>
	</ol>
	<div class="carousel-inner">
		<c:forEach items="${pictures }" varStatus="i" var="picture">
			<div class="carousel-item ${i.index==0?"active":"" }">
				<img src="/pic/${picture.url}" alt="${picture.desc }"
					style="width: 100%; height: 300px;">
				<div class="carousel-caption d-none d-md-block">
					<h5>${picture.desc }</h5>

				</div>
			</div>
		</c:forEach>
	</div>
	<a class="carousel-control-prev" href="#carouselExampleCaptions"
		role="button" data-slide="prev"> <span
		class="carousel-control-prev-icon" aria-hidden="true"></span> <span
		class="sr-only">Previous</span>
	</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
		role="button" data-slide="next"> <span
		class="carousel-control-next-icon" aria-hidden="true"></span> <span
		class="sr-only">Next</span>
	</a>
</div>