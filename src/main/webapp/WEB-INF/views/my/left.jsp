<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.channel{
	font-size: 20px;
	font: bold;
}
.list-group-item-info{
	color: #fff;
}
.list-group-item:hover {
	cursor:pointer;
	color: #fff;
	background-color: #bee5eb;
}
.list-group-item:hover a{
	text-decoration: none;
}
.list-group-item-info a{
	color: #000;
}
</style>
<div class="avatar" style="padding-left: 15px;">
	<img alt="头像" src="/resource/img/guest.jpg" class="img-thumbnail">
</div>

<br />
<div>
	<div class="list-group"> 
		<ul class="list-group">
			<li 
				class="list-group-item  text-center list-group-item-info"><a
				class="channel" href="javascript:void(0)" data="/my/article/articles">我的文章
			</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/my/article/topublish"
				class="list-group-item">发布文章</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" class="list-group-item" >我的评论</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" class="list-group-item" ata="/my/article/uploadPicture">上传头像</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/my/user/update"
				class="list-group-item">个人设置</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/my/collects"
				class="list-group-item">个人收藏</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/blog/toBlog"
				class="list-group-item">写博客</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/my/articlepic/publishpic"
				class="list-group-item">发布图片</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/article/publishVote"
				class="list-group-item">发起投票</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(function () {
		$("#center").load("/my/article/articles");
		//为左侧菜单添加点击事件
		 $(".list-group-item").click(function(){
			 var li =$("ul li ");
			//先移除所有的list-group-item-info样式
			li.removeClass("list-group-item-info");
			//为左侧菜单添加动态点击样式 
			$(this).addClass("list-group-item-info");
			 //获取点击URL
	      var url = $(this).find("a").attr("data");
			 //在当前页面的中间区域执行url
			 //alert(url)
			 $("#center").load(url);
		 })
	})		
</script>