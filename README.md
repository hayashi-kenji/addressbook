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



\## 使用技術

\- \*\*Backend\*\*: Java 17+, Jakarta Servlet 6.0, JSP

\- \*\*Frontend\*\*: HTML5, CSS3, JavaScript

\- \*\*Database\*\*: MySQL 8.0

\- \*\*Server\*\*: Apache Tomcat 10.1

\- \*\*Tool\*\*: Eclipse (Pleiades All-in-One)



\## 使用ライブラリ

\- \[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) (mysql-connector-j-x.x.x.jar)



## ドキュメント
プロジェクトのコード詳細については、Javadoc 形式でドキュメントを作成しています。

- **参照方法**: `/docs` ディレクトリ内の `index.html` をブラウザで開くことで、各クラスのメソッドや設計意図を確認できます。



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

