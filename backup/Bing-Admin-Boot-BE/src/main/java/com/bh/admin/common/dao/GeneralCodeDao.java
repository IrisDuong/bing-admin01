package com.bh.admin.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bh.admin.common.dto.GeneralCodeDto;
import com.bh.admin.common.entity.GeneralCode;
import com.bh.admin.common.entity.pk.GeneralCodePk;

public interface GeneralCodeDao extends JpaRepository<GeneralCode, GeneralCodePk> {

	@Query("SELECT new com.bh.admin.common.dto.GeneralCodeDto"
			+ "( A.commonCode, A.detailCode, B.codeName,A.useYn,\"\", A.orderSeq)" + "	FROM GeneralCode A "
			+ "	LEFT JOIN LocaleInput B ON B.localeCode = A.localeCode"
			+ " WHERE A.commonCode = :commonCode  AND B.langCode = :langCode")
	List<GeneralCodeDto> findByCommonCode(@Param("commonCode") String commonCode, @Param("langCode") String langCode);
}
