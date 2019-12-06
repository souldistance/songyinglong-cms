<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<style>
.sticky {
	position: -webkit-sticky;
	position: sticky;
	top: 0px;
	padding: 6px;
	z-index: 10;
}

#left-nav .nav-link:hover {
	background-color: #ed4040 !important;
	color: #fff !important;
	font-size: 18px !important;
}

#left-nav .nav-link {
	font-size: 16px;
	display: block;
	border-radius: 4px;
	padding: 0px;
}

#left-nav .nav-item {
	/* width: 110px; */
	height: 40px;
	line-height: 40px;
	text-align: center;
	padding: 0px;
	color: #444;
	margin-bottom: 2px;
	transition-property: color, background-color;
}

#left-nav .nav-item .active {
	background-color: #ed4040 !important;
	color: #fff !important;
}

.logo {
	width: 110px;
	height: 28px;
	margin-bottom: 16px;
}

#top {
	background-color: black !important;
	padding: 0px;
}

#top a {
	color: #fff;
	font-size: 14px;
	padding-left: 0px;
	padding-right: 0px;
}

#top .nav-item {
	padding: 0 10px;
	border-right: 1px solid #3a3a3a;
}

.textOverflow {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	padding: 10px;
}

.list-group-item {
	padding: 10px 0px;
}

#content li.list-group-item {
	width: 100%;
	border: none !important;
	border-bottom: 1px solid rgba(0, 0, 0, 0.125) !important;
}

#right .card {
	width: 100% !important;
}

#categoryNav {
	border-bottom: 2px solid #f2f2f2;
	font-size: 16px;
	background: #fff;
}

.myactive {
	border-bottom: 2px solid #f85959;
}

#categoryNav li {
	margin-right: 33px;
	line-height: 40px;
	padding-top: 0px;
	padding-bottom: 0px;
}

#categoryNav li a {
	font-size: 17px;
	padding: 0px;
}

.categorySticky {
	position: -webkit-sticky;
	position: sticky;
	top: 0px;
	padding: 0px;
	z-index: 10;
}

#user div a {
	font-size: 14px;
	text-align: center;
	color: #000;
}

.modal-backdrop {
	opacity: 0.05 !important;
	filter: alpha(opacity = 0.05) !important;
}
</style>
<script type="text/javascript">
	function detail(id) {
		$("#article_content").load("/article/detail?id="+id);
	}
	
</script>
<title>Insert title here</title>
</head>
<body style="min-width: 450px;">
	<!-- 头部 -->
	<nav class="navbar navbar-light bg-light"
		style="padding-left: 0px; padding-right: 0px; background-color: black;"
		id="top">
		<ul class="nav" style="float: left;">
			<li class="nav-item"><a class="nav-link active" href="#">下载APP</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">0° / 0° 
			</a></li>
		</ul>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link" href="#">反馈</a></li>
			<li class="nav-item"><a class="nav-link" href="#">投诉侵权</a></li>
			<li class="nav-item"><a class="nav-link" href="#">头条产品</a></li>
			<c:if test="${user.username!=null }">
				<li class="nav-item" style="border: none;">
					<div class="btn-group" id="user">
						<a href="#" class="nav-link dropdown-toggle" role="button"
							id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <c:out
								value="${user.username}" default="cms-User" />
						</a>
						<div class="dropdown-menu" style="top: 40px; left: -110px;">
							<a class="dropdown-item" href="/my/">个人主页</a> <a
								class="dropdown-item" href="#">个人设置</a> <a class="dropdown-item"
								href="#">我的文章</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="/passport/logout">注销</a>
						</div>
					</div>
				</li>
				<!-- <li class="nav-item"><a class="nav-link active"
					href="/passport/logout"
					style="background-color: #ED4040; color: #fff; width: 68px; text-align: center; z-index: 10; height: 100%;">注销</a>
				</li> -->
			</c:if>
			<c:if test="${user.username==null }">
				<li class="nav-item" style="border: none;" style="border: none;"><a
					class="nav-link active" href="/passport/login"
					style="background-color: #ED4040; color: #fff; width: 68px; text-align: center; z-index: 10; height: 100%;">登录</a>
				</li>
			</c:if>
		</ul>
	</nav>


	<div class="container">
		<div class="row">
			<!-- 左侧栏目 -->
			<div class="col-md-2" style="padding-right: 0px;">
				<ul class="nav nav-pills flex-column sticky"
					style="float: right; margin-top: 20px; width: 100%" id="left-nav">
					<li class="nav-item" style="text-align: center;"><a href="/">
							<img class="logo" alt="logo"
							src="/resource/css/img/logo.271e845.png">
					</a></li>
					<li class="nav-item"><a
						class="nav-link ${article.channelId==null?" active":"" }" href="/">热点</a></li>
					<c:forEach items="${channels }" var="channel">
						<li class="nav-item"><a
							class="nav-link ${article.channelId==channel.id?"
							active":"" }"
							href="/?channelId=${channel.id }">${channel.name }</a></li>
					</c:forEach>
				</ul>

			</div>

			<!-- 中间文章标题 -->
			<div class="col-md-7  min_h_500" id="content"
				style="padding-left: 30px;">
				<ul class="list-group list-group-horizontal" id="display_content"
					style="height: 100%">
					<li class="list-group-item"><c:choose>
							<c:when test="${article.channelId==null }">
								<!-- 轮播图 -->
								<div id="demo" class="carousel slide" data-ride="carousel" 
									style="width: 100%; margin-top: 20px;" data-interval="2000">
									<!-- 指示符 -->
									<ul class="carousel-indicators">
										<li data-target="#demo" data-slide-to="0" class="active"></li>
										<li data-target="#demo" data-slide-to="1"></li>
										<li data-target="#demo" data-slide-to="2"></li>
									</ul>
									<!-- 轮播图片 -->
									<div class="carousel-inner">
										<div class="carousel-item active">
											<img src="/resource/img/img_fjords_wide.jpg"
												style="width: 100%">
										</div>
										<div class="carousel-item">
											<img src="/resource/img/img_mountains_wide.jpg"
												style="width: 100%">
										</div>
										<div class="carousel-item">
											<img src="/resource/img/img_nature_wide.jpg"
												style="width: 100%">
										</div>
									</div>
									<!-- 左右切换按钮 -->
									<a class="carousel-control-prev" href="#demo" data-slide="prev">
										<span class="carousel-control-prev-icon"></span>
									</a> <a class="carousel-control-next" href="#demo"
										data-slide="next"> <span
										class="carousel-control-next-icon"></span>
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<!-- 二级分类 显示当前侧边栏所选中的栏目下的分类 -->
								<div class="categorySticky">
									<ul class="nav" id="categoryNav">
										<li class="nav-item"><a
											class="nav-link ${article.categoryId==null?"
											myactive":"" }" href="/?channelId=${article.channelId }">全部</a></li>
										<c:forEach items="${categories }" var="category">
											<li class="nav-item"><a
												class="nav-link ${category.id==article.categoryId?"
												myactive":"" }" href="/?channelId=${article.channelId }&categoryId=${category.id }">${category.name }</a>
											</li>
										</c:forEach>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>
						<ul class="list-group list-group-flush"
							style="margin-bottom: 20px;">
							<c:forEach items="${pg.list }" var="article">
								<li class="list-group-item">
									<div style="width: 25%; height: 100px; float: left;">
										<a href="#"><img alt="" src="/pic/${article.picture }"
											style="width: 100%; height: 100px;"></a>
									</div>
									<div style="float: right; height: 100px; width: 75%;"
										class="textOverflow">
										<%-- <a data-toggle="modal" data-target="#exampleModalScrollable"
											href="#" onclick="detail(${article.id})"
											style="margin: 3px; margin-top: 10px; font-size: 20px; font-weight: bold;"
											title="${article.title }">${article.title }</a> --%>
										<a href="/article/detail?id=${article.id}"  target="_blank"
											style="margin: 3px; margin-top: 10px; font-size: 20px; font-weight: bold;"
											title="${article.title }">${article.title }</a>
										<div style="padding: 8px;">
											<p style="color: #999; font-size: 14px;">${article.user.username }
												· 最后修改时间 :
												<fmt:formatDate value="${article.updated }"
													pattern="yyyy-MM-dd mm:HH:ss" />
												· 点击量 : ${article.hits }
											</p>
										</div>
									</div>

								</li>
							</c:forEach>
						</ul> <jsp:include page="/WEB-INF/views/common/pages.jsp"></jsp:include>
					</li>
				</ul>
			</div>

			<!-- 右侧边栏 -->
			<div class="col-md-3  min_h_500" id="right" style="margin-top: 30px;">
				<!--  最新文章 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong> 最新文章</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${lastArticle.list }" var="article">
								<p class="textOverflow"
									style="padding: 0px; padding-bottom: 10px; border-bottom: 1px solid #e8e8e8;">
									<%-- <a data-toggle="modal" data-target="#exampleModalScrollable"
										href="#" onclick="detail(${article.id})"
										title="${article.title }">${article.title }</a> --%>
									<a href="/article/detail?id=${article.id}"  target="_blank"
											title="${article.title }">${article.title }</a>
								</p>
							</c:forEach>
						</div>
					</div>
					<%-- <!-- 模态框 -->
					<div class="modal fade bd-example-modal-lg"
						id="exampleModalScrollable" tabindex="-1" role="dialog"
						aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
						<div class="modal-dialog modal-dialog-scrollable modal-lg"
							role="document">
							<div class="modal-content">

								<!-- 模态框头部 -->
								<div class="modal-header">
									<h4 class="modal-title" id="title">${article.title }</h4>
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
					</div> --%>
				</div>
				<!-- 图片集 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>图片集</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${picturesInfo.list }" var="article">
								<div class="media">
									<img src="/pic/${article.picture }" class="mr-3"
										alt="${article.title }" width="60px" height="60px"
										style="margin-right: 0 !important">
									<div class="media-body textOverflow">
										<%-- <a data-toggle="modal" data-target="#exampleModalScrollable"
											href="#" onclick="detail(${article.id})"
											title="${article.title }">${article.title }</a> --%>
										<a href="/article/detail?id=${article.id}"  target="_blank"
											title="${article.title }">${article.title }</a>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>
				<!-- 专题 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>专题</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${specialList }" var="s">
								<div class="media">
									<div class="media-body">
										<h4>
											<strong>${s.title }</strong>
										</h4>
										<h5 align="center">${s.abstracts }</h5>
										<br>
										<ul>
											<c:forEach items="${s.specialArticles}" var="ss">
												<li><a href="/select?id=${ss.article.id }"
													target="_blank">${ss.article.title }</a></li>
											</c:forEach>
										</ul>
									</div>
								</div>
								<br>
								<hr>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 友情链接 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>友情链接</strong>
						</div>
						<div class="card-body">
							<ul class="nav">
								<c:forEach items="${linksInfo.list }" var="link">
									<li class="nav-item"><a class="nav-link active"
										href="${link.url }" target="_blank">${link.text }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<!-- 友情链接显示功能 -->
	<div class="container" style="margin-bottom: 20px;">
		<ul class="nav" style="justify-content: center;" id="link">
			<li class="nav-item"><a class="nav-link active"
					href="${link.url }" target="_blank" style="padding: 0;color: #666 ">友情链接:<span class="mod_copyright_split" style="margin: 0 8px;">|</span></a>
			</li>
			<c:forEach items="${linksInfo.list }" var="link" varStatus="i">
				<li class="nav-item"><a class="nav-link active"
					href="${link.url }" target="_blank" style="padding: 0;color: #666 ">${link.text }
					<c:if test="${linksInfo.list.size()-1!=i.index }"><span class="mod_copyright_split" style="margin: 0 8px;">|</span></c:if></a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		function limit(pageNum) {
			if (pageNum == 0) {
				return;
			}
			location = "/?pageNum=" + pageNum +"&channelId="+'${article.channelId }' +"&categoryId="
					+ '${article.categoryId }';
		}
	</script>
</body>
</html>