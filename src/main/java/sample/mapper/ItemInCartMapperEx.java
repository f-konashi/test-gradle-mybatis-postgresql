package sample.mapper;

import java.util.List;

import sample.model.ItemInfo;

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
    int insert(Integer userId, Integer itemId, Integer itemCount);
    
    /**
     * 
     * @param userId: 会員管理番号
     * @return List<ItemInfo>:　買い物かごに入っている商品リスト
     */
    List<ItemInfo> selectItemInCart(Integer userId);
    
}