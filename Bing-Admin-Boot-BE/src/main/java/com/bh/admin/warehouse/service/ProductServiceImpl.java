package com.bh.admin.warehouse.service;

import org.springframework.transaction.annotation.Transactional;

import com.bh.admin.warehouse.entity.Product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> searchProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
