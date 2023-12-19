package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.model.Sys01User;

public interface Sys01UserRepository {
	List<Sys01User> selectList(QueryAttr queryAttr);
	Sys01User selectOne(Sys01User user);
	
	Integer insert(Sys01User user);
	Integer update(Sys01User user);
	Integer delete(Sys01User user);
}
