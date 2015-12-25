package sample.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sample.form.UpdateUserInfoForm;
import sample.form.UserInfoForm;
import sample.model.UserInfo;
import sample.security.MyUserDetails;
import sample.service.UserInfoService;

@Controller
@RequestMapping(value = "/mypage")
public class MyPageController extends CommonController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 会員登録画面で使用するフォームに対応したオブジェクトを初期化し、Modelに追加する (Thymeleafからアクセスさせるために必要).
     * 
     * @return 会員登録画面でのフォーム入力値を格納するオブジェクト
     */
    @ModelAttribute
    UserInfoForm setupUserInfoForm() {
        return new UserInfoForm();
    }
    
    /**
     * 会員登録画面で使用するフォームに対応したオブジェクトを初期化し、Modelに追加する (Thymeleafからアクセスさせるために必要).
     * 
     * @return 会員登録画面でのフォーム入力値を格納するオブジェクト
     */
    @ModelAttribute
    UpdateUserInfoForm setupUpdateUserInfoForm(Principal principal) {
        // ログインユーザーの会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);
        
        // 個別会員情報をデータベースから取得し、modelに格納する.
        UserInfo userInfo = userInfoService.getUserWithoutPassByLoginId(loginUserData.getLoginId());
        
        // 取得した会員情報を会員情報変更ページのフォーム値のデフォルト値として表示する為の設定
        UpdateUserInfoForm updateUserInfoForm = new UpdateUserInfoForm();
        updateUserInfoForm.setName(userInfo.getName());
        updateUserInfoForm.setGender(userInfo.getGender());
        
        return updateUserInfoForm;
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
        model.addAttribute("userInfo", userInfo);
        return "/mypage/info";
    }

    /**
     * 会員情報の変更画面を表示します。
     * 
     * @param model
     * @param principal
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
        
        return "/mypage/setting";
    }

    /**
     * 会員情報の変更画面を表示します。
     * 
     * @param model
     * @param principal
     * @param
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUserInfo(Model model, Principal principal, 
            @Valid UpdateUserInfoForm updateUserInfoForm, BindingResult result) {
        // ログインユーザーの会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);

        // フォーム入力値をチェックし、エラーがあれば会員変更ページにエラーを表示させる.
        if (result.hasErrors()) {
//            for (FieldError err : result.getFieldErrors()) {
//                log.debug("error code = [" + err.getCode() + "]");
//                System.out.println(err);
//            }
            // 個別会員情報をデータベースから取得し、modelに格納する.
            UserInfo userInfo = userInfoService.getUserWithoutPassByLoginId(loginUserData.getLoginId());
            model.addAttribute("userInfo", userInfo);
            return "mypage/setting";
        }
        
        // 会員情報を変更する.
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(loginUserData.getLoginId());
        userInfo.setName(updateUserInfoForm.getName());
        userInfo.setGender(updateUserInfoForm.getGender());
        userInfo.setPassword(new StandardPasswordEncoder().encode(updateUserInfoForm.getPassword()));
        System.out.println(userInfoService.updateUser(userInfo));
        
        // 個別会員情報をデータベースから取得し、modelに格納する.
        UserInfo updatedUserInfo = userInfoService.getUserWithoutPassByLoginId(loginUserData.getLoginId());
        model.addAttribute("userInfo", updatedUserInfo);
        
        model.addAttribute("update", true);
        return "mypage/info";
    }
}
