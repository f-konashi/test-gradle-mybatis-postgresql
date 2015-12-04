package sample.mapper;

import java.util.List;

import sample.model.ItemInfo;

/**
 * 「MyBatis Generator」で自動生成されるMapperクラスとは別に、独自に定義したメソッドを提供するクラスです。
 * 
 * @author f-konashi
 * 
 */
public interface ItemInfoMapperEx {
	/**
	 * テーブルデータを全件取得する
	 * 
	 * @param
	 * @return
	 */
	List<ItemInfo> getAll();
}