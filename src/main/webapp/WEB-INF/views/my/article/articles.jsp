<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="input-group">
	<div class="form-group" >
		<div class="input-group input-group-inline">
		<label for="title">标题名:</label> <input type="text" id="title"
		name="title" class="form-control form-control-sm" placeholder="请输入标题名"
		aria-label="Recipient's title" aria-describedby="button-addon2"  >
		<button class="btn btn-outline-secondary btn-sm" type="button"
					id="button-addon2" onclick="query()">查询</button>
		</div>
  	</div>
</div>
	<table class="table" >
		<thead class="thead-light " align="center" >
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
						否
					</c:if> <c:if test="${article.hot==1 }">
						是
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
	$("#center").load(
			"/my/article/articles?title=" + $('[name=title]').val());
}
//分页功能
function limit(pageNum) {
	if (pageNum == 0) {
		return;
	}
	$("#center").load(
			"/my/article/articles?title=" + $('[name=title]').val()
					+ "&pageNum=" + pageNum);
}
//查询后回显数值
$(function() {
	$('[name=title]').val('${article.title}');
})
//根据文章ID查询详情
	function detail(id) {
		//在模态框中加载
		$("#article_content").load(
				"/my/article/detail?id="+id);
	}
</script>