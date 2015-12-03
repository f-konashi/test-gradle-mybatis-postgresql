package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import sample.mapper.UserAuthorityMapper;
import sample.model.UserAuthority;

/**
 * 
 * @author f-konashi
 */
@Service
public class UserAuthorityService {
	@Autowired
	private UserAuthorityMapper userAuthorityMapper;

	/**
	 * IDで検索した結果を取得して返却する
	 *
	 * @return List<UserAuthority> 検索結果
	 */
	public List<UserAuthority> getUserAuthorityByUserId(Integer userId) {
		return userAuthorityMapper.selectByUserId(userId);
	}
}
