package com.bh.admin.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bh.admin.common.dto.CommonCodeDto;
import com.bh.admin.common.dto.GeneralCodeDto;
import com.bh.admin.common.dto.ParamCommonCodeDto;
import com.bh.admin.common.dto.ParamGeneralCodeDto;
import com.bh.admin.common.entity.CommonCode;
import com.bh.admin.common.entity.GeneralCode;
import com.bh.admin.common.service.BaseCodeService;

@RestController
@RequestMapping("/baseCodeController")
public class BaseCodeController {

	@Autowired
	BaseCodeService baseCodeService;

	@GetMapping("/commonCode/getListCommonCodeByWorkCode/{workCode}")
	public ResponseEntity<?> getListCommonCodeByWorkCode(@PathVariable String workCode,
			@RequestParam("langCode") String langCode) {
		HttpStatus httpStatus = null;
		String message = "";
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<CommonCodeDto> listCommonCodes = baseCodeService.getListCommonCodeByWorkCode(workCode, langCode);
			data.put("listCommonCodes", listCommonCodes);
			message = "Get list Common Code Successfully !";
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			// TODO: handle exception
			message = "Created General Code Failed !";
			httpStatus = HttpStatus.NO_CONTENT;
		}
		data.put("message", message);
		return new ResponseEntity(data, httpStatus);
	}

	@PostMapping("/commonCode/create")
	public ResponseEntity<?> createCommonCode(@RequestBody ParamCommonCodeDto param) {
		HttpStatus httpStatus = null;
		Map<String, Object> data = new HashMap<String, Object>();
		String message = "";
		try {
			CommonCode savedCommonCode = baseCodeService.createCommonCode(param);
			message = "Created Common Code Successfully !";
			httpStatus = HttpStatus.CREATED;
			data.put("savedCommonCode", savedCommonCode);
		} catch (Exception e) {
			// TODO: handle exception
			message = "Created Common Code Failed !";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		data.put("message", message);
		return new ResponseEntity(data, httpStatus);
	}

	@PostMapping("/generalCode/create")
	public ResponseEntity<?> createGeneralCode(@RequestBody List<ParamGeneralCodeDto> param) {
		HttpStatus httpStatus = null;
		String message = "";
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			if (param != null && param.size() > 0) {
				param.forEach(e ->baseCodeService.createGeneralCode(e));
				message = "Created General Code Successfully !";
				httpStatus = HttpStatus.CREATED;
			} else {

				message = "Created General Code Failed !";
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			message = "Created General Code Failed !";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		data.put("message", message);
		return new ResponseEntity(data, httpStatus);
	}

	@GetMapping("/generalCode/getAllByCommonCode")
	public ResponseEntity<?> getAllGeneralByCommonCode(@RequestParam("commonCode") String commonCode,
			@RequestParam("langCode") String langCode) {
		HttpStatus httpStatus;
		String message;
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			List<GeneralCodeDto> listGeneralCodes = baseCodeService.getListGeneralsByCommonCode(commonCode, langCode);
			data.put("listGeneralCodes", listGeneralCodes);
			message = "Get List General Code By Common Code Successfully !";
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			// TODO: handle exception
			message = "Get List General Code By Common Code Failed !";
			httpStatus = HttpStatus.NO_CONTENT;
		}
		data.put("message", message);
		return new ResponseEntity(data, httpStatus);
	}
}
