package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

import sample.form.SortForm;
import sample.model.BuyingHistory;
import sample.security.MyUserDetails;
import sample.service.BuyingHistoryService;

/**
 * 購入履歴画面の各種動作を制御するコントローラークラスです.
 * 
 * @author f-konashi
 *
 */
@Controller
public class BuyingHistoryController {
    @Autowired
    private BuyingHistoryService buyingHistoryService;

    /**
     * 購入履歴画面で使用するフォームに対応したオブジェクトを初期化し、Modelに追加する
     * (Thymeleafからアクセスさせるために必要).
     * 
     * @return　購入履歴画面でのフォーム入力値を格納するオブジェクト
     */
    @ModelAttribute
    SortForm setupUserSortForm() {
        return new SortForm();
    }
    
    /**
     * 購入履歴情報をデータベースから呼び出し、画面出力します.
     * 
     * @param model
     * @param principal
     * @return ブラウザに表示するページ
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
     * 購入履歴をソートします.
     * 
     * @param model
     * @param orderby
     *            ソート条件
     * @return ブラウザに表示するページ(buyinghistory.html)
     */
    @RequestMapping(value = { "sort/date/{condition}" })
    public String sortByDate(Model model, Principal principal, @PathVariable("condition") String condition) {
        // ログインユーザーの会員情報を取得する.
        MyUserDetails loginUserData = getLoginUserData(principal);
        
        // ソート結果を取得する。
        List<BuyingHistory> buyingHistoryList = buyingHistoryService
                .selectSortedBuyingHistory(loginUserData.getUserId(), condition);
        model.addAttribute(buyingHistoryList);

        return "buyinghistory";
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
