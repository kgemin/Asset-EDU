package kr.co.kfs.assetedu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.model.Sys01User;
import kr.co.kfs.assetedu.repository.Sys01UserRepository;

@Service
public class Sys01UserService {
	private Sys01UserRepository userRepository;
	
	public Sys01UserService(Sys01UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<Sys01User> selectList(QueryAttr queryAttr){
		return userRepository.selectList(queryAttr);
	}
	
	public Sys01User selectOne(Sys01User user) {
		return userRepository.selectOne(user);
	}
	
	@Transactional
	public int insert(Sys01User user) {
		return userRepository.insert(user);
	}
	
	@Transactional
	public int update(Sys01User user) {
		return userRepository.update(user);
	}
	
	@Transactional
	public int delete(Sys01User user) {
		return userRepository.delete(user);
	}
}
