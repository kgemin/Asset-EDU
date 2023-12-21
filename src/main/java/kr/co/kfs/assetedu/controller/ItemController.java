package kr.co.kfs.assetedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.kfs.assetedu.service.Itm01ItemService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private Itm01ItemService service;
	
	@GetMapping("list")
	public String list() {
		
		return "/item/list";
	}
}
