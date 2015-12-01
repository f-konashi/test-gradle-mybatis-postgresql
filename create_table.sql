-- user_info: ユーザーの情報を管理するテーブル
CREATE TABLE user_info (
	(
		user_id serial primary key,		-- 主キー
		login_id varchar(256),			-- ログインID
		password varchar(256) not null,	-- ログインパスワード
		name varchar(255) not null,		-- 氏名(画面表示名)
		gender varchar(1) not null		-- 性別
		enabled boolean					-- アカウント有効性(true = 有効)
	);
	
-- user_authority: ユーザーの権限を管理するテーブル
CREATE TABLE user_authority 
	(
		user_id int PRIMARY KEY,		-- 権限を許可されたユーザのID
		authority VARCHAR(256) NOT NULL,-- 許可された権限名
		FOREIGN KEY (user_id) REFERENCES user_info(user_id) -- user_infoテーブルのuser_idを参照する
	);

-- LiquorID_seq: UserInfoテーブルのuser_id列のIDを管理するシーケンス
CREATE SEQUENCE LiquorID_seq start 10001;