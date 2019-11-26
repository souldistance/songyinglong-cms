<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="input-group mb-3" style="width: 300px;">
	<input type="text" name="username" class="form-control form-control-sm"
		placeholder="请输入用户名" aria-label="Recipient's username"
		aria-describedby="button-addon2">
	<div class="input-group-append">
		<button class="btn btn-outline-secondary btn-sm" type="button"
			id="button-addon2" onclick="query()">查询</button>
	</div>
</div>
<table class="table">
	<thead class="thead-light " align="center">
		<tr>
			<th scope="col">序号</th>
			<th scope="col">用户名</th>
			<th scope="col">昵称</th>
			<th scope="col">性别</th>
			<th scope="col">生日</th>
			<th scope="col">角色</th>
			<th scope="col">注册日期</th>
			<th scope="col">修改日期</th>
			<th scope="col">用户状态</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach items="${pg.list }" var="user" varStatus="i">
			<tr class="thead-light table-bordered">
				<td>${(pg.pageNum-1)*pg.pageSize+i.index+1 }</td>
				<td>${user.username }</td>
				<td>${user.nickname }</td>
				<td>${user.gender==1?"男":"女" }</td>
				<td><fmt:formatDate value="${user.birthday }"
						pattern="yyyy-MM-dd" /></td>
				<td>${user.role==1?"管理员":"普通用户"}</td>
				<td><fmt:formatDate value="${user.created }"
						pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${user.updated }"
						pattern="yyyy-MM-dd" /></td>
				<td class="locked"><c:choose>
						<c:when test="${user.role==1}">
							<button type="button" class="btn btn-success" disabled="disabled">正常</button>
						</c:when>
						<c:otherwise>
							<c:if test="${user.locked==0 }">
								<button type="button" class="btn btn-warning" onclick="update(${user.id},this)">禁用</button>
							</c:if>
							<c:if test="${user.locked==1 }">
								<button type="button" class="btn btn-success" onclick="update(${user.id},this)">正常</button>
							</c:if>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="/WEB-INF/views/common/pages.jsp" />
<script type="text/javascript">
	function query() {
		$("#content-wrapper").load(
				"/admin/user/users?username=" + $('[name=username]').val());
	}
	function limit(pageNum) {
		if (pageNum == 0) {
			return;
		}
		$("#content-wrapper").load(
				"/admin/user/users?username=" + $('[name=username]').val()
						+ "&pageNum=" + pageNum);
	}
	$(function() {
		$('[name=username]').val('${user.username}')
	})
	function update(id,thiz) {
		$.ajax({
			url:"/admin/user/update",
			data:{id:id,locked:($(thiz).text()=='正常'?0:1)},
			success:function(flag){
				if(flag.code==0){
					if($(thiz).text()=='正常'){
						$(thiz).removeClass("btn-success");
						$(thiz).addClass("btn-warning");
					}else{
						$(thiz).removeClass("btn-warning");
						$(thiz).addClass("btn-success");
					}
					$(thiz).text($(thiz).text()=='正常'?"禁用":"正常");
				}else{
					alert(flag.message);
				}
			},error:function(msg){
				alert("系统错误!");
			}
		})
	}
</script>
