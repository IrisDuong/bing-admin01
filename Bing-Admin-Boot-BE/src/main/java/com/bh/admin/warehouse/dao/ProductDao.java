package com.bh.admin.warehouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bh.admin.warehouse.entity.Product;

public interface ProductDao extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

}
