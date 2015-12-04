package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.ItemInCartMapper;
import sample.model.ItemInCart;
import sample.model.ItemInfoEx;

/**
 * サービスクラスです。
 * 
 * @author f-konashi
 */
@Service
public class ItemInCartService {
	@Autowired
	private ItemInCartMapper itemInCartMapper;

	/**
	 * 「買い物かごに入れる」ボタンがクリックされた商品を、クリックした会員の買い物かごに追加する。
	 * 
	 *　@param 
	 *	Integer userId: 会員管理番号 
	 *	Integer itemId: 商品管理番号
	 *	Integer itemCount:　商品個数
	 *
	 * @return int: 買い物かごに追加した商品数
	 * 	・null値がリターンされることはない。
	 */
	public int setItemInCart(ItemInCart itemInCart) {
		return itemInCartMapper.insertOne(itemInCart);
	}
	
	/**
	 * 
	 * @param userId:　会員管理番号
	 * 
	 * @return List<ItemInfo>: 買い物かごに入っている商品リスト
	 * 	・null値がリターンされることはない。
	 */
	public List<ItemInfoEx> getItemInCart(Integer userId) {
		return itemInCartMapper.selectItemInCart(userId);
	}
	
	/**
	 * 
	 * 
	 * @param userId　
	 * @return int 削除された件数
	 * 	・null値がリターンされることはない。
	 */
	public int deleteItemInCart(Integer cartid) {
		return itemInCartMapper.deleteItemInCartByCartId(cartid);
	}
	
	
	/**
	 * 
	 * 
	 * @param userId　
	 * @return int 削除された件数
	 * 	・null値がリターンされることはない。
	 */
	public int deleteItemAllInCart(Integer userId) {
		return itemInCartMapper.deleteItemByUserId(userId);
	}
}
