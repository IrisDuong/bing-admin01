package com.bh.admin.warehouse.service;

import java.util.List;
import java.util.Set;

import com.bh.admin.warehouse.dto.ProductCategoryDTO;
import com.bh.admin.warehouse.entity.ProductCategory;

public interface ProductCategoryService {
	void saveProductCategory(ProductCategoryDTO param);
	ProductCategoryDTO getProductCategoryById(String id);
	List<ProductCategoryDTO> searchProductCategories(ProductCategoryDTO param);

	public default ProductCategory mapToModel(ProductCategoryDTO param) {
		ProductCategory model = ProductCategory.builder()
				.productCategoryName(param.getProductCategoryName())
				.status(param.getStatus())
				.treeLevel(param.getTreeLevel())
				.orderSeq(param.getOrderSeq())
				.parentCategoryId(param.getParentCategoryId())
				.isSubCategory(param.isSubCategory())
				.build();
		return model;
	}

	public default ProductCategoryDTO mapToDTO(ProductCategory param) {
		ProductCategoryDTO dto = ProductCategoryDTO.builder()
				.productCategoryCode(param.getProductCategoryCode())
				.productCategoryName(param.getProductCategoryName())
				.status(param.getStatus())
				.treeLevel(param.getTreeLevel())
				.orderSeq(param.getOrderSeq())
				.parentCategoryId(param.getParentCategoryId())
				.isSubCategory(param.isSubCategory())
				.build();
		return dto;
	}
}
