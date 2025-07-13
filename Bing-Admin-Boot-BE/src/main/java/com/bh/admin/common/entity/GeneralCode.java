package com.bh.admin.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bh.admin.common.entity.pk.GeneralCodePk;
import com.bh.admin.util.dto.BaseDto;

@Entity
@Table(name = "sys_general_code")
@EntityListeners(AuditingEntityListener.class)
@IdClass(GeneralCodePk.class)
public class GeneralCode extends BaseDto<String> {
	@Id
	@Column(name = "common_code", length = 6)
	private String commonCode;

	@Id
	@Column(name = "detail_code", length = 4)
	private String detailCode;

	@Column(name = "use_yn", length = 1)
	private String useYn;

	private int orderSeq;

	private Long localeCode;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JoinTable(name = "general_locale"
//	, joinColumns = {
//			@JoinColumn(name = "common_code"),
//			@JoinColumn(name = "detailCode")
//	}
//	,inverseJoinColumns = {
//			@JoinColumn(name = "locale_code"),
//			@JoinColumn(name = "lang_code")
//	}
//	)
//	private Set<LocaleInput> listLocaleInputs;

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

	public int getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}

	public Long getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(Long localeCode) {
		this.localeCode = localeCode;
	}

//	public Set<LocaleInput> getListLocaleInputs() {
//		return listLocaleInputs;
//	}
//
//	public void setListLocaleInputs(Set<LocaleInput> listLocaleInputs) {
//		this.listLocaleInputs = listLocaleInputs;
//	}

}
