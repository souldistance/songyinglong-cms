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
	<link rel="stylesheet"
	href="/resource/css/bootstrap.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="/resource/js/jquery.validate.js"></script>
<link href="/resource/css/jquery/screen.css" rel="stylesheet"
	type="text/css">
<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	
	
	//二级联动 当栏目变化时查询该栏目下的分类
	$("[name=channelId]").change(function() {
		var channelId=this.value;
		if(channelId==-1){
			$("[name=categoryId]").html("<option>--请选择--</option>");
			return;
		}
		$.get(
			"/my/selectCategoriesBychannelId",
			{channelId:channelId},
			function (obj) {
				var data=obj.data;
				var str="";
				for ( var i in data) {
					 str+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("[name=categoryId]").html(str);
			}
		); 
	})
	$("#myform").validate({
		rules : {
			title : {
				required : true,
			},
			channelId : {
				min : 1,
			},
			categoryId : {
				min : 1
			}
		},
		messages : {
			title : {
				required : "标题必须录入",
			},
			channelId : {
				min : "栏目必须选择",
			},
			categoryId : {
				min : "分类必须选择"
			}
		},
		submitHandler : function() {
			var formDate=new FormData($("#myform")[0]);
			formDate.set("content",editor1.html());
			$.ajax({
				url:"/my/publish",
				data: formDate,
				type: "post",
				processData: false,
				contentType: false,
				success: function (flag) {
					if(flag.code==0){
						alert(flag.message);
						location="/my";
					}else{
						alert(flag.message);
					}
				},error:function(){
					alert("系统异常");
				}
			})
				
		}
		
	})
	//点击重置按钮时 重置分类下拉框
	function myReset() {
		$("[name=categoryId]").html("<option>--请选择--</option>");
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form style="margin-top: 18px;" id="myform" onreset="myReset()">
		<div class="form-group">
			<label for="title"><span
				style="font-size: 20px; font-style: italic;">标题:</span> </label> <input
				type="text" class="form-control" id="title" name="title"
				placeholder="请输入文章标题">
		</div>
		<div class="form-group">
			<textarea name="content1" cols="100" rows="8"
				style="width: 700px; height: 200px; visibility: hidden; width: 100%"><%=htmlspecialchars(htmlData)%></textarea>
		</div>
		<div class="form-group form-group-inline">
			<span style="font-size: 18px; font-style: italic;">栏目:</span> <select
				name="channelId" class="form-control-sm">
				<option value="-1">--请选择--</option>
				<c:forEach items="${channels }" var="channel">
					<option value="${channel.id }">${channel.name }</option>
				</c:forEach>
			</select> <span style="font-size: 18px; font-style: italic;">分类:</span> <select
				name="categoryId" class="form-control-sm">
				<option>--请选择--</option>
			</select>
		</div>
		<div class="form-group form-group-inline">
			<label for="title"><span
				style="font-size: 20px; font-style: italic;">标题图片:</span> </label> <input
				type="file" class="form-control" id="titlePicture" name="file">
		</div>
		<br />
		<button type="submit" name="button" class="btn btn-info"
			>发布文章</button>
		<button type="reset" class="btn btn-warning">重置</button>
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