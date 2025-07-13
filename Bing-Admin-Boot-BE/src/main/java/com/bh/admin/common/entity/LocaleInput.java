package com.bh.admin.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bh.admin.common.entity.pk.LocaleInputPk;
import com.bh.admin.util.dto.BaseDto;

@Entity
@Table(name = "sys_local_input")
@EntityListeners(AuditingEntityListener.class)
@IdClass(LocaleInputPk.class)
public class LocaleInput extends BaseDto<String> {

	@Id
	@Column(name = "locale_code")
	private Long localeCode;

	@Id
	@Column(name = "lang_code", length = 2)
	private String langCode;

	@Column(name = "code_name", length = 4000)
	private String codeName;

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
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

}
