package sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.UserInfo;
import sample.security.MyUserDetails;
import sample.service.UserInfoService;

@Controller
@EnableAutoConfiguration
public class TestController {
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 会員情報を登録する。
	 * 
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model, @RequestParam("loginId") String loginId, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("gender") String gender) {
		// 入力されたデータを、エンティティークラスに格納する。
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setGender(gender);
		userInfo.setLoginId(loginId);
		userInfo.setPassword(password);
		userInfo.setEnabled(true);
		
		// 入力されたデータを登録する。
		userInfoService.registUser(userInfo);

		model.addAttribute("userInfo", userInfo);
		return "regist";
	}

	/**
	 * 全会員情報を画面に出力する。
	 * 
	 */
	@RequestMapping(value = "/serch")
	public String displayUserAll(Model model) {
		List<UserInfo> userInfoList = userInfoService.getUserAll();
		model.addAttribute("userInfoList", userInfoList);
		return "serch";
	}
	
	/**
	 * 個別会員情報を画面に出力する。
	 * 
	 */
	@RequestMapping(value = "/mypage")
	public String displayUser(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails) {
		// ログインユーザー名からユーザー情報を取得する
		UserInfo userInfo = userInfoService.getUserByLoginId(myUserDetails.getLoginId());
		model.addAttribute("userInfo", userInfo);
		return "mypage";
	}

}