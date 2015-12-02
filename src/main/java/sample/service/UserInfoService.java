package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.mapper.UserInfoMapper;
import sample.model.UserInfo;

import java.util.List;

/**
 * サービスクラス
 * @author f-konashi
 */
@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * ユーザーデータを全件取得する。
	 * 
	 * @return データベースに登録されている全ユーザー情報
	 */
	public List<UserInfo> getUserAll() {
		return userInfoMapper.getAll();
	}
	
    /**
     *　ログインIDで検索した結果を取得する。
     *
     *　@param String ログインID
     * @return　UserInfo　検索に該当したユーザー情報
     */
    public UserInfo getUserByLoginId(String loginId) {
        return userInfoMapper.selectByLoginId(loginId);
    }

	/**
	 * 入力されたユーザーデータをデータベースに１件挿入する。
	 * 
	 * @param String ユーザー情報
	 * @return　DBに挿入された件数
	 */
	public int registUser(UserInfo userInfo) {
		return userInfoMapper.insertOne(userInfo);
	}
}