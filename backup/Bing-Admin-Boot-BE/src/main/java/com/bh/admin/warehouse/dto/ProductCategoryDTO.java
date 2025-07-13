package com.bh.admin.warehouse.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.bh.admin.warehouse.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rowNo;
	private String productCategoryCode;
	private String productCategoryName;
	private String status;
	private int treeLevel;
	private int orderSeq;
	private Integer parentCategoryId;
	private boolean isSubCategory;
	public ProductCategoryDTO(int rowNo, String productCategoryName, String status,
			int treeLevel, int orderSeq, Integer parentCategoryId, boolean isSubCategory) {
		super();
		this.rowNo = rowNo;
		this.productCategoryName = productCategoryName;
		this.status = status;
		this.treeLevel = treeLevel;
		this.orderSeq = orderSeq;
		this.parentCategoryId = parentCategoryId;
		this.isSubCategory = isSubCategory;
	}
	
	
}
