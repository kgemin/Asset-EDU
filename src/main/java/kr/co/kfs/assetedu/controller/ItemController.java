package kr.co.kfs.assetedu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kfs.assetedu.model.Itm01Item;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import kr.co.kfs.assetedu.service.Itm01ItemService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private Itm01ItemService service;
	
	@Autowired
	private Com02CodeService codeService;
	
	@GetMapping("list")
	public String list(String searchText, Model model) {
		model.addAttribute("pageTitle", "종목정보 리스트");
		QueryAttr queryAttr = new QueryAttr();
		queryAttr.put("searchText", searchText);
		List<Itm01Item> list = service.selectList(queryAttr);
		model.addAttribute("list", list);
		return "/item/list";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("pageTitle", "종목등록");
		model.addAttribute("item", new Itm01Item());
		model.addAttribute("stkListTypeList", codeService.codeList("ListType"));
		model.addAttribute("marketTypeList" , codeService.codeList("MarketType"));
		model.addAttribute("stkTypeList"    , codeService.codeList("StkType"));
		return "/item/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("item") Itm01Item item,
						BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("stkListTypeList", codeService.codeList("ListType"));
			model.addAttribute("marketTypeList" , codeService.codeList("MarketType"));
			model.addAttribute("stkTypeList"    , codeService.codeList("StkType"));
			return "/item/insert_form";	
		}
		
		String msg;
		Itm01Item checkItem = service.selectOne(item);
		
		if(checkItem != null) {
			msg = String.format("\"%s\" 코드는 이미 \"%s\" 종목으로 등록되어있습니다.", checkItem.getItm01ItemCd(), checkItem.getItm01ItemNm());
			model.addAttribute("stkListTypeList", codeService.codeList("ListType"));
			model.addAttribute("marketTypeList" , codeService.codeList("MarketType"));
			model.addAttribute("stkTypeList"    , codeService.codeList("StkType"));
			bindingResult.addError(new FieldError("", "", msg));
			return "/item/insert_form";
		} else {
			int affectedCount = service.insert(item);
			
			msg = String.format("\"%s\" 주식종목이 등록되었습니다", item.getItm01ItemNm());
			redirectAttr.addAttribute("mode", "insert");
			redirectAttr.addAttribute("msg", msg);
			return "redirect:/item/success";
		}
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("item") Itm01Item item, Model model) {
		item = service.selectOne(item);
		model.addAttribute("item", item);
		model.addAttribute("stkListTypeList", codeService.codeList("ListType"));
		model.addAttribute("marketTypeList" , codeService.codeList("MarketType"));
		model.addAttribute("stkTypeList"    , codeService.codeList("StkType"));
		return "/item/update_form";
	}
	
	@PostMapping("update")
	public String update_form(@ModelAttribute("item") Itm01Item item, Model model) throws UnsupportedEncodingException {
		service.update(item);
		String msg = String.format("\"%s\"종목이 수정되었습니다.", item.getItm01ItemNm());
		return "redirect:/item/success?mode=update&itemCd=" + item.getItm01ItemCd() + "&msg=" + URLEncoder.encode(msg, "UTF-8");
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String itemCd, Model model) {
		model.addAttribute("pageTitle", "종목등록");
		model.addAttribute("msg", msg);
		model.addAttribute("mode", model);
		model.addAttribute("itemCd", itemCd);
		
		return "/item/success";
	}
	
	@GetMapping("delete")
	public String delete(@ModelAttribute Itm01Item item) {
		int deletedCount = service.delete(item);
		if(deletedCount > 0) {
			log.warn("종목코드 : {}가 삭제되었습니다.");
		}
		return "redirect:/item/list";
	}
}
