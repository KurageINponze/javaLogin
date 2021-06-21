<%--
エラー画面
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang= "ja">
	<head>
	<meta charset="UTF-8">
	<title>Error</title>
	</head>
	<body>
		<h1>Error</h1>
		<section>
			<h2>申し訳ございません。障害が発生しました</h2>
			<p>
				以下のメッセージご確認ください。
				<%=request.getAttribute("errorMsg") %>
			</p>
		</section>
		<p><a href="/javalogin/index.jsp">TOPへ戻る</a></p>
	</body>
</html>