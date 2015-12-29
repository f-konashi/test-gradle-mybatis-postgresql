package sample.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

import sample.util.Message;

/**
 * 会員登録画面での入力値を格納するクラスです. このクラスを使用して、バリデーションも行います.
 * 
 * @author f-konashi
 *
 */
public class UserInfoForm {
    /**
     * 入力フォームの「名前」テキストボックスに対応するフィールド. 1文字以上50文字以内で入力されているかバリデーションする.
     */
    @NotEmpty(message = Message.ERROR_EMPTY)
    @Length(max = 50, message = Message.ERROR_MAX_LENGTH)
    private String name;

    /**
     * 入力フォームの「性別」ラジオボタンに対応するフィールド. 選択されているかどうかバリデーションする.
     */
    @NotEmpty(message = Message.ERROR_SELECT)
    private String gender = "男";

    /**
     * 入力フォームの「ログインID」テキストボックスに対応するフィールド. 5文字以上50文字以内で入力されているかバリデーションする.
     */
    @NotEmpty(message = Message.ERROR_EMPTY)
    @Length(min = 5, max = 50, message = Message.ERROR_MINMAX_LENGTH)
    private String loginId;

    /**
     * 入力フォームの「パスワード」テキストボックスに対応するフィールド. 5文字以上50文字以内で入力されているかバリデーションする.
     */
    @NotEmpty(message = Message.ERROR_EMPTY)
    @Length(min = 5, max = 50, message = Message.ERROR_MINMAX_LENGTH)
    private String password;

    /**
     * 入力フォームの「パスワード(確認用)」テキストボックスに対応するフィールド. 5文字以上50文字以内で入力されているかバリデーションする.
     */
    @NotEmpty(message = Message.ERROR_EMPTY)
    @Length(min = 5, max = 50, message = Message.ERROR_MINMAX_LENGTH)
    private String confirmPassword;
    
    @AssertTrue(message = Message.NOT_MATCH)
    public boolean isValidPassword() {
        System.out.println("bari");
        //  「パスワード」の項目が未入力の場合、バリデーションしない.
        if (password == null || password.length() == 0) {
            return true;
        }
        //  「パスワード(確認用)」の項目が未入力の場合、バリデーションしない.
        if (confirmPassword == null || confirmPassword.length() == 0) {
            return true;
        }
        // コメント欄に記入がされているか確認する.
        if (confirmPassword.equals(password)) {
            return true;
        }
        return false;
    }

    // 以下、全てアクセッサーメソッド

    public String getName() {
        return name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
