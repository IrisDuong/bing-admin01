package com.bh.admin.common.dto;

import java.util.List;
import java.util.Set;

import com.bh.admin.common.entity.LocaleInput;

public class ParamCommonCodeDto {
	private String commonCode;
	private String codeType;
	private String useYn;
	private String workCode;

	private List<LocaleInput> listLocaleInputs;

	public String getCommonCode() {
		return commonCode;
	}

	public void setCommonCode(String commonCode) {
		this.commonCode = commonCode;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<LocaleInput> getListLocaleInputs() {
		return listLocaleInputs;
	}

	public void setListLocaleInputs(List<LocaleInput> listLocaleInputs) {
		this.listLocaleInputs = listLocaleInputs;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

}
