package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.mapper.UserInfoMapper;
import sample.model.UserInfo;

import java.util.List;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * テーブルデータを全件取得する
	 * 
	 * @return 全件リスト
	 */
	public List<UserInfo> getAll() {
		return userInfoMapper.getAll();
	}

	/**
	 * テーブルデータを１件挿入する
	 * 
	 * @return　挿入件数
	 */
	public int insert(UserInfo userInfo) {
		return userInfoMapper.insertOne(userInfo);
	}
	
    /**
     * IDで検索した結果を取得し返却する
     *
     * @return　UserInfo　検索結果
     */
    public UserInfo getUserByLoginId(String loginId) {
        return userInfoMapper.selectByLoginId(loginId);
    }
}