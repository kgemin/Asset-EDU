package kr.co.kfs.assetedu.repository;

import java.util.List;

import kr.co.kfs.assetedu.model.Com02Code;
import kr.co.kfs.assetedu.model.QueryAttr;

public interface Com02CodeRepository {
	List<Com02Code> selectList(QueryAttr queryAttr);
	Integer selectCount(QueryAttr queryAttr);
	Com02Code selectOne(Com02Code code);
	
	int insert(Com02Code code);
	int update(Com02Code code);
	int delete(Com02Code code);
}
