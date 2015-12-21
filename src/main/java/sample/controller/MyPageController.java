package sample.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.form.UserInfoForm;
import sample.model.UserInfo;
import sample.security.MyUserDetails;
import sample.service.UserInfoService;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController extends CommonController {
    
    @Autowired
    private UserInfoService userInfoService;
    
    // *********************************************************************
    // ModelAttributeメソッド一覧
    // *********************************************************************
    /**
     * 会員登録画面で使用するフォームに対応したオブジェクトを初期化し、Modelに追加する
     * (Thymeleafからアクセスさせるために必要).
     * 
     * @return　会員登録画面でのフォーム入力値を格納するオブジェクト
     */
    @ModelAttribute
    UserInfoForm setupUserInfoForm() {
        return new UserInfoForm();
    }
    
    /**
     * 個別会員情報をデータベースから呼び出し、画面出力します.
     * 
     * @param model
     * @param principal
     * @return ブラウザに表示するページ
     */
    @RequestMapping("/info")
    public String displayUserInfo(Model model, Principal principal) {
        // ログイン済の会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);

        // 個別会員情報をデータベースから取得し、modelに格納する.
        UserInfo userInfo = userInfoService.getUserWithoutPassByLoginId(loginUserData.getLoginId());
        System.out.println(userInfo.getUserId());
        model.addAttribute("userInfo", userInfo);
        return "mypage/info";
    }
    
    /**
     * 
     * 
     * @param 
     * @return 
     */
    @RequestMapping("/setting")
    public String displaySetting(Model model, Principal principal) {
        // ログインユーザーの会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);
        
        // 個別会員情報をデータベースから取得し、modelに格納する.
        UserInfo userInfo = userInfoService.getUserWithoutPassByLoginId(loginUserData.getLoginId());
        model.addAttribute("userInfo", userInfo);
        
        return "mypage/setting";
    }
}
