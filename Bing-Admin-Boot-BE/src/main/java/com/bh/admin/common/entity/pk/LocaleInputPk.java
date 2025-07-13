package com.bh.admin.common.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class LocaleInputPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long localeCode;

	private String langCode;

	public LocaleInputPk(Long localeCode, String langCode) {
		super();
		this.localeCode = localeCode;
		this.langCode = langCode;
	}

	public LocaleInputPk() {
		super();
	}

	public Long getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(Long localeCode) {
		this.localeCode = localeCode;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		if (obj instanceof LocaleInputPk) {
			LocaleInputPk pk = (LocaleInputPk) obj;
			return pk.localeCode.equals(this.localeCode) && pk.langCode.equals(this.langCode);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.localeCode);
	}
}
