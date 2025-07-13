package com.bh.admin.common.dto;

import java.util.List;
import java.util.Set;

import com.bh.admin.common.entity.LocaleInput;

public class CommonCodeDto {
	private int rowNo;
	private String commonCode;
	private String codeType;
	private String useYn;
	private String workCode;
	private String codeName;

	public CommonCodeDto() {
		super();
	}

	public CommonCodeDto(int rowNo, String commonCode, String codeType, String useYn, String workCode,
			String codeName) {
		super();
		this.rowNo = rowNo;
		this.commonCode = commonCode;
		this.codeType = codeType;
		this.useYn = useYn;
		this.workCode = workCode;
		this.codeName = codeName;
	}

	public CommonCodeDto(String commonCode, String codeType, String useYn, String workCode, String codeName) {
		super();
		this.commonCode = commonCode;
		this.codeType = codeType;
		this.useYn = useYn;
		this.workCode = workCode;
		this.codeName = codeName;
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

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

}
