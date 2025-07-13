package com.bh.admin.warehouse.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.bh.admin.warehouse.entity.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	private int rowNo;
	private String productCode;
	private String productName;
	private String manufacturer;
	private BigDecimal importPrice;
	private long importQty;
	private Long productSttCode;
	private Long productImportSttCode;
	private String importDate;
	private String supplierCode;
	private String productCategoryCode;
	private String thumbnailPath;

}
