package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import sample.form.UserInfoForm;
import sample.form.OrderInfoForm;
import sample.model.BuyingHistory;
import sample.model.ItemInCart;
import sample.model.ItemInfo;
import sample.model.ItemInfoEx;
import sample.model.UserInfo;
import sample.security.MyUserDetails;
import sample.service.BuyingHistoryService;
import sample.service.ItemInCartService;
import sample.service.ItemInfoService;
import sample.service.UserInfoService;

/**
 * アプリケーション全体を制御するクラス(コントローラークラス)です.
 * 
 * @author f-konashi
 *
 */
@Controller
@EnableAutoConfiguration
@SessionAttributes(value = { "itemInfoInCartList", "postage", "total" })
public class TestController {
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ItemInfoService itemInfoService;

	@Autowired
	private ItemInCartService itemInCartService;

	@Autowired
	private BuyingHistoryService buyingHistoryService;

	/**
	 * 会員登録画面で使用するフォームに対応したオブジェクトを初期化し、Modelに追加する
	 * (Thymeleafからアクセスさせるために必要).
	 * 
	 * @return
	 */
	@ModelAttribute
	UserInfoForm setupUserInfoForm() {
		return new UserInfoForm();
	}

	/**
	 * 画面で使うフォームに対応したオブジェクトを初期化し、Modelに追加する
	 * (Thymeleafからアクセスさせるために必要).
	 * 
	 * @return
	 */
	@ModelAttribute
	OrderInfoForm setupOrderInfoForm() {
		return new OrderInfoForm();
	}
	
	// *********************************************************************
	// RequestMappingメソッド一覧
	// *********************************************************************

	@RequestMapping("/input")
	public String displayInputPage(Model model) {
		return "input";
	}

	/**
	 * トップページを表示します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = { "/", "/index" })
	public String displayIndexPage(Model model) {
		// 全商品一覧データを取得し、モデルに格納する.
		model.addAttribute("itemInfoList", getAllItemInfo());
		return "index";
	}

	/**
	 * 会員情報を登録し、登録完了画面を出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model, @Valid UserInfoForm inputUserInfo, BindingResult result) {

		// フォーム入力値をチェックし、エラーがあれば会員登録ページにエラーを表示させる.
		if (result.hasErrors()) {
			for (FieldError err : result.getFieldErrors()) {
				// log.debug("error code = [" + err.getCode() + "]");
				System.out.println(err);
			}
			return "input";
		}

		// 入力されたデータを、エンティティークラスに格納する.
		UserInfo userInfo = new UserInfo();
		userInfo.setName(inputUserInfo.getName());
		userInfo.setGender(inputUserInfo.getGender());
		userInfo.setLoginId(inputUserInfo.getLoginId());
		userInfo.setEnabled(true);

		// パスワードは、エンティティクラスに登録する前にハッシュ化する.
		// userInfo.setPassword(password); ← ハッシュ化しない
		// userInfo.setPassword(new
		// ShaPasswordEncoder(256).encodePassword(password, null));
		userInfo.setPassword(new StandardPasswordEncoder().encode(inputUserInfo.getPassword()));

		// 入力されたデータを登録する.
		userInfoService.registUser(userInfo);

		model.addAttribute("userInfo", userInfo);
		return "regist";
	}

	/**
	 * 全会員情報を画面に出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータを格納する
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/serch")
	public String displayUserAll(Model model) {
		// 全会員情報を取得し、modelに格納する.
		List<UserInfo> userInfoList = userInfoService.getUserAll();
		model.addAttribute("userInfoList", userInfoList);
		return "serch";
	}

	/**
	 * 個別会員情報をデータベースから呼び出し、画面出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/mypage")
	public String displayUserInfo(Model model, Principal principal) {
		// ログイン済の会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 個別会員情報をデータベースから取得し、modelに格納する.
		UserInfo userInfo = userInfoService.getUserByLoginId(loginUserData.getLoginId());
		model.addAttribute("userInfo", userInfo);
		return "mypage";
	}

	/**
	 * 購入履歴情報をデータベースから呼び出し、画面出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping("/buyinghistory")
	public String displayBuyingHistory(Model model, Principal principal) {
		// ログイン済の会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 個別購入履歴情報をデータベースから取得し、modelに格納する.
		List<BuyingHistory> buyingHistoryList = buyingHistoryService.selectAllBuyingHistory(loginUserData.getUserId());
		model.addAttribute("buyingHistoryList", buyingHistoryList);

		return "buyinghistory";
	}

	/**
	 * 買い物かご画面を出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/shoppingcart")
	public String DisplayCart(Model model, Principal principal) {
		// ログイン済の会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かごに入っている商品一覧を取得し、modelに格納する.
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(loginUserData.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);
		return "shoppingcart";
	}

	/**
	 * 「買い物かごに入れる」ボタンがクリックされた商品を、その会員の買い物かごに登録し、買い物かご画面を出力します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータを格納する
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/addItemInCart")
	public String addItemAndDisplayCart(Model model, Principal principal, @RequestParam("itemId") Integer itemId,
			@RequestParam("itemCount") Integer itemCount) {
		// ログイン済の会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// クリックされた商品を、買い物かごに追加する.
		ItemInCart itemInCart = new ItemInCart();
		itemInCart.setUserId(loginUserData.getUserId());
		itemInCart.setItemId(itemId);
		itemInCart.setItemCount(itemCount);
		itemInCartService.setItemInCart(itemInCart);

		// 買い物かごに入っている商品一覧を取得し、modelに格納する.
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(loginUserData.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);
		model.addAttribute("itemId", itemId);
		return "shoppingcart";
	}

	/**
	 * 買い物かご内から、 「削除」ボタンがクリックされた商品のみ削除する.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/deleteItemInCart")
	public String deleteItemInCart(Model model, Principal principal, @RequestParam("cartId") Integer cartId) {
		// 選択された商品を削除する.
		itemInCartService.deleteItemInCart(cartId);

		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かごに入っている商品一覧を取得し、modelに格納する.
		List<ItemInfoEx> ItemInfoInCartList = getAllItemInfoInCart(loginUserData.getUserId());
		model.addAttribute("itemInCartList", ItemInfoInCartList);

		return "shoppingcart";
	}

	/**
	 * 買い物かご内から、 全商品を削除します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/deleteItemAllInCart")
	public String deleteItemAllInCart(Model model, Principal principal) {
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かご内の全商品を削除する.
		itemInCartService.deleteItemAllInCart(loginUserData.getUserId());

		// 買い物かごに入っている商品一覧を取得し、modelに格納する.
		List<ItemInfoEx> ItemInfoInCartList = getAllItemInfoInCart(loginUserData.getUserId());
		model.addAttribute("itemInCartList", ItemInfoInCartList);

		return "shoppingcart";
	}

	/**
	 * 決済画面を表示します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/orderform")
	public String displayOrderformPage(Model model, Principal principal) {
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かごに入っている商品一覧を取得し、小計と合計を算出する.
		List<ItemInfoEx> itemInfoInCartList = getAllItemInfoInCart(loginUserData.getUserId());

		// ここから※算出処理は、後でビジネスクラスに移行予定
		int total = 0;
		int postage = 0;
		final double TAX_RATE = 1.08;
		for (ItemInfoEx item : itemInfoInCartList) {
			// 商品小計(税込)を算出する.
			int subtotal = (int) (item.getPrice() * item.getItemCount() * TAX_RATE);
			item.setSubtotal(subtotal);

			// 商品合計(税込)を算出する.
			total += subtotal;

			// 送料を算出する(商品内で送料の最大値を適用する).
			if (item.getPostage() > postage) {
				postage = item.getPostage();
			}
		}
		// 最終合計(商品合計(税込) + 送料)を算出する.
		total += postage;
		// ここまで※算出処理は、後でビジネスクラスに移行予定

		// modelに格納する.
		model.addAttribute("itemInfoInCartList", itemInfoInCartList);
		model.addAttribute("postage", postage);
		model.addAttribute("total", total);

		return "orderform";
	}

	/**
	 * 決済完了画面を表示します.
	 * 
	 * @param Model
	 *            htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/orderComplete")
	public String displayOrderCompletePage(Model model, Principal principal, SessionStatus sessionStatus,
			@ModelAttribute("postage") Integer postage, @ModelAttribute("total") Integer total,
			@Valid OrderInfoForm orderInfoForm, BindingResult result)
					throws HttpSessionRequiredException {
		
		// フォーム入力値をチェックし、エラーがあれば会員登録ページにエラーを表示させる.
		if (result.hasErrors()) {
			for (FieldError err : result.getFieldErrors()) {
				// log.debug("error code = [" + err.getCode() + "]");
				System.out.println(err);
			}
			return "orderform";
		}
		
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 決済情報をデータベースに登録する.
		BuyingHistory buyingHistory = new BuyingHistory();
		buyingHistory.setUserId(loginUserData.getUserId());
		buyingHistory.setPayment(orderInfoForm.getPayment());
		buyingHistory.setDelivery(orderInfoForm.getDelivery());
		buyingHistory.setBuyingDate(new Date());
		buyingHistory.setPostage(postage);
		buyingHistory.setTotalPrice(total);
		buyingHistoryService.insertOrderInfo(buyingHistory);

		// 買い物かご内の全商品を削除する.
		itemInCartService.deleteItemAllInCart(loginUserData.getUserId());

		// セッションを破棄する.
		sessionStatus.setComplete();

		// modelに格納する.
		model.addAttribute("buyingHistory", buyingHistory);

		return "ordercomplete";
	}

	// *********************************************************************
	// privateメソッド一覧
	// *********************************************************************

	/**
	 * データベースに登録されている全商品データを取得します.
	 * 
	 * @param なし
	 * @return データベースに登録されている全商品データ
	 */
	private List<ItemInfo> getAllItemInfo() {
		return itemInfoService.getItemAll();
	}

	/**
	 * 特定ユーザーの買い物かごに入っている全商品データを取得します.
	 * 
	 * @param ユーザーID
	 * @return データベースに登録されている全商品データ
	 */
	private List<ItemInfoEx> getAllItemInfoInCart(Integer userId) {
		return itemInCartService.getItemInCart(userId);
	}

	/**
	 * ログインユーザーの会員情報を取得します.
	 * 
	 * @param principal
	 * @return 会員情報
	 */
	private MyUserDetails getLoginUserData(Principal principal) {
		Authentication auth = (Authentication) principal;
		return (MyUserDetails) auth.getPrincipal();
	}
}