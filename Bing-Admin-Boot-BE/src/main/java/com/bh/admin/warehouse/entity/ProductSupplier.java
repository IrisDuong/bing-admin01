package com.bh.admin.warehouse.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bh.admin.util.AppConstants;
import com.bh.admin.util.BaseAuditing;
import com.bh.admin.warehouse.entity.Product;

@Entity
@Table(name = "WH_PRODUCT_SUPPLIER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ProductSupplier extends BaseAuditing<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "supplier_code", length = 14)
	@GeneratedValue(generator = "pk_generator")
	@GenericGenerator(name = "pk_generator", parameters = @Parameter(name = "prefix", value = "PS-"), strategy = AppConstants.PK_GENERATOR_LOCATION)
	private String supplierCode;
	
	@Column(name = "supplier_Name")
	private String supplierName;

	@Column(name = "phone_no")
	private String phoneNo;

	private String address;

	private String email;
	
	private String fax;

	@Column(name = "website_addr")
	private String websiteAddr;
	
	private String statusCode;

//	@Transient
//	private String prefix;
	
	public ProductSupplier(String supplierCode) {
		super();
		this.supplierCode = supplierCode;
	}

	public ProductSupplier(
			String supplierCode, String supplierName, String phoneNo, String address, String email, String fax,
			String websiteAddr, String statusCode,String createdBy, LocalDateTime createdAt, String updatedBy, LocalDateTime updatedAt) {
		super(createdBy, createdAt, updatedBy, updatedAt);
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.phoneNo = phoneNo;
		this.address = address;
		this.email = email;
		this.fax = fax;
		this.websiteAddr = websiteAddr;
		this.statusCode = statusCode;
	}
	
	
}
