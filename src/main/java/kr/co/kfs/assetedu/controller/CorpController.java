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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kfs.assetedu.model.Com01Corp;
import kr.co.kfs.assetedu.model.PageAttr;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.service.Com01CorpService;
import kr.co.kfs.assetedu.service.Com02CodeService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/corp")
public class CorpController {
	
	@Autowired
	private Com01CorpService service;
	
	@Autowired
	private Com02CodeService codeService;
	
	@GetMapping("list")
	public String list(String searchText
			, @RequestParam(value = "pageSize", defaultValue = "10", required = false)Integer pageSize
			, @RequestParam(value = "currentPageNumber", defaultValue = "1", required = false)Integer currentPageNumber, Model model) {
		model.addAttribute("pageTitle", "기관정보 리스트");
//		QueryAttr queryAttr = new QueryAttr();
//		queryAttr.put("searchText", searchText);
//		List<Com01Corp> list = service.selectList(queryAttr);
//		model.addAttribute("list", list);
		Integer startCount = (currentPageNumber -1) * pageSize + 1;
		QueryAttr queryAttr = new QueryAttr();
		queryAttr.put("searchText", searchText);
		Long totalCount = service.selectCount(queryAttr);
		PageAttr pageAttr = new PageAttr(totalCount, pageSize, currentPageNumber);
		log.debug("pageAttr:{}", pageAttr);
		queryAttr.put("pageAttr", pageAttr);
		List<Com01Corp> list = service.selectList(queryAttr);
		model.addAttribute("list", list);
		model.addAttribute("pageAttr", pageAttr);
		model.addAttribute("startCount", startCount);
		return "/corp/list";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("pageTitle", "기관등록");
		model.addAttribute("corp", new Com01Corp());
		model.addAttribute("corpTypeList", codeService.codeList("CorpType"));
		return "/corp/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("corp") Com01Corp corp,
				BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) throws UnsupportedEncodingException {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("corpTypeList", codeService.codeList("CorpType"));
			return "/corp/insert_form";
		}
		
		String msg;
		
		//기관코드 중복체크
				Com01Corp checkCorp = service.selectOne(corp);
				if(checkCorp != null) {
					msg = String.format("\"%s\" 코드는 이미 \"%s\" 으로 등록되어있습니다.", checkCorp.getCom01CorpCd(), checkCorp.getCom01CorpNm());
					bindingResult.addError(new FieldError("", "", msg));
					model.addAttribute("corpTypeList", codeService.codeList("CorpType"));
					return "/corp/insert_form";	
				} else {
					int affectedCount = service.insert(corp);
					log.debug("DB에 적용된 갯수 : {}",affectedCount);

					msg = String.format("\"%s\" 기관정보가 등록되었습니다", corp.getCom01CorpNm());
					redirectAttr.addAttribute("mode", "insert");
					redirectAttr.addAttribute("msg" , msg);
					return "redirect:/corp/success";
				}
		
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("corp") Com01Corp corp, Model model) {
		corp = service.selectOne(corp);
		model.addAttribute("corp", corp);
		model.addAttribute("corpTypeList", codeService.codeList("CorpType"));
		return "/corp/update_form";
	}
	
	@PostMapping("update")
	public String update_form(@ModelAttribute("corp") Com01Corp corp, Model model) throws UnsupportedEncodingException {
		service.update(corp);
		String msg = String.format("\"%s\" 기관정보가 수정되었습니다.", corp.getCom01CorpNm());
		return "redirect:/corp/success?mode=update&corpCd=" + corp.getCom01CorpCd() + "&msg=" + URLEncoder.encode(msg, "UTF-8");
	}
	
	@GetMapping("delete")
	public String delete(@ModelAttribute Com01Corp corp) {
		int deletedCount = service.delete(corp);
		if(deletedCount > 0) {
			log.warn("기관코드 : {}가 삭제되었습니다.");
		}
		return "redirect:/corp/list";
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String corpCd, Model model) {
		model.addAttribute("pageTitle", "기관정보 등록");
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("corpCd", corpCd);
		return "/corp/success";
	}
}
