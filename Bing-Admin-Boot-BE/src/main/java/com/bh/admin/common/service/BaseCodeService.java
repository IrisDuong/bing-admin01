package com.bh.admin.common.service;

import java.util.List;

import com.bh.admin.common.dto.CommonCodeDto;
import com.bh.admin.common.dto.GeneralCodeDto;
import com.bh.admin.common.dto.ParamCommonCodeDto;
import com.bh.admin.common.dto.ParamGeneralCodeDto;
import com.bh.admin.common.entity.CommonCode;
import com.bh.admin.common.entity.GeneralCode;

public interface BaseCodeService {

	CommonCode createCommonCode(ParamCommonCodeDto param);

	GeneralCode createGeneralCode(ParamGeneralCodeDto param);

	List<CommonCodeDto> getListCommonCodeByWorkCode(String workCode, String langCode);

	List<GeneralCodeDto> getListGeneralsByCommonCode(String commonCode, String langCode);
}
