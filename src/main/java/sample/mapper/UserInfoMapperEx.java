package sample.mapper;

import java.util.List;

import sample.model.UserInfo;

/**
 * 「MyBatis Generator」で自動生成されるMapperクラスとは別に、独自に定義したメソッドを提供するクラスです。
 * 
 * @author f-konashi
 * 
 */
public interface UserInfoMapperEx {
	/**
	 * テーブルデータを全件取得する。
	 * 
	 * @param
	 * @return
	 */
	List<UserInfo> getAll();

	/**
	 * データを１件挿入する。
	 * 
	 * @param
	 * @return
	 */
	int insertOne(UserInfo record);

	/**
	 * ログインIDからユーザー情報を取得する。
	 * 
	 * @param
	 * @return
	 */
	UserInfo selectByLoginId(String loginId);
}