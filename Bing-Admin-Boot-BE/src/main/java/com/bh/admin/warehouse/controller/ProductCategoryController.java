package com.bh.admin.warehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.bh.admin.warehouse.dto.ProductCategoryDTO;
import com.bh.admin.warehouse.entity.ProductCategory;
import com.bh.admin.warehouse.service.ProductCategoryService;

@RestController
@RequestMapping("/productCategoryController")
public class ProductCategoryController {

	@Autowired
	ProductCategoryService productCategoryService;

	@PostMapping("/createNew")
	public ResponseEntity<?> createProductCategory(@RequestBody ProductCategoryDTO param) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			productCategoryService.saveProductCategory(param);
			httpStatus = HttpStatus.CREATED;
			message = "Create new Product Category Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			message = "Create New Product Category Failed !";
		}
		data.put("message", message);
		return new ResponseEntity(data, httpStatus);
	}

	@GetMapping("/search")
	public ResponseEntity<?> getAllProdutCategories(@RequestBody ProductCategoryDTO param) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			List<ProductCategoryDTO> listProductCategories = productCategoryService.searchProductCategories(param);
			data.put("listProductCategories", listProductCategories);
			httpStatus = HttpStatus.OK;
			message = "Get All Product Categories Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.NO_CONTENT;
			message = "Get All Product Categories Failed !";
		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}

	@GetMapping("/getProductCategoryById/{id}")
	public ResponseEntity<?> getProductCategoryById(@PathVariable("id") String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			ProductCategoryDTO productCategory = productCategoryService.getProductCategoryById(id);
			data.put("data", productCategory);
			httpStatus = HttpStatus.OK;
			message = "Get Product Category Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.NO_CONTENT;
			message = "Get Product Category Failed !";
		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}

	@GetMapping("/getProductGroupById/{id}")
	public ResponseEntity<?> getProductGroupById(@PathVariable("id") String id) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			ProductCategoryDTO result = productCategoryService.getProductCategoryById(id);
			data.put("result", result);
			httpStatus = HttpStatus.OK;
			message = "Get Product Group Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.NO_CONTENT;
			message = "Get Product Group Failed !";
		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}

	@GetMapping("/getAllProductGroupsByCategory")
	public ResponseEntity<?> getAllProductGroupsByCategory(
			@RequestParam("productCategoryCode") String productCategoryCode) {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		String message = "";
//		try {
//			ProductCategoryDTO param = new ProductCategoryDTO();
//			param.setProductCategoryCode(productCategoryCode);
//
//			Set<ProductGroupDto> allProductGroups = productCategoryService.getAllProductGroupsByCategory(param);
//			data.put("data", allProductGroups);
//			httpStatus = HttpStatus.OK;
//			message = "Get Product Group Successfully !";
//		} catch (Exception e) {
//			// TODO: handle exception
//			httpStatus = HttpStatus.NO_CONTENT;
//			message = "Get Product Group Failed !";
//		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}

}
