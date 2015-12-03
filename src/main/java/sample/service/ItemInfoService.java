package sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.mapper.ItemInfoMapper;
import sample.model.ItemInfo;

import java.util.List;

/**
 * サービスクラス
 * 
 * @author f-konashi
 */
@Service
public class ItemInfoService {
	@Autowired
	private ItemInfoMapper itemInfoMapper;

	/**
	 * 商品データを全件取得する。
	 * 
	 * @return データベースに登録されている全商品情報
	 */
	public List<ItemInfo> getItemAll() {
		return itemInfoMapper.getAll();
	}
}