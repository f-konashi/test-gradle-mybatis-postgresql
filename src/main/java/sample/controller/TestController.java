package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import sample.form.OrderInfoForm;
import sample.model.BuyingHistory;
import sample.model.UserInfo;
import sample.model.ItemInfo;
import sample.model.ItemInfoEx;
import sample.model.ItemInCart;
import sample.service.CommonService;
import sample.service.UserInfoService;
import sample.service.ItemInfoService;
import sample.service.ItemInCartService;
import sample.service.BuyingHistoryService;
import sample.security.MyUserDetails;

/**
 * アプリケーション全体を制御するクラス(コントローラークラス)です.
 * 
 * @author f-konashi
 *
 */
@Controller
@EnableAutoConfiguration
@SessionAttributes(value = { "itemInfoInCartList", "postage", "total" })
public class TestController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    private CommonService commonService;
    
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private ItemInfoService itemInfoService;

	@Autowired
	private ItemInCartService itemInCartService;

	@Autowired
	private BuyingHistoryService buyingHistoryService;

	// *********************************************************************
    // ModelAttributeメソッド一覧
    // *********************************************************************
	
    /**
     * 決済ページ(orderform.html)で、入力フォームに対応したオブジェクトを初期化し、Modelに追加する.
     * (Thymeleafからアクセスさせるために必要).
     * 
     * @return　決済ページでの入力フォーム値を格納するオブジェクト
     */
    @ModelAttribute
    OrderInfoForm setupUserOrderInfoForm() {
        return new OrderInfoForm();
    }
	
	// *********************************************************************
    // displayメソッド一覧
    // *********************************************************************
	
	/**
	 * 商品一覧をデータベースから取得し、トップページをブラウザに表示します.
	 * 
	 * @param model
	 * @return ブラウザに表示するページ(index.html)
	 */
	@RequestMapping(value = { "/", "/index" })
	public String displayIndexPage(Model model) {
		// 全商品一覧データを取得し、モデルに格納する.
		model.addAttribute("itemInfoList", getAllItemInfo());
		return "index";
	}
    
    /**
     * 買い物かご画面をブラウザに表示します.
     * 
     * @param model
     * @param principal
     * @return ブラウザに表示するページ
     */
    @RequestMapping(value = "/shoppingcart")
    public String displayShoppincart(Model model, Principal principal) {
        // ログイン済の会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);

        // 買い物かごに入っている商品一覧を取得し、viewに渡す.
        List<ItemInfoEx> itemInCartList = itemInCartService.getItemInCart(loginUserData.getUserId());
        model.addAttribute("itemInCartList", itemInCartList);
        return "shoppingcart";
    }
    
    /**
     * 注文完了画面をブラウザに表示します.
     * 
     * @param model
     * @return ブラウザに表示するページ(ordercomplete.html)
     */
    @RequestMapping(value = "/ordercomplete")
    public String displayOrdercomplete(Model model) {
        return "ordercomplete";
    }
    
    // *********************************************************************
    // redirectメソッド一覧
    // *********************************************************************

	/**
	 * 「買い物かごに入れる」ボタンがクリックされた商品を、その会員の買い物かごに登録し、買い物かご画面にリダイレクトします.
	 * 
	 * ブラウザ側でページ更新された場合に、何度も商品が買い物かごに追加されてしまうのを回避する為に、
	 * 別途作成した買い物かご表示メソッドにリダイレクトしています.
	 * 
     * @param model
     * @param principal
	 * @return リダイレクト先
	 */
	@RequestMapping(value = "/addItemInCart")
	public String addItemInCart(Model model, Principal principal, 
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

		return "redirect:/shoppingcart";
	}

	/**
	 * 買い物かご内から、 「削除」ボタンがクリックされた商品のみ削除し、、買い物かご画面にリダイレクトします.
	 * 
     * @param model
     * @param principal
     * @param cartId
	 * @return ブラウザに表示するページ
	 */
	@RequestMapping(value = "/deleteItemInCart")
	public String deleteItemInCart(Model model, Principal principal, @RequestParam("cartId") Integer cartId) {
		// 選択された商品を削除する.
		itemInCartService.deleteItemInCart(cartId);

        return "redirect:/shoppingcart";
	}

	/**
	 * 買い物かご内から、 全商品を削除し、買い物かご画面にリダイレクトします.
	 * 
     * @param model
     * @param principal
	 * @return ブラウザに表示するhtmlページ
	 */
	@RequestMapping(value = "/deleteItemAllInCart")
	public String deleteItemAllInCart(Model model, Principal principal) {
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);

		// 買い物かご内の全商品を削除する.
		itemInCartService.deleteItemAllInCart(loginUserData.getUserId());

        return "redirect:/shoppingcart";
	}

	/**
	 * 決済画面を表示します.
	 * 
	 * @param model
	 * @return ブラウザに表示するページ
	 */
	@RequestMapping(value = "/orderform")
	public String displayOrderformPage(Model model, Principal principal) {
		// ログインユーザーの会員情報を取得する.
		MyUserDetails loginUserData = getLoginUserData(principal);
		// 買い物かごに入っている商品一覧を取得する.
		List<ItemInfoEx> itemInfoInCartList = getAllItemInfoInCart(loginUserData.getUserId());
		// 送料を算出し、viewに渡す.
        int postage = commonService.calcPostage(itemInfoInCartList);
        model.addAttribute("postage", postage);
        // 商品小計と合計金額を算出し、viewに渡す.
        int totalPrice = commonService.calcTotalPrice(itemInfoInCartList);
        model.addAttribute("total", totalPrice + postage);
		model.addAttribute("itemInfoInCartList", itemInfoInCartList);
		return "orderform";
	}

	/**
	 * 決済完了画面を表示します.
	 * 
	 * @param model
	 * @return ブラウザに表示するページ
	 */
	@RequestMapping(value = "/orderComplete")
	public String displayOrderCompletePage(RedirectAttributes redirectAttributes, Principal principal, SessionStatus sessionStatus,
			@ModelAttribute("postage") Integer postage, 
			@ModelAttribute("total") Integer total,
			@Valid OrderInfoForm orderInfoForm, 
			BindingResult result) throws HttpSessionRequiredException {
		
		// フォーム入力値をチェックし、エラーがあれば会員登録ページにエラーを表示させる.
		if (result.hasErrors()) {
            logger.error("入力エラー");
//			for (FieldError err : result.getFieldErrors()) {
//				// log.debug("error code = [" + err.getCode() + "]");
//			    logger.error(err.toString());
//			    logger.debug(err.toString());
//				System.out.println(err);
//			}
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

		// 入力されたデータをリダイレクト先でも利用可能にする。
		redirectAttributes.addFlashAttribute("buyingHistory", buyingHistory);

		return "redirect:/ordercomplete";
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
	
	   // *********************************************************************
    // その他(TEST)メソッド一覧
    // *********************************************************************
	
    /**
     * 全会員情報をブラウザに表示します.
     * 
     * @param model
     * @return ブラウザに表示するページ(serch.html)
     */
    @RequestMapping(value = "/serch")
    public String displayAllUser(Model model) {
        // 全会員情報を取得し、viewに渡す.
        List<UserInfo> userInfoList = userInfoService.getUserAll();
        model.addAttribute("userInfoList", userInfoList);
        return "serch";
    }
}