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
<button type="button" class="btn btn-warning" style="float: right;margin-bottom: 5px;" onclick="insert()">添加</button>
<table class="table">
	<thead class="thead-light " align="center">
		<tr>
			<th scope="col">序号</th>
			<th scope="col">链接名称</th>
			<th scope="col">URL</th>
			<th scope="col">创建时间</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach items="${pg.list }" var="link" varStatus="i">
			<tr class="thead-light table-bordered">
				<td>${(pg.pageNum-1)*pg.pageSize+i.index+1 }</td>
				<td>${link.text }</td>
				<td><a href="${link.url }" target="_blank">${link.url }</a></td>
				<td><fmt:formatDate value="${link.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
			</tr>
		</c:forEach>
	</tbody>
	
</table>
<jsp:include page="/WEB-INF/views/common/pages.jsp" />
<script type="text/javascript">
//分页功能
function limit(pageNum) {
	if (pageNum == 0) {
		return;
	}
	$("#content-wrapper").load(
			"/admin/link/links?pageNum=" + pageNum);
}
function insert() {
	$("#content-wrapper").load("admin/link/insertLink");
}
</script>
