package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import sample.form.UserInfoForm;
import sample.model.UserInfo;
import sample.service.UserInfoService;

/**
 * 会員登録に関しての処理を制御するコントローラーです.
 * 
 * @author f-konashi
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterUserController {
    /** Logbackを利用　*/ 
    private static final Logger logger = LoggerFactory.getLogger(RegisterUserController.class);
    
    /** サービスクラスをインジェクション　*/ 
    @Autowired
    private UserInfoService userInfoService;
    
    /**
     * 会員登録画面で使用するフォームに対応したオブジェクトを初期化し、Modelクラスに追加します.
     * 
     * @return　会員登録画面でのフォーム入力値を格納するオブジェクト
     */
    @ModelAttribute
    UserInfoForm setupUserInfoForm() {
        return new UserInfoForm();
    }
    
    /**
     * 会員情報入力画面をブラウザに表示します.
     * 
     * @param model Viewに渡す値を格納するオブジェクト
     * @return View名(このView名を元にViewResolverが実際のView処理を解決します)
     */
    @RequestMapping(value = "/input")
    public String displayInputPage(Model model) {
        return "/register/input";
    }
    
    /**
     * 会員登録完了画面をブラウザに表示します.
     * 
     * @param model　Viewに渡す値を格納するオブジェクト
     * @return View名(このView名を元にViewResolverが実際のView処理を解決します)
     */
    @RequestMapping(value = "/complete")
    public String displayCompletePage(Model model) {
        return "/register/complete";
    }
    
    /**
     * 会員情報を登録し、登録完了画面をブラウザに表示します.
     * 
     * @param model Viewに渡す値を格納するオブジェクト
     * @param redirectAttributes　Viewに渡す値を格納するオブジェクト(リダイレクト先でも使用可能)
     * @param userInfoForm フォーム入力値
     * @param validationResult バリデーション結果
     * @return View名(このView名を元にViewResolverが実際のView処理を解決します)
     * ※フォーム入力値のバリデーションエラーがあった場合は、元の入力画面を表示する.
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String insertOne(
            Model model,
            RedirectAttributes redirectAttributes,
            @Valid UserInfoForm userInfoForm,
            BindingResult validationResult) {
        
        // フォーム入力値をチェックし、エラーがあれば会員登録ページにエラーを表示させる.
        if (validationResult.hasErrors()) {
//          for (FieldError err : result.getFieldErrors()) {
//              log.debug("error code = [" + err.getCode() + "]");
//              System.out.println(err);
//          }
            logger.info("バリデーションエラー");
            return "/register/input";
        }
        
        // 入力されたログインIDが既に使用されている場合は、再入力を促す.
        if (userInfoService.isUser(userInfoForm.getLoginId())) {
            model.addAttribute("errorMessage", "入力されたログインIDは、既に使用されております。");
            return "/register/input";
        }

        // 入力されたデータを、エンティティークラスに格納する.
        // パスワードは、エンティティクラスに登録する前にハッシュ化する.
        UserInfo userInfo = new UserInfo();
        userInfo.setName(userInfoForm.getName());
        userInfo.setGender(userInfoForm.getGender());
        userInfo.setLoginId(userInfoForm.getLoginId());
        userInfo.setEnabled(true);
        userInfo.setPassword(new StandardPasswordEncoder().encode(userInfoForm.getPassword()));
//      userInfo.setPassword(new　ShaPasswordEncoder(256).encodePassword(password, null));

        // 入力されたデータ登録し、登録完了ページを表示させる.
        userInfoService.registerUser(userInfo);
        redirectAttributes.addFlashAttribute("userInfo", userInfo);
        return "redirect:/register/complete";
    }
}
