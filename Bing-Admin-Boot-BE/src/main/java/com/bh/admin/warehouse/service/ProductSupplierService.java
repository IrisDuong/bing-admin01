package com.bh.admin.warehouse.service;

import java.util.List;
import java.util.Optional;

import com.bh.admin.warehouse.dto.ProductCategoryDTO;
import com.bh.admin.warehouse.dto.ProductSupplierDTO;
import com.bh.admin.warehouse.entity.ProductCategory;
import com.bh.admin.warehouse.entity.ProductSupplier;

public interface ProductSupplierService {

	List<ProductSupplierDTO> getAllProductSuppliers();
	List<ProductSupplierDTO> searchProductSuppliers(ProductSupplierDTO param);
	void saveProductSupplier(ProductSupplierDTO param);
	Optional<ProductSupplierDTO> getSupplierByCode(String supplierCode);

	public default ProductSupplier mapToModel(ProductSupplierDTO param) {
		ProductSupplier model = ProductSupplier.builder()
				.supplierCode(param.getSupplierCode())
				.supplierName(param.getSupplierName())
				.phoneNo(param.getPhoneNo())
				.address(param.getAddress())
				.email(param.getEmail())
				.fax(param.getFax())
				.websiteAddr(param.getWebsiteAddr())
				.statusCode(param.getStatusCode())
				.build();
		return model;
	}

	public default ProductSupplierDTO mapToDTO(ProductSupplier param) {
		ProductSupplierDTO dto = ProductSupplierDTO.builder()
				.supplierCode(param.getSupplierCode())
				.supplierName(param.getSupplierName())
				.phoneNo(param.getPhoneNo())
				.address(param.getAddress())
				.email(param.getEmail())
				.fax(param.getFax())
				.websiteAddr(param.getWebsiteAddr())
				.statusCode(param.getStatusCode())
				.build();
		return dto;
	}
}
