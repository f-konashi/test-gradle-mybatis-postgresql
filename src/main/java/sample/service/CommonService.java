package sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.model.ItemInfoEx;
import sample.util.Tax;

/**
 * 注文商品代金の計算処理を行うクラスです.
 * 
 * @author f-konashi
 */
@Service
public class CommonService {
    /**
     * 注文商品の合計金額を算出します.
     * 
     * @param itemInfoInCartList　 注文商品リスト
     * @return 商品合計金額
     */
    public int calcTotalPrice(List<ItemInfoEx> itemInfoInCartList) {
        int totalPrice = 0;
        for (ItemInfoEx item : itemInfoInCartList) {
            // 商品小計(税込)を算出する.
            int subtotal = (int) (item.getPrice() * item.getItemCount() * Tax.TAX_RATE);
            item.setSubtotal(subtotal);

            // 商品合計(税込)を算出する.
            totalPrice += subtotal;
        }
        return totalPrice;
    }

    /**
     * 注文商品の送料を算出します(商品内で送料の最大値を適用する).
     * 
     * @param itemInfoInCartList 注文商品リスト
     * @return 送料
     */
    public int calcPostage(List<ItemInfoEx> itemInfoInCartList) {
        int postage = 0;
        for (ItemInfoEx item : itemInfoInCartList) {
            if (item.getPostage() > postage) {
                postage = item.getPostage();
            }
        }
        return postage;
    }
}