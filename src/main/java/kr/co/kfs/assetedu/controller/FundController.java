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

import kr.co.kfs.assetedu.model.Fnd01Fund;
import kr.co.kfs.assetedu.model.QueryAttr;
import kr.co.kfs.assetedu.service.Com02CodeService;
import kr.co.kfs.assetedu.service.Fnd01FundService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/fund")
public class FundController {
	
	@Autowired
	private Fnd01FundService fundService;
	
	@Autowired
	private Com02CodeService codeService;
	
	@GetMapping("list")
	public String list(String searchText, Model model) {
		model.addAttribute("pageTitle", "펀드정보 리스트");
		QueryAttr queryAttr = new QueryAttr();
		queryAttr.put("searchText", searchText);
		List<Fnd01Fund> list = fundService.selectList(queryAttr);
		model.addAttribute("list", list);
		return "/fund/list";
	}
	
	@GetMapping("insert")
	public String insert(Model model) {
		model.addAttribute("pageTitle", "펀드등록");
		
		model.addAttribute("fund",new Fnd01Fund());
		model.addAttribute("fundTypeList", codeService.codeList("FundType"));
		model.addAttribute("publicCdList", codeService.codeList("PublicCode"));
		model.addAttribute("unitCdList"  , codeService.codeList("FundUnitCode"));
		model.addAttribute("parentCdList", codeService.codeList("FundParentCode"));
		return "/fund/insert_form";
	}
	
	@PostMapping("insert")
	public String insert(@Valid @ModelAttribute("fund") Fnd01Fund fund,
			BindingResult bindingResult, RedirectAttributes redirectAttr, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("fundTypeList", codeService.codeList("FundType"));
			model.addAttribute("publicCdList", codeService.codeList("PublicCode"));
			model.addAttribute("unitCdList"  , codeService.codeList("FundUnitCode"));
			model.addAttribute("parentCdList", codeService.codeList("FundParentCode"));
			return "/fund/insert_form";
		}
		
		String msg;
		
		Fnd01Fund checkFund = fundService.selectOne(fund);
		if(checkFund != null) {
			msg = String.format("\"%s\" 펀드코드는 이미 \"%s\" 펀드로 등록되어있습니다.", checkFund.getFnd01FundCd(), checkFund.getFnd01FundNm());
			model.addAttribute("fundTypeList", codeService.codeList("FundType"));
			model.addAttribute("publicCdList", codeService.codeList("PublicCode"));
			model.addAttribute("unitCdList"  , codeService.codeList("FundUnitCode"));
			model.addAttribute("parentCdList", codeService.codeList("FundParentCode"));
			bindingResult.addError(new FieldError("", "", msg));
			return "/fund/insert_form";
		} else {
			int affectedCount = fundService.insert(fund);
			
			msg = String.format("\"%s\" 펀드가 등록되었습니다", fund.getFnd01FundNm());
			redirectAttr.addAttribute("mode", "insert");
			redirectAttr.addAttribute("msg" , msg);
			return "redirect:/fund/success";
		}
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute("fund") Fnd01Fund fund, Model model) {
		 fund = fundService.selectOne(fund);
		 
		model.addAttribute("fund", fund);
		model.addAttribute("fundTypeList", codeService.codeList("FundType"));
		model.addAttribute("publicCdList", codeService.codeList("PublicCode"));
		model.addAttribute("unitCdList"  , codeService.codeList("FundUnitCode"));
		model.addAttribute("parentCdList", codeService.codeList("FundParentCode"));
		return "/fund/update_form";
	}
	
	@PostMapping("update")
	public String update_form(@ModelAttribute("fund") Fnd01Fund fund, Model model) throws UnsupportedEncodingException {
		fundService.update(fund);
		String msg = String.format("\"%s\" 펀드가 수정되었습니다.", fund.getFnd01FundNm());
		return "redirect:/fund/success?mode=update&fundCd=" + fund.getFnd01FundCd()+"&msg=" + URLEncoder.encode(msg, "UTF-8");
	}
	
	@GetMapping("delete")
	public String delete(@ModelAttribute Fnd01Fund fund) {
		
		int deletedCount = fundService.delete(fund);
		if(deletedCount > 0) {
			log.warn("펀드코드 : {}가 삭제되었습니다");
		}
		return "redirect:/fund/list";
	}
	
	@GetMapping("success")
	public String success(String msg, String mode, String fundCd, Model model) {
		model.addAttribute("pageTitle", "펀드등록");
		model.addAttribute("msg", msg);
		model.addAttribute("mode", mode);
		model.addAttribute("fundCd", fundCd);
		return "/fund/success";
	}
}
