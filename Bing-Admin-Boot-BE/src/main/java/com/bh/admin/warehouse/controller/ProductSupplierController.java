package com.bh.admin.warehouse.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bh.admin.util.exception.BadRequestException;
import com.bh.admin.warehouse.dto.ProductSupplierDTO;
import com.bh.admin.warehouse.entity.Product;
import com.bh.admin.warehouse.service.ProductSupplierService;

@RestController
@RequestMapping("/wh/productSupplier")
public class ProductSupplierController {

	@Autowired
	ProductSupplierService productSupplierService;
	
	@PostMapping("/search")
	public ResponseEntity<?> searchProductSuppliers(@RequestBody ProductSupplierDTO reqParam){
		try {
			List<ProductSupplierDTO> _listSuppliers = productSupplierService.searchProductSuppliers(reqParam);
			return new ResponseEntity<>(_listSuppliers, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductSuppliers(@RequestBody ProductSupplierDTO reqParam){
		try {
			productSupplierService.saveProductSupplier(reqParam);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getSupplierByCode/{supplierCode}")
	public ResponseEntity<ProductSupplierDTO> getSupplierByCode(@PathVariable Optional<String> supplierCode){
		try {
			Optional<ProductSupplierDTO> _supplier = productSupplierService.getSupplierByCode(supplierCode.get());
			return new ResponseEntity<ProductSupplierDTO>(_supplier.get(), HttpStatus.OK);
		}catch (NoSuchElementException e) {
			// TODO: handle exception
			throw new BadRequestException("Supplier Code is required !");
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dummyTestDataProductSupplier")
	public ResponseEntity<?> dummyTestDataProductSupplier() {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			for(int i = 1; i <=1000000;i++) {
				ProductSupplierDTO p = ProductSupplierDTO.builder()
						.supplierName("NCC - "+i)
						.build();
				productSupplierService.saveProductSupplier(p);
			}
			httpStatus = HttpStatus.OK;
			message = "Dummy Test Data Product Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.NO_CONTENT;
			message = "Get All Product Categories Failed !";
		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}

	@GetMapping("/getAllProductSuppliers")
	public ResponseEntity<?> getAllProductSuppliers() {
		Map<String, Object> data = new HashMap<String, Object>();
		HttpStatus httpStatus;
		String message = "";
		try {
			Long start = System.currentTimeMillis();
			List<ProductSupplierDTO> result = productSupplierService.getAllProductSuppliers();
			data.put("result", result);
			System.out.println("--------------------------total time of searching supplier = "+(System.currentTimeMillis() - start));
			httpStatus = HttpStatus.OK;
			message = "Dummy Test Data Product Successfully !";
		} catch (Exception e) {
			// TODO: handle exception
			httpStatus = HttpStatus.NO_CONTENT;
			message = "Get All Product Categories Failed !";
		}
		data.put("message", message);
		return new ResponseEntity<>(data, httpStatus);
	}
}
