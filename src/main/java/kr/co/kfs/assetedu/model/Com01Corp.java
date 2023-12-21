package kr.co.kfs.assetedu.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Com01Corp {
	
	@NotBlank(message="기관코드가 존재하지 않습니다.")
	private String com01CorpCd;
	
	@NotBlank(message="기관명이 존재하지 않습니다.")
	private String com01CorpNm;
	
	@NotBlank(message="기관구분이 존재하지 않습니다.")
	private String com01CorpType;
	
	private String com01CorpEnm;
	
	private String com01ExtnCorpCd;
	
	private String com01CorpTypeNm;
}
