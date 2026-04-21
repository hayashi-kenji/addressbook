\# Address Book Application



Java Servlet と JSP を使用した、Webベースの住所録管理システムです。

MVCアーキテクチャに基づき、データの参照、登録、編集、削除（CRUD機能）を実装しています。



\## 概要

このプロジェクトは、基本的なWebアプリケーション開発の流れ（フロントエンド、バックエンド、データベース連携）を学習・実践するために作成しました。



\## 機能

\- \*\*住所一覧表示\*\*: 登録されている住所情報を一覧で確認できます。

\- \*\*新規登録\*\*: 新しい連絡先をデータベースに登録します（JavaScriptによるバリデーション付き）。

\- \*\*編集・削除機能\*\*: 管理者認証（パスワード入力）を経て、既存のデータを編集または削除できます。

\- \*\*DB連携\*\*: MySQLを使用し、都道府県テーブルと結合してデータを取得します。


> [!IMPORTANT]
> **セキュリティに関する注意点**
> 現在、パスワードのハッシュ化は未実装です。学習用のデモアプリとして構成しているため、機密性の高いパスワードは入力しないでください。今後、Spring Security等の導入に合わせ、ハッシュ化対応を予定しています。


\## 使用技術

\- \*\*Backend\*\*: Java 17+, Jakarta Servlet 6.0, JSP

\- \*\*Frontend\*\*: HTML5, CSS3, JavaScript

\- \*\*Database\*\*: MySQL 8.0

\- \*\*Server\*\*: Apache Tomcat 10.1

\- \*\*Tool\*\*: Eclipse (Pleiades All-in-One)



\## 使用ライブラリ

\- \[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (mysql-connector-j-x.x.x.jar)



## 📂 ドキュメント構成

プロジェクトの設計からテストまでの全工程をドキュメント化しています。

### 1. [設計・仕様書](./docs/spec)
システムの核となる設計資料をまとめています。
- **要求仕様書**: システムの目的、機能一覧、非機能要件の定義。
- **システム構成図**: MVCアーキテクチャおよびシステム構成図。
- **ワイヤーフレーム**: 各画面のレイアウトおよびUI設計。

### 2. [設計・仕様書](./docs/spec)
品質を担保するためのテスト工程を公開しています。
- **テスト仕様書**: 正常系・異常系（境界値等）を含む全テストケース一覧。
- **テスト結果報告書**: 実際の動作エビデンス（スクリーンショット）を含むテスト実施記録。

### 3. [API仕様書 (JavaDoc)](https://hayashi-kenji.github.io/addressbook/api/index.html)
- **JavaDoc**: 各クラスおよびメソッドの役割、引数、戻り値を網羅したAPIドキュメント。
- スマホでの閲覧に対応しています。



\## データベース構成

実行には以下のテーブルが必要です。



```sql

\-- 都道府県マスタ

CREATE TABLE prefectures (

&#x20;   address1 VARCHAR(2) PRIMARY KEY,

&#x20;   prefecture VARCHAR(10)

);



\-- 住所データ

CREATE TABLE addresses (

&#x20;   id INT AUTO\_INCREMENT PRIMARY KEY,

&#x20;   name VARCHAR(50) NOT NULL,

&#x20;   phone VARCHAR(20),

&#x20;   address1 VARCHAR(2), -- 都道府県コード

&#x20;   address2 VARCHAR(100),

&#x20;   address3 VARCHAR(100),

&#x20;   FOREIGN KEY (address1) REFERENCES prefectures(address1)

);

