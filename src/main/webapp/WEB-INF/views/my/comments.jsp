<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>我的博客</title>
</head>
<body>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param
						value="comments" name="module" /></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-body">
						<h1>我的评论</h1>
						<hr />
						<table class="table table-striped table-bordered table-hover" style="table-layout:fixed;">
							<thead >
								<tr align="center">
									<th>评论正文</th>
									<th width="30%">评论文章</th>
									<th width="25%">发表时间</th>
									<th width="11%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pg.comments}" var="comment">
									<tr>
										<td>${comment.content}</td>
										<td style="overflow:hidden;white-space: nowrap;text-overflow: ellipsis;">
											<a href="/article/${comment.article.id}">${comment.article.title}</a>
										</td>
										<td align="center">
											<fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td>
											<button type="button" class="btn btn-danger" onclick="del(${comment.id},1)">删除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/common/pages.jsp" />

	<script type="text/javascript">
		function del(id,deleted){
			if(confirm("确定要删除这条评论吗?")){
				$.post(
					"/my/comment/del",
					{id:id,deleted:deleted},
					function(result){
						if(result){
							alert("删除成功!");
							location.reload();
						}else{
							alert("删除失败!");
						}
					},
					"text"
				);
			}
		}
	</script>
</body>
</html>