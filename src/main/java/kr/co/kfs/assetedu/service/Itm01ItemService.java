package kr.co.kfs.assetedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kfs.assetedu.repository.Itm01ItemRepository;

@Service
public class Itm01ItemService {

	@Autowired
	private Itm01ItemRepository itemRepository;
}
