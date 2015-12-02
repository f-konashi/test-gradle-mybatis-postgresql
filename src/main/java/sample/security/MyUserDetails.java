package sample.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import sample.model.UserInfo;

/**
 * 認証情報を設定するためのクラス
 * (認証済ユーザーの情報を格納したエンティティクラス)
 *
 * @author f-konashi
 *
 */
public class MyUserDetails extends User {
	private static final long serialVersionUID = 1L;
	private final Integer userId;
	private final String loginId;
	private final String name;

	public MyUserDetails(UserInfo userInfo, List<? extends GrantedAuthority> authorityList) {
		super(userInfo.getName(), userInfo.getPassword(), true, true, true, true, authorityList);
		this.userId = userInfo.getUserId();
		this.loginId = userInfo.getLoginId();
		this.name = userInfo.getName();
	}

	public Integer getUserId() {
		return userId;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public String getName() {
		return name;
	}
}
