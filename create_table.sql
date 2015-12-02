-- user_info: ユーザー情報を管理するテーブル
CREATE TABLE user_info (
	(
		user_id SERIAL PRIMARY KEY,		-- 主キー
		login_id VARCHAR(255), NOT NULL,-- ログインID
		password VARCHAR(255) NOT NULL,	-- ログインパスワード
		name VARCHAR(255) NOT NULL,		-- 氏名(画面表示名)
		gender VARCHAR(1) NOT NULL		-- 性別
		enabled BOOLEAN					-- アカウント有効性(true = 有効)
	);
	
-- user_authority: ユーザーの権限を管理するテーブル
CREATE TABLE user_authority 
	(
		user_id INT PRIMARY KEY,		-- 権限を許可されたユーザのID
		authority VARCHAR(255) NOT NULL,-- 許可された権限名
		FOREIGN KEY (user_id) REFERENCES user_info(user_id) -- user_infoテーブルのuser_idを参照する
	);
	
-- item_id:　商品情報を管理するテーブル
CREATE TABLE item_info
	(
		item_id SERIAL PRIMARY KEY,		-- 商品管理番号
		name varchar(255) not null,		-- 商品名
		price varchar(255) not null,	--　商品価格
		postage varchar(255) not null,	-- 送料
		stock varchar(255) not null		-- 在庫
	);

-- UserID_seq: UserInfoテーブルのuser_id列のIDを管理するシーケンス
CREATE SEQUENCE UserID_seq start 10001;

-- LiquorID_seq: ItemInfoテーブルのitem_id列のIDを管理するシーケンス
CREATE SEQUENCE ItemID_seq start 10001;