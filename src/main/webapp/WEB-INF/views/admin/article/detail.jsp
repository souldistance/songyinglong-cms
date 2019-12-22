<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//修改文章审核状态
	function updateStatus(id,status) {
		/* $("#content-wrapper").load(
				"/admin/article/update?id="+id+"&status="+status); */
		$.post(
			"/admin/article/update",
			{id:id,status:status,contentType:0},
			function (msg) {
				if(msg){
					alert("操作成功!");
					$("#content-wrapper").load("/admin/article?status=2");
				}else{
					alert("操作失败!");
				}
			}
		)
	}
	$(function () {
		$("#tit").html("“"+'${article.title }'+"”");
	})
</script>
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
<button type="button" class="btn btn-success"
	onclick="updateStatus(${article.id},1)">通过</button>
<button type="button" class="btn btn-warning"
	onclick="updateStatus(${article.id},-1)">驳回</button>
<div class="container">${article.content }</div>
