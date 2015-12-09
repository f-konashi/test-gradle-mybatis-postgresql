package sample.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sample.model.ItemInCart;
import sample.model.ItemInfo;
import sample.model.ItemInfoEx;
import sample.model.UserInfo;
import sample.security.MyUserDetails;
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
public class TestController {
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ItemInfoService itemInfoService;

	@Autowired
	private ItemInCartService itemInCartService;

	/**
	 * トップページを表示します.
	 * 
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = {"/", "/index"})
	public String displayIndexHtml(Model model) {
		// 全商品一覧データを取得し、モデルに格納する.
		model.addAttribute("itemInfoList", getAllItemInfo());
		return "index";
	}
	/**
	 * 会員情報を登録し、登録完了画面を出力します.
	 * 
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return　ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model, 
			@RequestParam("loginId") String loginId, 
			@RequestParam("name") String name,
			@RequestParam("password") String password, 
			@RequestParam("gender") String gender) {

		// 入力されたデータを、エンティティークラスに格納する.
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setGender(gender);
		userInfo.setLoginId(loginId);
		userInfo.setEnabled(true);

		// パスワードは、エンティティクラスに登録する前にハッシュ化する.
		// userInfo.setPassword(password); ← ハッシュ化しない
		// userInfo.setPassword(new
		// ShaPasswordEncoder(256).encodePassword(password, null));
		userInfo.setPassword(new StandardPasswordEncoder().encode(password));

		// 入力されたデータを登録する.
		userInfoService.registUser(userInfo);

		model.addAttribute("userInfo", userInfo);
		return "regist";
	}

	/**
	 * 全会員情報を画面に出力します.
	 * 
	 * @param　Model htmlページに渡したいデータを格納する
	 * @return　ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/serch")
	public String displayUserAll(Model model) {
		// 全会員情報を取得し、modelに格納する.
		List<UserInfo> userInfoList = userInfoService.getUserAll();
		model.addAttribute("userInfoList", userInfoList);
		return "serch";
	}

	/**
	 * 個別会員情報を画面に出力します.
	 * 
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return　ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/mypage")
	public String displayUser(Model model, Principal principal) {
		// ログイン済の会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 個別会員情報を取得するし、modelに格納する.
		UserInfo userInfo = userInfoService.getUserByLoginId(loginUserData.getLoginId());
		model.addAttribute("userInfo", userInfo);
		return "mypage";
	}

	/**
	 * 買い物かご画面を出力します.
	 * 
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return　ブラウザに表示するhtmlページ
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
	 * @param　Model htmlページに渡したいデータを格納する
	 * @return　ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/addItemInCart")
	public String addItemAndDisplayCart(Model model, Principal principal, 
			@RequestParam("itemId") Integer itemId,
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
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return　ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/deleteItemInCart")
	public String deleteItemInCart(Model model, Principal principal, @RequestParam("cartId") Integer cartId) {
		// 選択された商品を削除する.
		itemInCartService.deleteItemInCart(cartId);
		
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かごに入っている商品一覧を取得し、modelに格納する.
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(loginUserData.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);

		return "shoppingcart";
	}

	/**
	 * 買い物かご内から、 全商品を削除します.
	 * 
	 * @param　Model htmlページに渡したいデータの格納先
	 * @return　ブラウザに表示するhtmlページ
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
	
//*********************************************************************
// privateメソッド一覧　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 
//*********************************************************************
	
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