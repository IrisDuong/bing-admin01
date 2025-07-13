package com.bh.admin.warehouse.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.admin.warehouse.dao.ProductDao;
import com.bh.admin.warehouse.dto.ProductCategoryDTO;
import com.bh.admin.warehouse.entity.Product;

@RestController
@RequestMapping("/productController")
public class ProductController {
	@Autowired
	ProductDao productDao;

	
}
