package sample.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.form.BuyingHistorySortForm;
import sample.mapper.BuyingHistoryMapper;
import sample.model.BuyingHistory;
import sample.model.BuyingHistoryExample;

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
	 * 【1レコード挿入】
	 * データベースのbuying_historyテーブル注文情報を購入履歴として登録します.
	 * 
	 * @param　買い物かごに登録する商品
	 * @return 買い物かごに追加した商品数
	 */
	public int insertOrderInfo(BuyingHistory buyingHistory) {
		return buyingHistoryMapper.insertOne(buyingHistory);
	}

	/**
	 * 【全件取得】
	 * 任意のuserIdのbuying_historyテーブルデータを全件取得します.
	 * 
	 *　@param データ取得したい会員のuserId
	 * @return 引数で指定した会員の全件データ
	 */
	public List<BuyingHistory> selectAllBuyingHistory(Integer userId){
		return buyingHistoryMapper.selectAllBuyingHistory(userId);
	}
	
	/**
     * 【選択取得】
     * 任意のuserIdのbuying_historyテーブルデータをソート条件に合わせて取得します.
     * 
     *　@param データ取得したい会員のuserId
     * @return 引数で指定した会員の全件データ
     */
    public List<BuyingHistory> selectSortedBuyingHistory(Integer userId, String orderby){
        BuyingHistoryExample example = new BuyingHistoryExample();
        example.createCriteria().andUserIdEqualTo(userId);
        // ORDER BY句を指定する。
        example.setOrderByClause("buying_date " + orderby);
        return buyingHistoryMapper.selectSortedBuyingHistory(example);
    }
    
    /**
     * 【選択取得】
     * 条件に合わせて検索します.
     * 
     */
    public List<BuyingHistory> selectedBuyingHistory(Integer userId, BuyingHistorySortForm buyingHistorySortForm){
        BuyingHistoryExample example = new BuyingHistoryExample();
        example.or()
            .andUserIdEqualTo(userId);
        
        // (検索日時が「全て」以外の場合)検索条件に、日時を設定する。
        Date date = new Date();
        int pastDate = -(Integer.parseInt(buyingHistorySortForm.getBuyingDate()));
        if(pastDate != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, pastDate);
            date = calendar.getTime();
        }
        
        // 決済方法
        /*
        switch(buyingHistorySortForm.getPayment()) {
        case "0":
            
            break;
        case "1":
            break;
        case "2":
            break;
        case "3":
            break;
        default:
            break;
        }
        */
        
        // 検索条件を設定する。

        example.or().andBuyingDateGreaterThan(date);

        // 検索条件に、ORDER BY句を指定する。
        example.setOrderByClause("buying_date desc");
        
        // 検索を実行する。
        return buyingHistoryMapper.selectSortedBuyingHistory(example);
    }
}