<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<div class="container" style="width: 50%">
	<form id="linkForm">
		<div class="form-group row">
			<label for="text" class="col-sm-3 col-form-label col-form-label-sm">链接名称:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="text" name="text">
			</div>
		</div>
		<div class="form-group row">
			<label for="url" class="col-sm-3 col-form-label col-form-label-sm">url:</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="url" name="url">
			</div>
		</div>
		<button type="button" class="btn btn-info" onclick="tj()">添加</button>
		<button type="reset" class="btn btn-warning" >重置</button>
	</form>
</div>

<script type="text/javascript">
	//分页功能
	function tj() {
		$.ajax({
			url : "/admin/link/insertLink",
			data : $("#linkForm").serialize(),
			type : "post",
			success : function(flag) {
				if (flag.code == 0) {
					alert(flag.message);
					$("#content-wrapper").load("admin/link/links");
				} else {
					alert(flag.message);
				}
			},
			error : function() {
				alert("系统错误!");
			}
		})
	}
</script>
