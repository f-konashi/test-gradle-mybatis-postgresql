package sample.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import sample.model.UserInfo;

/**
 * 認証情報を設定するためのクラス
 *
 * @author f-konashi
 *
 */
public class MyUserDetails extends User {
	private static final long serialVersionUID = 1L;
	private final String orgName;

	public MyUserDetails(UserInfo userInfo, List<? extends GrantedAuthority> authorityList) {
		// 「userInfo.getName」から「Integer.toString(userInfo.getUserId())に変更
		super(Integer.toString(userInfo.getUserId()), userInfo.getPassword(), true, true, true, true, authorityList);
		// 「userInfo.getOrganizationName()」から「userInfo.getName()」に変更
		this.orgName = userInfo.getName();
	}

	public String getOrgName() {
		return orgName;
	}
}
