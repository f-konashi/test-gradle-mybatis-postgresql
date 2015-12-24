package sample.form;

/**
 * 会員登録画面での入力値を格納するクラスです. このクラスを使用して、バリデーションも行います.
 * 
 * @author f-konashi
 *
 */
public class UpdateUserInfoForm extends UserInfoForm {
    boolean updateName;

    boolean updateGender;

    boolean updatePassword;
    
    // 以下、全てアクセッサーメソッド
    
    public boolean isUpdateName() {
        return updateName;
    }

    public void setUpdateName(boolean updateName) {
        this.updateName = updateName;
    }

    public boolean isUpdateGender() {
        return updateGender;
    }

    public void setUpdateGender(boolean updateGender) {
        this.updateGender = updateGender;
    }

    public boolean isUpdatePassword() {
        return updatePassword;
    }
}
