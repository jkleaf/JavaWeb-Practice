<!doctype html>
<%@page import="dao.TreeImageDao"%>
<%@page import="bean.TreeImage"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="utf-8">
<title>已拍摄照片</title>

<link rel="stylesheet" type="text/css" href="res/css/style.css" />

</head>
<body>

	<div id="banner">
		<div id="carousel">
			<%
				List<TreeImage> treeImages = TreeImageDao.getImages("20190101", "20991231");
				for (TreeImage treeImage : treeImages) {
			%>
				<img src="upload/<%=treeImage.getName()%>" data-url="#">
			<%		
				}
			%>
		</div>
	</div>

	<script src="res/js/jquery-1.11.0.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="res/js/slider.js" type="text/javascript" charset="utf-8"></script>
	<script>
		$(function() {
			$('#carousel').carousel({
				curDisplay : 0, //默认索引
				autoPlay : false, //是否自动播放
				interval : 3000
			//间隔时间
			});
		});
	</script>
</body>
</html>
