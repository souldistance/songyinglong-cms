<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
/* 设置模态框不变色  */
.modal-backdrop {
	opacity: 0.05 !important;
	filter: alpha(opacity = 0.05) !important;
}
</style>
<script type="text/javascript">
//根据文章ID查询详情
function detail(id) {
	$("#article_content").load("/admin/article/detail?id="+id);
}

</script>
<div class="input-group mb-3 " style="width: 35%;">
	<label for="title">标题名:</label> <input type="text" id="title" name="title"
		class="form-control form-control-sm" placeholder="请输入标题名"
		aria-label="Recipient's title" aria-describedby="button-addon2">
	<div style="width: 16%;">
	<select class="form-control form-control-sm" name="status" >
		<option value="0">待审</option>
		<option value="1">已审</option>
		<option value="-1">驳回</option>
		<option value="2">全部</option>
	</select>
	</div>
	<div class="input-group-append ">
		<button class="btn btn-outline-secondary btn-sm" type="button"
			id="button-addon2" onclick="query()">查询</button>
	</div>
</div>
<table class="table">
	<thead class="thead-light " align="center">
		<tr>
			<th scope="col">序号</th>
			<th scope="col">文章标题</th>
			<th scope="col">作者</th>
			<th scope="col">文章状态</th>
			<th scope="col">发布时间</th>
			<th scope="col">点击量</th>
			<th scope="col">是否热门</th>
			<th scope="col">操作</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach items="${pg.list }" var="article" varStatus="i">
			<tr class="thead-light table-bordered">
				<td>${(pg.pageNum-1)*pg.pageSize+i.index+1 }</td>
				<td>${article.title }</td>
				<td>${article.user.username }</td>
				<td><c:if test="${article.status==0}">
						待审
					</c:if> <c:if test="${article.status==-1}">
						驳回
					</c:if> <c:if test="${article.status==1}">
						已审
					</c:if></td>
				<td><fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${article.hits }</td>
				<td class="hot"><c:if test="${article.hot==0 }">
						<button type="button" onclick="updateHot(${article.id},this)"
							class="btn btn-warning">否</button>
					</c:if> <c:if test="${article.hot==1 }">
						<button type="button" class="btn btn-success"
							onclick="updateHot(${article.id},this)">是</button>
					</c:if></td>
				<td>
					<button class="btn btn-info" data-toggle="modal"
								data-target="#exampleModalScrollable" onclick="detail(${article.id})">详情</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="/WEB-INF/views/common/pages.jsp" />
<script type="text/javascript">
//查询列表
function query() {
	$("#content-wrapper").load(
			"/admin/article/articles?title=" + $('[name=title]').val()+"&status=" + $('[name=status]').val());
}
//分页功能
function limit(pageNum) {
	if (pageNum == 0) {
		return;
	}
	$("#content-wrapper").load(
			"/admin/article/articles?title=" + $('[name=title]').val()+"&status=" + $('[name=status]').val()
					+ "&pageNum=" + pageNum);
}
//查询后回显数值
$(function() {
	$('[name=title]').val('${article.title}');
	var status='${article.status}';
	if(status==null || status==''){
		$('[name=status]').val("2");
	}else{
		$('[name=status]').val('${article.status}');
	}
})
//修改热门状态
function updateHot(id,thiz) {
	var hot= $(thiz).text();
	$.ajax({
		url:"/admin/article/update",
		data:{id:id,hot:(hot=="是"?0:1)},
		success:function(flag){
			if(flag.code==0){
				if(hot=='是'){
					$(thiz).removeClass("btn-success");
					$(thiz).addClass("btn-warning");
				}else{
					$(thiz).removeClass("btn-warning");
					$(thiz).addClass("btn-success");
				}
				$(thiz).html(hot=='是'?"否":"是");
			}else{
				alert(flag.message);
			}
		},error:function(msg){
			alert("系统错误!");
		}
	})
}
</script>
