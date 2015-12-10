package sample.mapper;

import java.util.List;

import sample.model.ItemInCart;
import sample.model.ItemInfoEx;

public interface ItemInCartMapperEx {
	/**
	 * DBに登録する。
	 * 
	 *　@param 
	 *	Integer userId: 会員管理番号 
	 *	Integer itemId: 商品管理番号
	 *	Integer itemCount:　商品個数
	 *
	 * @return 
	 * 	int
	 * 	・買い物かごに追加した商品数をリターンする。
	 * 	・null値がリターンされることはない。
	 */
    int insertOne(ItemInCart record);
    
    /**
     * 
     * @param userId: 会員管理番号
     * @return List<ItemInfo>:　買い物かごに入っている商品リスト
     */
    List<ItemInfoEx> selectItemInCart(Integer userId);
    
    /**
     * 
     * @param loginId: 会員管理番号
     * @return int:　削除されたレコード件数
     */
    int deleteItemByUserId(Integer userId);
    
    /**
     * 
     * @param cartId: 買い物かご管理番号
     * @return int:　削除されたレコード件数
     */
    int deleteItemInCartByCartId(Integer cartId);
}