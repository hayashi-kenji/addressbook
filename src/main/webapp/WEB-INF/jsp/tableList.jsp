<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.AddData"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>住所一覧 - 住所録システム</title>
</head>
<body>
	<header>
		<h1>登録住所一覧</h1>
	</header>

	<main>
		<%
		// サーブレットから渡されたリストを取得
		List<AddData> list = (List<AddData>) request.getAttribute("list");

		if (list != null && !list.isEmpty()) {
		%>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>名前</th>
					<th>電話番号</th>
					<th>都道府県</th>
					<th>市区町村・番地</th>
					<th>建物名・その他</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (AddData a : list) {
				%>
				<tr>
					<td><%=a.getId()%></td>
					<td><%=a.getName()%></td>
					<td><%=a.getPhone()%></td>
					<td><%=a.getAddress1()%></td>
					<td><%=a.getAddress2()%></td>
					<td><%=a.getAddress3()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<p class="info-message">現在、登録されているデータはありません。</p>
		<%
		}
		%>
	</main>

	<footer>
		<nav>
			<ul>
				<li><a href="AddressBookServlet">メニュー画面へ戻る</a></li>
				<li><a href="RegisterServlet">新規登録はこちら</a></li>
			</ul>
		</nav>
		<p>
			<small>&copy; 2026 Address Book Project</small>
		</p>
	</footer>
</body>
</html>