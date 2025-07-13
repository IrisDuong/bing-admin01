package com.bh.admin.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.common.dao.CommonCodeDao;
import com.bh.admin.common.dao.GeneralCodeDao;
import com.bh.admin.common.dao.LocaleInputDao;
import com.bh.admin.common.dto.CommonCodeDto;
import com.bh.admin.common.dto.GeneralCodeDto;
import com.bh.admin.common.dto.ParamCommonCodeDto;
import com.bh.admin.common.dto.ParamGeneralCodeDto;
import com.bh.admin.common.entity.CommonCode;
import com.bh.admin.common.entity.GeneralCode;
import com.bh.admin.common.entity.LocaleInput;

@Service
@Transactional
public class BaseCodeServiceImpl implements BaseCodeService {

	@Autowired
	LocaleInputDao localeInputDao;

	@Autowired
	CommonCodeDao commonCodeDao;

	@Autowired
	GeneralCodeDao generalCodeDao;

	@Override
	public CommonCode createCommonCode(ParamCommonCodeDto param) {
		// TODO Auto-generated method stub
		CommonCode commonCode = new CommonCode();
		commonCode.setCodeType(param.getCodeType());
		commonCode.setUseYn(param.getUseYn());
		commonCode.setWorkCode(param.getWorkCode());
		commonCode.setTmpPrefix(param.getWorkCode());
		Long maxLocaleCode = getMaxLocaleCode();

		for (LocaleInput localeInput : param.getListLocaleInputs()) {
			localeInput.setLocaleCode(maxLocaleCode);
			localeInput = localeInputDao.save(localeInput);
		}
		commonCode.setLocaleCode(maxLocaleCode);
		commonCodeDao.save(commonCode);
		return new CommonCode();
	}

	@Override
	public GeneralCode createGeneralCode(ParamGeneralCodeDto param) {
		// TODO Auto-generated method stub
		GeneralCode generalCode = new GeneralCode();
		generalCode.setCommonCode(param.getCommonCode());
		generalCode.setDetailCode(param.getDetailCode());
		generalCode.setUseYn(param.getUseYn());
		generalCode.setOrderSeq(param.getOrderSeq());

		Long maxLocaleCode = getMaxLocaleCode();
		for (LocaleInput localeInput : param.getListLocaleInputs()) {
			localeInput.setLocaleCode(maxLocaleCode);
			localeInput = localeInputDao.save(localeInput);
		}
		generalCode.setLocaleCode(maxLocaleCode);
		return generalCodeDao.save(generalCode);
	}

	public Long getMaxLocaleCode() {
		LocaleInput lastedLocaleInput = localeInputDao.findTopByOrderByLocaleCodeDesc();
		Long maxLocaleCode = lastedLocaleInput == null ? 1 : lastedLocaleInput.getLocaleCode() + 1;
		return maxLocaleCode;
	}

	@Override
	public List<CommonCodeDto> getListCommonCodeByWorkCode(String workCode, String langCode) {
		// TODO Auto-generated method stub
		List<CommonCodeDto> listCommonCodes = commonCodeDao.findByWorkCode(workCode, langCode);
		AtomicInteger index = new AtomicInteger();
		listCommonCodes.stream().forEach(c -> c.setRowNo(index.getAndIncrement() + 1));
//		List<CommonCodeDto> results = new ArrayList<CommonCodeDto>();
//		listCommonCodes.forEach(c->{
////			Optional<LocaleInput>  localeInput = localeInputDao.findByLocaleCodeAndLangCode(c.getLocaleCode(), langCode);
//			
//			CommonCodeDto commonCodeDto = new CommonCodeDto();
//			commonCodeDto.setCommonCode(c.getCommonCode());
//			commonCodeDto.setCodeType(c.getCodeType());
//			commonCodeDto.setUseYn(c.getUseYn());
//			commonCodeDto.setWorkCode(c.getWorkCode());
//			commonCodeDto.setCodeName(localeInput.isPresent() ? localeInput.get().getCodeName() : "");
//			
//			results.add(commonCodeDto);
//		});
		return listCommonCodes;
	}

	@Override
	public List<GeneralCodeDto> getListGeneralsByCommonCode(String commonCode, String langCode) {
		// TODO Auto-generated method stub
		List<GeneralCodeDto> listGeneralCodes = generalCodeDao.findByCommonCode(commonCode, langCode);
		AtomicInteger index = new AtomicInteger();
		listGeneralCodes.stream().forEach(c -> {
			c.setRowNo(index.getAndIncrement() + 1);
//			c.setUseYnCodeName(c.)
		});
		return listGeneralCodes;
	}

}
