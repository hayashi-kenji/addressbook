<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.AddData"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>データ管理 - 住所録システム</title>
</head>
<body>
	<header>
		<h1>住所録一覧（編集・削除）</h1>
	</header>

	<main>
		<%-- データが存在する場合のみテーブルを表示 --%>
		<%
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
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (AddData a : list) {
				%>
				<tr>
					<td><%=a.getId()%></td>
					<td><strong><%=a.getName()%></strong></td>
					<td><%=a.getPhone()%></td>
					<td><%=a.getAddress1()%></td>
					<td><%=a.getAddress2()%></td>
					<td><%=a.getAddress3()%></td>
					<td class="actions"><a href="EditServlet?id=<%=a.getId()%>"
						class="btn-edit">編集</a> <%-- 削除前の確認ダイアログ --%> <a
						href="DeleteServlet?id=<%=a.getId()%>" class="btn-delete"
						onclick="return confirm('ID:<%=a.getId()%> (<%=a.getName()%>様) のデータを完全に削除してもよろしいですか？');">
							削除 </a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<p>登録されているデータがありません。</p>
		<%
		}
		%>
	</main>

	<footer>
		<p>
			<a href="AddressBookServlet">メニュー画面へ戻る</a>
		</p>
	</footer>
</body>
</html>