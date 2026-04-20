<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<%-- 外部スクリプトの読み込み --%>
<script src="js/prefectures.js" defer></script>
<script src="js/checkform.js" defer></script>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/style.css">
<title>住所編集 - 住所録</title>
</head>
<body>

	<h1>住所録編集</h1>
	
	<%-- サーブレットからの更新エラーメッセージ表示用 --%>
	<p style="color: red;">${errMsg}</p>
	
	<pre>
    <form action="EditServlet" method="post">
        <%-- 更新対象を特定するための隠しフィールド (重要) --%>
        <input type="hidden" name="id" value="${address.id}">

        <%-- 各入力項目：value属性に既存のデータを埋め込み --%>
        名前			<input type="text" id="name" name="name"
				value="${address.name}" placeholder="氏名を入力" autofocus />
        <%-- 
          電話番号：type="tel" と maxlength を追加 
          valueには既存の番号が表示される
        --%>
        電話番号		<input type="tel" id="phone" name="phone"
				value="${address.phone}" placeholder="09012345678 ハイフン不要"
				maxlength="11" />
        <%-- 
          住所1：data-selected 属性を元に js/prefectures.js が
          初期選択状態（selected）を制御する
        --%>
        住所１(都道府県)	<select id="address1" name="address1"
				data-selected="${address.address1}"></select>
				
        住所２(市区町村)	<input type="text" id="address2" name="address2"
				value="${address.address2}" placeholder="千代田区1-1-1" />
				
        住所３(その他)		<input type="text" id="address3" name="address3"
				value="${address.address3}" placeholder="建物名・マンション名" />
        <%-- 
          更新ボタン：checkForm() を呼び出してバリデーションを実行 
        --%>
        						<input type="button" value="更新を保存" onClick="checkForm()" />
    </form>
    
    <a href="AddressBookServlet">メニュー画面へ戻る</a>
    </pre>

</body>
</html>