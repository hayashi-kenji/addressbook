<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>管理者認証 - 住所録システム</title>
</head>
<body>
	<header>
		<h1>管理者認証</h1>
	</header>

	<main>
		<section>
			<p>編集・削除機能を利用するには、管理者パスワードを入力してください。</p>

			<%-- 認証失敗時などにエラーメッセージを表示する場合 --%>
			<p style="color: red;">${errMsg}</p>

			<form action="EditTableListServlet" method="post">
				<div style="margin-bottom: 10px;">
					<label for="adminpass">管理者パスワード：</label>
					<%-- 
                      autocomplete="current-password": ブラウザのパスワードマネージャーを適切に機能させる
                      required: ブラウザ側での未入力チェック
                    --%>
					<input type="password" id="adminpass" name="adminpass"
						placeholder="パスワードを入力" autocomplete="current-password" required autofocus />
				</div>

				<div>
					<input type="submit" value="認証して一覧を表示" />
				</div>
			</form>
		</section>
	</main>

	<footer>
		<p>
			<a href="AddressBookServlet">メニュー画面へ戻る</a>
		</p>
	</footer>
</body>
</html>