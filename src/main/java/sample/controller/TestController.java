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
import sample.model.ItemInfoEx;
import sample.model.UserInfo;
import sample.security.MyUserDetails;
import sample.service.ItemInCartService;
import sample.service.UserInfoService;

/**
 * コントローラークラスです。
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
	private ItemInCartService itemInCartService;

	/**
	 * 会員情報を登録し、登録完了画面を出力します。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String insertOne(Model model, @RequestParam("loginId") String loginId, @RequestParam("name") String name,
			@RequestParam("password") String password, @RequestParam("gender") String gender) {

		// 入力されたデータを、エンティティークラスに格納する。
		UserInfo userInfo = new UserInfo();
		userInfo.setName(name);
		userInfo.setGender(gender);
		userInfo.setLoginId(loginId);
		userInfo.setEnabled(true);

		// パスワードは、エンティティクラスに登録する前にハッシュ化する。
		// userInfo.setPassword(password); ← ハッシュ化しない
		// userInfo.setPassword(new
		// ShaPasswordEncoder(256).encodePassword(password, null));
		userInfo.setPassword(new StandardPasswordEncoder().encode(password));

		// 入力されたデータを登録する。
		userInfoService.registUser(userInfo);

		model.addAttribute("userInfo", userInfo);
		return "regist";
	}

	/**
	 * 全会員情報を画面に出力します。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/serch")
	public String displayUserAll(Model model) {
		List<UserInfo> userInfoList = userInfoService.getUserAll();
		model.addAttribute("userInfoList", userInfoList);
		return "serch";
	}

	/**
	 * 個別会員情報を画面に出力します。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/mypage")
	public String displayUser(Model model, Principal principal) {
		// ログイン済の会員情報を取得する。
		Authentication auth = (Authentication) principal;
		MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();

		// 個別会員情報を取得する。
		UserInfo userInfo = userInfoService.getUserByLoginId(myUserDetails.getLoginId());

		// 個別会員情報を、modelに格納する。
		model.addAttribute("userInfo", userInfo);
		return "mypage";
	}

	/**
	 * 買い物かご画面を出力します。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/shoppingcart")
	public String DisplayCart(Model model, Principal principal) {
		// ログイン済の会員情報を取得する。
		Authentication auth = (Authentication) principal;
		MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();

		// 買い物かごに入っている商品一覧を取得し、modelに格納する。
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(myUserDetails.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);
		
		return "shoppingcart";
	}

	/**
	 * 「買い物かごに入れる」ボタンがクリックされた商品を、その会員の買い物かごに登録し、買い物かご画面を出力します。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/addItemInCart")
	public String addItemAndDisplayCart(Model model, Principal principal, @RequestParam("itemId") Integer itemId,
			@RequestParam("itemCount") Integer itemCount) {
		// ログイン済の会員情報を取得する。
		Authentication auth = (Authentication) principal;
		MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();

		// クリックされた商品を、買い物かごに追加する。
		ItemInCart itemInCart = new ItemInCart();
		itemInCart.setUserId(myUserDetails.getUserId());
		itemInCart.setItemId(itemId);
		itemInCart.setItemCount(itemCount);
		itemInCartService.setItemInCart(itemInCart);

		// 買い物かごに入っている商品一覧を取得し、modelに格納する。
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(myUserDetails.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);
		model.addAttribute("itemId", itemId);
		return "shoppingcart";
	}

	/**
	 * 買い物かご内から、 「削除」ボタンがクリックされた商品のみ削除する。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deleteItemInCart")
	public String deleteItemInCart(Model model, Principal principal,
			@RequestParam("cartId") Integer cartId) {
		// ログイン済の会員情報を取得する。
		Authentication auth = (Authentication) principal;
		MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();
		
		// 選択された商品を削除する。
		itemInCartService.deleteItemInCart(cartId);
		
		// 買い物かごに入っている商品一覧を取得し、modelに格納する。
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(myUserDetails.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);
		
		return "shoppingcart";
	}

	/**
	 * 買い物かご内から、 全商品を削除する。
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deleteItemAllInCart")
	public String deleteItemAllInCart(Model model, Principal principal) {
		// ログイン済の会員情報を取得する。
		Authentication auth = (Authentication) principal;
		MyUserDetails myUserDetails = (MyUserDetails) auth.getPrincipal();

		// 買い物かご内の全商品を削除する。
		itemInCartService.deleteItemAllInCart(myUserDetails.getUserId());

		// 買い物かごに入っている商品一覧を取得し、modelに格納する。
		List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(myUserDetails.getUserId());
		model.addAttribute("itemInCartList", itemInCartList);

		return "shoppingcart";
	}
}