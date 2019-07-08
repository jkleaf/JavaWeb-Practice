<%@page import="dao.TreeImageDao"%>
<%@page import="bean.TreeImage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QueryTrees2</title>
</head>
<body>
	<%
		List<TreeImage> treeImages=TreeImageDao.getImages("20190617", "20190621");
		for(TreeImage treeImage:treeImages){
			out.print(treeImage.getName()+"<br>");
		}
	%>
</body>
</html>