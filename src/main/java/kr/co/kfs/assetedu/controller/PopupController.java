package kr.co.kfs.assetedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.service.Com01CorpService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/popup")
public class PopupController {
	
	@Autowired
	private Com01CorpService corpService;
	
	@GetMapping("corp")
	public String corp(String searchText, String codeCd, String codeNm,
			@RequestParam(value="pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="currentPageNumber", required = false, defaultValue = "1") Integer currentPageNumber,
			Model model) {
		
		QueryAttr queryAttr = new QueryAttr();
		queryAttr.put("searchText", searchText);
		Long totalCount = corpService.selectCount(queryAttr);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		
		queryAttr.putClass(pageAttr);
		
		List<Com01Corp> list;
		if(totalCount > 0) {
			list = corpService.selectList(queryAttr);
		} else {
			Com01Corp corpModel = new Com01Corp();
			corpModel.setCom01CorpNm("조회내역이 없습니다");
			list = new ArrayList<>();
			list.add(corpModel);
		}
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		
		return "/common/popup_corp";
	}
	
	@GetMapping("fund")
	public String fund(String searchText, String fundCd, String fundNm,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "currentPageNumber", required = false, defaultValue = "1") Integer currentPageNumber,
			Model model) {
		
		return "/common/popup_fund";
	}
}
