<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<%-- 外部スクリプトの読み込み（deferによりDOM構築後に実行） --%>
<script src="js/prefectures.js" defer></script>
<script src="js/checkform.js" defer></script>

<meta charset="UTF-8">
<%-- レスポンシブ対応のためのmetaタグ（スマホ表示も考慮する場合） --%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="css/style.css">
<title>新規登録 - 住所録</title>
</head>
<body>
	<h1>新規登録</h1>

	<%-- サーブレットからのエラーメッセージ表示エリア --%>
	<p style="color: red;">${errMsg}</p>

	<pre>
    <form action="RegisterServlet" method="post">
        <%-- 名前入力 --%>
        名前			<input type="text" id="name" name="name"
				placeholder="氏名を入力" autofocus />
        <%-- 
          電話番号入力 
          - type="tel": モバイルで数字キーボードを優先表示
          - maxlength="11": 11桁を超える入力を物理的に制限
          - placeholder: 入力例を提示
        --%>
        電話番号		<input type="tel" id="phone" name="phone"
				placeholder="09012345678 ハイフン不要" maxlength="11" />
        <%-- 住所1：js/prefectures.js により option が動的に生成される --%>
        住所１(都道府県)	<select id="address1" name="address1"></select>
        <%-- 住所2・3 --%>
        住所２(市区町村)	<input type="text" id="address2" name="address2"
				placeholder="千代田区1-1-1" />
        
        住所３(その他)		<input type="text" id="address3" name="address3"
				placeholder="建物名・マンション名" />
        
        <%-- 
          送信ボタン 
          type="button" にすることで、checkForm()内のバリデーション通過時のみ 
          submit() が実行されるように制御している
        --%>
        						<input type="button" value="登録" onClick="checkForm()" />
    </form>
    
    <a href="AddressBookServlet">メニュー画面へ戻る</a>
    </pre>
</body>
</html>