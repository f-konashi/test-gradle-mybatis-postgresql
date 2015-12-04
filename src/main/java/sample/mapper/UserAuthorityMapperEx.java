package sample.mapper;

import java.util.List;

import sample.model.UserAuthority;

/**
 * 「MyBatis Generator」で自動生成されるMapperクラスとは別に、独自に定義したメソッドを提供するクラスです。
 * 
 * @author f-konashi
 * 
 */
public interface UserAuthorityMapperEx {
	/**
	 * ログインIDからユーザー情報を検索する。
	 * 
	 * @param
	 * @return
	 */
	List<UserAuthority> selectByUserId(Integer userId);
}