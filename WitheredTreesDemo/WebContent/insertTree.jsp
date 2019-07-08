<%@page import="dao.TreeImageDao"%>
<%@page import="bean.TreeImage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertTree</title>
</head>
<body>
	<%
		TreeImage treeImage = new TreeImage("20190621194516", "PNG_20190613_194516_treeImg.png", 119.23586,
				26.086978, "666", "20190613");
		int num = TreeImageDao.addTreeImage(treeImage);
		out.print(num);
	%>
</body>
</html>