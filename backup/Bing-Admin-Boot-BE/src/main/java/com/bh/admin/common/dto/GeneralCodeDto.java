package com.bh.admin.common.dto;

import java.util.List;

import com.bh.admin.common.entity.LocaleInput;

public class GeneralCodeDto {

	private int rowNo;
	private String commonCode;
	private String detailCode;
	private String codeName;
	private String useYn;
	private String useYnCodeName;
	private int orderSeq;

	public GeneralCodeDto() {
		super();
	}

	public GeneralCodeDto(String commonCode, String detailCode, String codeName, String useYn, String useYnCodeName,
			int orderSeq) {
		super();
		this.commonCode = commonCode;
		this.detailCode = detailCode;
		this.codeName = codeName;
		this.useYn = useYn;
		this.useYnCodeName = useYnCodeName;
		this.orderSeq = orderSeq;
	}
	
	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

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

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public int getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}



	public String getUseYnCodeName() {
		return useYnCodeName;
	}



	public void setUseYnCodeName(String useYnCodeName) {
		this.useYnCodeName = useYnCodeName;
	}

}
