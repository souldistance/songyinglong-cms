<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//模态框 头部回显
	$(function () {
		$("#articleTitle").html("“"+'${article.title }'+"”");
	})
</script>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
		<div class="container container-sm">
			<h1 class="display-4">${article.title }</h1>
			<p class="lead">
				<fmt:formatDate value="${article.created }"
					pattern="yyyy-MM-dd HH:mm:ss" /><span style="padding-left: 20px">点击量: ${article.hits }</span>
			</p>
			<p class="lead">发布人 · <span>${article.user.username }</span></p>
		</div>
	</div>
	<hr>
	<div class="container">
  	${article.content }
</div>
