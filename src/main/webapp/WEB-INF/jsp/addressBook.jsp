<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>メニュー - 住所録システム</title>
</head>
<body>
	<header>
		<h1>住所録管理システム</h1>
	</header>

	<main>
		<nav>
			<ul>
				<li><a href="RegisterServlet">新規登録</a> … 新しい住所を登録します。</li>
				<li><a href="TableListServlet">一覧表示</a> … 登録されている住所を閲覧します。</li>
				<li><a href="EditTableListServlet">内容修正・削除</a> <small>(管理者用パスワードが必要です)</small>
				</li>
				<li><a href="manual/user_manual.pdf" target="_blank">ユーザマニュアル</a> <small>(別タブで開きます)</small>
				</li>
			</ul>
		</nav>
	</main>

	<footer>
		<p>
			<small>&copy; 2026 Address Book Project</small>
		</p>
	</footer>
</body>
</html>