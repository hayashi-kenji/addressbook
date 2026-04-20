function checkForm() {
    var name = document.getElementById('name').value.trim();
    var phone = document.getElementById('phone').value.trim();
    var address1 = document.getElementById('address1').value;
    var address2 = document.getElementById('address2').value.trim();
    var errmsg = "";

    // 1. 名前のチェック
    if (name === "") {
        errmsg += "名前を入力してください\n";
    } else if (!isNaN(name)) {
        // 全体が数値として解釈できる場合はエラー
        errmsg += "名前を正しく入力してください（数値のみは不可）\n";
    }

    // 2. 電話番号のチェック（正規表現を使用）
    // ^0 : 0で始まる
    // \d{9,10} : その後に数字が9桁または10桁続く（合計10〜11桁）
    // $ : 終端
    var phonePattern = /^0\d{9,10}$/;
    if (phone === "") {
        errmsg += "電話番号を入力してください\n";
    } else if (!phonePattern.test(phone)) {
        errmsg += "電話番号は0から始まる10桁または11桁の半角数字で入力してください（ハイフン不要）\n";
    }

    // 3. 都道府県のチェック
    if (address1 === "") {
        errmsg += "都道府県を選択してください\n";
    }

    // 4. 市区町村のチェック
    if (address2 === "") {
        errmsg += "市区町村を入力してください\n";
    }

    // エラー表示または送信
    if (errmsg !== "") {
        alert(errmsg);
    } else {
        document.querySelector('form').submit();
    }
}