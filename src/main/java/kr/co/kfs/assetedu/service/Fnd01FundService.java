package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.repository.Fnd01FundRepository;

@Service
public class Fnd01FundService {
	
	@Autowired
	private Fnd01FundRepository fundRepository;
	
	public List<Fnd01Fund> selectList(QueryAttr queryAttr){
		return fundRepository.selectList(queryAttr);
	}
	
	public Long selectCount(QueryAttr queryAttr) {
		return fundRepository.selectCount(queryAttr);
	}
	
	public Fnd01Fund selectOne(Fnd01Fund fund) {
		return fundRepository.selectOne(fund);
	}
	
	@Transactional
	public int insert(Fnd01Fund fund) {
		return fundRepository.insert(fund);
	}
	
	@Transactional
	public int update(Fnd01Fund fund) {
		return fundRepository.update(fund);
	}
	
	@Transactional
	public int delete(Fnd01Fund fund) {
		return fundRepository.delete(fund);
	}
}
