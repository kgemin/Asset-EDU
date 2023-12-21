package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.Com02Code;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.repository.Com02CodeRepository;

@Service
public class Com02CodeService {
	
	@Autowired
	private Com02CodeRepository codeRepository;
	
	public List<Com02Code> selectList(QueryAttr queryAttr){
		return codeRepository.selectList(queryAttr);
	}
	
	public Integer selectCount(QueryAttr queryAttr) {
		return codeRepository.selectCount(queryAttr);
	}
	
	public Com02Code selectOne(Com02Code code) {
		return codeRepository.selectOne(code);
	}
	
	@Transactional
	public int insert(Com02Code code) {
		return codeRepository.insert(code);
	}
	
	@Transactional
	public int update(Com02Code code) {
		return codeRepository.update(code);
	}
	
	@Transactional
	public int delete(Com02Code code) {
		return codeRepository.delete(code);
	}
	
	public List<Com02Code> codeList(String comCd){
		QueryAttr queryAttr = new QueryAttr();
		queryAttr.put("com02ComCd", comCd);
		queryAttr.put("com02CodeType", "D");
		queryAttr.put("com02UseYn", "true");
		return codeRepository.selectList(queryAttr);
	}
}
