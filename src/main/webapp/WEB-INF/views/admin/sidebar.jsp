<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.nav-link{
	font-size: 20px;
}
.list-group-item-info{
	color: #fff;
}
.nav{
	margin-top: 25px;
	margin-left: 10px;
}

</style>
<title>侧边栏</title>
	<ul class="nav nav-pills flex-column" id="left">
		<li class="nav-item"><a class="nav-link list-group-item-info"
			href="/admin">后台首页</a></li>
		<li class="nav-item"><a class="nav-link " href="#"
			data="/admin/article/articles">文章管理</a></li>
		<li class="nav-item"><a class="nav-link" href="#"
			data="/admin/user/users">用户管理</a></li>
		<li class="nav-item"><a class="nav-link" href="#"
			data="/admin/category/categories">分类管理</a></li>
		<li class="nav-item"><a class="nav-link" href="#">系统设置</a></li>
		<li class="nav-item"><a class="nav-link" href="#" data="/admin/link/links">友情链接</a></li>
		<li class="nav-item"><a class="nav-link" href="#">文章专题</a></li>
		</li>
	</ul>
	<hr class="d-sm-none">
<script type="text/javascript">
	$(function () {
		//为左侧菜单添加点击事件
		 $("#left .nav-link").click(function(){
			 var li =$("ul li a");
			//先移除所有的list-group-item-info样式
			li.removeClass("list-group-item-info");
			//为左侧菜单添加动态点击样式 
			$(this).addClass("list-group-item-info");
			 //获取点击URL
	      var url = $(this).attr("data");
			 //在当前页面的中间区域执行url
			 //alert(url)
			 $("#content-wrapper").load(url);
		 })
	})		
</script>