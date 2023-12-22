package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Itm01Item;
import kr.co.kfs.assetedu.model.QueryAttr;

public interface Itm01ItemRepository {
	
	List<Itm01Item> selectList(QueryAttr queryAttr);
	Long selectCount(QueryAttr queryAttr);
	Itm01Item selectOne(Itm01Item item);
	
	int insert(Itm01Item item);
	int update(Itm01Item item);
	int delete(Itm01Item item);
}
