<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="chikuwa.minami.login.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
</head>
<body>
	<h1>ようこそ${name}さん</h1>
		<p><a href="/javalogin/logout">ログアウト</a></p>
	<h2>一定時間経過後こちらをクリックするとエラーとなります</h2>
		<p><a href="/javalogin/timeError">こちら</a></p>
</body>
</html>