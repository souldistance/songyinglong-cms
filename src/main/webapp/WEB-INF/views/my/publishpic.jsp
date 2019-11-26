<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8" />
<title>发布文章</title>
<script src="/resource/js/jquery-3.2.1.js"></script>
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<link rel="stylesheet" href="/resource/css/bootstrap.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="/resource/js/jquery.validate.js"></script>
<link href="/resource/css/jquery/screen.css" rel="stylesheet"
	type="text/css">
<style>
.card1 {
	float: left !important;
	width: 33.3% !important;
}
</style>
<script>
	$("#myform").validate({
		rules : {
			title : {
				required : true,
			},
			files:{
				required:true
			}
		},
		messages : {
			title : {
				required : "标题必须录入",
			},
			files:{
				required : "至少上传一个文件",
			}
		},
		submitHandler : function() {
			var formDate = new FormData($("#myform")[0]);
			$.ajax({
				url : "/my/publishpic",
				data : formDate,
				type : "post",
				processData : false,
				contentType : false,
				success : function(flag) {
					if (flag.code == 0) {
						alert(flag.message);
						location = "/my";
					} else {
						alert(flag.message);
					}
				},
				error : function() {
					alert("系统异常");
				}
			})

		}

	})
	function addCard() {
		var card = "<div class='card1' >"
				+ "<div class='card' style='width: 100%;'>"
				+ "<div class='card-header'>"
				+ "<label for='title'>标题图片</label>"
				+"<button type='button' class='ml-2 mb-1 close'  onclick='closeCard(this)'>"
				+"<span aria-hidden='true'>&times;</span></button>"
				+"<input type='file' class='form-control' name='files' id='file'>"
				+ "</div>"
				+ "<div class='card-body'>图片描述:"
				+ "<textarea rows='5' cols='' name='desc' style='width: 100%;'></textarea>"
				+ "</div></div></div>";
		$("#mdiv").append(card);
	}
	function closeCard(thiz) {
		$(thiz).parents(".card1").remove();
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form style="margin-top: 18px;" id="myform">
		<button type="button" onclick="addCard()" class="btn btn-info">添加card</button>
		<button type="submit"  class="btn btn-info">发布图片</button>
		<div class="form-group">
			<label for="title"><span
				style="font-size: 20px; font-style: italic;">标题:</span> </label> <input
				type="text" class="form-control" id="title" name="title"
				placeholder="请输入文章标题">
		</div>
		<div id="mdiv"
			style="border: 1px solid red; width: 100%; float: left;">
			<div class="card1">
				<div class="card" style="width: 100%;">
					<div class="card-header">
						<label for="title">标题图片</label>
						<input type="file" class="form-control" name="files" id="file">
					</div>
					<div class="card-body">
						图片描述:
						<textarea rows="5" cols="" name="desc" style="width: 100%;"></textarea>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>