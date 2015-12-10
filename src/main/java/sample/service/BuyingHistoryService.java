package sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.BuyingHistoryMapper;
import sample.model.BuyingHistory;

/**
 * サービスクラスです.
 * 
 * @author f-konashi
 */
@Service
public class BuyingHistoryService {
	@Autowired
	private BuyingHistoryMapper buyingHistoryMapper;

	/**
	 * データベースのbuying_istoryテーブル注文情報を購入履歴として登録します.
	 * 
	 * @param
	 * @return 買い物かごに追加した商品数
	 */
	public int insertOrderInfo(BuyingHistory buyingHistory) {
		return buyingHistoryMapper.insertOne(buyingHistory);
	}

	/**
	 * 
	 *　@param
	 * @return 
	 */
	public List<BuyingHistory> selectAllBuyingHistory(Integer userId){
		return buyingHistoryMapper.selectAllBuyingHistory(userId);
	}
}
