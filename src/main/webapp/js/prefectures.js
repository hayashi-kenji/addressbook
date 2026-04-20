document.addEventListener('DOMContentLoaded', () => {
  const prefList = [
    "北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県",
    "茨城県", "栃木県", "群馬県", "埼玉県", "千葉県", "東京都", "神奈川県",
    "新潟県", "富山県", "石川県", "福井県", "山梨県", "長野県", "岐阜県",
    "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県",
    "奈良県", "和歌山県", "鳥取県", "島根県", "岡山県", "広島県", "山口県",
    "徳島県", "香川県", "愛媛県", "高知県", "福岡県", "佐賀県", "長崎県",
    "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
  ];

  const select = document.getElementById('address1');
  // JSPに埋め込んだ現在のIDを取得
  const selectedId = select.getAttribute('data-selected');

  const defaultOption = document.createElement('option');
  defaultOption.text = '都道府県を選択';
  defaultOption.value = '';
  select.appendChild(defaultOption);

  prefList.forEach((pref, index) => {
    const value = String(index + 1); // 数値を文字列に変換
    const option = document.createElement('option');
    option.value = value;
    option.text = pref;

    // もし現在のIDと一致したら、その項目を選択状態にする
    if (value === selectedId) {
        option.selected = true;
    }

    select.appendChild(option);
  });
});