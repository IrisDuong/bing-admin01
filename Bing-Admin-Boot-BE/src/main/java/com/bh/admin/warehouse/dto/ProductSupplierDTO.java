package com.bh.admin.warehouse.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSupplierDTO  implements Serializable {

	private int rowNo;
	private String supplierCode;
	private String supplierName;
	private String phoneNo;
	private String address;
	private String email;
	private String fax;
	private String websiteAddr;
	private String statusName;
	private String statusCode;
	public ProductSupplierDTO(String supplierCode) {
		super();
		this.supplierCode = supplierCode;
	}
	
}
