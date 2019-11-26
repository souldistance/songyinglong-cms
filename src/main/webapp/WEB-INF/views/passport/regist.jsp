<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="/resource/js/validate_addMethod.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/bootstrap.js"></script>
<link href="/resource/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="/resource/css/cms.css" rel="stylesheet" type="text/css">
<link href="/resource/css/jquery/screen.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript">
	$(function () {
		$("#form1").validate({
			rules:{
				username:{
					required: true,
					isCN:true,
					rangelength: [2,6],
					remote: {
						url:"/passport/findUsername",
						type:"post",
						dataType:"json",
						data:{username:function() {
							return $("#username").val();
						}}
					}
				},
				password:{
					required: true,
					rangelength: [6,10]
				},
				repassword:{
					equalTo:"#password"
				}
			},
			messages: {
				username:{
					required: "用户名不能为空",
					rangelength: "长度必须为2-6",
					remote: "用户已存在"
				},
				password:{
					required: "密码不能为空",
					rangelength: "长度必须为6-10"
				},
				repassword:{
					equalTo:"两次密码不一致"
				}
			}
		})
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
		<div class="container" style="margin-top: 20px; margin-bottom: 35px;">
			<div class="row passport_bg">
				<div class="col-md-6" style="padding-top: 10px;">
					<div class="card" >
						<div class="card-body">
							<span style="color: red">${message }</span>
							<h5 class="card-title" align="center">用户注册</h5>
							<form id="form1" method="post" action="/passport/regist">
								<div class="form-group">
									<label for="username"> 用户名:</label> <input type="text"
										class="form-control" name="username" id="username">
								</div>
								<div class="form-group">
									<label for="password">密码:</label> <input id="password"
										class="form-control" type="password" name="password">
								</div>
								<div class="form-group">
									<label for="repassword">确认密码:</label> <input id="repassword"
										class="form-control" type="password" name="repassword">
								</div>
								<div class="form-group form-check">
									<label for="gender">性别:</label>
									<div class="form-check-inline">
										<input class="form-check-input" type="radio" name="gender"
											value="1" checked="checked">男 <input
											class="form-check-input" type="radio" name="gender" value="0">女
									</div>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-info">注册</button>
									<button type="reset" class="btn btn-warning">重置</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="passport_r">
						<h3>最新加入的用户：</h3>
						<p align="center">
							<img src="/resource/img/guest.jpg" alt="..."
								class="rounded-circle img-thumbnail" />
							&nbsp;&nbsp;&nbsp;&nbsp; <img src="/resource/img/guest1.jpg"
								alt="..." class="rounded-circle img-thumbnail" />
						</p>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>