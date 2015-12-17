package sample.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import sample.util.Message;

/**
 * 会員登録画面での入力値を格納するクラスです. 
 * このクラスを使用して、バリデーションも行います.
 * 
 * @author f-konashi
 *
 */
public class UserInfoForm {
	/**
	 * 入力フォームの「名前」テキストボックスに対応するフィールド.
	 * 1文字以上50文字以内で入力されているかバリデーションする.
	 */
	@NotEmpty(message = Message.ERROR_EMPTY)
	@Length(max = 50, message = Message.ERROR_MAX_LENGTH)
	private String name;

	/**
	 * 入力フォームの「性別」ラジオボタンに対応するフィールド.
	 * 選択されているかどうかバリデーションする.
	 */
	@NotEmpty(message = Message.ERROR_SELECT)
	private String gender;

	/**
	 * 入力フォームの「ログインID」テキストボックスに対応するフィールド.
	 * 5文字以上50文字以内で入力されているかバリデーションする.
	 */
	@NotEmpty(message = Message.ERROR_EMPTY)
	@Length(min = 5, max = 50, message = Message.ERROR_MINMAX_LENGTH)
	private String loginId;

	/**
	 * 入力フォームの「ログインID」テキストボックスに対応するフィールド.
	 * 5文字以上50文字以内で入力されているかバリデーションする.
	 */
	@NotEmpty(message = Message.ERROR_EMPTY)
	@Length(min = 5, max = 50, message = Message.ERROR_MINMAX_LENGTH)
	private String password;

	// 以下、全てアクセッサーメソッド
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId == null ? null : loginId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

}
