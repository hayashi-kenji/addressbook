<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>登録完了 - 住所録システム</title>
</head>
<body>
	<header>
		<h1>登録成功</h1>
	</header>

	<main>
		<section class="success-message">
			<%-- 登録された名前を表示して、どのアクションが成功したかを明示 --%>
			<p>
				<strong>${name}</strong> さんの住所情報を正常に登録しました。
			</p>
		</section>

		<nav>
			<ul>
				<li><a href="AddressBookServlet">メニュー画面へ戻る</a></li>
				<li><a href="TableListServlet">登録された一覧を見る</a></li>
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