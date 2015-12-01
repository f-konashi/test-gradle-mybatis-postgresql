package sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.UserInfo;
import sample.service.UserInfoService;

@Controller
@EnableAutoConfiguration
public class TestController {
	@Autowired
	private UserInfoService userInfoService;
	
//	@Autowired
//	private TesttableService testtableService;
	
	/**
	 * ユーザー情報を登録する
	 * 
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model,
			@RequestParam("loginId") String loginId,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("gender") String gender) {
		// 入力されたデータを、エンティティークラスに格納する
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setGender(gender);
		userInfo.setLoginId(loginId);
		userInfo.setPassword(password);
		userInfo.setEnabled(true);

		userInfoService.insert(userInfo);
		return "input";
	}

	/**
	 * 全件データを画面に出力する。
	 * 
	 */
	@RequestMapping(value = "/serch")
	public String selectAll(Model model) {
		List<UserInfo> userInfoList = userInfoService.getAll();
		model.addAttribute("userInfoList", userInfoList);
		return "serch";

	}

}