package com.bh.admin.warehouse.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParamProductDTO {
	private String productCode;
	private String productName;
	private String manufacturer;
	private BigDecimal importPriceFrom;
	private BigDecimal importPriceTo;
	private long importQtyFrom;
	private long importQtyTo;
	private Long productSttCode;
	private Long productImportSttCode;
	private String importDateFrom;
	private String importDateTo;
	private String supplierCode;
	private String productCategoryCode;
	private String thumbnailPath;
}
