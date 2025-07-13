package com.bh.admin.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bh.admin.util.AppConstants;
import com.bh.admin.util.dto.BaseDto;

@Entity
@Table(name = "sys_common_code")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonCode extends BaseDto<String> {

	@Id
	@GeneratedValue(generator = "pkGenerator")
	@GenericGenerator(name = "pkGenerator",
		//	parameters = @Parameter(name="pkPrefix", value = "BHCMC"), 
			strategy = AppConstants.PK_GENERATOR_LOCATION
	)
	private String commonCode;

	@Column(name = "code_type", length = 3)
	private String codeType;

	@Column(name = "use_yn", length = 1)
	private String useYn;

	@Column(name = "work_code", length = 4, nullable = false)
	private String workCode;

	private Long localeCode;

	@Transient
	private String tmpPrefix;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	@JoinTable(name = "common_locale"
//	, joinColumns = @JoinColumn(name = "common_code")
//	,inverseJoinColumns = {
//			@JoinColumn(name = "locale_code"),
//			@JoinColumn(name = "lang_code")
//	}
//	)
//	private Set<LocaleInput> listLocaleInputs;

	
}
