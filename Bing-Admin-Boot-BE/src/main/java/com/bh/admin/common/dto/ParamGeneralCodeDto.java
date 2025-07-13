package com.bh.admin.common.dto;

import java.util.List;

import com.bh.admin.common.entity.LocaleInput;

public class ParamGeneralCodeDto {

	private String commonCode;
	private String detailCode;
	private String useYn;
	private int orderSeq;
	private List<LocaleInput> listLocaleInputs;

	public String getCommonCode() {
		return commonCode;
	}

	public void setCommonCode(String commonCode) {
		this.commonCode = commonCode;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public int getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
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

}
