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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/bootstrap.js"></script>
<link href="<%=request.getContextPath()%>/resource/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<!-- top  navbar navbar-light bg-light-->
	<!-- 头部 -->
	<nav class="navbar navbar-expand-sm  bg-dark navbar-dark"
		style="justify-content: space-between; padding: 0px;">
		<a class="navbar-brand" href="#"><img
			style="width: 50px; height: 50px;" src="/resource/img/lol.jpg"
			class="rounded" alt="logo"><span style="font: bold;" class="">CMS系统后台</span>
		</a>
		<ul class="nav" style="margin-top: 0">
			<c:choose>
				<%-- 登录显示用户菜单 --%>
				<c:when test="${admin.username!=null}">
					<li class="nav-item"><a class="nav-link" href="/my/home">
							<img alt="" src="/resource/img/default_avatar.png"
							style="max-height: 2.5rem" class="rounded img-fluid">
					</a></li>
					<li class="nav-item">
						<div class="dropdown" style="padding-top: 0.4rem;"></div>
						<div class="btn-group">
							<a href="#" class="nav-link dropdown-toggle" role="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <c:out
									value="${admin.username}" default="cms-User" />
							</a>
							<div class="dropdown-menu" style="top: 45px; left: -20px;">

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/passport/logout">退出</a>
							</div>
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<%-- 未登录显示登录注册链接 --%>
					<li class="nav-item"><a class="nav-link"
						href="/passport/regist">注册</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/passport/login">登录</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>

	<!-- content -->
	<div class="container-fluid" style="margin-bottom: 100px;">
		<div class="row">
			<!-- 侧边栏 -->
			<div class="col-sm-2">
				<jsp:include page="/WEB-INF/views/admin/sidebar.jsp"></jsp:include>
			</div>
			<!-- 中间内容区域 -->
			<div class="col-sm-10" style="margin-bottom: 30px;">
				<div align="center" style="margin-top: 40px;" id="content-wrapper">
					<img alt="后台首页"
						src="/resource/img/f603918fa0ec08fa3139e00153ee3d6d55fbda5f.jpg"
						class="rounded-circle">
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框 -->
	<div class="modal fade bd-example-modal-lg" id="exampleModalScrollable"
		tabindex="-1" role="dialog"
		aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-scrollable modal-lg"
			role="document">
			<div class="modal-content">

				<!-- 模态框头部 -->
				<div class="modal-header">
					<h4 class="modal-title" id="tit"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 模态框主体 -->
				<div class="modal-body" id="article_content"></div>

				<!-- 模态框底部 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">关闭</button>
				</div>

			</div>
		</div>
	</div>
	<!-- foot -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>