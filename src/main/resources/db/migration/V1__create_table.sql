-- ************************************
-- シーケンス作成
-- ************************************

-- UserID_seq: UserInfoテーブルのuser_id列のIDを管理するシーケンス
CREATE SEQUENCE UserID_seq start 10001;

-- ItemID_seq: ItemInfoテーブルのitem_id列のIDを管理するシーケンス
CREATE SEQUENCE ItemID_seq start 10001;

-- BuyingID_seq: BuyingInfoテーブルのbuying_id列のIDを管理するシーケンス
CREATE SEQUENCE BuyingID_seq start 10001;

-- ************************************
-- テーブル作成
-- ************************************

-- user_info: ユーザー情報を管理するテーブル
CREATE TABLE user_info
	(
		user_id		SERIAL		 PRIMARY KEY,		-- ユーザー管理番号(主キー)
		login_id	VARCHAR(255) NOT NULL UNIQUE,	-- ログインID
		password	VARCHAR(255) NOT NULL,			-- ログインPW
		name		VARCHAR(255) NOT NULL,			-- 氏名(画面表示名)
		gender		VARCHAR(1)	 NOT NULL,			-- 性別
		enabled		BOOLEAN							-- アカウント有効性(true = 有効)
	);
	
-- user_authority: ユーザー権限を管理するテーブル
CREATE TABLE user_authority 
	(
		user_id		INT			 PRIMARY KEY,				-- ユーザー管理番号(主キー/外部キー)
		authority	VARCHAR(255) NOT NULL,					-- 許可された権限名
		FOREIGN KEY (user_id) REFERENCES user_info(user_id) -- user_infoテーブルのuser_idを参照する
	);
	
-- item_id:　商品情報を管理するテーブル
CREATE TABLE item_info
	(
		item_id		SERIAL		 PRIMARY KEY,	-- 商品管理番号(主キー)
		name		VARCHAR(255) NOT NULL,		-- 商品名
		price		INT			 NOT NULL,		--　商品価格
		postage		INT			 NOT NULL,		-- 送料
		stock		INT			 NOT NULL		-- 在庫
	);

-- item_in_cart: 買い物かごに格納されている商品を管理するテーブル
CREATE TABLE item_in_cart
	(
		cart_id		SERIAL	PRIMARY KEY, -- 買い物かご管理番号
		user_id 	INT		NOT NULL,	 -- 会員管理番号
		item_id 	INT 	NOT NULL,	 --　商品管理番号
		item_count	INT 	NOT NULL,	 --　商品追加個数
		
		FOREIGN KEY (user_id) REFERENCES user_info(user_id), -- user_infoテーブルのuser_idを参照する
		FOREIGN KEY (item_id) REFERENCES item_info(item_id)  -- item_infoテーブルのuser_idを参照する
	);

-- buying_history: 商品の購入履歴を管理するテーブル
CREATE TABLE buying_history
    (
         buying_id   SERIAL       PRIMARY KEY, -- 購入管理番号(主キー)
         user_id     INT          NOT NULL,    -- ユーザー管理番号(外部キー)
         buying_date DATE         NOT NULL,    -- 購入日
         payment     VARCHAR(255) NOT NULL,    -- 決済方法
         delivery    VARCHAR(255) NOT NULL,    -- 配送方法
         postage     INT          NOT NULL,    -- 送料
         total_price INT          NOT NULL,    -- 合計金額
         
         FOREIGN KEY (user_id) REFERENCES user_info(user_id)  -- user_infoテーブルのuser_idを参照する
    );

-- ************************************
-- データ挿入
-- ************************************

-- user_infoテーブルに挿入:
-- passwordは、「pass」を暗号化したもの
INSERT INTO user_info (login_id, password, name, gender, enabled) VALUES ('user', 'eb5aac9753c02eb6ca6f3ab4004f78965bebb9acf857b1357b5e7db0c21f7356281fb62a957c64dc', '小梨文博', '男', true);

-- user_authorityテーブルに挿入:
INSERT INTO user_authority (user_id, authority) VALUES (1, 'user');

-- item_infoテーブルに挿入:
INSERT INTO item_info (name, price, postage, stock) VALUES ('商品A', 500, 100, 5);
INSERT INTO item_info (name, price, postage, stock) VALUES ('商品B', 300, 150, 5);
INSERT INTO item_info (name, price, postage, stock) VALUES ('商品C', 200, 100, 0);
INSERT INTO item_info (name, price, postage, stock) VALUES ('商品D', 100, 200, 5);
INSERT INTO item_info (name, price, postage, stock) VALUES ('商品E', 400, 100, 10);

-- item_in_cartテーブルに挿入:
INSERT INTO item_in_cart(user_id, item_id, item_count) VALUES(1, 1, 2);
INSERT INTO item_in_cart(user_id, item_id, item_count) VALUES(1, 3, 4);
INSERT INTO item_in_cart(user_id, item_id, item_count) VALUES(1, 4, 3);

-- buying_history テーブルに挿入:
INSERT INTO 
	buying_history 
	(user_id, buying_date, payment, delivery, postage, total_price) 
	VALUES 
	(1, '2015/12/02', '銀行振込', 'クロネコヤマト', 500, 2500);
