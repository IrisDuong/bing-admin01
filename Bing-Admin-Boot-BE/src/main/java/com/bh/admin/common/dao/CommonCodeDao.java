package com.bh.admin.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bh.admin.common.dto.CommonCodeDto;
import com.bh.admin.common.entity.CommonCode;

public interface CommonCodeDao extends JpaRepository<CommonCode, String> {

	@Query("SELECT new com.bh.admin.common.dto.CommonCodeDto(A.commonCode, A.codeType , A.useYn , A.workCode, B.codeName) "
			+ "FROM CommonCode A " + "LEFT JOIN LocaleInput B ON B.localeCode = A.localeCode "
			+ "WHERE A.workCode = :workCode AND B.langCode = :langCode")
	List<CommonCodeDto> findByWorkCode(@Param("workCode") String workCode, @Param("langCode") String langCode);
}
