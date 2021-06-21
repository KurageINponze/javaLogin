<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインするだけ</title>
</head>
<body>
	<h1>ログイン</h1>
	<section>
			<p>
				IDとパスワードを入力し、ログインをクリックしてください
			</p>
			<form action="/javalogin/login" method="post">
				<table>
						<tr>
							<th>ID</th>
							<td>
								<input type="text" id="loginId" name="loginId" value="${loginId}" required>
							</td>
						</tr>
						<tr>
							<th>パスワード</th>
							<td>
								<input type="password" id="loginPw" name="loginPw" required>
							</td>
						</tr>
						<tr>
							<td>
								<button type="submit">ログイン</button>
							</td>
						</tr>
				</table>
			</form>
		</section>
</body>
</html>