package com.bh.admin.warehouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bh.admin.warehouse.entity.ProductCategory;

public interface ProductCategoryDao extends JpaRepository<ProductCategory, String>, JpaSpecificationExecutor<ProductCategory>{

}
