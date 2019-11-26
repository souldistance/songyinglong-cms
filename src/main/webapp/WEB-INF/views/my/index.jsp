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
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<style type="text/css">
.container {
	max-width: 1200px;
}
</style>
<style type="text/css">
/* 模态框遮罩层设置透明度  */
.modal-backdrop {
  opacity: 0.05 !important;
  filter: alpha(opacity=0.05) !important;
}
</style>
<title>个人中心</title>
</head>
<body>
	<!-- top -->
	<jsp:include page="/WEB-INF/views/common/top.jsp" />
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	<br />
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3" style="padding-left: 0px;">
				<!-- 导航条 -->
				<jsp:include page="/WEB-INF/views/my/left.jsp"></jsp:include>
				<br />
			</div>
			<div class="col-md-9" id="center">
				<!-- 中间内容区域 -->
				<div style="display: none">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
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
					<h4 class="modal-title" id="articleTitle">${article.title }</h4>
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
	<!-- footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>