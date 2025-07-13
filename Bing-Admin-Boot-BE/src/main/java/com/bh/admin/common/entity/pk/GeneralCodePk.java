package com.bh.admin.common.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class GeneralCodePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String commonCode;
	private String detailCode;

	public GeneralCodePk(String commonCode, String detailCode) {
		super();
		this.commonCode = commonCode;
		this.detailCode = detailCode;
	}

	public GeneralCodePk() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		if (obj instanceof GeneralCodePk) {
			GeneralCodePk castedObj = (GeneralCodePk) obj;
			return castedObj.commonCode.equals(commonCode) && castedObj.detailCode.equals(detailCode);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(commonCode, detailCode);
	}

}
