package com.bh.admin.warehouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.bh.admin.warehouse.entity.ProductSupplier;

public interface ProductSupplierDao extends JpaRepository<ProductSupplier, String>, JpaSpecificationExecutor<ProductSupplier> {

}
