package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.repository.Com01CorpRepository;

@Service
public class Com01CorpService {
	
	@Autowired
	private Com01CorpRepository corpRepository;
	
	public List<Com01Corp> selectList(QueryAttr queryAttr){
		return corpRepository.selectList(queryAttr);
	}
	
	public Long selectCount(QueryAttr queryAttr) {
		return corpRepository.selectCount(queryAttr);
	}
	
	public Com01Corp selectOne(Com01Corp com01Corp) {
		return corpRepository.selectOne(com01Corp);
	}
	
	@Transactional
	public int insert(Com01Corp corp) {
		return corpRepository.insert(corp);
	}
	
	@Transactional
	public int update(Com01Corp corp) {
		return corpRepository.update(corp);
	}
	
	@Transactional
	public int delete(Com01Corp corp) {
		return corpRepository.delete(corp);
	}
}
